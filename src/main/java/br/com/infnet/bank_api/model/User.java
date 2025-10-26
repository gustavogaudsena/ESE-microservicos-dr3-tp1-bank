package br.com.infnet.bank_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id@GeneratedValue
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 200)
    private String email;

    @Column(length = 50)
    private String phone;
}
