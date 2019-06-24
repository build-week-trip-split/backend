package com.lambdaschool.tripsplit.services;

import com.lambdaschool.tripsplit.models.Bill;
import com.lambdaschool.tripsplit.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BillServiceImpl implements BillService
{
    @Autowired
    private BillRepository billRepos;

    @Override
    public List<Bill> findAll()
    {
        List<Bill> list = new ArrayList<>();
        billRepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Bill findBillById(long id)
    {
        return null;
    }

    @Override
    public void delete(long id)
    {

    }

    @Override
    public Bill save(Bill bill)
    {
        return null;
    }

    @Override
    public Bill update(Bill bill, long id)
    {
        return null;
    }
}
