package ee.helmes.sector.service;

import ee.helmes.sector.domain.InputData;
import ee.helmes.sector.domain.Sector;
import ee.helmes.sector.mock.MockResources;
import ee.helmes.sector.repository.SectorClassificationRepository;
import ee.helmes.sector.repository.SectorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SectorServiceTest {

    @Mock
    private SectorRepository sectorRepository;

    @Mock
    private SectorClassificationRepository sectorClassificationRepository;

    @InjectMocks
    private SectorService sectorService;

    @Test
    void saveSectorDataNew() {
        Long selectedSectorClassification = 12L;
        InputData inputData = MockResources.createAndPopulateInputData();
        inputData.setSectors(new ArrayList<>());

        sectorService.saveSectorData(inputData, List.of(selectedSectorClassification));

        verify(sectorClassificationRepository).getReferenceById(selectedSectorClassification);
        verify(sectorRepository, times(1)).save(any(Sector.class));
        verify(sectorRepository, never()).delete(any(Sector.class));
    }

    @Test
    void saveSectorDataExistsSame() {
        Long selectedSectorClassification = 25L;
        InputData inputData = MockResources.createAndPopulateInputData();

        sectorService.saveSectorData(inputData, List.of(selectedSectorClassification));

        verify(sectorClassificationRepository, never()).getReferenceById(selectedSectorClassification);
        verify(sectorRepository, never()).save(any(Sector.class));
        verify(sectorRepository, never()).delete(any(Sector.class));
    }

    @Test
    void saveSectorDataExistsDifferent() {
        Long selectedSectorClassification = 12L;
        InputData inputData = MockResources.createAndPopulateInputData();

        sectorService.saveSectorData(inputData, List.of(selectedSectorClassification));

        verify(sectorClassificationRepository).getReferenceById(selectedSectorClassification);
        verify(sectorRepository, times(1)).save(any(Sector.class));
        verify(sectorRepository, times(1)).delete(any(Sector.class));
    }
}
