package com.st.search_job_bot.service.handler;

import com.st.search_job_bot.cache.Cache;
import com.st.search_job_bot.domain.BotState;
import com.st.search_job_bot.domain.User;
import com.st.search_job_bot.repo.UserRepo;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageHandler {

    private Map<BotState, InputMessage>  handllers =  new HashMap<>();
    private UserRepo userEventHandler;
    private final Cache cache;

    public MessageHandler(List<InputMessage> handllers, Cache cache) {
        this.cache = cache;
        handllers.forEach(handler -> this.handllers.put(handler.getHandlerName(), handler));
    }

    public SendMessage proccesInputMessage(BotState state, Message message){

        InputMessage inputMessage = getInputMessage(state);

        User user = (User) userEventHandler.findById(message.getChatId()).orElse(null);

        if (user == null){
            user = new User();
            user.setId(message.getChatId());
        }

        switch (state){
            case INPUT_NAME:
                user.setUsername(message.getText());
                break;
            case LOCATION_FOR_SEARCH:
                user.setLooking_job(message.getText());
                break;
            case SELECT_EXPERIENCE:
                user.setExperience(Integer.parseInt(message.getText()));
            case INPUT_VACATION:
                user.setLooking_job(message.getText());
        }


        return inputMessage.handle(message);
    }

    public InputMessage getInputMessage(BotState state){
        if (isActiveState(state)){
            return handllers.get(BotState.STARTING);
        }
         return handllers.get(state);
    }

    private boolean isActiveState(BotState state) {
        switch (state) {
            case GET_JOB:
            case CREATE:
            case SEND_EMAIL:
            case RESET_DATA:
            case STARTING:
            case INPUT_VACATION:
            case SELECT_EXPERIENCE:
            case FILLING_PROFILE:
                return true;
            default:
                return false;
        }
    }
}
