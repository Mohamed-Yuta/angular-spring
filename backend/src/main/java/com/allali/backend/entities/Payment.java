package com.allali.backend.entities;

import com.allali.backend.enums.PaymentStatus;
import com.allali.backend.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private LocalDate date ;
    private double amount ;
    private PaymentType type ;
    private PaymentStatus status = PaymentStatus.CREATED ;
    private String file ;
    @ManyToOne
    private Student student ;
}
