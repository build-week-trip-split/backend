package com.lambdaschool.tripsplit.services;

import com.lambdaschool.tripsplit.models.Trip;
import com.lambdaschool.tripsplit.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
    public Trip findByTripId(long id) throws EntityNotFoundException
    {
        return tripRepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (tripRepos.findById(id).isPresent())
        {
            tripRepos.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Trip save(Trip trip)
    {
       Trip newTrip = new Trip();

       newTrip.setTripname(trip.getTripname());
       newTrip.setStartDate(trip.getStartDate());
       newTrip.setEndDate(trip.getEndDate());
       newTrip.setCreatedby(trip.getCreatedby());

       return  tripRepos.save(newTrip);
    }

    @Transactional
    @Override
    public Trip update(Trip trip, long id)
    {
        Trip currentTrip = tripRepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if(trip.getTripname() != null)
        {
            currentTrip.setTripname(trip.getTripname());
        }

        if(trip.getStartDate() != null)
        {
            currentTrip.setStartDate(trip.getStartDate());
        }

        if(trip.getEndDate() != null)
        {
            currentTrip.setEndDate(trip.getEndDate());
        }

        if(trip.getCreatedby() != )
        {
            currentTrip.setCreatedby(trip.getCreatedby());
        }
    }
}
