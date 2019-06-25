package com.lambdaschool.tripsplit.controllers;


import com.lambdaschool.tripsplit.models.Bill;
import com.lambdaschool.tripsplit.models.ErrorDetail;
import com.lambdaschool.tripsplit.services.BillService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/bills")
public class BillController
{
    @Autowired
    private BillService billService;

    @ApiOperation(value = "return all Bills", response = Bill.class, responseContainer = "List")
    @GetMapping(value = "/bills",
            produces = {"application/json"})
    public ResponseEntity<?> listAllBills()
    {
        List<Bill> myBills = billService.findAll();
        return new ResponseEntity<>(myBills, HttpStatus.OK);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @ApiOperation(value = "Retrieves a bill associated with the billid", response = Bill.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Bill Found", response = Bill.class),
            @ApiResponse(code = 404, message = "Bill Not Found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/bill/{billId}",
            produces = {"application/json"})
    public ResponseEntity<?> getBillById(@ApiParam(value = "Bill id", required = true, example = "1")
                                               @PathVariable
                                                       Long billId)
    {
        Bill b = billService.findBillById(billId);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @ApiOperation(value = "Creates a new bill", notes = "The newly created bill id will be sent in the location header", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Bill Created Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error creating bill", response = ErrorDetail.class)
    })
    @PostMapping(value = "/bill/{tripid}/{userid}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewBill(@Valid
                                              @RequestBody
                                                      Bill newBill, @PathVariable long userid, @PathVariable long tripid) throws URISyntaxException
    {
        newBill = billService.save(newBill,tripid,userid);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newBillURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{billid}").buildAndExpand(newBill.getBillid()).toUri();
        responseHeaders.setLocation(newBillURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @PutMapping(value = "/bill/{billid}")
    public ResponseEntity<?> updateBill(
            @RequestBody
                    Bill updateBill,
            @PathVariable
                    long billid)
    {
        billService.update(updateBill, billid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @DeleteMapping("/bill/{billid}")
    public ResponseEntity<?> deleteBillById(
            @PathVariable
                    long billid)
    {
        billService.delete(billid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
