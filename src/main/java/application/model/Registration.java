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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "camp_id")
    private Camp camp;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    @NotNull
    private RegistrationType registrationType;

    @Column(name = "pre_registration_date")
    private Date preRegistrationDate;

    @Column(name = "pre_registration_price_offer")
    private int preRegistrationPriceOffer;

    @Column(name = "pre_regisration_note", length = 300)
    private String preRegistrationNote;

    protected Registration() {
    }

    public Long getId() {
        return id;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(RegistrationType registrationType) {
        this.registrationType = registrationType;
    }

    public Date getPreRegistrationDate() {
        return preRegistrationDate;
    }

    public void setPreRegistrationDate(Date preRegistrationDate) {
        this.preRegistrationDate = preRegistrationDate;
    }

    public int getPreRegistrationPriceOffer() {
        return preRegistrationPriceOffer;
    }

    public void setPreRegistrationPriceOffer(int preRegisrationPriceOffer) {
        this.preRegistrationPriceOffer = preRegisrationPriceOffer;
    }

    public String getPreRegistrationNote() {
        return preRegistrationNote;
    }

    public void setPreRegistrationNote(String preRegistrationNote) {
        this.preRegistrationNote = preRegistrationNote;
    }

}
