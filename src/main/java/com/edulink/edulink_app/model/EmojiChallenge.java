package com.edulink.edulink_app.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("EMOJI")
@Getter @Setter @NoArgsConstructor
public class EmojiChallenge extends Challenge {
    private String mediaId; 
}
