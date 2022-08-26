package sector.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sector.domain.InputData;
import sector.repository.InputDataRepository;
import sector.util.InputDataForm;
import sector.util.SessionUtil;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputDataService {

    private final InputDataRepository inputDataRepository;

    private final SectorService sectorService;

    public InputDataForm getInputDataForm(HttpSession session) {
        InputDataForm inputDataForm = new InputDataForm();
        Optional<Long> inputDataId = SessionUtil.getInputDataId(session);

        if (inputDataId.isPresent()) {
            InputData inputData = inputDataRepository.getReferenceById(inputDataId.get());
            mapInputDataToForm(inputData, inputDataForm);
        }

        return inputDataForm;
    }

    private void mapInputDataToForm(InputData inputData, InputDataForm inputDataForm) {
        inputDataForm.setName(inputData.getName());
        inputDataForm.setAgreedToTerms(inputData.isAgreedToTerms());
        inputDataForm.setSectors(inputData.getSectors().stream().map(sector -> sector.getSectorClassification().getId()).toList());
    }

    @Transactional
    public InputData saveInputDataForm(InputDataForm request) {
        InputData inputData = Optional.ofNullable(request.getId())
                .map(inputDataRepository::getReferenceById)
                .orElse(new InputData());

        inputData.setName(request.getName());
        inputData.setAgreedToTerms(request.isAgreedToTerms());

        InputData savedInputData = inputDataRepository.save(inputData);
        sectorService.saveSectorData(savedInputData, request.getSectors());

        return savedInputData;
    }
}
