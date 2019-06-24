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

    private long Paidby;

    @ManyToOne
    @JoinColumn(name = "tripid")
    @JsonIgnoreProperties("bills")
    private Trip trip;

    public Bill()
    {
    }

    public Bill(String billTitle, double billAmount, long paidby, Trip trip)
    {
        this.billTitle = billTitle;
        this.billAmount = billAmount;
        Paidby = paidby;
        this.trip = trip;
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

    public long getPaidby()
    {
        return Paidby;
    }

    public void setPaidby(long paidby)
    {
        Paidby = paidby;
    }

    public Trip getTrip()
    {
        return trip;
    }

    public void setTrip(Trip trip)
    {
        this.trip = trip;
    }
}
