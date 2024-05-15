package br.com.stoom.api.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Float price;

    private LocalDate expirationDate;

    private Long brandID;

    private String brandName;

    private String category;

    private LocalDate registrationDate;

    private Boolean fgInactive;
}
