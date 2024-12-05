package com.chamdev.museumproject.model;

import com.chamdev.museumproject.utils.enums.CategoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private CategoryType name;

    @OneToMany(mappedBy = "category")
    private List<MuseumObject> museumObjects;

    public Category(CategoryType name) {
        this.name = name;
    }
}
