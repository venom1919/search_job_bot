package com.st.search_job_bot.service.menu;

import com.st.search_job_bot.service.MessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Menu {
    private final MessageService servicel;

    public Menu(MessageService servicel) {
        this.servicel = servicel;
    }

    public SendMessage getInlineMenuExperience(Long chatId){

//        List<InlineKeyboardButton> buttons = new ArrayList<>();
//        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
//        InlineKeyboardButton exp1 = new InlineKeyboardButton();
//
//        exp1.setText("Від 0 до 1 року");
//        buttons.add(exp1);
//        markup.setKeyboard(Collections.singletonList(buttons));
//
//        return markup;


        SendMessage sendMessage = new SendMessage();
//        sendMessage.setText(servicel.getMessage("optional_messages.skill_level"));
//        sendMessage.setChatId(String.valueOf(chatId));
//        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
//        List<InlineKeyboardButton> listKeyboard = new ArrayList<>();
////        listKeyboard.add(
////                Collections.singletonList(InlineKeyboardButton.builder()
////                        .text("рік")
////                        .callbackData("student")
////                        .build()));
//
//
//        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
//        rowList.add(listKeyboard);
//        markup.setKeyboard(rowList);
//        sendMessage.setReplyMarkup(markup);


//        var editText = new EditMessageText();
//        editText.setChatId(String.valueOf(chatId));
//        editText.setMessageId(Math.toIntExact(chatId));
//        editText.setText("returnNewText");
//        editText.setReplyMarkup(InlineKeyboardMarkup
//                .builder()
//                .keyboardRow(
//                        Collections.singletonList(
//                                InlineKeyboardButton
//                                        .builder()
//                                        .text("Link")
//                                        .url("https://supportyourart.com/cdn/uploads/2022/03/unnamed-6-e1647462359464.jpeg")
//                                        .build()))
//                .build());

//        var reply = new ReplyKeyboardMarkup();
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow keyboardRow1 = new KeyboardRow();
//        KeyboardRow keyboardRow2 = new KeyboardRow();
//        KeyboardRow keyboardRow3 = new KeyboardRow();
//        keyboardRow1.add("0-1");
//        keyboardRow2.add("1-3");
//        keyboardRow3.add("3-5");
//        keyboardRow1.add(KeyboardButton.builder().text("with 1- 3").requestContact(true).build());
//
//        keyboardRows.add(keyboardRow1);
//        reply.setKeyboard(keyboardRows);
//
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText("Вкажіть ваш досвід");

        InlineKeyboardMarkup inline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>keyboard = new ArrayList<>();

        keyboard.add(Collections.singletonList(InlineKeyboardButton.builder().text("0-1").callbackData("student").build()));
        keyboard.add(Collections.singletonList(InlineKeyboardButton.builder().text("1-3").callbackData("junior").build()));
        keyboard.add(Collections.singletonList(InlineKeyboardButton.builder().text("3-5").callbackData("middle").build()));
        keyboard.add(Collections.singletonList(InlineKeyboardButton.builder().text(">5").callbackData("senior").build()));

        inline.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(inline);

        return sendMessage;
    }


}
