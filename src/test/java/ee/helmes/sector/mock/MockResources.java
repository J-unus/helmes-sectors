package ee.helmes.sector.mock;

import ee.helmes.sector.domain.InputData;
import ee.helmes.sector.domain.Sector;
import ee.helmes.sector.domain.SectorClassification;

import java.util.List;

public class MockResources {

	public static InputData createAndPopulateInputData() {
		SectorClassification sectorClassification = SectorClassification.builder()
				.name("testClassification")
				.build();
		sectorClassification.setId(25L);

		Sector sector = Sector.builder()
				.sectorClassification(sectorClassification)
				.build();

		InputData inputData = InputData.builder()
				.agreedToTerms(true)
				.name("testData")
				.sectors(List.of(sector))
				.build();
		sector.setInputData(inputData);

		return inputData;
	}
}
