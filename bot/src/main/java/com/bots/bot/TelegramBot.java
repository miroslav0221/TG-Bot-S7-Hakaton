package com.bots.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final String message_start = "✨ Привет! Я — бот-помощник твоим удобным перелетам ✈\uFE0F  \n" +
            "\uD83E\uDD16 Я покажу загруженность стоек регистрации в твоем аэропорту \uD83E\uDD16  \n" +
            "\n" +
            "\uD83D\uDDFA Из какого ты города? \uD83C\uDF0D\n";

    private final List<String> cities = Arrays.asList("Новосибирск", "Москва", "Санкт-Петербург", "Казань");

    private final String botUsername = "S7_Helper_bot";
    private final String botToken = "7581177756:AAEi6wClmr8O9oHHRUutllGyQZEb60qUWjw";

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String receivedText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (receivedText.equals("/help")) {
                sendMessageWithButtons(chatId, message_start, cities);
            } else if (cities.contains(receivedText)) {
                sendMessage(chatId, "Вы выбрали город: " + receivedText + " ✅");
            }
        }
    }

    private void sendMessageWithButtons(Long chatId, String text, List<String> options) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);

        // Создание клавиатуры
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true); // Авто-размер под экран пользователя
        keyboardMarkup.setOneTimeKeyboard(true); // Скрыть после выбора

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        // Итерация по списку городов и создание кнопок
        for (String option : options) {
            KeyboardRow row = new KeyboardRow();
            row.add(new KeyboardButton(option));
            keyboardRows.add(row);
        }

        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        }

        catch (TelegramApiException e) {
            e.printStackTrace();
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
