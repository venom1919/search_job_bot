package com.st.search_job_bot.cache;


import com.st.search_job_bot.domain.BotState;
import com.st.search_job_bot.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDetailsCacheImpl implements Cache {

    private Map<Long, BotState> stateMap = new HashMap<>();
    private Map<Long, User> profileMap = new HashMap<>();

    @Override
    public void saveBotState(long id, BotState state) {
        stateMap.put(id, state);
    }

    @Override
    public BotState getState(long id) {

//        stateMap.remove(id);
        BotState cs = stateMap.get(id);
        if (cs==null){
            return cs = BotState.STARTING;
        }
        return cs;
    }

    @Override
    public User getProfileUser(long id) {
        return profileMap.get(id);
    }

    @Override
    public void saveUserProfile(long id, User profile) {
        profileMap.put(id, profile);
    }
}


