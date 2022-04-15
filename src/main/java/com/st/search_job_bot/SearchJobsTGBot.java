package com.st.search_job_bot;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;

public class SearchJobsTGBot extends SpringWebhookBot {

    private String webHookPath;
    private String botUserName;
    private String botToken;

    private TelegramFacade telegramFacade;

    public SearchJobsTGBot(SetWebhook setWebhook, TelegramFacade telegramFacade, DefaultBotOptions defaultBotOptions) {
        super(defaultBotOptions, setWebhook);
        this.telegramFacade = telegramFacade;
    }

    public SearchJobsTGBot(SetWebhook setWebhook, TelegramFacade telegramFacade) {
        super(setWebhook);
        this.telegramFacade = telegramFacade;
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {

        if (update.getMessage() != null && update.getMessage().hasText()) {
//            System.out.println(update.getMessage());
//            try {
//                execute(new SendMessage(update.getMessage().getChatId().toString(), "Hi " + update.getMessage().getText()));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }

            try {
                execute(telegramFacade.handleUpdate(update));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    public String getBotToken() {
        return botToken;
    }

    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}