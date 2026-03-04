package com.edulink.edulink_app.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("MUSIC")
@Getter @Setter @NoArgsConstructor
public class MusicChallenge extends Challenge {
    private String mediaId; 
    private Integer pauseTime;
    private Integer startTime;
}
