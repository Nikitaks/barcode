package com.barcode.tgbot;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.barcode.tgbot.bot.BarcodeBot;

@SpringBootApplication
public class TgBotApplication {

	@Autowired
	private BarcodeBot barcodeBot;
	
	public static void main(String[] args) {
		SpringApplication.run(TgBotApplication.class, args);
	    
	}
	
	@PostConstruct
	public void registerTgBot() {
		try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(barcodeBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}
	
}
