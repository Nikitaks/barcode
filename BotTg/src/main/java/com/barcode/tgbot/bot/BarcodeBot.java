package com.barcode.tgbot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.barcode.tgbot.kafka.KafkaProduccer;

@EnableKafka
@Component
public class BarcodeBot extends TelegramLongPollingBot {

	@Autowired
	private KafkaProduccer kafkaProduccer;
	
	@Autowired
	private ApplicationContext context;
	
	@Value(value = "${com.barcode.tgbot.bot.username}")
    private String botUsername;
	
	@Value(value = "${com.barcode.tgbot.bot.token}")
    private String botToken;
	
	private String chatId; 
	
	@Override
	public void onUpdateReceived(Update update) {
	    // We check if the update has a message and the message has text
	    if (update.hasMessage() && update.getMessage().hasText()) {
	        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
	        chatId = update.getMessage().getChatId().toString();
	        message.setChatId(chatId);
	        
	        String messageText = update.getMessage().getText();
	        String returnedMessageString = processString(messageText);
	        message.setText(returnedMessageString);
	        
	        try {
	            execute(message); // Call method to send the message
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }
	}

    private String processString(String messageText) {
		

    	if ("echo".equals(messageText) || "joke".equals(messageText) || 
    			messageText.startsWith("bar")) {
    		kafkaProduccer.sendMessage(messageText);
    		return "sended";
    	}
    	if ("shut".equals(messageText)) {
            ((ConfigurableApplicationContext) context).close();
            System.exit(0);
            return "shuting down!";
    	}
    	return "echo or joke or shut or bar<barcode>";
	}

	@Override
    public String getBotUsername() {
    	return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
    
    @KafkaListener(topics = {"messageServiceToTgBot"})
	public void kafkaIncomingMessage(String kafkaIncomingMessage) {
    	
    	if (chatId == null)
    		return;
		SendMessage message = new SendMessage();
		message.setChatId(chatId);
		message.setText(kafkaIncomingMessage);

		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}

	}
}
