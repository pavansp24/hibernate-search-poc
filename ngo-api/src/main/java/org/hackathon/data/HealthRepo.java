package org.hackathon.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRepo extends JpaRepository<Health, String> {

}
