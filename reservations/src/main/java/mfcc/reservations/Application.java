package mfcc.reservations;

import mfcc.reservations.management.model.Operation;
import mfcc.reservations.management.model.OperationType;
import mfcc.reservations.management.DeadlockDetector;
import mfcc.reservations.model.User;
import mfcc.reservations.transaction.TransactionRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
        new DeadlockDetector().start();
//        List<Operation> operations = new ArrayList<>();
//        operations.add(new Operation(OperationType.INSERT, new User(3, "arnold", "arnold", "arnold")));
//        Map<String,Object> dataForUpdate = new HashMap<String,Object>();
//        dataForUpdate.put("username","arpi");
//        User user = new User(3, null, null, null);
//        user.setDataForUpdate(dataForUpdate);
//        operations.add(new Operation(OperationType.UPDATE, user));
//        operations.add(new Operation(OperationType.DELETE, new User(3, "arpi", "arnold", "arnold")));
//        new TransactionRunner().runTransaction(operations);
    }

}