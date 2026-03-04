package com.edulink.edulink_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edulink.edulink_app.model.MusicChallenge;
import com.edulink.edulink_app.model.UserLevel;

public interface MusicChallengeRepository extends JpaRepository<MusicChallenge, Long> {
    List<MusicChallenge> findByStartTime(Integer startTime);    
    List<MusicChallenge> findByLevel(UserLevel level);
}
