package org.miniproject.elearningplatform.model.submodel;

import lombok.*;
import org.hibernate.annotations.Columns;

import jakarta.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class UserPhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String number;

    @Column
    private String countryCode = "MA";

    // Constructor
    public UserPhoneNumber(String number) {
        this.number = number;
    }
}
