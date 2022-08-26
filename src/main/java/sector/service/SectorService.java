package sector.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sector.domain.InputData;
import sector.domain.Sector;
import sector.repository.SectorClassificationRepository;
import sector.repository.SectorRepository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository sectorRepository;

    private final SectorClassificationRepository sectorClassificationRepository;

    @Transactional
    public void saveSectorData(InputData inputData, List<Long> sectorSaveRequests) {
        Map<Long, Sector> savedValues = inputData.getSectors().stream()
                .collect(Collectors.toMap(Sector::getId, Function.identity()));

        sectorSaveRequests.forEach(sectorSaveRequest -> {
            if (savedValues.containsKey(sectorSaveRequest)) {
                savedValues.remove(sectorSaveRequest);
            } else {
                Sector sector = Sector.builder()
                        .inputData(inputData)
                        .sectorClassification(sectorClassificationRepository.getReferenceById(sectorSaveRequest))
                        .build();
                sectorRepository.save(sector);
            }
        });

        if (!savedValues.isEmpty()) {
            savedValues.values().forEach(sectorRepository::delete);
        }
    }
}
