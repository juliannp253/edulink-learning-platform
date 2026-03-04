package com.edulink.edulink_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edulink.edulink_app.model.EmojiChallenge;
import com.edulink.edulink_app.model.UserLevel;

public interface EmojiChallengeRepository extends JpaRepository<EmojiChallenge, Long> {

    List<EmojiChallenge> findByLevel(UserLevel level);
}
