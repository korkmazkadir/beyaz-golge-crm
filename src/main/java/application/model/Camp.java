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
@Table(name = "camp")
public class Camp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "place", length = 100)
    private String place;

    @Column(name = "start_date", length = 100)
    private Date startDate;

    @Column(name = "end_date", length = 100)
    private Date endDate;

    @OneToMany(
            mappedBy = "camp",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Registration> registrations = new LinkedList<>();

    protected Camp() {
    }

    public Camp(String name, String place, Date startDate, Date endDate) {
        this.name = name;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Camp(Long id, String name, String place, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Camp{" + "id=" + id + ", name=" + name + ", place=" + place + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }

    public void addRegistration(Registration registration) {
        registrations.add(registration);
        registration.setCamp(this);
    }

    public void removeRegistration(Registration registration) {
        registrations.remove(registration);
        registration.setCamp(null);
    }

}
