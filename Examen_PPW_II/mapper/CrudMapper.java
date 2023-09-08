package io.github.cepr0.crud.mapper;
import io.github.cepr0.crud.dto.CrudRequest;
import io.github.cepr0.crud.dto.CrudResponse;
import io.github.cepr0.crud.model.IdentifiableEntity;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.lang.NonNull;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@MapperConfig(
		nullValueMappingStrategy = RETURN_DEFAULT,
		nullValueCheckStrategy = ALWAYS,
		nullValuePropertyMappingStrategy = IGNORE,
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		componentModel = "spring"
)
public interface CrudMapper<T extends IdentifiableEntity, Q extends CrudRequest, S extends CrudResponse> {

	@NonNull T toCreate(@NonNull Q request);

	@NonNull T toUpdate(@NonNull Q request, @MappingTarget @NonNull T target);

	@NonNull S toResponse(@NonNull T entity);
}