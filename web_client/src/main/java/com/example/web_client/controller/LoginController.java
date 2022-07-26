package com.example.web_client.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.web_client.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class LoginController {

	WebClient webClient = WebClient.create("http://localhost:9999");
	
	@RequestMapping("/")
    public String welcome(HttpSession session) {
		session.setAttribute("TOKEN", null);
        return "login/Login";
    }
	@PostMapping("/login")
    public String Login(User user, HttpServletRequest request1,HttpSession session) {
		
		try {
			
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper Obj = new ObjectMapper();
			String jsonStr = Obj.writeValueAsString(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(jsonStr, headers);
			
			ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9999/authenticate", entity,String.class);
			
			if (response.getStatusCode() == HttpStatus.OK) {
				
				  JSONObject userJson = new JSONObject(response.getBody());
				  
				  String token = (String) userJson.get("token");
				  
				  session.setAttribute("TOKEN", token);
				  
				} else {
					return "redirect:/";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/position";
    }
}
