package mfcc.reservations.management;


import mfcc.reservations.management.model.Transaction;

import java.time.LocalDateTime;

public class TransactionController {

    private static int id = 0;

    public static synchronized Transaction generateTransaction() {
        return new Transaction(id++, "transaction"+id++, LocalDateTime.now());
    }
}
