package com.lambdaschool.tripsplit.services;

import com.lambdaschool.tripsplit.models.Trip;

import java.util.List;

public interface TripService
{
    List<Trip> findAll(String username);

    Trip findByTripId(long id);

    void delete(long id);

    Trip save(Trip trip,String username);

    Trip update(Trip trip, long id);

//    List<Trip> userTrips (String username);

    void addUsersToTrips(String username, long tripid);

    void completeTrip(long tripid);

}
