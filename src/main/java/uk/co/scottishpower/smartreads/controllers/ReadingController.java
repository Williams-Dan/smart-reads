package uk.co.scottishpower.smartreads.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.scottishpower.smartreads.dtos.ReadingDto;
import uk.co.scottishpower.smartreads.exceptions.BusinessLogicException;
import uk.co.scottishpower.smartreads.model.Reading;
import uk.co.scottishpower.smartreads.service.ReadingService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/smart/reads/", produces = "application/json")
public class ReadingController {
    private final Logger logger = LoggerFactory.getLogger(ReadingController.class);

    private final ReadingService readingService;

    @Autowired
    public ReadingController(ReadingService readingService){
        this.readingService = readingService;
    }

    @GetMapping("/{accountNumber}")
    public ReadingDto getReading(@PathVariable("accountNumber") String accountNumber){
        logger.info("Querying reading by account number: {}...", accountNumber);
        Reading reading = Optional.ofNullable(readingService.findByAccountNumber(accountNumber)).orElseThrow(() -> new BusinessLogicException("Reading could not be found"));
        logger.info("Reading retrieved: {}", reading);
        return new ReadingDto(reading.getGasSmartRead(), reading.getElectricitySmartRead());
    }
}
