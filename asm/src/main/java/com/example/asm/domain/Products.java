package com.example.asm.domain;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.ToString.Exclude;

@Data
@Entity
@Table(name = "product")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 50, nullable = false)

    private int Id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 1000, nullable = false)
    private String image;

    @Column(length = 200, nullable = false)
    private double price;

    @Temporal(TemporalType.TIMESTAMP)
    // @Column(nullable = false)
    private Date createDate;

    @PrePersist
    private void createAt() {
        createDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
      createDate = new Date();
    }

    @Column(length = 200, nullable = false)
    private boolean available;

    @Column(length = 1000, nullable = false)
    private String description;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;



    @OneToMany(mappedBy = "product")
    Set<OrderDetail> orderDetails;


    // @OneToMany(mappedBy = "products", cascade = { CascadeType.ALL })
    // Set<> accounts;

    // @ManyToMany(mappedBy = "products")
    // @EqualsAndHashCode.Exclude
    // @Exclude
    // private Collection<OrderDetails> orderDetails;

}
