package com.ibm.proposal.cart.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String proposalId;
    @Column
    private String productId;
    @Column
    private String serial;
    @Column
    private String installDate;
    @Column
    private String warrantyEndDate;
    @Column
    private String installAt;
    @Column
    private String installAtSite;
    @Column
    private String addLocationBasedServices;
}
