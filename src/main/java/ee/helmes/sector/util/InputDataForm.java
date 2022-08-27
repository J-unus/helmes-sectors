package ee.helmes.sector.util;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class InputDataForm {

    @NotBlank
    private String name;

    @AssertTrue
    private boolean agreedToTerms;

    @NotEmpty
    private List<Long> sectors;
}
