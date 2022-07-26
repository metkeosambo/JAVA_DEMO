package com.example.api_aes.service;

import java.util.List;

import com.example.api_aes.model.Position;


public interface PositionService {

	public void createUser( Position pos);
	
	
	public List<Position> getAllPosition();
}
