package sector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sector.domain.InputData;

public interface InputDataRepository extends JpaRepository<InputData, Long> {
}
