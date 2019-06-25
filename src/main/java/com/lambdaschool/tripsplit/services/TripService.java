package com.lambdaschool.tripsplit.services;

import com.lambdaschool.tripsplit.models.Trip;

import java.util.List;

public interface TripService
{
    List<Trip> findAll();

    Trip findByTripId(long id);

    void delete(long id);

    Trip save(Trip trip,long userid);

    Trip update(Trip trip, long id);

}
