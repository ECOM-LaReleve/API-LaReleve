package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 2254274640238186573L;

	@Id
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		// JSON format
		return String.format("{'id': %d}", this.id);
	}
}
