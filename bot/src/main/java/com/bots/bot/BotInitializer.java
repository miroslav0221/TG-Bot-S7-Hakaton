package com.bots.bot;

import com.bots.bot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotInitializer {
    private final TelegramBot bot;

    public BotInitializer(TelegramBot bot_) {
        bot = bot_;
    }

    @Bean
    public TelegramBotsApi init() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
            return botsApi;
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
