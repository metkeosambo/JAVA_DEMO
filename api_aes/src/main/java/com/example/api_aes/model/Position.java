package com.example.api_aes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Position {

	private Integer Id;
	
	@JsonProperty("positionid")
	private Integer positionId;
	@JsonProperty("positionName")
	private String positionName;
	private String description;
	
	public Position() {
		super();
	}
	public Position(Integer Id,Integer positionId, String positionName,String  description) {
		super();
		this.Id = Id;
		this.positionId = positionId;
		this.positionName = positionName;
		this.description = description;
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}
	
	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
