package application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
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

    @OneToMany(
            mappedBy = "registration",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnoreProperties("registration")
    private List<Meeting> meetings = new LinkedList<>();

    protected Registration() {
    }

    public Registration(Long id) {
        this.id = id;
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

    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
        meeting.setRegistration(this);
    }

    public void removeMeeting(Meeting meeting) {
        meetings.remove(meeting);
        meeting.setRegistration(null);
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

}
