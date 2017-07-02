package org.hackathon.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="TBL_NGO_HEALTH")
public class Health {

	@Id
	@Column(name="version")
	private String version;
	
	@Column(name="name")
	private String name;

	public Health() {
		// TODO Auto-generated constructor stub
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
