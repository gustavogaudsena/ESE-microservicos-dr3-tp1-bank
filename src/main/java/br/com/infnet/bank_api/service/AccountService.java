package br.com.infnet.bank_api.service;

import br.com.infnet.bank_api.model.Account;
import br.com.infnet.bank_api.model.User;
import br.com.infnet.bank_api.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository repository;


    public Account getByAccountNumber(Integer accountNumber) {
        return repository.findById(accountNumber).orElseThrow(() -> new NoSuchElementException("Account not found: " + accountNumber));
    }

    public User getUserByAccountNumber(Integer accountNumber) {
        Account acc = repository.findById(accountNumber).orElseThrow(() -> new NoSuchElementException("Account not found: " + accountNumber));

        // busca no serviço de usuários o usuário

        return new User();
    }

    public Account save(Account account) {
        return repository.save(account);
    }

    @Transactional()
    public boolean deposit(Integer accountNumber, Integer amount) {
        Account account = repository.findById(accountNumber).orElseThrow(() -> new NoSuchElementException("Account not found: " + accountNumber));

        account.deposit(amount);

        repository.save(account);

        return true;
    }

    @Transactional()
    public boolean withdraw(Integer accountNumber, Integer amount) throws IOException {
        Account account = repository.findById(accountNumber).orElseThrow(() -> new NoSuchElementException("Account not found: " + accountNumber));

        account.withdraw(amount);

        repository.save(account);

        return true;
    }


}
