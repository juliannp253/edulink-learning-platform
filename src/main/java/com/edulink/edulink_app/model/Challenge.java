package com.edulink.edulink_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "challenges")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @Enumerated(EnumType.STRING)
    private UserLevel level; // KIDS o GENERAL

    private String title;
    private String questionText; // Ej: "The cat is ____ the table" o "Translate: 🍎"
    private String correctAnswer;
    private String options; // Lista separada por comas: "under,on,in,between"

    // Campos específicos para multimedia
    private String mediaId;   // ID de YouTube (ej: dQw4w9WgXcQ) o nombre de imagen
    private Integer pauseTime;
    private Integer startTime; // Segundo exacto para pausar el video (solo para MUSIC)
}