package com.example.projectcsms;

public class bookingservice {
    String carmodel,typeofservice,servicedetails;

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public String getTypeofservice() {
        return typeofservice;
    }

    public void setTypeofservice(String typeofservice) {
        this.typeofservice = typeofservice;
    }

    public String getServicedetails() {
        return servicedetails;
    }

    public void setServicedetails(String servicedetails) {
        this.servicedetails = servicedetails;
    }

    public bookingservice(String carmodel, String typeofservice, String servicedetails) {
        this.carmodel = carmodel;
        this.typeofservice = typeofservice;
        this.servicedetails = servicedetails;

    }
}
