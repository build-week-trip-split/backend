package com.lambdaschool.tripsplit.services;

import com.lambdaschool.tripsplit.models.Bill;

import java.util.List;

public interface BillService
{
    List<Bill> findAll(long tripid);

    Bill findBillById(long id);

    void  delete(long id);

    Bill save( Bill bill,long tripid, String username);

//    Bill save2(Bill bill);

    Bill update(Bill bill, long id);

}
