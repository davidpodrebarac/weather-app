package apis.weatherapp.threading.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ThreadControl {
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;
    private String message;
    private Boolean commandStart;

    public ThreadControl() {
    }

    public ThreadControl(Boolean commandStart, String message) {
        this.commandStart = commandStart;
        this.message = message;
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    public Boolean getCommandStart() {
        return commandStart;
    }

    public void setCommandStart(Boolean commandStart) {
        this.commandStart = commandStart;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }
}
