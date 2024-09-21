package de.mxzx.model;

import de.mxzx.converter.LocationConverter;
import lombok.*;
import org.bukkit.Location;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Warp {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    private String id;
    @NaturalId
    @Convert(converter = LocationConverter.class)
    @Lob
    private Location location;
    private String name;
    private String world;
    private int x;
    private int y;
    private int z;
    private float pitch;
    private float yaw;
}
