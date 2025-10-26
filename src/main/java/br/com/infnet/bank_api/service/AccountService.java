package br.com.infnet.bank_api.service;

import br.com.infnet.bank_api.model.Account;
import br.com.infnet.bank_api.model.User;
import br.com.infnet.bank_api.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository repository;
    private final RestTemplate restTemplate;


    public Account getByAccountNumber(String accountNumber) {
        return repository.findById(accountNumber).orElseThrow(() -> new NoSuchElementException("Account not found: " + accountNumber));
    }

    public User getUserByAccountNumber(String accountNumber) {
        Account acc = repository.findById(accountNumber).orElseThrow(() -> new NoSuchElementException("Account not found: " + accountNumber));

        String url = "http://localhost:8080/" + acc.getUserId();

        return restTemplate.getForObject(url, User.class);
    }

    public Account save(Account account) {
        return repository.save(account);
    }

    @Transactional()
    public boolean deposit(String accountNumber, Integer amount) {
        Account account = repository.findById(accountNumber).orElseThrow(() -> new NoSuchElementException("Account not found: " + accountNumber));

        account.deposit(amount);

        repository.save(account);

        return true;
    }

    @Transactional()
    public boolean withdraw(String accountNumber, Integer amount) throws IOException {
        Account account = repository.findById(accountNumber).orElseThrow(() -> new NoSuchElementException("Account not found: " + accountNumber));

        account.withdraw(amount);

        repository.save(account);

        return true;
    }


}
