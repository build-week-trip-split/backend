package com.lambdaschool.tripsplit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "bill")
public class Bill
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long billid;

    private String billTitle;

    private double billAmount;


    @ManyToOne
    @JoinColumn(name = "tripid")
    @JsonIgnoreProperties("bills")
    private Trip trip;



    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("bills")
    private User paidBy;

    public Bill()
    {
    }

    public Bill(String billTitle, double billAmount, Trip trip, User paidBy)
    {
        this.billTitle = billTitle;
        this.billAmount = billAmount;
        this.trip = trip;
        this.paidBy = paidBy;
    }

    public long getBillid()
    {
        return billid;
    }

    public void setBillid(long billid)
    {
        this.billid = billid;
    }

    public String getBillTitle()
    {
        return billTitle;
    }

    public void setBillTitle(String billTitle)
    {
        this.billTitle = billTitle;
    }

    public double getBillAmount()
    {
        return billAmount;
    }

    public void setBillAmount(double billAmount)
    {
        this.billAmount = billAmount;
    }

    public Trip getTrip()
    {
        return trip;
    }

    public void setTrip(Trip trip)
    {
        this.trip = trip;
    }

    public User getPaidBy()
    {
        return paidBy;
    }

    public void setPaidBy(User paidBy)
    {
        this.paidBy = paidBy;
    }
}
