package sector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sector.domain.BaseDomain;
import sector.domain.InputData;
import sector.repository.InputDataRepository;
import sector.repository.SectorClassificationRepository;
import sector.request.in.InputDataSaveRequest;
import sector.service.InputDataService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class InputDataController {

	private final InputDataRepository inputDataRepository;

	private final SectorClassificationRepository sectorClassificationRepository;

	private final InputDataService inputDataService;

	@GetMapping
	public String getInputDataForm(Model model, HttpSession session) {
		model.addAttribute("sectorClassifications", sectorClassificationRepository.findByParentIdIsNullOrderByName()); // TODO magic strings


		Object inputDataId = session.getAttribute("inputDataId");
		InputDataSaveRequest saveRequest = new InputDataSaveRequest();

		if (inputDataId instanceof Long) {
			InputData inputData = inputDataRepository.getReferenceById((Long) inputDataId);

			saveRequest.setName(inputData.getName());
			saveRequest.setAgreedToTerms(inputData.isAgreedToTerms());
			saveRequest.setSectors(inputData.getSectors().stream().map(sector -> sector.getSectorClassification().getId()).toList());
		}

		model.addAttribute("inputData", saveRequest);
		return "index";
	}

	@PostMapping("/save")
	public String saveInputDataForm(@ModelAttribute @Valid InputDataSaveRequest inputDataSaveRequest, Model model, HttpSession session) {
		Object inputDataId = session.getAttribute("inputDataId");
		if (inputDataId instanceof Long) {
			inputDataSaveRequest.setId((Long) inputDataId);
		}
		InputData inputData = inputDataService.saveInputDataForm(inputDataSaveRequest);
		session.setAttribute("inputDataId", inputData.getId());

		model.addAttribute("sectorClassifications", sectorClassificationRepository.findByParentIdIsNullOrderByName()); // TODO Refactor dupe code
		model.addAttribute("inputData", inputDataSaveRequest);

		return "redirect:/";
	}
}
