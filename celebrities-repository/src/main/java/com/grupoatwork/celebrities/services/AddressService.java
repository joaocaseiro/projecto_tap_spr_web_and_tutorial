package com.grupoatwork.celebrities.services;

import java.util.List;

import com.grupoatwork.celebrities.model.Address;

public interface AddressService {
	public Address save(Address address);
	Address findById(long id);
	List<Address> findAll();
}
