package ee.helmes.sector.service;

import ee.helmes.sector.domain.InputData;
import ee.helmes.sector.mock.MockResources;
import ee.helmes.sector.repository.InputDataRepository;
import ee.helmes.sector.util.AttributeName;
import ee.helmes.sector.util.InputDataForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InputDataServiceTest {

	@Mock
	private InputDataRepository inputDataRepository;

	@Mock
	private SectorService sectorService;

	@InjectMocks
	private InputDataService inputDataService;

	@Test
	void getInputDataFormSessionNew() {
		HttpSession httpSession = new MockHttpSession();

		InputDataForm result = inputDataService.getInputDataForm(httpSession);

		assertThat(result).isEqualTo(new InputDataForm());
	}

	@Test
	void getInputDataFormSessionExists() {
		Long inputDataId = 1L;

		HttpSession httpSession = new MockHttpSession();
		httpSession.setAttribute(AttributeName.INPUT_DATA_ID, inputDataId);
		InputData inputData = MockResources.createAndPopulateInputData();

		when(inputDataRepository.getReferenceById(inputDataId)).thenReturn(inputData);

		InputDataForm result = inputDataService.getInputDataForm(httpSession);

		assertThat(result.getName()).isEqualTo(inputData.getName());
		assertThat(result.isAgreedToTerms()).isTrue();
		assertThat(result.getSelectedSectorClassifications().size()).isEqualTo(1);
		assertThat(result.getSelectedSectorClassifications().get(0))
				.isEqualTo(inputData.getSectors().get(0).getSectorClassification().getId());
	}

	@Test
	void saveInputDataFormSessionNew() {
		HttpSession httpSession = new MockHttpSession();
		InputDataForm inputDataForm = new InputDataForm();

		inputDataService.saveInputDataForm(inputDataForm, httpSession);

		verify(inputDataRepository, never()).getReferenceById(anyLong());
		verify(inputDataRepository).save(any(InputData.class));
		verify(sectorService).saveSectorData(any(), eq(inputDataForm.getSelectedSectorClassifications()));
	}

	@Test
	void saveInputDataFormSessionExists() {
		Long inputDataId = 1L;

		HttpSession httpSession = new MockHttpSession();
		httpSession.setAttribute(AttributeName.INPUT_DATA_ID, inputDataId);

		InputDataForm inputDataForm = InputDataForm.builder()
				.name("testing")
				.agreedToTerms(true)
				.selectedSectorClassifications(List.of(12L))
				.build();
		InputData inputData = MockResources.createAndPopulateInputData();

		when(inputDataRepository.getReferenceById(inputDataId)).thenReturn(inputData);

		inputDataService.saveInputDataForm(inputDataForm, httpSession);

		ArgumentCaptor<InputData> inputDataArgumentCaptor = ArgumentCaptor.forClass(InputData.class);
		verify(inputDataRepository).save(inputDataArgumentCaptor.capture());
		verify(sectorService).saveSectorData(any(), eq(inputDataForm.getSelectedSectorClassifications()));

		InputData capturedInputData = inputDataArgumentCaptor.getValue();
		assertThat(capturedInputData.getName()).isEqualTo(inputDataForm.getName());
		assertThat(capturedInputData.isAgreedToTerms()).isTrue();
	}
}
