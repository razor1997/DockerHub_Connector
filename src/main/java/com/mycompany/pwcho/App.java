package com.mycompany.pwcho;

import java.sql.*;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws SQLException{

        DisplayMenu();
    }
    public static void DisplayMenu() throws SQLException {
        System.out.println();
        System.out.println("Start ");
        System.out.println("1. Display DataBase");
        System.out.println("2. Add User");
        System.out.println("3. Edit User");
        System.out.println("4. Delete user");
        System.out.println("5. Exit");
        System.out.println();

        System.out.print("Choosen option : ");

        String MENU = new Scanner(System.in).next();
        while(!(MENU.equals("1") || MENU.equals("2") || MENU.equals("3") || MENU.equals("4")|| MENU.equals("5")))
        {
            System.out.print("Wrong option! Try again");
            MENU = new Scanner(System.in).next();
        }

        switch (MENU) {
            case "1":
                ShowDatabase();
                break;
            case "2":
                saveHuman();
                break;
            case "3":
                updateHuman();
                break;
            case "4":
                deleteHuman();
                break;
            case "5":
                System.exit(0);
                break;
            default:
                break;
        }
    }

    public static void ShowDatabase() throws SQLException {
        DBHelper dbHelper = new DBHelper();
        Statement statement = dbHelper.connection.createStatement();
        String query = "SELECT * FROM human";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            System.out.println("|"+rs.getString("human_id")+"|=  "+rs.getString("Name") + " | "  + rs.getString("Subname")+ " | "  +rs.getString("Age") );
        }
        DisplayMenu();
    }
    public static void saveHuman() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Name : ");
        String name = in.nextLine();
        System.out.println("SubName: ");
        String subname = in.nextLine();
        System.out.println("Age ");
        int age = in.nextInt();

        DBHelper dbHelper = new DBHelper();

        String sql_stmt = "INSERT INTO human (name, subname, age) VALUES ('"+ name+"','" +subname +"',"+ age + ")";

        dbHelper.ExecuteSQLStatement(sql_stmt);
        System.out.println("Added users: "+name + " " + subname + " "+ age);
        DisplayMenu();
    }

    public static void deleteHuman() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Get ID to delete: ");
        int id = in.nextInt();
        DBHelper dbHelper = new DBHelper();

        String sql_stmt = "DELETE FROM human WHERE human_id = "+id+"";

        dbHelper.ExecuteSQLStatement(sql_stmt);
        System.out.println("User ID : "+id+ " deleted");
        DisplayMenu();
    }
    public static void updateHuman() throws SQLException {
        Scanner updateScanner = new Scanner(System.in);

        System.out.println("Give ID : ");
        int id = Integer.parseInt(updateScanner.nextLine());
        System.out.println("Name: ");
        String name = updateScanner.nextLine();
        System.out.println("SubName: ");
        String subname = updateScanner.nextLine();
        System.out.println("Age ");
        int age = updateScanner.nextInt();


        DBHelper dbHelper = new DBHelper();
        String sql_stmt = "UPDATE human SET imie = '"+name+"', nazwisko = '"+subname+"', wiek ="+ age +" WHERE human_id = " + id;
        dbHelper.ExecuteSQLStatement(sql_stmt);
        System.out.println("Id User : "+id+ " is update");
        DisplayMenu();
    }



}
