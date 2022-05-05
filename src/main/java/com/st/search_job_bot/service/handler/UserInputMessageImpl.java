package com.st.search_job_bot.service.handler;

import com.st.search_job_bot.cache.UserDetailsCacheImpl;
import com.st.search_job_bot.domain.BotState;
import com.st.search_job_bot.service.menu.Menu;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInputMessageImpl implements InputMessage{

    final UserDetailsCacheImpl cache;
    final RequestMessageHandler handler;
    final Menu menu;

    @Autowired
    public UserInputMessageImpl(UserDetailsCacheImpl cache, RequestMessageHandler handler, Menu menu, UserEventHandler userEvent) {
        this.cache = cache;
        this.handler = handler;
        this.menu = menu;
    }

    @Override
    public SendMessage handle(Message message) {
        if (cache.getState(message.getChatId()).equals(BotState.STARTING)){
            cache.saveBotState(message.getChatId(), BotState.INPUT_NAME);
        }
        else if(cache.getState(message.getChatId()).equals(BotState.INPUT_NAME)){
            cache.saveBotState(message.getChatId(), BotState.INPUT_VACATION);
        }
        else if (cache.getState(message.getChatId()).equals(BotState.INPUT_VACATION)){
            cache.saveBotState(message.getChatId(), BotState.SELECT_EXPERIENCE);
        }else if (cache.getState(message.getChatId()).equals(BotState.SELECT_EXPERIENCE)){
           cache.saveBotState(message.getChatId(), BotState.LOCATION_FOR_SEARCH);
        }
        return getMessage(message);
    }

    private SendMessage getMessage(Message message){

        Long chatId = message.getChatId();
        long userId = message.getFrom().getId();
        String mssg = message.getText();

        BotState state =  cache.getState(userId);
        SendMessage sendMessage = null;

        if (state.equals(BotState.STARTING)){
            sendMessage = handler.getTest(chatId, "optional_messages.name");
//            cache.saveBotState(chatId, BotState.SELECT_VACATION);
        }

        if (state.equals(BotState.INPUT_NAME)){
            sendMessage = handler.getTest(chatId, "optional_messages.speciality");
        }

        if (state.equals(BotState.INPUT_VACATION)){
            sendMessage = handler.getTest(chatId, "optional_messages.skill_level");
//            cache.saveBotState(chatId, BotState.SELECT_EXPERIENCE);
        }

        if(state.equals(BotState.SELECT_EXPERIENCE)){
//           return menu.getInlineMenuExperience(chatId);
          sendMessage = menu.getInlineMenuExperience(chatId);

//            sendMessage.setText();
        }

        if(state.equals(BotState.LOCATION_FOR_SEARCH)){
            sendMessage = handler.getTest(chatId, "optional_messages.location");
        }
        if(state.equals(BotState.FILLING_PROFILE)){
            sendMessage = handler.getTest(chatId, mssg);
        }

        return sendMessage;

    }

    @Override
    public BotState getHandlerName() {
        return BotState.STARTING;
    }

}
