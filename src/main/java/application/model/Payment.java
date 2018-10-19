/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType type;
    
    @Column(name = "order", nullable = false)
    private Integer order;
    
    @Column(name = "date", nullable = false)
    private Date date;
    
    @Column(name = "amount", nullable = false)
    private Double amount;
    
    @Column(name = "is_collected", columnDefinition = "boolean default true", nullable = false)
    private Boolean isCollected;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registration_id")
    private Registration registration;
    
    
}
