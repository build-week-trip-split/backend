package com.lambdaschool.tripsplit.services;

import com.lambdaschool.tripsplit.models.Bill;
import com.lambdaschool.tripsplit.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
    public Bill findBillById(long id) throws EntityNotFoundException
    {
        return billRepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (billRepos.findById(id).isPresent())
        {
            billRepos.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Bill save(Bill bill)
    {
        Bill newBill = new Bill();

        newBill.setBillTitle(bill.getBillTitle());
        newBill.setBillAmount(bill.getBillAmount());
        newBill.setPaidby(bill.getPaidby());
        newBill.setTrip(bill.getTrip());

        return billRepos.save(newBill);
    }

    @Transactional
    @Override
    public Bill update(Bill bill, long id)
    {
        return null;
    }
}
