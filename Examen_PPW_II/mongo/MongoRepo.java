package io.github.cepr0.crud.repo;

import io.github.cepr0.crud.model.IdentifiableEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

import static java.lang.String.format;

@NoRepositoryBean
public interface MongoRepo<T extends IdentifiableEntity<ID>, ID extends Serializable> extends CrudRepo<T, ID>, MongoRepository<T, ID> {
	@Override
	@NonNull
	default T create(@NonNull final T entity) {
		Objects.requireNonNull(entity, "¡La entidad dada no debe ser nula!");
		return insert(entity);
	}

	@NonNull
	Optional<T> getToUpdateById(@NonNull ID cedula);

	@Override
	@NonNull
	default <S> Optional<T> update(@NonNull final ID cedula, S source, @NonNull final BiFunction<S, T, T> mapper) {
		Objects.requireNonNull(source, "¡La source proporcionada no debe ser nula!");
		Objects.requireNonNull(mapper, "¡El mapper proporcionado no debe ser nula!");
		return getToUpdateById(cedula)
				.map(target -> save(mapper.apply(source, target)));
	}


	@NonNull
	Optional<T> getToDeleteById(@NonNull ID cedula);

	@Override
	@NonNull
	default Optional<T> del(@NonNull ID cedula) {
		return getToDeleteById(id).map(found -> {
			delete(found);
			return found;
		});
	}

	@Override
	@NonNull
	Optional<T> getById(@NonNull ID cedula);

	@NonNull
	default T getOne(@NonNull ID cedula) {
		return getById(id)
				.orElseThrow(() -> new DocNotFoundException(format("Mongo document with id '%s' not found", id)));
	}

	@Query("{id: { $exists: true }}")
	@Override
	@NonNull
	List<T> getAll();

	@Query("{id: { $exists: true }}")
	@Override
	@NonNull
	Page<T> getAll(@NonNull Pageable pageable);

	@Query("{id: { $exists: true }}")
	@Override
	@NonNull
	List<T> getAll(@NonNull Sort sort);
}