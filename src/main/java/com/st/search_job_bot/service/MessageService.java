package com.st.search_job_bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {

    private final Locale locale;
    private final MessageSource source;

    @Autowired
    public MessageService(@Value("${localeTag}") String optional_messages , MessageSource source) {
        this.locale = Locale.forLanguageTag(optional_messages);
        this.source = source;
    }

    public String getMessage(String msg){
        return source.getMessage(msg, null, locale);
    }

    public String getMessage(String msg, Object...obj){
        return source.getMessage(msg, obj, locale);
    }
}
