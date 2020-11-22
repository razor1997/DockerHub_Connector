import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException{
    
        DisplayMenu();
    }
    public static void DisplayMenu() throws SQLException {
        System.out.println();
        System.out.println("Menu: ");
        System.out.println("1. Pokaz baze");
        System.out.println("2. Dodaj do bazy");
        System.out.println("3. Podaj id czlowieka do usuniecia");
        System.out.println("4. Podaj id czlowieka do edycji");
        System.out.println("5. Exit");
        System.out.println();

        System.out.print("Select option: ");

        String MENU = new Scanner(System.in).next();
        while(!(MENU.equals("1") || MENU.equals("2") || MENU.equals("3") || MENU.equals("4")|| MENU.equals("5")))
        {
            System.out.print("Nie ma tekiego wyboru \n");
            System.out.print("Wybierz jeszcze raz:");
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
                deleteHuman();
                break;
            case "4":
                updateHuman();
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
            System.out.println("|"+rs.getString("human_id")+"|=  "+rs.getString("Imie") + " | "  + rs.getString("Nazwisko")+ " | "  +rs.getString("Wiek") );
        }
        DisplayMenu();
    }
    public static void saveHuman() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Podaj imie: ");
        String imie = in.nextLine();
        System.out.println("Podaj nazwisko: ");
        String nazwisko = in.nextLine();
        System.out.println("Podaj wiek ");
        int wiek = in.nextInt();

        DBHelper dbHelper = new DBHelper();

        String sql_stmt = "INSERT INTO human (imie, nazwisko, wiek) VALUES ('"+ imie+"','" +nazwisko +"',"+ wiek + ")";

        dbHelper.ExecuteSQLStatement(sql_stmt);
        System.out.println("Dodano do bazy "+imie + " " + nazwisko + " "+ wiek);
        DisplayMenu();
    }

    public static void deleteHuman() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Podaj id: ");
        int id = in.nextInt();
        DBHelper dbHelper = new DBHelper();

        String sql_stmt = "DELETE FROM human WHERE human_id = "+id+"";

        dbHelper.ExecuteSQLStatement(sql_stmt);
        System.out.println("Usunieto czlowieka "+id);
        DisplayMenu();
    }
    public static void updateHuman() throws SQLException {
        Scanner updateScanner = new Scanner(System.in);

        System.out.println("Podaj id do zmiany: ");
        int id = Integer.parseInt(updateScanner.nextLine());
        System.out.println("Podaj imie: ");
        String imie = updateScanner.nextLine();
        System.out.println("Podaj nazwisko: ");
        String nazwisko = updateScanner.nextLine();
        System.out.println("Podaj wiek ");
        int wiek = updateScanner.nextInt();


        DBHelper dbHelper = new DBHelper();
        String sql_stmt = "UPDATE human SET imie = '"+imie+"', nazwisko = '"+nazwisko+"', wiek ="+ wiek +" WHERE human_id = " + id;
        dbHelper.ExecuteSQLStatement(sql_stmt);
        System.out.println("Zaktualizowano czowieka o "+id);
        DisplayMenu();
    }
   


}
