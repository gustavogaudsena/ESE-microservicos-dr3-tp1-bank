package br.com.infnet.bank_api.repository;

import br.com.infnet.bank_api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
