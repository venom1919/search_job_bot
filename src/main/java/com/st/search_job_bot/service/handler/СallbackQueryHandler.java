package com.st.search_job_bot.service.handler;

import com.st.search_job_bot.cache.Cache;
import com.st.search_job_bot.domain.BotState;
import com.st.search_job_bot.service.menu.Menu;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
@Getter
@Setter
public class СallbackQueryHandler {

    BotState state;
    Cache cache;
    Menu menu;

    @Autowired
    public СallbackQueryHandler(Cache cache, Menu menu) {
        this.cache = cache;
        this.menu = menu;
    }

    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {

        final long idChat = buttonQuery.getMessage().getChatId();
        final long userId = buttonQuery.getFrom().getId();

        if (buttonQuery.getMessage().equals("student")){
            cache.saveBotState(idChat, BotState.SELECT_EXPERIENCE);
        }

        return  new SendMessage(String.valueOf(idChat), "Введите номер напоминания из списка.");
    }

}
