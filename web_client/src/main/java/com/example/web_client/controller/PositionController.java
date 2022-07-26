package com.example.web_client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.web_client.models.Position;
import com.example.web_client.models.TokenAccess;
import com.example.web_client.utils.Encrypt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PositionController {

	@RequestMapping("/position")
    public String Index(Model model,HttpSession session) throws JSONException, JsonMappingException, JsonProcessingException {
		
		//check token
		String ses = session.getAttribute("TOKEN")==null?"":session.getAttribute("TOKEN").toString();
		if(ses==""||ses==null) {
			
			return "redirect:/";
			
		}else {
			RestTemplate restTemplate = new RestTemplate();
			
			ObjectMapper Obj = new ObjectMapper();

			ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9999/position/all", null,String.class);
			
			if (response.getStatusCode() == HttpStatus.OK) {

				   JsonNode pos = Obj.readTree(response.getBody());
				   
				   ArrayList<Position> detail = new ArrayList<>();
				   
				   pos.forEach((temp) -> {
					   
					   detail.add(new Position(null, temp.get("positionid").asInt(), temp.get("positionName").toString().replace('"', ' '), temp.get("description").toString().toString().replace('"', ' ')));
					
				   });
				   model.addAttribute("position",detail);

				} else {
					return "redirect:/";
			}
			return "position/index";
		}
    }
	@PostMapping("/position/save")
    public String Login(Position pos, HttpServletRequest request1,HttpSession session) {

		String ses = session.getAttribute("TOKEN")==null?"":session.getAttribute("TOKEN").toString();
		
		if(ses==""||ses==null) {
			
			return "redirect:/";
			
		}else {
			try {
				RestTemplate restTemplate = new RestTemplate();
				
				ObjectMapper Obj = new ObjectMapper();
				
				String jsonStr = Obj.writeValueAsString(pos);
				
				// encryption
				String encrypy =Encrypt.encrypt(jsonStr);
				
				// map string data
				Map<String,String> hashmap = new HashMap<>();
				
				hashmap.put("data", encrypy);
				
				JSONObject json = new JSONObject(hashmap);
				
				//header barear JWT
				String token = "Bearer "+session.getAttribute("TOKEN").toString();
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.set("Authorization", token);
				
				HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);//json.toString()
				ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9999/position/create", entity,String.class);
				
				if (response.getStatusCode() == HttpStatus.OK) {
					
					  return "position/index";
					  
					} else {
						return "redirect:/";
				}
				
			} catch (Exception e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.getMessage();
				
			}
		}
    }
	
}
