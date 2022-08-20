package sector.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SectorController {


	@GetMapping("/hots")
	public String hots() {
		return "start";
	}

	@GetMapping("/start")
	public String start() {
		return "stop";
	}

	@GetMapping("/stop")
	public String stop() {
		return "start";
	}
}
