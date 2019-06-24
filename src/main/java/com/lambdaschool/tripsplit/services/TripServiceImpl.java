package com.lambdaschool.tripsplit.services;

import com.lambdaschool.tripsplit.models.Trip;
import com.lambdaschool.tripsplit.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TripServiceImpl implements TripService
{
    @Autowired
    private TripRepository tripRepos;

    @Override
    public List<Trip> findAll()
    {
        List<Trip> list = new ArrayList<>();
        tripRepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Trip findByTripId(long id)
    {
        return null;
    }

    @Override
    public void delete(long id)
    {

    }

    @Override
    public Trip save(Trip trip)
    {
        return null;
    }

    @Override
    public Trip update(Trip trip, long id)
    {
        return null;
    }
}
