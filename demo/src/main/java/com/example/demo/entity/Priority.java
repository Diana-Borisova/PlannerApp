package com.example.demo.entity;

import com.example.demo.entity.enums.PriorityEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity{
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private PriorityEnum name;

	@Column(nullable = false)
    private String description;






}
