package com.st.search_job_bot.controller;

import com.st.search_job_bot.config.JobTGBot;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@AllArgsConstructor
public class WHController {

    private final JobTGBot writeReadBot;

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return writeReadBot.onWebhookUpdateReceived(update);
    }

//    @GetMapping
//    public ResponseEntity get() {
//        return ResponseEntity.ok().build();
//    }

}
