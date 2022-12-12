package ru.mipt1c.homework.task2.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "NumberEntity")
@NoArgsConstructor
public class NumberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "key")
    private Integer key;

    @Column(name = "value")
    private Double value;

    public NumberEntity(Integer key, Double value) {
        this.key = key;
        this.value = value;
    }
}
