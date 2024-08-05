package com.codex.eduwave.controller;

import com.codex.eduwave.constant.ApiUrl;
import com.codex.eduwave.constant.StatusSPP;
import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.entity.Siswa;
import com.codex.eduwave.model.request.*;
import com.codex.eduwave.model.response.*;
import com.codex.eduwave.service.intrface.SiswaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SiswaControllerTest {

    @Mock
    private SiswaService siswaService;

    @InjectMocks
    private SiswaController siswaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = "SEKOLAH")
    void testCreateSiswa() {
        SiswaRequest request = new SiswaRequest();

        // Mock Golongan
        Golongan golongan = mock(Golongan.class);
        when(golongan.getId()).thenReturn("golonganId");
        when(golongan.getGolongan()).thenReturn("Golongan A");
        when(golongan.getSpp()).thenReturn(100000);

        // Mock Siswa
        Siswa siswa = mock(Siswa.class);
        when(siswa.getId()).thenReturn("siswaId");
        when(siswa.getNama()).thenReturn("John Doe");
        when(siswa.getNis()).thenReturn("12345");
        when(siswa.getEmail()).thenReturn("john.doe@example.com");
        when(siswa.getNoHp()).thenReturn("1234567890");
        when(siswa.getNoHpOrtu()).thenReturn("0987654321");
        when(siswa.getAlamat()).thenReturn("Some Address");
        when(siswa.getStatus()).thenReturn(StatusSPP.BELUM_LUNAS);
        when(siswa.getTagihan()).thenReturn(500000);
        when(siswa.getGolongan()).thenReturn(golongan);
        when(siswa.getIsActive()).thenReturn(true);
        when(siswaService.create(any(SiswaRequest.class))).thenReturn(siswa);

        ResponseEntity<BaseResponse> response = siswaController.createSiswa(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(siswaService, times(1)).create(any(SiswaRequest.class));
    }

    @Test
    @WithMockUser(roles = "SEKOLAH")
    void testGetById() {

        // Mock Golongan
        Golongan golongan = mock(Golongan.class);
        when(golongan.getId()).thenReturn("golonganId");
        when(golongan.getGolongan()).thenReturn("Golongan A");
        when(golongan.getSpp()).thenReturn(100000);

        // Mock Siswa
        Siswa siswa = mock(Siswa.class);
        when(siswa.getId()).thenReturn("siswaId");
        when(siswa.getNama()).thenReturn("John Doe");
        when(siswa.getNis()).thenReturn("12345");
        when(siswa.getEmail()).thenReturn("john.doe@example.com");
        when(siswa.getNoHp()).thenReturn("1234567890");
        when(siswa.getNoHpOrtu()).thenReturn("0987654321");
        when(siswa.getAlamat()).thenReturn("Some Address");
        when(siswa.getStatus()).thenReturn(StatusSPP.BELUM_LUNAS);
        when(siswa.getTagihan()).thenReturn(500000);
        when(siswa.getGolongan()).thenReturn(golongan);
        when(siswa.getIsActive()).thenReturn(true);
        when(siswaService.create(any(SiswaRequest.class))).thenReturn(siswa);
        when(siswaService.getById(anyString())).thenReturn(siswa);

        ResponseEntity<BaseResponse> response = siswaController.getById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(siswaService, times(1)).getById(anyString());
    }

    @Test
    @WithMockUser(roles = "SEKOLAH")

    void testUpdateSiswa() {
        // Mock Golongan
        Golongan golongan = mock(Golongan.class);
        when(golongan.getId()).thenReturn("golonganId");
        when(golongan.getGolongan()).thenReturn("Golongan A");
        when(golongan.getSpp()).thenReturn(100000);

        // Mock Siswa
        Siswa siswa = mock(Siswa.class);
        when(siswa.getId()).thenReturn("siswaId");
        when(siswa.getNama()).thenReturn("John Doe");
        when(siswa.getNis()).thenReturn("12345");
        when(siswa.getEmail()).thenReturn("john.doe@example.com");
        when(siswa.getNoHp()).thenReturn("1234567890");
        when(siswa.getNoHpOrtu()).thenReturn("0987654321");
        when(siswa.getAlamat()).thenReturn("Some Address");
        when(siswa.getStatus()).thenReturn(StatusSPP.BELUM_LUNAS);
        when(siswa.getTagihan()).thenReturn(500000);
        when(siswa.getGolongan()).thenReturn(golongan);
        when(siswa.getIsActive()).thenReturn(true);


        // Mock SiswaService
        when(siswaService.update(any(UpdateSiswaRequest.class))).thenReturn(siswa);

        // Prepare request
        UpdateSiswaRequest request = new UpdateSiswaRequest();
        // Set properties on the request as needed

        // Call the controller method
        ResponseEntity<BaseResponse> response = siswaController.updateSiswa(request);

        // Validate the response
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("successfully update data siswa", ((CommonResponse<SiswaResponse>) response.getBody()).getMessage());

        // Verify interactions
        verify(siswaService).update(any(UpdateSiswaRequest.class));
    }

    @Test
    @WithMockUser(roles = "SEKOLAH")
    void testInActiveSiswa() {
        doNothing().when(siswaService).inActive(anyString());

        ResponseEntity<BaseResponse> response = siswaController.inActiveSiswa("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(siswaService, times(1)).inActive(anyString());
    }

    @Test
    @WithMockUser(roles = "SEKOLAH")
    void testResetSiswaStatus() {
        UpdateStatusSiswaBulk request = new UpdateStatusSiswaBulk();
        request.setSiswaId(List.of("1", "2"));
        Siswa siswa = new Siswa();
        List<Siswa> siswaList = List.of(siswa, siswa);

        when(siswaService.getById(anyString())).thenReturn(siswa);
        when(siswaService.updateStatusBulk(anyList())).thenReturn(siswaList);

        ResponseEntity<BaseResponse> response = siswaController.resetSiswaStatus(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(siswaService, times(2)).getById(anyString());
        verify(siswaService, times(1)).updateStatusBulk(anyList());
    }

}
