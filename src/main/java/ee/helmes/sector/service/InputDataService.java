package ee.helmes.sector.service;

import ee.helmes.sector.repository.InputDataRepository;
import ee.helmes.sector.util.InputDataForm;
import ee.helmes.sector.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ee.helmes.sector.domain.InputData;

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

    public void saveInputDataForm(InputDataForm request, HttpSession session) {
        InputData inputData = SessionUtil.getInputDataId(session)
                .map(inputDataRepository::getReferenceById)
                .orElse(new InputData());

        inputData.setName(request.getName());
        inputData.setAgreedToTerms(request.isAgreedToTerms());

        InputData savedInputData = inputDataRepository.save(inputData);
        sectorService.saveSectorData(savedInputData, request.getSectors());

        SessionUtil.setInputDataId(session, inputData.getId());
    }
}
