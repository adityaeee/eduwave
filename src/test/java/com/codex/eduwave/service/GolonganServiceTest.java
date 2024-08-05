

package com.codex.eduwave.service;

import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.model.request.GolonganRequest;
import com.codex.eduwave.model.request.SearchGolonganRequest;
import com.codex.eduwave.model.request.UpdateGolonganRequest;
import com.codex.eduwave.model.response.JwtClaims;
import com.codex.eduwave.repository.GolonganRepository;
import com.codex.eduwave.service.intrface.JwtService;
import com.codex.eduwave.service.intrface.SekolahService;
import com.codex.eduwave.utils.ValidationUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GolonganServiceTest {

    @Mock
    private GolonganRepository golonganRepository;

    @Mock
    private SekolahService sekolahService;

    @Mock
    private ValidationUtil validationUtil;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private GolonganServiceImpl golonganService;

    private Golongan golongan;
    private Sekolah sekolah;
    private JwtClaims jwtClaims;

    @BeforeEach
    void setUp() {
        sekolah = Sekolah.builder()
                .id("sekolahId")
                .sekolah("Sekolah 1")
                .build();

        golongan = Golongan.builder()
                .id("1")
                .golongan("Golongan 1")
                .spp(100000)
                .sekolah(sekolah)
                .isDeleted(false)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        jwtClaims = new JwtClaims();
        jwtClaims.setAccountId("accountId");
        jwtClaims.setRoles(Collections.singletonList("ROLE_ADMIN"));
    }

    @Test
    void testCreate() {
        GolonganRequest request = new GolonganRequest();
        request.setGolongan("Golongan 1");
        request.setSpp(100000);

        when(httpServletRequest.getHeader("Authorization")).thenReturn("Bearer token");
        when(jwtService.getClaimsByToken("Bearer token")).thenReturn(jwtClaims);
        when(sekolahService.getSekolahByAccountId("accountId")).thenReturn(sekolah);
        when(golonganRepository.saveAndFlush(any(Golongan.class))).thenReturn(golongan);

        Golongan result = golonganService.create(request);

        assertNotNull(result);
        assertEquals("Golongan 1", result.getGolongan());
        verify(golonganRepository, times(1)).saveAndFlush(any(Golongan.class));
    }

    @Test
    void testGetById() {
        when(golonganRepository.findById("1")).thenReturn(Optional.of(golongan));

        Golongan result = golonganService.getById("1");

        assertNotNull(result);
        assertEquals("Golongan 1", result.getGolongan());
        verify(golonganRepository, times(1)).findById("1");
    }

    @Test
    void testGetByIdNotFound() {
        when(golonganRepository.findById("1")).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> golonganService.getById("1"));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        verify(golonganRepository, times(1)).findById("1");
    }

    @Test
    void testGetAll() {
        SearchGolonganRequest request = new SearchGolonganRequest();

        when(httpServletRequest.getHeader("Authorization")).thenReturn("Bearer token");
        when(jwtService.getClaimsByToken("Bearer token")).thenReturn(jwtClaims);
        when(sekolahService.getSekolahByAccountId("accountId")).thenReturn(sekolah);
        when(golonganRepository.findAll(any(Specification.class))).thenReturn(List.of(golongan));

        List<Golongan> result = golonganService.getAll(request);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Golongan 1", result.get(0).getGolongan());
        verify(golonganRepository, times(1)).findAll(any(Specification.class));
    }

    @Test
    void testUpdate() {
        UpdateGolonganRequest request = new UpdateGolonganRequest();
        request.setId("1");
        request.setGolongan("Golongan Updated");
        request.setSpp(200000);

        Golongan updatedGolongan = Golongan.builder()
                .id("1")
                .golongan("Golongan Updated")
                .spp(200000)
                .sekolah(sekolah)
                .isDeleted(false)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        when(golonganRepository.findById("1")).thenReturn(Optional.of(golongan));
        when(golonganRepository.saveAndFlush(any(Golongan.class))).thenReturn(updatedGolongan);

        Golongan result = golonganService.update(request);

        assertNotNull(result);
        assertEquals("Golongan Updated", result.getGolongan());
        verify(golonganRepository, times(1)).saveAndFlush(any(Golongan.class));
    }

    @Test
    void testDelete() {
        when(golonganRepository.findById("1")).thenReturn(Optional.of(golongan));

        golonganService.delete("1");

        verify(golonganRepository, times(1)).saveAndFlush(any(Golongan.class));
        assertTrue(golongan.getIsDeleted());
    }

}
