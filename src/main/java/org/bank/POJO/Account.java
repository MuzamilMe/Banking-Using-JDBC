package org.bank.POJO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Account {
   int customerid;
   int amout,pinCode;
   int  accountNo;
//   Date currentDate = new Date();
//   LocalDateTime now = LocalDateTime.now();
//   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    Date dateOfCreate = dateFormat.format(now);
Date date = Calendar.getInstance().getTime();
   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   String dateOfCreate = dateFormat.format(date);
   String accountType;

   public int getCustomerid() {
      return customerid;
   }

   public void setCustomerid(int customerid) {
      this.customerid = customerid;
   }



   public Account() {
   }

   public int getAccountNo() {

      return accountNo;
   }

   public void setAccountNo(int accountNo) {

      this.accountNo = accountNo;
   }

   public int getPinCode() {
      return pinCode;
   }

   public void setPinCode(int pinCode) {

      this.pinCode = pinCode;
   }



   public void setDateOfCreate() {
      String Doc = this.dateOfCreate;

   }
   public String getDateOfCreate() {

      return dateOfCreate;
   }

   public String getAccountType() {
      return accountType;
   }

   public void setAccountType(String accountType) {
      this.accountType = accountType;
   }

   public int getAmout() {
      return amout;
   }

   public void setAmout(int amout) {
      this.amout = amout;
   }

}
