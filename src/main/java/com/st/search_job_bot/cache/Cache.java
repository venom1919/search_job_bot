package com.st.search_job_bot.cache;

import com.st.search_job_bot.domain.BotState;
import com.st.search_job_bot.domain.User;

public interface Cache {

    void saveBotState(long id, BotState state);
    BotState getState(long id);
    User getProfileUser(long id);
    void saveUserProfile(long id, User profile);
 }
