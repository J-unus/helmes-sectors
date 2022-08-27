package ee.helmes.sector.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputData extends BaseDomain {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private boolean agreedToTerms;

	@Builder.Default
	@OneToMany(mappedBy = "inputData")
	private List<Sector> sectors = new ArrayList<>();
}
