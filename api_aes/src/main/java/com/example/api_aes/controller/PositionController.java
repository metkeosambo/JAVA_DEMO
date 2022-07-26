package com.example.api_aes.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_aes.model.Position;
import com.example.api_aes.repository.PositionDao;
import com.example.api_aes.service.PositionService;
import com.example.api_aes.utils.Encrypt;
import com.fasterxml.jackson.databind.ObjectMapper;




@RestController
@RequestMapping("/position")
public class PositionController {

	@Autowired
	PositionDao posdao;
	
	@Autowired
	PositionService posservice;
	
	
	@RequestMapping(value="/create",headers = {
    "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public String createPostion(@RequestBody String para){//Position pos
		try {
			JSONObject json = new JSONObject(para);
			String decrypt = Encrypt.decrypt(json.getString("data"));
			ObjectMapper Obj = new ObjectMapper();
			Position pos = Obj.readValue(decrypt, Position.class);
			posservice.createUser(pos);
			return "success";
		}catch(Exception ex) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		
	}	
	@RequestMapping(value="/all",method = RequestMethod.POST)
	public List<Position> getAllPostion(){
		
		return posservice.getAllPosition();
	}
}
