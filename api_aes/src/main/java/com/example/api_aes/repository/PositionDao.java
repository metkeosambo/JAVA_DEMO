package com.example.api_aes.repository;

import java.util.List;

import com.example.api_aes.model.Position;


public interface PositionDao {

	public void createPosition( Position user);
	
	public List<Position> getAllPosition();
}
