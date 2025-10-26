package br.com.infnet.bank_api.dto;

import java.util.UUID;

public record AccountDto(Integer accountNumber, Integer agencyNumber, UUID userId) {
}
