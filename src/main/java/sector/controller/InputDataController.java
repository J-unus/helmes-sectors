package sector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sector.repository.SectorClassificationRepository;

@Controller
@RequiredArgsConstructor
public class InputDataController {

	private final SectorClassificationRepository sectorClassificationRepository;

	@GetMapping
	public String getSectorClassifications(Model model) {
		model.addAttribute("sectorClassifications", sectorClassificationRepository.findByParentIdIsNullOrderByName());
		return "index";
	}
}
