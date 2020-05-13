package com.sergiu.util;

public enum TypeFile {
	SUPERVISORS("SUPERVISORS"), CANDIDATES("CANDIDATES"), HALLS("HALLS");

	TypeFile(String mesage) {
		this.mesage = mesage;
	}

	private final String mesage;

	public String getMesage() {
		return mesage;
	}
}