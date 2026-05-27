package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "visits")
@Data
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pet_id")
    private Integer petId;

    @Column(name = "vet_id")
    private Integer vetId;

    @Column(name = "visit_date")
    private Date visitDate;

    private String description;
    private Double cost;
}