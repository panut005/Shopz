package ConnectDataBase;

import model.Customer;
import model.Product;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDB {
    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";


    public static Customer customerToken;

    public ArrayList<Customer> getAllCustomer(){
        ArrayList<Customer>customers=new ArrayList<>();
        try{
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select * from Customer";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    String firstname=resultSet.getString("first_name");
                    String lastname=resultSet.getString("last_name");
                    String address=resultSet.getString("address");
                    String subdistrict=resultSet.getString("subdistrict");
                    String province=resultSet.getString("province");
                    String zipcode=resultSet.getString("zipcode");
                    String tel = resultSet.getString("tel_number");
                    customers.add(new Customer(firstname,lastname,address,subdistrict,province,zipcode,tel));
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

//    public static void login(String username, String password){
//        try{
//            Class.forName(dbName);
//            Connection conn = DriverManager.getConnection(dbURL);
//
//            Statement myStmt = conn.createStatement();
//
//            ResultSet myRs  = myStmt.executeQuery("select * from Customer where username" +
//                    " = '"  + username + "' and password = '" + password+ "'" );
//
//            if(myRs.next()){
//                String username1 =myRs.getString("username");
//                String pass= myRs.getString("password");
//                String firstname = myRs.getString("first_name");
//                String lastname = myRs.getString("last_name");
//                String address = myRs.getString("address");
//                String tel_number = myRs.getString("tel_number");
//
//                customerToken = new Customer(username1,pass,address,firstname,lastname,tel_number);
//                conn.close();
//            }
//
//        }
//        catch (Exception exc){
//            exc.printStackTrace();
//        }
//    }

    public static void register(String firstname,String lastname,
                                String address, String district, String province, String zipcode,
                                String tel_number){
        try{
            Class.forName(dbName);
            Connection conn = DriverManager .getConnection(dbURL);
                String query = " insert into customer (tel_number,first_name,last_name,address,district,province,zipcode)"
                        + " values (?, ?, ?, ?,?, ?, ?)";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString (1, tel_number);
                preparedStmt.setString (2, firstname);
                preparedStmt.setString (3, lastname);
                preparedStmt.setString (4, address);
                preparedStmt.setString (5, district);
                preparedStmt.setString (6, province);
                preparedStmt.setString (7, zipcode);
                preparedStmt.execute();
//                login(userID,passID);
                conn.close();




        }
        catch (Exception exc){
            exc.printStackTrace();
        }

    }

//    public static boolean checkUsername(String username){ // true if have data in DB
//        Connection conn = null;
//        try{
//            Class.forName(dbName);
//            conn = DriverManager .getConnection(dbURL) ;
//            Statement myStmt = conn.createStatement();
//
//            ResultSet myRs  = myStmt.executeQuery("select * from Customer where username" +
//                        " = '"  + username + "'" );
//
//            String username1 =myRs.getString("username");
//            conn.close();
//            if(username1!=null){
//                System.out.println(username1);
//                return true;
//            }
//            return false;
//
//        }
//        catch (Exception exc){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return false;
////            exc.printStackTrace();
//        }
//
//    }

    public static boolean  checkTelephoneNo(String tel_number)  { // true if have data in DB
        Connection conn = null;
        try{
            Class.forName(dbName);
            conn = DriverManager .getConnection(dbURL);
            Statement myStmt = conn.createStatement();

            ResultSet myRs  = myStmt.executeQuery("select * from Customer where tel_number" +
                    " = '"  + tel_number + "'" );

            String telephone_number =myRs.getString("tel_number");

            conn.close();
            if(telephone_number!=null){
                System.out.println(telephone_number);
                return true;
            }
            return false;

        }
        catch (Exception exc){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
//            exc.printStackTrace();
        }

    }
    public static void updateAddress(String newAddress){
        try {
            Class.forName(dbName);
            Connection connection = DriverManager .getConnection(dbURL);
            if(connection != null){
                String query  = " UPDATE customer SET address= '"+ newAddress +"'"+ " WHERE tel_number = '"+customerToken.getTel_number()+"';";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
