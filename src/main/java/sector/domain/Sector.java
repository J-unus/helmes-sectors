package sector.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sector extends BaseDomain {

	@JsonIgnoreProperties(value = {"sectors", "hibernateLazyInitializer"})
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "input_data_id")
	InputData inputData;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "sector_classification_id")
	SectorClassification sectorClassification;
}
