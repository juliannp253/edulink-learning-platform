package com.edulink.edulink_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edulink.edulink_app.model.ActivityType;
import com.edulink.edulink_app.model.Challenge;
import com.edulink.edulink_app.model.UserLevel;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByTypeAndLevel(ActivityType type, UserLevel level);
}