package uk.co.scottishpower.smartreads.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import uk.co.scottishpower.smartreads.model.Reading;
import uk.co.scottishpower.smartreads.repositories.ReadingRepository;

import java.math.BigDecimal;

@Component
public class DemoDataPopulator implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LoggerFactory.getLogger(DemoDataPopulator.class);

    //This is only for demo purposes and should be removed once the real database has been created
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ReadingRepository readingRepository = event.getApplicationContext().getBean(ReadingRepository.class);

        logger.info("Clearing database...");
        readingRepository.deleteAll();
        logger.info("Database cleared...");

        logger.info("Inserting demo readings...");

        for (long i = 0; i < 100; i++) {

            Reading reading = new Reading();
            reading.setAccountNumber(String.format("ADS%s", i * 2));
            reading.setGasId(i * 4);
            reading.setElectricityId(i * 9);
            reading.setElectricitySmartRead(new BigDecimal(i * 3));
            reading.setGasSmartRead(new BigDecimal(i * 7));

            reading = readingRepository.save(reading);
            logger.info("Created Reading {} ...", reading);
        }

        logger.info("Finished creating demo readings...");
    }
}
