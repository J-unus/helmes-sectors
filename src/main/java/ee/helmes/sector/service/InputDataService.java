package ee.helmes.sector.service;

import ee.helmes.sector.domain.InputData;
import ee.helmes.sector.repository.InputDataRepository;
import ee.helmes.sector.util.InputDataForm;
import ee.helmes.sector.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class InputDataService {

	private final InputDataRepository inputDataRepository;

	private final SectorService sectorService;

	public InputDataForm getInputDataForm(HttpSession session) {
		return SessionUtil.getInputDataId(session)
				.map(inputDataRepository::getReferenceById)
				.map(this::mapInputDataToForm)
				.orElse(new InputDataForm());
	}

	private InputDataForm mapInputDataToForm(InputData source) {
		return InputDataForm.builder()
				.name(source.getName())
				.agreedToTerms(source.isAgreedToTerms())
				.selectedSectorClassifications(source.getSectors().stream().map(sector -> sector.getSectorClassification().getId()).toList())
				.build();
	}

	public void saveInputDataForm(InputDataForm request, HttpSession session) {
		InputData inputData = SessionUtil.getInputDataId(session)
				.map(inputDataRepository::getReferenceById)
				.orElse(new InputData());

		inputData.setName(request.getName());
		inputData.setAgreedToTerms(request.isAgreedToTerms());

		InputData savedInputData = inputDataRepository.save(inputData);
		sectorService.saveSectorData(savedInputData, request.getSelectedSectorClassifications());

		SessionUtil.setInputDataId(session, inputData.getId());
	}
}
