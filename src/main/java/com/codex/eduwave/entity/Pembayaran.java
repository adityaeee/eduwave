package com.codex.eduwave.entity;

import com.codex.eduwave.constant.NameTable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = NameTable.PAYMENT)
public class Pembayaran {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "redirect_url", nullable = false)
    private String redirectUrl;

    @Column(name = "transaction_status", nullable = false)
    private String transactionStatus;
//    settlement
//    deny
//    pending
//    cancel
//    expire
//    failure

}
