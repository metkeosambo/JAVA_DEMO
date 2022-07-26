package com.example.api_aes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.api_aes.model.Position;
import com.example.api_aes.repository.PositionDao;

@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	PositionDao posdao;
	@Override
	public void createUser(Position pos) {
		
		posdao.createPosition(pos);
	}

	@Override
	public List<Position> getAllPosition() {
		// TODO Auto-generated method stub
		return posdao.getAllPosition();
	}

}
