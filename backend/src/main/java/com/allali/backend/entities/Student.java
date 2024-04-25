package com.allali.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Student {
    @Id
    private String id ;
    @Column(unique = true)
    private String code ;
    private String firstName ;
    private String lastName ;
    private String email ;
    private String image ;
    @OneToMany(mappedBy = "student" , fetch = FetchType.LAZY)
    private List<Payment> paymentList ;

}
