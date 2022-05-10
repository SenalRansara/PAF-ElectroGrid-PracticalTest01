package java.com.electrogrid.power_consumption.repository;

import java.com.electrogrid.power_consumption.model.PowerConsumption;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PowerConsumptionRepository {


    //Creating the Database Connection
    Connection con = null;

    public PowerConsumptionRepository() {

        String url = "jdbc:mysql://localhost:3306/electrogrid_db";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);

            System.out.println("Database is successfully Connected!!!");
        }
        catch (Exception e) {
            System.out.println("Error while Connecting to database!!");
        }
    }

    //Implementing a method for retrive data for display
    public List<PowerConsumption> getPowerConsumptions(){

        List<PowerConsumption> powerCon = new ArrayList<>();
        String sql = "SELECT * FROM electrogrid_db.powercon";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                PowerConsumption pc = new PowerConsumption();
                pc.setId(rs.getInt(1));
                pc.setAccountNo(rs.getString(2));
                pc.setInvoiceNo(rs.getString(3));
                pc.setUserName(rs.getString(4));
                pc.setUsedUnit(rs.getInt(5));
                pc.setTotalCost(rs.getDouble(6));

                powerCon.add(pc);
            }
        }
        catch (Exception e) {
            System.out.println("Error while fetching data!");
        }

        return  powerCon;
    }


    //Implementing a method for retrive data for search operation
    public PowerConsumption getPowerConsumption(int id) {

        String sql = "SELECT * FROM electrogrid_db.powercon WHERE id="+id;
        PowerConsumption pc = new PowerConsumption();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if(rs.next()){
                pc.setId(rs.getInt(1));
                pc.setAccountNo(rs.getString(2));
                pc.setInvoiceNo(rs.getString(3));
                pc.setUserName(rs.getString(4));
                pc.setUsedUnit(rs.getInt(5));
                pc.setTotalCost(rs.getDouble(6));

            }
        }
        catch (Exception e) {
            System.out.println("Error in Serching data!!!");
        }

        return pc;
    }

    //Implementing a method for Create data for Create Operation
    public String createPowerConsumption(PowerConsumption pc1) {

        String sql = "INSERT INTO electrogrid_db.powercon VALUES (?,?,?,?,?,?)";
        String output ="";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, pc1.getId());
            st.setString(2, pc1.getAccountNo());
            st.setString(3, pc1.getInvoiceNo());
            st.setString(4, pc1.getUserName());
            st.setInt(5, pc1.getUsedUnit());
            int units = pc1.getUsedUnit();
            double totalC = 0;
            if(units < 100){
                totalC = units * 1.20;
            }else if(units<300){
                totalC = 100 * 1.20 + (units - 100) * 2;
            } else if (units > 300) {
                totalC = 100 * 1.20 + 200 * 2 + (units - 300) * 3;
            }
            pc1.setTotalCost(totalC);

            st.setDouble(6, pc1.getTotalCost());
            st.executeUpdate();
            output = "Inserted Successfully !";
        }
        catch (Exception e) {
            output = "adding data into database went wrong!";
            System.err.println(e.getMessage());
        }
        return output;
    }

    //Implementing a method for update data for update operation
    public String updatePowerConsumption(PowerConsumption pc1) {

        String sql = "UPDATE electrogrid_db.powercon SET accountNo=?, invoiceNo=?, userName =?, usedUnits=?, totalCost=? WHERE id =?";
        String output ="";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, pc1.getAccountNo());
            st.setString(2, pc1.getInvoiceNo());
            st.setString(3, pc1.getUserName());
            st.setInt(4, pc1.getUsedUnit());
            st.setDouble(5, pc1.getTotalCost());
            st.setInt(6,pc1.getId());

            st.executeUpdate();
            output = "Updated Successfully !";
        }
        catch (Exception e) {
            output = "Database cannot update Power Consumption details !";
            System.err.println(e.getMessage());
        }
        return output;
    }

    //Implementing a method for Delete data for delete operation
    public String deletePowerConsumption(int id) {

        String sql = "DELETE FROM electrogrid_db.powercon WHERE id =?";
        String output ="";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            //System.out.println("Successfully deleted the Power Consumption Entry!!!");
            output = "Deleted Successfully !";
        }
        catch (Exception e) {
            output = "Error While Deleting!";
            System.err.println(e.getMessage());
        }
    return output;
    }
}
