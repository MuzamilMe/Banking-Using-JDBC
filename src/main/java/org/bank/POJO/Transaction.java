package org.bank.POJO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {

    int counter;
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");


    String transectionId;


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    String transferType;
    int amount;
    int Sender_Acc;
    int Reciever_Acc;

    public int getSender_Acc() {
        return Sender_Acc;
    }

    public void setSender_Acc(int sender_Acc) {
        Sender_Acc = sender_Acc;
    }

    public int getReciever_Acc() {
        return Reciever_Acc;
    }

    public void setReciever_Acc(int reciever_Acc) {
        Reciever_Acc = reciever_Acc;
    }

    public String getTransectionId() {
        return transectionId;
    }

    public void setTransectionId(String transectionId) {
        this.transectionId = transectionId;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getDateOfTransfer() {

        return String.valueOf(formatter.format(currentDate));
    }

    public void setDateOfTransfer() {
        LocalDate dateOfTransfer= this.currentDate;
    }

    public int getAmount() {

        return amount;
    }

    public void setAmount(int amount) {

        this.amount = amount;
    }
}
