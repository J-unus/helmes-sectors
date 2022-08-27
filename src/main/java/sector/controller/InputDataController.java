package sector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sector.repository.SectorClassificationRepository;
import sector.service.InputDataService;
import sector.util.AttributeName;
import sector.util.InputDataForm;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class InputDataController {

	private static final String INDEX_TEMPLATE = "index";
	private static final String REDIRECT_TO_INDEX_TEMPLATE = "redirect:/";

	private final SectorClassificationRepository sectorClassificationRepository;

	private final InputDataService inputDataService;

	@GetMapping
	public String getIndex(Model model, HttpSession session) {
		model.addAttribute(AttributeName.SECTOR_CLASSIFICATIONS, sectorClassificationRepository.findByParentIdIsNullOrderByName());
		model.addAttribute(AttributeName.INPUT_DATA_FORM, inputDataService.getInputDataForm(session));

		return INDEX_TEMPLATE;
	}

	@PostMapping("/save")
	public String saveInputDataForm(@ModelAttribute @Valid InputDataForm inputDataForm, HttpSession session) {
		inputDataService.saveInputDataForm(inputDataForm, session);

		return REDIRECT_TO_INDEX_TEMPLATE;
	}
}
