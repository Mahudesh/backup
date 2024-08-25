package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Modal.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer>
{
	Owner findByOwnerEmail(String ownerEmail);
}