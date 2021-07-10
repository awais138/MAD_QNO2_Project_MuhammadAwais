package com.example.constructionapp;

public class model_class_to_post {

    String contactno, fullname, price, Description, city;

    public model_class_to_post() {
    }

    public model_class_to_post(String contactno, String fullname, String price, String Description, String city) {
        this.contactno = contactno;
        this.fullname = fullname;
        this.price = price;
        this.Description = Description;

    }

    public String getContactno() {
        return contactno;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return Description;
    }

    public String getCity() {
        return city;
    }
}