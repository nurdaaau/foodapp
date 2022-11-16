package com.example.ourfoodapp;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {

    private Connection connection;

    // For Amazon Postgresql
    // private final String host = "ssprojectinstance.csv2nbvvgbcb.us-east-2.rds.amazonaws.com"

    // For Google Cloud Postgresql
    // private final String host = "35.44.16.169";

    // For Local PostgreSQL
    private final String host = "ec2-44-208-88-195.compute-1.amazonaws.com";

    private final String database = "d1f4bd07ofjuj6";
    private final int port = 5432;
    private final String user = "aqqyrxhvocslxx";
    private final String pass = "4366b938bae00411ebc8b53c2ff159e003f38431ced9d7722755a6c5784ce9c2";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;

    public DatabaseManager()
    {
        //this.url = String.format(this.url, this.host, this.port, this.database);
        this.url = "postgres://aqqyrxhvocslxx:4366b938bae00411ebc8b53c2ff159e003f38431ced9d7722755a6c5784ce9c2@ec2-44-208-88-195.compute-1.amazonaws.com:5432/d1f4bd07ofjuj6";
        connect();
        //this.disconnect();
        System.out.println("connection status:" + status);
    }

    private void connect()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;
                    System.out.println("connected:" + status);
                }
                catch (Exception e)
                {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.status = false;
        }
    }

    public Connection getExtraConnection()
    {
        Connection c = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, pass);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }
}