package com.barcode.messageprocesor.logic;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.barcode.messageprocesor.dto.Food;
import com.barcode.messageprocesor.dto.Jokes;
import com.barcode.messageprocesor.kafka.KafkaProduccer;

@EnableKafka
@Component
public class EchoStub {

	@Autowired
	private KafkaProduccer kafkaProduccer;

	private String getBarcodeData(String barcode) {
		
	 RestTemplate restTemplate = new RestTemplate();
	 HttpHeaders headers = new HttpHeaders();
        //headers.add("user-agent", "");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        
		ResponseEntity<Food> responce = null;
		try {			
			responce = restTemplate.exchange("https://world.openfoodfacts.org/api/v2/product/" + barcode + ".json", 
					HttpMethod.GET,entity, Food.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			return "Error";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
		String returnedText = responce != null ? responce.getBody().toString() : "null";
		
		return returnedText;
	}
	
	private String getJoke() {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        
		ResponseEntity<Jokes> responce = null;
		try {			
			responce = restTemplate.exchange("https://api.chucknorris.io/jokes/random", HttpMethod.GET,entity,Jokes.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			return "Error";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
		String returnedText = responce != null ? responce.getBody().getValue() : "null";
		return returnedText;
	}

    @KafkaListener(topics = {"tgBotToMessageService"})
	public void kafkaIncomingMessage(String messageFromTgBot) {
    	
    	if ("joke".equals(messageFromTgBot)) {
    		kafkaProduccer.sendMessage("joke:" + getJoke());
    	}
    	if (messageFromTgBot.startsWith("bar")) {
    		kafkaProduccer.sendMessage("BarcodeData: " + getBarcodeData(messageFromTgBot.substring(3)));
    	}
    	if ("echo".equals(messageFromTgBot)) {
    		kafkaProduccer.sendMessage("echo at:" + 
    			LocalDateTime.now().toString() + " " + messageFromTgBot);
    	}
	}
}
