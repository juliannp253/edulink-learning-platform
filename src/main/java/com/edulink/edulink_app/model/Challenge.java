package com.edulink.edulink_app.model;

import jakarta.persistence.*; 
import lombok.*;

@Entity
@Table(name = "challenges")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
public abstract class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false) 
    private ActivityType type;

    @Enumerated(EnumType.STRING)
    private UserLevel level; 

    private String title;
    private String questionText; 
    private String correctAnswer;
    private String options;
}