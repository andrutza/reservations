package mfcc.reservations.controller;

import mfcc.reservations.management.model.Operation;
import mfcc.reservations.model.Model;
import mfcc.reservations.transaction.TransactionRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    private TransactionRunner transactionRunner = new TransactionRunner();

    @RequestMapping(value = "/runInTransaction", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<List<Model>> runInTransaction(@RequestBody List<Operation> operations) throws InterruptedException {
        return transactionRunner.runTransaction(operations);
    }
}
