package uk.co.scottishpower.smartreads.controllers;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.scottishpower.smartreads.SmartReadsApplication;
import uk.co.scottishpower.smartreads.model.Reading;
import uk.co.scottishpower.smartreads.repositories.ReadingRepository;
import uk.co.scottishpower.smartreads.utils.Builder;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmartReadsApplication.class)
@AutoConfigureMockMvc
public class ReadingControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ReadingRepository readingRepository;

    @After
    public void tearDown() {
        readingRepository.deleteAll();
    }

    @Test
    public void testGetReading_success() throws Exception {
        Reading reading = new Builder<>(Reading.class)
                .and(x -> x.setAccountNumber("testAccNumberForReading"))
                .and(x -> x.setElectricityId(87L))
                .and(x -> x.setGasId(52L))
                .and(x -> x.setElectricitySmartRead(new BigDecimal(1370740187)))
                .and(x -> x.setGasSmartRead(new BigDecimal(89900223)))
                .build();

        readingRepository.save(reading);

        mvc.perform(get("/api/smart/reads/{accountNumber}", reading.getAccountNumber())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gasRead").value(reading.getGasSmartRead()))
                .andExpect(jsonPath("$.electricityRead").value(reading.getElectricitySmartRead()));
    }

    @Test
    public void testGetReading_noReading() throws Exception {
        mvc.perform(get("/api/smart/reads/{accountNumber}", "34563DFGL")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Reading could not be found"));
    }
}
