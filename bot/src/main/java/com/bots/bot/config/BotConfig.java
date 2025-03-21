package com.bots.bot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("classpath:application.properties") // Подключаем application.properties
public class BotConfig {
    @Value("${bot.name}") // Получаем имя бота из application.properties
    private String botName;

    @Value("${bot.token}") // Получаем токен бота из application.properties
    private String token;
}
