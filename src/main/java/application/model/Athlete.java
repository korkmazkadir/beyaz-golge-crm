package application.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Column(name = "bloodType", length = 5)
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

    @Column(name = "email", length = 25)
    private String email;

    @Column(name = "city", length = 25)
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

    ///////////////////////////////////////////
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

    protected Athlete() {
    }

}
