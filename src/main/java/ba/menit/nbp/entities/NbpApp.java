package ba.menit.nbp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "nbp_apps")
public class NbpApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appId;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    private LocalDate expiryDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public NbpApp() {
    }

    public NbpApp(String appId, User manager, LocalDate expiryDate) {
        this.appId = appId;
        this.manager = manager;
        this.expiryDate = expiryDate;
    }

    public NbpApp(Long id, String appId, User manager, LocalDate expiryDate) {
        this.id = id;
        this.appId = appId;
        this.manager = manager;
        this.expiryDate = expiryDate;
    }
}