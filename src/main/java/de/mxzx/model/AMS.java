package de.mxzx.model;


import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter
public class AMS {

    @NaturalId
    private String id;
    @Id
    private String ownerId;
    private long spawner;
    private long coins;
    private long totalCoins;
    private long crates;
    private int nextCoolingTick;
    private int loadLoad;
    private int ticksToIncrease;
    private int ticksWithCooling;
    private boolean isCoolingDown;
    @ElementCollection
    private List<String> friends;
  @ElementCollection
  @Enumerated(EnumType.STRING)
    private List<AMSUpgrade> upgrades;
    // private final int slot_info = 13;
    //  private final int slot_collect = 11;
    // private final int slot_spawner = 20;
    // private final int slot_upgrades = 24;
    //private final int slot_friends = 15;


}
