package com.barcode.messageprocesor;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class EasyController {

	@GetMapping("/")
	public String index() {
		return "Hello!";
	}
	@GetMapping("/get/{messageText}")
	public String processString(@PathVariable String messageText) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responce = null;
		responce = ResponseEntity.ok((String)null);
		try {
			
			System.out.println("responce");
			responce = restTemplate.getForEntity(new URI("https://" + messageText), String.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		}
		return messageText + " and recieved = " + (responce != null ? responce.getBody() : "null");
	}

}
