package uk.co.scottishpower.smartreads.dtos;

import java.math.BigDecimal;

public class ReadingDto {
    private BigDecimal gasRead;
    private BigDecimal electricityRead;

    public ReadingDto(BigDecimal gasRead, BigDecimal electricityRead) {
        this.gasRead = gasRead;
        this.electricityRead = electricityRead;
    }

    public BigDecimal getGasRead() {
        return gasRead;
    }

    public void setGasRead(BigDecimal gasRead) {
        this.gasRead = gasRead;
    }

    public BigDecimal getElectricityRead() {
        return electricityRead;
    }

    public void setElectricityRead(BigDecimal electricityRead) {
        this.electricityRead = electricityRead;
    }
}
