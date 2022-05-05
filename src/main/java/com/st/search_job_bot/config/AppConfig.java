package com.st.search_job_bot.config;

import com.st.search_job_bot.service.TelegramFacade;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
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
    public JobTGBot springWebhookBot(SetWebhook setWebhook, TelegramFacade telegramFacade) {

        JobTGBot bot = new JobTGBot(setWebhook, telegramFacade);

        bot.setWebHookPath(telegramConfig.getWebHookPath());
        bot.setBotUserName(telegramConfig.getBotUserName());
        bot.setBotToken(telegramConfig.getBotToken());

        return bot;

    }

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return  messageSource;
    }

}
