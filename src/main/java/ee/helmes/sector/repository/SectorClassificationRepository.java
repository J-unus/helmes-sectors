package ee.helmes.sector.repository;

import ee.helmes.sector.domain.SectorClassification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectorClassificationRepository extends JpaRepository<SectorClassification, Long> {

	List<SectorClassification> findByParentIdIsNullOrderByName();
}
