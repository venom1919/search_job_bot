package com.st.search_job_bot.service;

import com.st.search_job_bot.cache.UserDetailsCacheImpl;
import com.st.search_job_bot.domain.BotState;
import com.st.search_job_bot.service.handler.MessageHandler;
import com.st.search_job_bot.service.handler.СallbackQueryHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TelegramFacade {

    final MessageHandler messageHandler;
    final СallbackQueryHandler handlerQuery;
    final UserDetailsCacheImpl cache;

    public TelegramFacade(com.st.search_job_bot.service.handler.MessageHandler messageHandler, СallbackQueryHandler handler, UserDetailsCacheImpl cache) {
        this.messageHandler = messageHandler;
        this.handlerQuery = handler;
        this.cache = cache;
    }

    public BotApiMethod<?> handleUpdate(Update update) {

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return null;
        } else {

            Message message = update.getMessage();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(message.getChatId()));
            if (message.hasText()) {
//                sendMessage.setText("Hello world : " + update.getMessage().getText());

                return handleInputMessage(message);

            }
        }
        return null;
    }

//    public BotApiMethod<?> getCallbackQuery(CallbackQuery callbackQuery){
//
////        long idChat = callbackQuery.getMessage().getChatId();
////        long userId = callbackQuery.getFrom().getId();
//
//        if (callbackQuery.getMessage().equals("student")){
//        }
//        return handlerQuery.processCallbackQuery(callbackQuery);
//    }
    public SendMessage handleInputMessage(Message message) {

        BotState state;
        SendMessage sendMessage ;

        System.out.println(message.getText());

        switch (message.getText()){

            case "/start":
                state = BotState.STARTING;
                break;
            case "Вкажіть професію":
                state = BotState.INPUT_VACATION;
                break;
            case "Виберіть ваш рівень досвіду":
                state = BotState.SELECT_EXPERIENCE;
            case "Вкажіть місце пошуку":
                state = BotState.LOCATION_FOR_SEARCH;
            case "Получить возможные варианты":
                state = BotState.GET_JOB;
//            case "java":
//                state = BotState.SELECT_EXPERIENCE;
            default:
                state = cache.getState(message.getChatId());
        }

        cache.saveBotState(message.getChatId(), state);
        sendMessage = messageHandler.proccesInputMessage(state, message);
        return sendMessage;
    }

}
