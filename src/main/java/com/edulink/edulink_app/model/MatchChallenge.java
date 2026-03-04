package com.edulink.edulink_app.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("MATCH") 
@Getter @Setter @NoArgsConstructor
public class MatchChallenge extends Challenge {

}