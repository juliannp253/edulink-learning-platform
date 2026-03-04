package com.edulink.edulink_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edulink.edulink_app.model.MatchChallenge;
import com.edulink.edulink_app.model.UserLevel;

public interface MatchChallengeRepository extends JpaRepository<MatchChallenge, Long> { 
    List<MatchChallenge> findByLevel(UserLevel level);
}
