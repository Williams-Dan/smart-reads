package uk.co.scottishpower.smartreads.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Reading {
    @Id
    @SequenceGenerator(name = "reading_generator", sequenceName = "reading_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "reading_generator", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;
    @Column(name = "ACCOUNT_NUMBER", unique = true, nullable = false)
    private String accountNumber;
    @Column(name = "GAS_ID")
    private long gasId;
    @Column(name = "ELEC_ID")
    private long electricityId;
    @Column(name = "ELEC_SMART_READ")
    private BigDecimal electricitySmartRead;
    @Column(name = "GAS_SMART_READ")
    private BigDecimal gasSmartRead;

    public Reading() {
    }

    public Reading(long id, String accountNumber, long gasId, long electricityId, BigDecimal electricitySmartRead, BigDecimal gasSmartRead) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.gasId = gasId;
        this.electricityId = electricityId;
        this.electricitySmartRead = electricitySmartRead;
        this.gasSmartRead = gasSmartRead;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getGasId() {
        return gasId;
    }

    public void setGasId(long gasId) {
        this.gasId = gasId;
    }

    public BigDecimal getGasSmartRead() {
        return gasSmartRead;
    }

    public void setGasSmartRead(BigDecimal gasSmartRead) {
        this.gasSmartRead = gasSmartRead;
    }

    public long getElectricityId() {
        return electricityId;
    }

    @Override
    public String toString() {
        return "Reading{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", gasId=" + gasId +
                ", electricityId=" + electricityId +
                ", electricitySmartRead=" + electricitySmartRead +
                ", gasSmartRead=" + gasSmartRead +
                '}';
    }

    public void setElectricityId(long electricityId) {
        this.electricityId = electricityId;
    }

    public BigDecimal getElectricitySmartRead() {
        return electricitySmartRead;
    }

    public void setElectricitySmartRead(BigDecimal electricitySmartRead) {
        this.electricitySmartRead = electricitySmartRead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Reading reading = (Reading) o;

        return id == reading.id &&
                gasId == reading.gasId &&
                electricityId == reading.electricityId &&
                Objects.equals(accountNumber, reading.accountNumber) &&
                Objects.equals(electricitySmartRead, reading.electricitySmartRead) &&
                Objects.equals(gasSmartRead, reading.gasSmartRead);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, gasId, electricityId, electricitySmartRead, gasSmartRead);
    }
}
