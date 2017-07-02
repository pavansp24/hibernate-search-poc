package org.hackathon.service;

import org.hackathon.data.Health;
import org.hackathon.data.HealthRepo;
import org.hackathon.ngo.OrikaBeanMapper;
import org.hackathon.ngo.pojo.HealthStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

	@Autowired
	private HealthRepo repo;
	
	@Autowired
	private OrikaBeanMapper mapper;
	
	public HealthStatus getStatus() {
		Health health = repo.findAll().get(0);
		return mapper.map(health, HealthStatus.class);
	}
}
