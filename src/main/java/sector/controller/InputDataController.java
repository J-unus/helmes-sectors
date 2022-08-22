package sector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sector.domain.InputData;
import sector.repository.SectorClassificationRepository;
import sector.request.in.InputDataSaveRequest;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class InputDataController {

	private final SectorClassificationRepository sectorClassificationRepository;

	@GetMapping
	public String getInputDataForm(Model model) {
		model.addAttribute("sectorClassifications", sectorClassificationRepository.findByParentIdIsNullOrderByName()); // TODO magic strings
		model.addAttribute("inputData", new InputDataSaveRequest());
		return "index";
	}

	@PostMapping("/save")
	public String saveInputDataForm(@ModelAttribute @Valid InputDataSaveRequest inputDataSaveRequest, Model model) {
		model.addAttribute("sectorClassifications", sectorClassificationRepository.findByParentIdIsNullOrderByName());
		model.addAttribute("inputData", inputDataSaveRequest);
		System.out.println(inputDataSaveRequest);
		return "index";
	}
}
