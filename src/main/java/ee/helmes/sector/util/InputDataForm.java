package ee.helmes.sector.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputDataForm {

	@NotBlank
	private String name;

	@AssertTrue
	private boolean agreedToTerms;

	@NotEmpty
	private List<Long> selectedSectorClassifications;
}
