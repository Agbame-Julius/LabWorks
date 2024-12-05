package com.project.Product_Management_System.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "left_child_id")
    private Category leftChild;

    @OneToOne
    @JoinColumn(name = "right_child_id")
    private Category rightChild;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;


}
