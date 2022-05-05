package com.st.search_job_bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class OptionalMessageService {

    private final MessageService messageService;

    @Autowired
    public OptionalMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public SendMessage getOptionalMessage(Long id, String optionalMessage){
        return new SendMessage(id.toString(), messageService.getMessage(optionalMessage));
    }

    public SendMessage getOptionalMessage(Long id, String optionalMessage, Object...obj){
        return new SendMessage(id.toString(), messageService.getMessage(optionalMessage, obj));
    }

}
