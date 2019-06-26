package com.lambdaschool.tripsplit.services;

import com.lambdaschool.tripsplit.models.Bill;
import com.lambdaschool.tripsplit.models.Trip;
import com.lambdaschool.tripsplit.models.User;
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
@Service(value = "billService")
public class BillServiceImpl implements BillService
{
    @Autowired
    private BillRepository billRepos;

    @Autowired
    private TripRepository tripRepos;

    @Autowired
    private UserRepository userRepos;

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
    public Bill save(Bill bill,long tripid, long userid)
    {
        Bill newBill = new Bill();
        User user = userRepos.findById(userid)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(userid)));
        Trip trip = tripRepos.findById(tripid)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(tripid)));

        newBill.setBillTitle(bill.getBillTitle());
        newBill.setBillAmount(bill.getBillAmount());
        newBill.setPaidBy(user);
        newBill.setTrip(trip);

        return billRepos.save(newBill);
    }

//    @Override
//    public Bill save2(Bill bill)
//    {
//        Bill newBill = new Bill();
//
////        newBill.setBillTitle(bill.getBillTitle());
//////        newBill.setBillAmount(bill.getBillAmount());
//////        newBill.setTrip(bill.getTrip());
//////
//////        for(User u : bill.getPaidBy())
//
//        return billRepos.save(newBill);
//    }

    @Transactional
    @Override
    public Bill update(Bill bill, long id)
    {
        return null;
    }
}
