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
    @Query(value = "SELECT usertrips.tripid FROM users join usertrips on usertrips.userid = users.userid where users.username = :username",nativeQuery = true)
    List<Tripid> findTripsByUsername ( String username);
}
   // SELECT usertrips.tripid FROM users join usertrips on usertrips.userid = users.userid where users.username