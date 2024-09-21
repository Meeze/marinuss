package de.mxzx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Punishment {


	@NaturalId
	private String issuerId;
	@NaturalId
	private String punishedId;
	@Id
	private String banId;
	private String reason;
	private long timestamp;
	//-1 = perm
	private long until;
	@Enumerated(EnumType.STRING)
	private PunishmentType type;


}
