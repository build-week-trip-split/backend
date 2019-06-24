package com.lambdaschool.tripsplit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trip")
public class Trip
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long tripid;

    private String tripname;

    private String startDate;

    private String endDate;

    private boolean completed;

    private long createdby;


    @OneToMany(mappedBy = "trip",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    @JsonIgnoreProperties("trip")
    private List<Bill> bills = new ArrayList<>();

    @ManyToMany(mappedBy = "trips")
    @JsonIgnoreProperties("users")
    private List<User> users = new ArrayList<>();

    public Trip()
    {
    }

    public Trip(String tripname, String startDate, String endDate, long createdby)
    {
        this.tripname = tripname;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdby = createdby;
        this.completed = false;
    }

    public long getTripid()
    {
        return tripid;
    }

    public void setTripid(long tripid)
    {
        this.tripid = tripid;
    }

    public String getTripname()
    {
        return tripname;
    }

    public void setTripname(String tripname)
    {
        this.tripname = tripname;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    public long getCreatedby()
    {
        return createdby;
    }

    public void setCreatedby(long createdby)
    {
        this.createdby = createdby;
    }

    public List<Bill> getBills()
    {
        return bills;
    }

    public void setBills(List<Bill> bills)
    {
        this.bills = bills;
    }

    public List<User> getUserss()
    {
        return users;
    }

    public void setUserss(List<User> users)
    {
        this.users = users;
    }
}
