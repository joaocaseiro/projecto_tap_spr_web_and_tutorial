package com.grupoatwork.celebrities.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupoatwork.celebrities.model.Address;
import com.grupoatwork.celebrities.model.Parish;


public interface AddressDao extends JpaRepository<Address, Long> {
	List<Address> findAllByParish(Parish parish);

	Address findById(long id);
}
