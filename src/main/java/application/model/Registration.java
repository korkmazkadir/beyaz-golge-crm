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

    ////////////////////////////////////////////////////////////////////////////
    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "price")
    private int price;

    @Column(name = "advance_payment")
    private int advancePayment;

    @Column(name = "number__of_installments")
    private int numberOfInstallments;

    @Column(name = "joining_date")
    private Date joiningDate;

    @Column(name = "leaving_date")
    private Date leavingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "transportaion_type")
    private TransportaionType transportaionType;

    @Column(name = "trasnportation_note", length = 300)
    private String transportationNote;

    @Column(name = "form")
    private Boolean form = false;

    @Column(name = "agreement")
    private Boolean agreement = false;

    @Column(name = "health_report")
    private Boolean healthReport = false;

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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(int advancePayment) {
        this.advancePayment = advancePayment;
    }

    public int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(int numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    public TransportaionType getTransportaionType() {
        return transportaionType;
    }

    public void setTransportaionType(TransportaionType transportaionType) {
        this.transportaionType = transportaionType;
    }

    public String getTransportationNote() {
        return transportationNote;
    }

    public void setTransportationNote(String transportationNote) {
        this.transportationNote = transportationNote;
    }

    public Boolean getForm() {
        return form;
    }

    public void setForm(Boolean form) {
        this.form = form;
    }

    public Boolean getAgreement() {
        return agreement;
    }

    public void setAgreement(Boolean agreement) {
        this.agreement = agreement;
    }

    public Boolean getHealthReport() {
        return healthReport;
    }

    public void setHealthReport(Boolean healthReport) {
        this.healthReport = healthReport;
    }

}
