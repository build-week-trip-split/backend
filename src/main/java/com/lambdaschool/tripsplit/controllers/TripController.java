package com.lambdaschool.tripsplit.controllers;

import com.lambdaschool.tripsplit.models.ErrorDetail;
import com.lambdaschool.tripsplit.models.Trip;
import com.lambdaschool.tripsplit.services.TripService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController
{
    @Autowired
    private TripService tripService;


    @ApiOperation(value = "return all trips", response = Trip.class, responseContainer = "List")
    @GetMapping(value = "/trips/{username}",
            produces = {"application/json"})
    public ResponseEntity<?> listAllTrips(@PathVariable String username)
    {
        List<Trip> myTrips = tripService.findAll(username);
        return new ResponseEntity<>(myTrips, HttpStatus.OK);
    }

    ////////////////////////////////////////////////////////////////////


    @ApiOperation(value = "Retrieves a trip associated with the tripid", response = Trip.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Trip Found", response = Trip.class),
            @ApiResponse(code = 404, message = "Trip Not Found", response = Trip.class)
    })
    @GetMapping(value = "/trip/{tripId}",
            produces = {"application/json"})
    public ResponseEntity<?> getTripById(@ApiParam(value = "Trip id", required = true, example = "1")
                                               @PathVariable
                                                       Long tripId)
    {
        Trip r = tripService.findByTripId(tripId);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }


    ///////////////////////////////////////////////////////////////////////////////



    @ApiOperation(value = "Creates a new Trip", notes = "The newly created Trip id will be sent in the location header", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Trip Created Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error creating Trip", response = ErrorDetail.class)
    })
    @PostMapping(value = "/trip/{username}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewTrip(@Valid
                                              @RequestBody
                                                      Trip newTrip, @PathVariable String username) throws URISyntaxException
    {
        newTrip = tripService.save(newTrip,username);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTripURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{tripid}").buildAndExpand(newTrip.getTripid()).toUri();
        responseHeaders.setLocation(newTripURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }



    ///////////////////////////////////////////////////////////////////////////////////////


    @PutMapping(value = "/trip/{tripid}")
    public ResponseEntity<?> updateTrip(
            @RequestBody
                    Trip updateTrip,
            @PathVariable
                    long tripid)
    {
        tripService.update(updateTrip, tripid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    @DeleteMapping("/trip/{tripid}")
    public ResponseEntity<?> deleteTripById(
            @PathVariable
                    long tripid)
    {
        tripService.delete(tripid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @ApiOperation(value = "Adds users to a trip", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User added", response = Trip.class),
            @ApiResponse(code = 500, message = "User was not added", response = Trip.class)
    })
    @PostMapping(value = "/trip/adduser/{tripid}/{username}")
    public ResponseEntity<?> addUserToTrip(
                                         @PathVariable
                                                 long tripid, @PathVariable String username)
    {
       tripService.addUsersToTrips(username,tripid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    ////////////////////////////////////////////////////////////////////////



    @PutMapping(value = "/trip/completed/{tripid}")
    public ResponseEntity<?> completeTrip(@PathVariable long tripid)
    {
        tripService.completeTrip(tripid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
