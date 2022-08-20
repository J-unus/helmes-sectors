package sector.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputData extends BaseDomain {

	@Column(nullable = false)
    String name;

	@Column(nullable = false)
	String sectors;

	@Column(nullable = false)
	boolean agreedToTerms;
}
