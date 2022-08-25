package sector.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import sector.domain.BaseDomain;
import sector.domain.InputData;
import sector.domain.Sector;
import sector.repository.InputDataRepository;
import sector.repository.SectorClassificationRepository;
import sector.repository.SectorRepository;
import sector.request.in.InputDataSaveRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InputDataService {

    private final InputDataRepository inputDataRepository;

    private final SectorRepository sectorRepository;

    private final SectorClassificationRepository sectorClassificationRepository;

    @Transactional
    public InputData saveInputDataForm(InputDataSaveRequest request) {
        InputData inputData = Optional.ofNullable(request.getId())
                .map(inputDataRepository::getReferenceById)
                .orElse(new InputData());

        inputData.setName(request.getName());
        inputData.setAgreedToTerms(request.isAgreedToTerms());

        InputData savedInputData = inputDataRepository.save(inputData);
        saveSectorData(savedInputData, request.getSectors());

        return savedInputData;
    }

    private void saveSectorData(InputData inputData, List<Long> sectorSaveRequests) { // TODO to other service
        Map<Long, Sector> savedValues = inputData.getSectors().stream()
                .collect(Collectors.toMap(BaseDomain::getId, Function.identity()));

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
