package org.bank.POJO;

public class Transaction {

    String dd;
    String transectionId;
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
        return dd;
    }

    public void setDateOfTransfer(String dateOfTransfer) {

        this.dd = dateOfTransfer;
    }

    public int getAmount() {

        return amount;
    }

    public void setAmount(int amount) {

        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{ " +
                "Date: " + dd +
                ", transectionId: '" + transectionId + '\'' +
                ", transferType: '" + transferType + '\'' +
                ", amount: " + amount +
                ", Sender_Acc: " + Sender_Acc +
                ", Reciever_Acc: " + Reciever_Acc +
                "\n"+'}';
    }
}
