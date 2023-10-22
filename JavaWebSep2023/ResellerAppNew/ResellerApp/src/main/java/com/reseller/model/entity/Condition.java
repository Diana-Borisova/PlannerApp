package com.reseller.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private ConditionEnum conditionEnum;

    @Column(nullable = false)
    private String description;

    public Condition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConditionEnum getConditionEnum() {
        return conditionEnum;
    }

    public void setConditionEnum(ConditionEnum conditionEnum) {
        this.conditionEnum = conditionEnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
