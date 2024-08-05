package com.codex.eduwave.service;

import com.codex.eduwave.entity.Account;
import com.codex.eduwave.entity.Image;
import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.model.request.AuthRequest;
import com.codex.eduwave.model.request.SearchSekolahRequest;
import com.codex.eduwave.model.request.SekolahRequest;
import com.codex.eduwave.model.request.UpdateSekolahRequest;
import com.codex.eduwave.model.response.JwtClaims;
import com.codex.eduwave.repository.SekolahRepository;
import com.codex.eduwave.service.intrface.*;
import com.codex.eduwave.utils.ValidationUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SekolahServiceImplTest {

    @Mock
    private SekolahRepository sekolahRepository;
    @Mock
    private ImageService imageService;
    @Mock
    private AuthService authService;
    @Mock
    private AccountService accountService;
    @Mock
    private ValidationUtil validationUtil;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private JwtService jwtService;

    @InjectMocks
    private SekolahServiceImpl sekolahService;

    private Sekolah sekolah;
    private SekolahRequest sekolahRequest;
    private UpdateSekolahRequest updateSekolahRequest;
    private AuthRequest authRequest;
    private JwtClaims jwtClaims;

    @BeforeEach
    void setUp() {
        sekolah = Sekolah.builder()
                .id("1")
                .sekolah("Sekolah ABC")
                .email("sekolah@abc.com")
                .noHp("08123456789")
                .npsn("12345678")
                .createdAt(new Date())
                .updatedAt(new Date())
                .isDeleted(false)
                .createdBy("admin")
                .build();

        sekolahRequest = SekolahRequest.builder()
                .sekolah("Sekolah ABC")
                .email("sekolah@abc.com")
                .noHp("08123456789")
                .npsn("12345678")
                .logo(null)
                .password("password")
                .build();

        updateSekolahRequest = UpdateSekolahRequest.builder()
                .id("1")
                .sekolah("Sekolah XYZ")
                .email("sekolah@xyz.com")
                .noHp("08198765432")
                .logo(null)
                .build();

        authRequest = AuthRequest.builder()
                .username("12345678")
                .password("password")
                .build();

        jwtClaims = JwtClaims.builder()
                .accountId("1")
                .roles(Collections.singletonList("ROLE_ADMIN"))
                .build();
    }

    @Test
    void createSekolahTest() {
        when(imageService.create(any())).thenReturn(new Image());
        when(authService.register(any(AuthRequest.class))).thenReturn(new Account());
        when(httpServletRequest.getHeader(anyString())).thenReturn("Bearer token");
        when(jwtService.getClaimsByToken(anyString())).thenReturn(jwtClaims);
        when(accountService.getByAccountId(anyString())).thenReturn(new Account());
        when(sekolahRepository.saveAndFlush(any(Sekolah.class))).thenReturn(sekolah);

        Sekolah createdSekolah = sekolahService.createSekolah(sekolahRequest);

        assertNotNull(createdSekolah);
        assertEquals(sekolah.getSekolah(), createdSekolah.getSekolah());
    }

    @Test
    void getByNpsnTest() {
        when(httpServletRequest.getHeader(anyString())).thenReturn("Bearer token");
        when(jwtService.getClaimsByToken(anyString())).thenReturn(jwtClaims);
        when(sekolahRepository.findByNpsn(anyString())).thenReturn(Optional.of(sekolah));

        Sekolah foundSekolah = sekolahService.getByNpsn("12345678");

        assertNotNull(foundSekolah);
        assertEquals(sekolah.getNpsn(), foundSekolah.getNpsn());
    }

    @Test
    void updateSekolahTest() {
        when(sekolahRepository.findById(anyString())).thenReturn(Optional.of(sekolah));
        when(sekolahRepository.saveAndFlush(any(Sekolah.class))).thenReturn(sekolah);

        Sekolah updatedSekolah = sekolahService.update(updateSekolahRequest);

        assertNotNull(updatedSekolah);
        assertEquals(updateSekolahRequest.getSekolah(), updatedSekolah.getSekolah());
        assertEquals(updateSekolahRequest.getEmail(), updatedSekolah.getEmail());
        assertEquals(updateSekolahRequest.getNoHp(), updatedSekolah.getNoHp());
    }

    @Test
    void deleteSekolahTest() {
        when(sekolahRepository.findById(anyString())).thenReturn(Optional.of(sekolah));

        sekolahService.delete("1");

        verify(sekolahRepository, times(1)).saveAndFlush(sekolah);
        assertTrue(sekolah.getIsDeleted());
    }

    @Test
    void getAllSekolahTest() {
        Page<Sekolah> sekolahPage = new PageImpl<>(Collections.singletonList(sekolah));
        when(sekolahRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(sekolahPage);

        SearchSekolahRequest request = new SearchSekolahRequest();
        request.setPage(1);
        request.setSize(10);
        request.setSortBy("sekolah");
        request.setDirection("ASC");

        Page<Sekolah> result = sekolahService.getAllSekolah(request);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }
}
