package com.lambdaschool.tripsplit.services;

import com.lambdaschool.tripsplit.models.Trip;
import com.lambdaschool.tripsplit.repository.BillRepository;
import com.lambdaschool.tripsplit.repository.TripRepository;
import com.lambdaschool.tripsplit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "tripService")
public class TripServiceImpl implements TripService
{
    @Autowired
    private TripRepository tripRepos;

    @Autowired
    private BillRepository billRepos;

    @Autowired
    private UserRepository userRepos;

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
    public Trip save(Trip trip,long userid)
    {
       Trip newTrip = new Trip();

       newTrip.setTripname(trip.getTripname());
       newTrip.setStartDate(trip.getStartDate());
       newTrip.setEndDate(trip.getEndDate());
       newTrip.setUsers(trip.getUsers());
       newTrip.setMadeByWhom(userid);

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

        if(trip.getMadeByWhom() != null)
        {
            currentTrip.setMadeByWhom(trip.getMadeByWhom());
        }

//        if(trip.getBills().size() > 0)
//        {
//            billRepos.deleteById(currentTrip.getTripid());
//        }

        return tripRepos.save(currentTrip);
    }
}
