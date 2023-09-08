
package io.github.cepr0.demo.model;

import io.github.cepr0.demo.model.base.LongIdEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
@DynamicInsert
@DynamicUpdate
public class Usuario extends LongIdEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Instant createdAt;

	@Column(nullable = false)
	private Instant updatedAt;

	private boolean deleted;
}