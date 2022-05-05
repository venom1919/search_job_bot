package com.st.search_job_bot.service.handler;


import com.st.search_job_bot.service.MessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestMessageHandler {

    private final MessageService servicel;

    public RequestMessageHandler(MessageService servicel) {
        this.servicel = servicel;
    }
    public SendMessage getTest(Long id, String message){
        return new SendMessage(id.toString(), servicel.getMessage(message));
    }

    public SendMessage getMenu(Long id, String message){
        InlineKeyboardButton buttonDel = new InlineKeyboardButton();
        buttonDel.setText("Удалить");
        SendMessage sendMessage = new SendMessage();

        sendMessage.setText(servicel.getMessage(message));
        sendMessage.setChatId(String.valueOf(id));
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> listKeyboard = new ArrayList<>();
//        listKeyboard.add(
//                Collections.singletonList(InlineKeyboardButton.builder()
//                        .text("рік")
//                        .callbackData("student")
//                        .build()));


        listKeyboard.add(buttonDel);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(listKeyboard);
        markup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }
}
