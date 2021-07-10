package com.example.constructionapp;

public class post_request {


    public String fullname, contactno, city, price, Description;

    public post_request()
    {}

    public post_request(String Description, String fullname, String contactno, String city, String price){

        this.fullname =fullname;
        this.contactno=contactno;
        this.city=city;
        this.price=price;
        this.Description=Description;

    }
}
