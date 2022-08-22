package sector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sector.domain.SectorClassification;

import java.util.List;

public interface SectorClassificationRepository extends JpaRepository<SectorClassification, Long> {

    List<SectorClassification> findByParentIdIsNullOrderByName();
}
