package br.com.infnet.bank_api.controller;

import br.com.infnet.bank_api.dto.*;
import br.com.infnet.bank_api.model.Account;
import br.com.infnet.bank_api.model.User;
import br.com.infnet.bank_api.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;


    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getByAccountNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok(service.getByAccountNumber(accountNumber));
    }

    @PostMapping()
    public ResponseEntity<Account> create(@RequestBody AccountDto dto) {

        Account account = new Account();
        account.setAccountNumber(dto.accountNumber());
        account.setAgencyNumber(dto.agencyNumber());
        account.setUserId(dto.userId());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(account));
    }

    @PutMapping("/{accountNumber}/deposit")
    public ResponseEntity<DepositResponseDTO> deposit(@PathVariable String accountNumber, @RequestBody DepositDTO dto) {
        service.deposit(accountNumber, dto.amount());

        String message = String.format("Deposit of %d done", dto.amount());
        return ResponseEntity.ok(new DepositResponseDTO(true, message));
    }

    @PutMapping("/{accountNumber}/withdraw")
    public ResponseEntity<WithdrawResponseDTO> withdraw(@PathVariable String accountNumber, @RequestBody WithdrawDTO dto) {
        try {
            service.withdraw(accountNumber, dto.amount());

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new WithdrawResponseDTO(true, e.getMessage()));
        }
        String message = String.format("Deposit of %d done", dto.amount());

        return ResponseEntity.ok(new WithdrawResponseDTO(true, message));

    }

    @GetMapping("/{accountNumber}/user")
    public ResponseEntity<User> getUserByAccountNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok(service.getUserByAccountNumber(accountNumber));
    }

}
