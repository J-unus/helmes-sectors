package sector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sector.domain.Sector;

public interface SectorRepository extends JpaRepository<Sector, Long> {
}
