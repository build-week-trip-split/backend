package com.lambdaschool.tripsplit.repository;

import com.lambdaschool.tripsplit.models.Trip;
import com.lambdaschool.tripsplit.view.Tripid;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TripRepository extends CrudRepository<Trip, Long>
{

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO usertrips (userid, tripid) values (:userid, :tripid)",nativeQuery = true)
    void insertIntoUserTrips(long tripid, long userid);

    @Transactional
    @Modifying
    @Query(value = "SELECT * from trip where made_by_whom = :userid",nativeQuery = true)
    List<Trip> findTripByUserId(long userid);

}

