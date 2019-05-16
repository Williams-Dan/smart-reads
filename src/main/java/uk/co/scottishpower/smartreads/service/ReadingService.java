package uk.co.scottishpower.smartreads.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.scottishpower.smartreads.model.Reading;
import uk.co.scottishpower.smartreads.repositories.ReadingRepository;

@Service
public class ReadingService {
    private final ReadingRepository readingRepository;

    @Autowired
    public ReadingService(ReadingRepository readingRepository){
        this.readingRepository = readingRepository;
    }

    public Reading findByAccountNumber(String accountNumber){
        return readingRepository.findByAccountNumber(accountNumber);
    }
}
