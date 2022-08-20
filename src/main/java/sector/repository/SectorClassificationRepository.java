package sector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sector.domain.SectorClassification;

public interface SectorClassificationRepository extends JpaRepository<SectorClassification, Long> {
}
