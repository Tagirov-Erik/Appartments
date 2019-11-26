package com.gmail.eriktagirov;

public class Appartments {
    @Id
    private int idAppartments;

    private String district;
    private String address;
    private double area;
    private int rooms_count;
    private double price;

    public Appartments() {
    }

    public Appartments(String district, String address, double area, int rooms_count, double price) {
        this.district = district;
        this.address = address;
        this.area = area;
        this.rooms_count = rooms_count;
        this.price = price;
    }

    public int getIdAppartment() {
        return idAppartments;
    }

    public void setIdAppartment(int idAppartment) {
        this.idAppartments = idAppartment;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getRooms_count() {
        return rooms_count;
    }

    public void setRooms_count(int rooms_count) {
        this.rooms_count = rooms_count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Appartments{" +
                "idAppartment=" + idAppartments +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", rooms_count=" + rooms_count +
                ", price=" + price +
                '}';
    }
}
