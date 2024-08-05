package com.codex.eduwave.service;

import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.entity.Siswa;
import com.codex.eduwave.model.request.SearchSiswaRequest;
import com.codex.eduwave.model.request.SiswaRequest;
import com.codex.eduwave.model.request.UpdateSiswaRequest;
import com.codex.eduwave.model.response.JwtClaims;
import com.codex.eduwave.repository.SiswaRepository;
import com.codex.eduwave.service.intrface.GolonganService;
import com.codex.eduwave.service.intrface.JwtService;
import com.codex.eduwave.service.intrface.SekolahService;
import com.codex.eduwave.utils.ValidationUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SiswaServiceTest {

    @InjectMocks
    private SiswaServiceImpl siswaService;

    @Mock
    private SiswaRepository siswaRepository;

    @Mock
    private GolonganService golonganService;

    @Mock
    private SekolahService sekolahService;

    @Mock
    private ValidationUtil validationUtil;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        SiswaRequest request = new SiswaRequest();
        request.setNama("Test Siswa");
        request.setNis("123456");
        request.setEmail("test@example.com");
        request.setGolonganId("1");

        Golongan golongan = new Golongan();
        golongan.setId("1");

        when(golonganService.getById("1")).thenReturn(golongan);
        when(siswaRepository.saveAndFlush(any(Siswa.class))).thenAnswer(i -> i.getArguments()[0]);

        Siswa result = siswaService.create(request);

        assertNotNull(result);
        assertEquals("Test Siswa", result.getNama());
        assertEquals("123456", result.getNis());
        verify(siswaRepository, times(1)).saveAndFlush(any(Siswa.class));
    }

    @Test
    void testGetById() {
        Siswa siswa = new Siswa();
        siswa.setId("1");
        when(siswaRepository.findById("1")).thenReturn(Optional.of(siswa));

        Siswa result = siswaService.getById("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
    }

    @Test
    void testGetByIdNotFound() {
        when(siswaRepository.findById("1")).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> siswaService.getById("1"));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        assertEquals("Siswa not found", exception.getReason());
    }

    @Test
    void testUpdate() {
        Siswa existingSiswa = new Siswa();
        existingSiswa.setId("1");
        Golongan golongan = new Golongan();
        golongan.setId("1");

        UpdateSiswaRequest request = new UpdateSiswaRequest();
        request.setId("1");
        request.setNama("Updated Siswa");
        request.setGolonganId("1");

        when(siswaRepository.findById("1")).thenReturn(Optional.of(existingSiswa));
        when(golonganService.getById("1")).thenReturn(golongan);
        when(siswaRepository.saveAndFlush(any(Siswa.class))).thenAnswer(i -> i.getArguments()[0]);

        Siswa result = siswaService.update(request);

        assertNotNull(result);
        assertEquals("Updated Siswa", result.getNama());
        verify(siswaRepository, times(1)).saveAndFlush(any(Siswa.class));
    }

    @Test
    void testInActive() {
        Siswa siswa = new Siswa();
        siswa.setId("1");
        siswa.setIsActive(true);

        when(siswaRepository.findById("1")).thenReturn(Optional.of(siswa));
        when(siswaRepository.saveAndFlush(any(Siswa.class))).thenReturn(siswa);

        siswaService.inActive("1");

        assertFalse(siswa.getIsActive());
        verify(siswaRepository, times(1)).saveAndFlush(siswa);
    }

    @Test
    void testGetAll() {
        // Mocking the required dependencies and methods
        when(httpServletRequest.getHeader("Authorization")).thenReturn("Bearer token");
        when(jwtService.getClaimsByToken("Bearer token")).thenReturn(new JwtClaims());
        when(sekolahService.getSekolahByAccountId(any())).thenReturn(new Sekolah());

        // Create a mock Page object
        Page<Siswa> page = new PageImpl<>(Collections.singletonList(new Siswa()), PageRequest.of(0, 10), 1);
        when(siswaRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(page);

        // Create the request
        SearchSiswaRequest request = new SearchSiswaRequest();
        request.setPage(1);
        request.setSize(10);
        request.setSortBy("nama");
        request.setDirection("ASC");

        // Call the service method
        Page<Siswa> result = siswaService.getAll(request);

        // Assert the results
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(siswaRepository, times(1)).findAll(any(Specification.class), any(PageRequest.class));
    }
}
