package com.nazarov.movieland.converter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "currency"
)
public class Currency {
    @Id
    private Long r030;
    private String txt;
    private double rate;
    private String name;
    private LocalDate exchangedate;
}
