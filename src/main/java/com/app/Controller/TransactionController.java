package com.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.CustomException.InvalidInputException;
import com.app.CustomException.ResourceNotFoundException;
import com.app.Dto.ApiResponse;
import com.app.Dto.TransactionDto;
import com.app.Dto.User;
import com.app.Service.TransactionService;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/transaction")
// @CrossOrigin()
public class TransactionController {

    @Autowired
    private TransactionService trscSrv;

    // using json to get the data securely from the user
    @PutMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody User user) {

        try {
            trscSrv.withdraw(user);
            ApiResponse response = new ApiResponse(user.getAmount() + "withdrawed");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException re) {
            throw re; // if server have some issue
        } catch (InvalidInputException ie) {
            throw ie; // if user feeds wrong input
        }

    }

    @PutMapping("/deposit")
       public ResponseEntity<?> deposit(@RequestBody User user) {

        try {
            trscSrv.deposit(user);
            ApiResponse response = new ApiResponse(user.getAmount() + "deposited");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException re) {
            throw re;
        } catch (InvalidInputException ie) {
            throw ie;
        }

    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getMethodName(@RequestParam int id) {
        
    try
        { 
            List<TransactionDto> trnasacitons = trscSrv.listAllTransactions(id);
            return ResponseEntity.status(HttpStatus.OK).body(trnasacitons);
        }
       catch(ResourceNotFoundException re)
       {
          throw re;
       } 
    }
    
    @PutMapping("transfer")
    public ResponseEntity<?> transfer(@RequestBody User user) {
        System.out.println(user);
        try {
            trscSrv.deposit(user);
            ApiResponse response = new ApiResponse(user.getAmount() + "transfered");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException re) {
            throw re;
        } catch (InvalidInputException ie) {
            throw ie;
        }
    }

}
