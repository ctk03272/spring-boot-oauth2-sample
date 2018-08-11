package com.example;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
public class LoginController {
	@Autowired
	private MemberRepository repository;

	@RequestMapping(value = "/Login.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> Login(@RequestBody Member member) throws URISyntaxException {
		ResponseEntity<String> rp = null;
		Member result = repository.findOne(member.getId());
		HttpResponse<String> response = null;
		if (result == null || !result.password.equals(member.getPassword())) {
			rp = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} else {
			try {
				response = Unirest.post("http://my_client_id:my_client_secret@localhost:8080/oauth/token")
						.header("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
						.header("cache-control", "no-cache")
						.header("postman-token", "88a585f9-42b7-c424-9781-daf0e4a788e8")
						.body("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"grant_type\"\r\n\r\npassword\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"scope\"\r\n\r\nread\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"username\"\r\n\r\nuser\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"password\"\r\n\r\ntest\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"client-id\"\r\n\r\nmy_client_id     \r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--")
						.asString();
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rp = new ResponseEntity<String>(response.getBody(), HttpStatus.ACCEPTED);
		}
		return rp;
	}
}
