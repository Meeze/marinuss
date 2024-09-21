package de.mxzx.model;

import de.mxzx.converter.LocationConverter;
import de.mxzx.converter.LocationListConverter;
import lombok.*;
import org.bukkit.Location;
import org.bukkit.Material;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mine {

    @Id
    private String name;
    @Convert(converter = LocationListConverter.class)
    @Lob
    private List<Location> locations;
    @Convert(converter = LocationListConverter.class)
    @Lob
    private List<Location> mineLocations;
    @Convert(converter = LocationConverter.class)
    @Lob
    private Location hologram;
    @ElementCollection
    private Map<Material, Integer> blocks;
    @Convert(converter = LocationConverter.class)
    @Lob
    private Location spawn;
    @Convert(converter = LocationConverter.class)
    @Lob
    private Location npcLocation;
    private int minLevel;
    private int maxLevel;
    @Transient
    private int resetTime = 300;
    @Transient
    private  int progress = 0;

}
