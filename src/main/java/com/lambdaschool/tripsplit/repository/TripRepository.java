package com.lambdaschool.tripsplit.repository;

import com.lambdaschool.tripsplit.models.Trip;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Long>
{
}
