package com.st.search_job_bot.repo;

import com.st.search_job_bot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findById(long id);
}
