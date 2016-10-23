package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 2254274640238186573L;

	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		// JSON format
		return String.format("{id: %d}", this.id);
	}
}
