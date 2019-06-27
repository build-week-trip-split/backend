package com.lambdaschool.tripsplit.repository;

import com.lambdaschool.tripsplit.models.Bill;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Long>
{
    @Transactional
    @Modifying
    @Query(value = "SELECT * from bill where tripid = :tripid",nativeQuery = true)
    List<Bill> findBillsbyTripId(long tripid);
}
