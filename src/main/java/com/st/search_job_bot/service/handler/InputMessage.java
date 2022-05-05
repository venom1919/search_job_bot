package com.st.search_job_bot.service.handler;

import com.st.search_job_bot.domain.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface InputMessage {

    SendMessage handle(Message message);
    BotState getHandlerName();

}
