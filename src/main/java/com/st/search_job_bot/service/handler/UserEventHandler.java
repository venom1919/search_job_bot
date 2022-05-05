package com.st.search_job_bot.service.handler;

import com.st.search_job_bot.cache.Cache;
import com.st.search_job_bot.domain.User;
import com.st.search_job_bot.repo.UserRepo;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@Data
public class UserEventHandler {

    final UserRepo userRepo;
    final Cache cache;

    public User saveUser(String name, Message message){

        User user = new User();
        user.setUsername(name);
        user.setId(message.getFrom().getId());
        userRepo.save(user);
        return user;
    }

    public User findUserById(long id){
        return userRepo.findById(id);
    }

}
