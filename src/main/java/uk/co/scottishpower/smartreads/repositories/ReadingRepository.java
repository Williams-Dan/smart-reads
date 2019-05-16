package uk.co.scottishpower.smartreads.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.scottishpower.smartreads.model.Reading;

@Repository
public interface ReadingRepository extends CrudRepository<Reading, Long> {
    Reading findByAccountNumber(String accountNumber);
}
