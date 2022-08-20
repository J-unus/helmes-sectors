package sector.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sector extends BaseDomain {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "input_data_id")
	InputData inputData;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "sector_classification_id")
	SectorClassification sectorClassification;
}
