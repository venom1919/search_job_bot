package com.st.search_job_bot.config;

import com.st.search_job_bot.SearchJobsTGBot;
import com.st.search_job_bot.TelegramFacade;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@AllArgsConstructor
public class AppConfig {

    private final TelegramConfig telegramConfig;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(telegramConfig.getWebHookPath()).build();
    }

    @Bean
    public SearchJobsTGBot springWebhookBot(SetWebhook setWebhook, TelegramFacade telegramFacade) {

        SearchJobsTGBot bot = new SearchJobsTGBot(setWebhook, telegramFacade);

        bot.setWebHookPath(telegramConfig.getWebHookPath());
        bot.setBotUserName(telegramConfig.getBotUserName());
        bot.setBotToken(telegramConfig.getBotToken());

        return bot;

    }
}
