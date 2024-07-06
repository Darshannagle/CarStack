package com.example.projectcsms;

public class bookingdata {
    String user,typeofcar,carmodel,payment;

    public String getTypeofcar() {
        return typeofcar;
    }

    public bookingdata(String user, String typeofcar, String carmodel, String payment) {
        this.user = user;
        this.typeofcar = typeofcar;
        this.carmodel = carmodel;
        this.payment = payment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTypeofcar(String typeofcar) {
        this.typeofcar = typeofcar;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }


}
