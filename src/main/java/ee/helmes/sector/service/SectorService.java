package ee.helmes.sector.service;

import ee.helmes.sector.domain.InputData;
import ee.helmes.sector.domain.Sector;
import ee.helmes.sector.repository.SectorClassificationRepository;
import ee.helmes.sector.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectorService {

	private final SectorRepository sectorRepository;

	private final SectorClassificationRepository sectorClassificationRepository;

	public void saveSectorData(InputData inputData, List<Long> selectedSectorClassifications) {
		Map<Long, Sector> savedValues = inputData.getSectors().stream()
				.collect(Collectors.toMap(sector -> sector.getSectorClassification().getId(), Function.identity()));

		selectedSectorClassifications.forEach(selectedSectorClassification -> {
			if (savedValues.containsKey(selectedSectorClassification)) {
				savedValues.remove(selectedSectorClassification);
			} else {
				createSector(inputData, selectedSectorClassification);
			}
		});

		if (!savedValues.isEmpty()) {
			savedValues.values().forEach(sectorRepository::delete);
		}
	}

	private void createSector(InputData inputData, Long sectorClassificationId) {
		Sector sector = Sector.builder()
				.inputData(inputData)
				.sectorClassification(sectorClassificationRepository.getReferenceById(sectorClassificationId))
				.build();
		sectorRepository.save(sector);
	}
}
