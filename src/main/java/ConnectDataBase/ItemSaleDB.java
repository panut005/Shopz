package ConnectDataBase;

import model.Item;
import model.Product;

import java.sql.*;
import java.util.ArrayList;

public class ItemSaleDB {
    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";


    public ArrayList<Item> getAllItem(){
        ArrayList<Item>products=new ArrayList<>();
        try{
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "select * from item";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    String id =resultSet.getString("ID");
                    String name=resultSet.getString("item");
                    int q = resultSet.getInt("quantity");
                    double p = resultSet.getDouble("price");
                    double a = resultSet.getDouble("amount");
                    String m = resultSet.getString("month");
                    String y =resultSet.getString("year");
                    products.add(new Item(id,name,p,q,a,m,y));
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static void update(Item item){
        try {
            Class.forName(dbName);
            Connection connection = DriverManager .getConnection(dbURL);
            if(connection != null){
                String query  = " UPDATE Product SET Name= '"+item.getItem()+"',Quantity = "+item.getQuantity()
                        +",Price = "+item.getPrice()+" WHERE ID = "+item.getId()+";";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
                System.out.println("update complete");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addItemToDB(Item item){
        System.out.println(item.getItem());
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if(connection != null){
                String query = "insert into item (id,item,price,quantity,amount,month,year) values " +
                        "('"+item.getId()+"','"+item.getItem()+"',"+item.getPrice()+","+item.getQuantity()+
                        ",'"+item.getAmount()+"','"+item.getMonth()+"','"+item.getYear()+"')";
                Statement p = connection.createStatement();
                p.executeUpdate(query);
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
