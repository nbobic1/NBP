package ba.menit.nbp.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "nbp_log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String actionName;

    @Column(nullable = false)
    private String tableName;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    private String dbUser;
}
