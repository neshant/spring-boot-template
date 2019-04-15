package com.example.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "fund")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fund {

    @ManyToOne()
    @JsonIgnore
    Investor investor;

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @JsonProperty("fund_id")
    UUID id;
    String name;
    String description;
    Integer price;
    @Column(updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;
}
