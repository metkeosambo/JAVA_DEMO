package com.example.api_aes.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.api_aes.mapper.PositionMapper;
import com.example.api_aes.model.Position;

@Repository
public class PositionDaoImpl implements PositionDao {

	@Autowired
	PositionMapper posmapper;
	@Override
	public void createPosition(Position pos) {
		posmapper.createPosition(pos);
	}

	@Override
	public List<Position> getAllPosition() {
		// TODO Auto-generated method stub
		return posmapper.getAllPosition();
	}

}
