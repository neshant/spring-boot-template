package com.example.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "investor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Investor {

    @ManyToOne()
    @JsonIgnore // this ignores returning client as this is null because there is no reverse mapping in client to
            Client client;

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    UUID id;
    String name;
    String description;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = Fund.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "investor_id")
    List<Fund> fund;

    @Column(updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Investor investor = (Investor) o;
        return Objects.equals(id, investor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
