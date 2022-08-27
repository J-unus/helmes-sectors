package ee.helmes.sector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ee.helmes.sector.domain.InputData;

public interface InputDataRepository extends JpaRepository<InputData, Long> {
}
