package com.bots.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    String message_start = "✨ Привет! Я — бот-помощник твоим удобным перелетам ✈\uFE0F  \n" +
            "\uD83E\uDD16 Я покажу загруженность стоек регистрации в твоем аэропорту \uD83E\uDD16  \n" +
            "\n" +
            "\uD83D\uDDFA Из какого ты города? \uD83C\uDF0D\n";
    private final String botUsername = "S7_Helper_bot"; // Замени на свой username
    private final String botToken = "7581177756:AAEi6wClmr8O9oHHRUutllGyQZEb60qUWjw"; // Вставь токен от BotFather

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    // Тестовый текст
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()
                && update.getMessage().getText().equals("/help")) {
            long chatId = update.getMessage().getChatId();
            sendMessage(chatId, message_start);
        }
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
