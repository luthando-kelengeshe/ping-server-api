package com.luthando.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luthando.server.model.Server;

public interface ServerRepo extends JpaRepository<Server, Long>{
	Server findByIpAddress(String ipAddress);
}
