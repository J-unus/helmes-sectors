package ee.helmes.sector.repository;

import ee.helmes.sector.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, Long> {
}
