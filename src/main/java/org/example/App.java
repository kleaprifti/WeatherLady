package org.example;

import org.example.dao.WeatherDao;
//import org.example.dao.WeatherDaoImpl;
import org.example.dao.WeatherDaoImpl;
import org.example.entity.Location;
import org.example.entity.WeatherParameters;
import org.example.entity.WindEntity;
import org.example.service.WeatherService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Hello world!
 *
 */
public class App {


    static Scanner scanner = new Scanner(System.in);
    static WeatherService serviceAPI = new WeatherService();

    static WeatherDao weatherDao = new WeatherDaoImpl();

    public static void main(String[] args) throws SQLException {
        printMenu();
    }

    public static void printMenu() throws SQLException {
        System.out.println("1. Add location");
        System.out.println("2. Display location");
        System.out.println("3. Get weather from API");
        System.out.print("Choose: ");
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                addLocationMenu();
                break;
            case "2":
                retrive();
                //displayLocation();
                break;
            case "3":
                getWeatherFromAPI();
                break;
        }
        // displayLocation();
    }


    public static void getWeatherFromAPI() throws SQLException {
        System.out.print("Please select city: ");
        String city = scanner.nextLine();
        WindEntity response = serviceAPI.getWeatherDataFromApi(city);
        System.out.println(response);
        System.out.println("Humidity: " + response.getHumidity());
        System.out.println("Temperature: " + response.getTemp());
        printMenu();
    }

    static Session session;
    static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {

        // Turn Hibernate logging off
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Location.class)
                .buildSessionFactory();
        return sessionFactory;
    }

    public static void retrive() {
        try {
            session = buildSessionFactory().openSession();
//                session.beginTransaction();
            //  session.beginTransaction();


            // creating transaction object
            Transaction t = session.beginTransaction();

            Query query = session.createQuery("from location");
            List<Location> fetchedData = query.list();
            //  java.util.List list = query.list();
           System.out.println(fetchedData);
            t.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
//    public static void displayLocation()  {
//
//            Location location= null;
//            try {
//                session = buildSessionFactory().openSession();
//                session.beginTransaction();
//                List<Location> data = session.createQuery(" From location").list();
//                for (Iterator iterator1 = data.iterator(); iterator1.hasNext(); ) {
//                    Location info1 = (Location) iterator1.next();
//                    System.out.print("Id: " + info1.getId());
//                    System.out.print(" City: " + info1.getCity());
//                    System.out.println(" Country: " + info1.getCountry());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (session != null) {
//                   session.close();
//               }
//         }
//    }


        public static void addLocationMenu(){
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));
                System.out.println("Please enter city: ");
                String city = reader.readLine();
                System.out.println("Please enter Country: ");
                String country = reader.readLine();
                URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=0b66a7d3d8a71dd36a55e2f3d12456df");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                int responseCode = conn.getResponseCode();
                if (responseCode != 200) {
                    throw new RuntimeException("Something went wrong!");
                } else {
                    StringBuilder informationString = new StringBuilder();
                    Scanner scanner = new Scanner(url.openStream());
                    while (scanner.hasNext()) {
                        informationString.append(scanner.nextLine());
                    }
                    scanner.close();

                    // Query here
                    Location location = new Location();
                    location.setCity(city);
                    location.setCountry(country);
                    weatherDao.insert(location);
                    System.out.println("Inserted into database");
                    printMenu();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


