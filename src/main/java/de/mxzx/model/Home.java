package de.mxzx.model;

import de.mxzx.converter.LocationConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.Location;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Home {

	@Id
	private String name;
	@NaturalId
	private String uuid;
	@Convert(converter = LocationConverter.class)
	@Lob
	private Location loc;


	
}
