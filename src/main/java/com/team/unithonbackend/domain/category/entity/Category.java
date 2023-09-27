package com.team.unithonbackend.domain.category.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "category_id")
    private int id;

    @Column(name = "category_name")
    private String name;

    @Column(name = "category_s3_name")
    private String s3_name;

    @Builder
    public Category(int id, String name, String s3_name) {
        this.id = id;
        this.name = name;
        this.s3_name = s3_name;
    }
}
