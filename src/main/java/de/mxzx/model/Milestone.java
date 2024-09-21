package de.mxzx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Copyright (c) charon, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by charon
 **/
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Milestone {

    @Id
    private String name;
    @ElementCollection
    private List<String> description;
    private int requirement;
    private int slots;


}
