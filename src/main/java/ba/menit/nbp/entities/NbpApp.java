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
}