package mateuszek.zadaniesylwiacredit.service;

import lombok.RequiredArgsConstructor;
import mateuszek.zadaniesylwiacredit.repository.CreditRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository repository;


}
