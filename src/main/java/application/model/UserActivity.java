package application.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_activity")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "activity_name", length = 100)
    private String activityName;

    @Column(name = "activity_time", length = 100)
    private Timestamp activityTime;

    protected UserActivity() {
    }

    public UserActivity(String activityName, Timestamp activityTime) {
        this.activityName = activityName;
        this.activityTime = activityTime;
    }

    public UserActivity(Long id, String activityName, Timestamp activityTime) {
        this.id = id;
        this.activityName = activityName;
        this.activityTime = activityTime;
    }

    public Long getId() {
        return id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Timestamp getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Timestamp activityTime) {
        this.activityTime = activityTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserActivity{" + "id=" + id + ", activityName=" + activityName + ", activityTime=" + activityTime + '}';
    }

}
