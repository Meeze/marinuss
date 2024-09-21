package de.mxzx.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) charon, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by charon
 **/
@Getter
@Entity
@Data
@NoArgsConstructor
public class Season {


    @Id
    private String uuidPlayer;
    @ElementCollection
    private List<Milestones> activeMilestones;
    @ElementCollection
    private List<Milestones> completedMilestones;
    @ElementCollection
    private List<Milestones> milestones;

}
