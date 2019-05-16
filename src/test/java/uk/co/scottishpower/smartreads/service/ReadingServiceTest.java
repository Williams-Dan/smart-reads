package uk.co.scottishpower.smartreads.service;

import org.junit.Before;
import org.junit.Test;
import uk.co.scottishpower.smartreads.model.Reading;
import uk.co.scottishpower.smartreads.repositories.ReadingRepository;
import uk.co.scottishpower.smartreads.utils.Builder;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReadingServiceTest {
    private ReadingRepository readingRepository;
    private ReadingService readingService;

    @Before
    public void setup(){
        readingRepository = mock(ReadingRepository.class);
        readingService = new ReadingService(readingRepository);
    }

    @Test
    public void testFindByAccountNumber_readingExists(){

        Reading reading = new Builder<>(Reading.class)
                .and(x -> x.setAccountNumber("testAccNumber"))
                .and(x -> x.setElectricityId(5L))
                .and(x -> x.setGasId(67L))
                .and(x -> x.setElectricitySmartRead(new BigDecimal(45555000)))
                .and(x -> x.setGasSmartRead(new BigDecimal(89900223)))
                .build();

        when(readingRepository.findByAccountNumber(reading.getAccountNumber())).thenReturn(reading);

        Reading actual = readingService.findByAccountNumber(reading.getAccountNumber());

        verify(readingRepository).findByAccountNumber(reading.getAccountNumber());

        assertThat(actual).isEqualToComparingFieldByField(reading);

    }

    @Test
    public void testFindByAccountNumber_readingDoesNotExist(){
        String accountNumber = "badAccNumber";

        when(readingRepository.findByAccountNumber(accountNumber)).thenReturn(null);

        Reading actual = readingService.findByAccountNumber(accountNumber);

        verify(readingRepository).findByAccountNumber(accountNumber);

        assertThat(actual).isNull();
    }

}
