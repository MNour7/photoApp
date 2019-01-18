package com.example.nour.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClassDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int classId;
	private String name;
}
