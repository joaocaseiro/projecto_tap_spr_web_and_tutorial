package com.grupoatwork.celebrity.dao;

import java.util.List;

import com.grupoatwork.celebrity.entities.Address;

public interface AddressDao {
	public List<Address> getAddresses();

	public Address read(Long id);

	public Address save(Address address);

	public void delete(Long id);

	public void delete(Address address);
}