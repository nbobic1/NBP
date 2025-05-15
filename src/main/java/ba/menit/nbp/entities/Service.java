package ba.menit.nbp.entities;

import jakarta.persistence.*;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    private Integer durationInMin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDurationInMin() {
        return durationInMin;
    }

    public void setDurationInMin(Integer durationInMin) {
        this.durationInMin = durationInMin;
    }

    public Service() {
    }

    public Service(String name, Double price, Integer durationInMin) {
        this.name = name;
        this.price = price;
        this.durationInMin = durationInMin;
    }

    public Service(Long id, String name, Double price, Integer durationInMin) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.durationInMin = durationInMin;
    }
}
