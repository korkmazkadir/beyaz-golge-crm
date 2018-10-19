package application.model;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "athlete")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "tc_id_number", length = 11)
    private String idNumber;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "surname", length = 50)
    private String surname;

    @Column(name = "birth_date")
    private Date birthDate;
    
    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "bloodType", length = 10)
    private String bloodType ;
    
    @Column(name = "mother_name", length = 100)
    private String motherNameSurname;

    @Column(name = "father_name", length = 100)
    private String fatherNameSurname;

    @Column(name = "mother_phone", length = 25)
    private String motherPhoneNumber;

    @Column(name = "father_phone", length = 25)
    private String fatherPhoneNumber;

    @Column(name = "phone_number", length = 25)
    private String phoneNumber;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "size", length = 5)
    private String size;
    
    @Column(name = "jersey_number", length = 5)
    private String jerseyNumber;
    
    @Column(name = "school", length = 100)
    private String school;
    
    
    @Column(name = "club", length = 100)
    private String club;
    
    @Column(name = "agency", length = 100)
    private String agency;
    
    ///////////////////////////////////////////////
    @Column(name = "protector_relationship", length = 50)
    private String protectorRelationship;

    @Column(name = "protector_name_surname", length = 50)
    private String protectorNameSurname;

    @Column(name = "protector_phone_number", length = 25)
    private String protectorPhoneNumber;

    @Column(name = "protector_mobile_phone_number", length = 25)
    private String protectorMobilePhoneNumber;

    @Column(name = "protector_email", length = 50)
    private String protectorEmail;

    ////////////////////////////////////////////////////////////////////////////
    @Column(name = "emergency_contact_relationship", length = 50)
    private String emergencyContactRelationship;

    @Column(name = "emergency_contact_name_surname", length = 100)
    private String emergencyContactNameSurname;

    @Column(name = "emergency_contact_phone_number", length = 25)
    private String emergencyContactPhoneNumber;

    @Column(name = "emergency_contact_mobile_phone_number", length = 25)
    private String emergencyContactMobilePhoneNumber;

    @Column(name = "emergency_contact_email", length = 50)
    private String emergencyContactEmail;

    ////////////////////////////////////////////////////////////////////////////
    
    @OneToMany(
        mappedBy = "athlete", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
    private List<Registration> registrations = new LinkedList<>();
    
    public Athlete() {
    }

    public Athlete(Long id, String idNumber, String name, String surname, Date birthDate, String gender, String bloodType, String motherNameSurname, String fatherNameSurname, String motherPhoneNumber, String fatherPhoneNumber, String phoneNumber, String email, String city, String size, String jerseyNumber, String school, String club, String agency) {
        this.id = id;
        this.idNumber = idNumber;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.bloodType = bloodType;
        this.motherNameSurname = motherNameSurname;
        this.fatherNameSurname = fatherNameSurname;
        this.motherPhoneNumber = motherPhoneNumber;
        this.fatherPhoneNumber = fatherPhoneNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.size = size;
        this.jerseyNumber = jerseyNumber;
        this.school = school;
        this.club = club;
        this.agency = agency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getMotherNameSurname() {
        return motherNameSurname;
    }

    public void setMotherNameSurname(String motherNameSurname) {
        this.motherNameSurname = motherNameSurname;
    }

    public String getFatherNameSurname() {
        return fatherNameSurname;
    }

    public void setFatherNameSurname(String fatherNameSurname) {
        this.fatherNameSurname = fatherNameSurname;
    }

    public String getMotherPhoneNumber() {
        return motherPhoneNumber;
    }

    public void setMotherPhoneNumber(String motherPhoneNumber) {
        this.motherPhoneNumber = motherPhoneNumber;
    }

    public String getFatherPhoneNumber() {
        return fatherPhoneNumber;
    }

    public void setFatherPhoneNumber(String fatherPhoneNumber) {
        this.fatherPhoneNumber = fatherPhoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(String jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getProtectorRelationship() {
        return protectorRelationship;
    }

    public void setProtectorRelationship(String protectorRelationship) {
        this.protectorRelationship = protectorRelationship;
    }

    public String getProtectorNameSurname() {
        return protectorNameSurname;
    }

    public void setProtectorNameSurname(String protectorNameSurname) {
        this.protectorNameSurname = protectorNameSurname;
    }

    public String getProtectorPhoneNumber() {
        return protectorPhoneNumber;
    }

    public void setProtectorPhoneNumber(String protectorPhoneNumber) {
        this.protectorPhoneNumber = protectorPhoneNumber;
    }

    public String getProtectorMobilePhoneNumber() {
        return protectorMobilePhoneNumber;
    }

    public void setProtectorMobilePhoneNumber(String protectorMobilePhoneNumber) {
        this.protectorMobilePhoneNumber = protectorMobilePhoneNumber;
    }

    public String getProtectorEmail() {
        return protectorEmail;
    }

    public void setProtectorEmail(String protectorEmail) {
        this.protectorEmail = protectorEmail;
    }

    public String getEmergencyContactRelationship() {
        return emergencyContactRelationship;
    }

    public void setEmergencyContactRelationship(String emergencyContactRelationship) {
        this.emergencyContactRelationship = emergencyContactRelationship;
    }

    public String getEmergencyContactNameSurname() {
        return emergencyContactNameSurname;
    }

    public void setEmergencyContactNameSurname(String emergencyContactNameSurname) {
        this.emergencyContactNameSurname = emergencyContactNameSurname;
    }

    public String getEmergencyContactPhoneNumber() {
        return emergencyContactPhoneNumber;
    }

    public void setEmergencyContactPhoneNumber(String emergencyContactPhoneNumber) {
        this.emergencyContactPhoneNumber = emergencyContactPhoneNumber;
    }

    public String getEmergencyContactMobilePhoneNumber() {
        return emergencyContactMobilePhoneNumber;
    }

    public void setEmergencyContactMobilePhoneNumber(String emergencyContactMobilePhoneNumber) {
        this.emergencyContactMobilePhoneNumber = emergencyContactMobilePhoneNumber;
    }

    public String getEmergencyContactEmail() {
        return emergencyContactEmail;
    }

    public void setEmergencyContactEmail(String emergencyContactEmail) {
        this.emergencyContactEmail = emergencyContactEmail;
    }
    
    public void addRegistration(Registration registration) {
        registrations.add(registration);
        registration.setAthlete(this);
    }
 
    public void removeRegistration(Registration registration) {
        registrations.remove(registration);
        registration.setAthlete(null);
    }

    @Override
    public String toString() {
        return "Athlete{" + "id=" + id + ", idNumber=" + idNumber + ", name=" + name + ", surname=" + surname + ", birthDate=" + birthDate + ", gender=" + gender + ", bloodType=" + bloodType + ", motherNameSurname=" + motherNameSurname + ", fatherNameSurname=" + fatherNameSurname + ", motherPhoneNumber=" + motherPhoneNumber + ", fatherPhoneNumber=" + fatherPhoneNumber + ", phoneNumber=" + phoneNumber + ", email=" + email + ", city=" + city + ", size=" + size + ", jerseyNumber=" + jerseyNumber + ", school=" + school + ", club=" + club + ", agency=" + agency + ", protectorRelationship=" + protectorRelationship + ", protectorNameSurname=" + protectorNameSurname + ", protectorPhoneNumber=" + protectorPhoneNumber + ", protectorMobilePhoneNumber=" + protectorMobilePhoneNumber + ", protectorEmail=" + protectorEmail + ", emergencyContactRelationship=" + emergencyContactRelationship + ", emergencyContactNameSurname=" + emergencyContactNameSurname + ", emergencyContactPhoneNumber=" + emergencyContactPhoneNumber + ", emergencyContactMobilePhoneNumber=" + emergencyContactMobilePhoneNumber + ", emergencyContactEmail=" + emergencyContactEmail + ", registrations=" + registrations + '}';
    }
    
    
    
}
