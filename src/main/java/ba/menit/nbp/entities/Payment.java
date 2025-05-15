package ba.menit.nbp.entities;

import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment() {
    }

    public Payment(Double amount, User user) {
        this.amount = amount;
        this.user = user;
    }

    public Payment(Long id, Double amount, User user) {
        this.id = id;
        this.amount = amount;
        this.user = user;
    }
}
