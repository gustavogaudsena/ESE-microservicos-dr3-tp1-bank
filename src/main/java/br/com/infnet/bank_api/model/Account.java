package br.com.infnet.bank_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(nullable = false)
    private Integer accountNumber;

    @Column(nullable = false)
    private Integer agencyNumber;

    @Column(nullable = false)
    private Integer balance;

    @Column(nullable = false)
    private UUID userId;


    public Integer deposit(Integer amount) {
        balance += amount;
        return balance;
    }

    public Integer withdraw(Integer amount) throws IOException {
        if (amount > balance) {
            throw new IOException("Not enough funds");
        }

        balance -= amount;
        return balance;
    }

}
