package com.gmail.eriktagirov;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION =
            "jdbc:mysql://localhost:3306/appartment?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "18091986Erik";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        ConnectionAppartments appartments = new ConnectionAppartments(
                DB_CONNECTION, DB_USER, DB_PASSWORD
        );

        System.out.println("Choose filter:");
        Filter filter = new Filter();

        System.out.println("Enter district:");
        String district = sc.nextLine();
        filter.setDistrict(district);

        System.out.println("Enter address:");
        String address = sc.nextLine();
        filter.setAddress(address);

        System.out.println("Enter area:");
        double area = sc.nextDouble();
        filter.setArea(area);

        System.out.println("Enter rooms:");
        int rooms_count = sc.nextInt();
        filter.setRooms_count(rooms_count);

        System.out.println("Enter price:");
        double price = sc.nextDouble();
        filter.setPrice(price);

        System.out.println(filter.toString());

        Connection conn = appartments.getConnection();

        try {
            AppartmentDAOEx dao = new AppartmentDAOEx(conn, "appartments");
            dao.selectByParameters(filter);
            List<Appartments> list = dao.getAll(Appartments.class);
            for (Appartments aprt : list)
                System.out.println(aprt);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            sc.close();
            if (conn != null) conn.close();
        }
    }
}
