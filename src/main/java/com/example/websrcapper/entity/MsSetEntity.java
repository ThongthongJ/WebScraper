package com.example.websrcapper.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ms_set")
@Data
public class MsSetEntity {
    @Id
    private String symbol;

}
