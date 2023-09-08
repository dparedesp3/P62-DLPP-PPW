package io.github.cepr0.crud.dto;

import org.springframework.lang.NonNull;

import java.io.Serializable;

public interface CrudResponse<ID extends Serializable> extends Serializable {

	@NonNull ID getId();
}