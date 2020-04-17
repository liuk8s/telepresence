package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
	@Value("${app.greeting.name:front_end_Java_code}")
	private String name;
	@Value("${app.server.url}")
	private String serverUrl;
	
	@RequestMapping("/")
	public String index() {
		return "Greetings from " + name + "!";
	}

	@RequestMapping("/backend")
	public String backend() {
		try {
			URL url = new URL(serverUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int status = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			return "Backend response code is "+ status + ". Content: " + content.toString();
		} catch (Exception e) {
			return "Error " + e.getMessage();
		}
	}

}
