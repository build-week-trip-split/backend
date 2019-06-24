package com.lambdaschool.tripsplit.repository;

import com.lambdaschool.tripsplit.models.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long>
{
}
