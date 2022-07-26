package com.example.web_client.models;

import java.util.List;


public class PositionList {

	private List<Position> positionList;
	
	public PositionList() {
		super();
	}
	public PositionList(List<Position> positionList) {
		super();
		this.positionList = positionList;
	}
	public List<Position> getPositionList(){
	    return this.positionList;
	}

	public void setPositionList(List<Position> positionList){
	    this.positionList = positionList;
	}
}
