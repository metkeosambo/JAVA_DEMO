package com.example.web_client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.web_client.models.Position;
import com.example.web_client.models.PositionList;
import com.example.web_client.models.User;
import com.example.web_client.utils.Encrypt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PositionController {

	@RequestMapping("/position")
    public String Index(Model model,HttpSession session) throws JSONException, JsonMappingException, JsonProcessingException {
		
		String ses = session.getAttribute("TOKEN")==null?"":session.getAttribute("TOKEN").toString();
		if(ses==""||ses==null) {
			
			return "redirect:/";
			
		}else {
			RestTemplate restTemplate = new RestTemplate();
			
			ObjectMapper Obj = new ObjectMapper();
//			String jsonStr = Obj.writeValueAsString(user);
			//String encrypy =Encrypt.encrypt(jsonStr);
			//HttpEntity<String> entity = new HttpEntity<String>(jsonStr, headers);
			ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9999/position/all", ses,String.class);
			
			if (response.getStatusCode() == HttpStatus.OK) {
//				  Object[] objects = response.getBody();
				  //Object[] pos = response.getBody();
				   JsonNode pos = Obj.readTree(response.getBody());
				   
				   ArrayList<Position> detail = new ArrayList<>();
				   
				   pos.forEach((temp) -> {
					   
					   detail.add(new Position(null, temp.get("positionid").asInt(), temp.get("positionName").toString().replace('"', ' '), temp.get("description").toString().toString().replace('"', ' ')));
					
				   });
				   model.addAttribute("position",detail);
				   //System.out.println(detail);
				  //JSONObject userJson = new JSONObject(response.getBody());
//				  String token = (String) userJson.get("token");
//				  session.setAttribute("TOKEN", token);
				  
				} else {
					return "redirect:/";
			}
			return "position/index";
		}
    }
	@PostMapping("/position/save")
    public String Login(Position pos, HttpServletRequest request1,HttpSession session) {
		
//		User user = new User();
//		user.setUserName(name);
		String ses = session.getAttribute("TOKEN")==null?"":session.getAttribute("TOKEN").toString();
		
		if(ses==""||ses==null) {
			
			return "redirect:/";
			
		}else {
			try {
//				ObjectMapper Obj = new ObjectMapper();
//				String jsonStr = Obj.writeValueAsString(user);
//				String encrypy =Encrypt.encrypt(jsonStr);
//				webClient.post()
//				.uri("/authenticate")
//				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//				.body(Mono.just(user), User.class)
//				.retrieve()
//				.bodyToMono(String.class);
				RestTemplate restTemplate = new RestTemplate();
				ObjectMapper Obj = new ObjectMapper();
				String jsonStr = Obj.writeValueAsString(pos);
//				String encrypy =Encrypt.encrypt(jsonStr);
//				Map<String,String> hashmap = new HashMap<>();
//				hashmap.put("data", encrypy);
//				JSONObject json = new JSONObject(hashmap);
				//String jsonStr1 = Obj.writeValueAsString(json);
				String token = "Bearer "+session.getAttribute("TOKEN").toString();
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.set("Authorization", token);
				//headers.add("Authorization", "Bearer "+session.getAttribute("TOKEN")==null?"":session.getAttribute("TOKEN").toString());

				HttpEntity<String> entity = new HttpEntity<String>(jsonStr, headers);//json.toString()
				ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9999/position/create", entity,String.class);
				if (response.getStatusCode() == HttpStatus.OK) {
//					  JSONObject userJson = new JSONObject(response.getBody());
//					  String token = (String) userJson.get("token");
//					  session.setAttribute("TOKEN", token);
					  System.out.println(response.getBody());
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
