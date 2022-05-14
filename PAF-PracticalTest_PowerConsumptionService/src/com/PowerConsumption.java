package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PowerConsumption {

    // Database Connection
    public Connection connect() {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid_db", "root", "root");
            System.out.println("Database Connection Succesfull");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while Connecting to Database");
        }

        return con;
    }

    // Insert PowerConsumption Details
    public String createPowerConsumption(int id, String accountNo, String invoiceNo, String userName, int usedUnits,
            float totalCost) {
        String output = "";

        try {
            // DB connection
            Connection con = connect();
            if (con == null) {
                return "Error while connecting to the database";
            }

            // create a prepared statement
            String query = "INSERT INTO `powercon`( `id`, `accountNo`, `invoiceNo`, `userName`, `usedUnits`, `totalCost`) VALUES (?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(query);

            // binding values
            st.setInt(1, id);
            st.setString(2, accountNo);
            st.setString(3, invoiceNo);
            st.setString(4, userName);
            st.setInt(5, usedUnits);
            int units = usedUnits;
            double totalC = 0;
            if (units < 100) {
                totalC = units * 1.20;
            } else if (units < 300) {
                totalC = 100 * 1.20 + (units - 100) * 2;
            } else if (units > 300) {
                totalC = 100 * 1.20 + 200 * 2 + (units - 300) * 3;
            }
            totalCost = (float) totalC;
            st.setFloat(6, totalCost);

            // execute the statement
            st.execute();
            con.close();
            String newPowerConsumptions = getPowerConsumptions();
            output = "{\"status\":\"success\", \"data\": \"" + newPowerConsumptions + "\"}";
        }

        catch (Exception e) {
            output = "{\"status\":\"error\", \"data\": \"Error while inserting the PowerConsumptions.\"}";
            System.err.println(e.getMessage());
        }
        return output;
    }

    // Read PowerConsumption table details
    public String getPowerConsumptions() {
        String output = "";

        try {
            Connection con = connect();
            if (con == null) {
                return "Error while fetching data from Database.";
            }

            // Prepare the html table to be displayed
            output = "<table border='1'><tr><th>PowerConsumption ID</th><th>Account No</th><th>Invoice No</th><th>User Name</th><th>Used Units</th><th>Total Cost</th>";
            String query = "SELECT * FROM `PowerConsumptions`";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // iterate through the rows in the result set
            while (rs.next()) {
                String id = Integer.toString(rs.getInt("id"));
                String acc_no = rs.getString("accountNo");
                String inc_no = rs.getString("invoiceNo");
                String u_name = rs.getString("userName");
                String units = rs.getString("usedUnits");
                String tot_cost = rs.getString("totalCost");

                // Add into the html table
                output += "<tr><td>" + id + "</td>";
                output += "<td>" + acc_no + "</td>";
                output += "<td>" + inc_no + "</td>";
                output += "<td>" + u_name + "</td>";
                output += "<td>" + units + "</td>";
                output += "<td>" + tot_cost + "</td>";

                // buttons
                output += "<td><form method='post' action='PowerCon.jsp'><td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
                        + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
                        + "<input name='p_id' type='hidden' value='" + id + "'>" + "</form></td></tr>";
            }

            // close the db connection
            con.close();

            // Complete the html table
            output += "</table>";
        }

        catch (Exception e) {
            output = "Error while fetching the PowerConsumptions!";
            System.err.println(e.getMessage());
        }

        return output;
    }

    // update PowerConsumption details
    public String updatePowerConsumption(int id, String accountNo, String invoiceNo, String userName, int usedUnits,
            float totalCost) {
        String output = "";
        try {
            Connection con = connect();
            if (con == null) {
                return "Error while connecting to the database";
            }

            // create a prepared statement
            String updatePowerConsumption = "UPDATE `powercon` SET `id`='" + id + "',accountNo='" + accountNo
                    + "',invoiceNo='" + invoiceNo + "',userName='" + userName + "',usedUnits='" + usedUnits
                    + "',totalCost='" + totalCost + "'";
            PreparedStatement st = con.prepareStatement(updatePowerConsumption);

            // execute the statement
            st.execute();
            con.close();
            output = "updtaed successfully";
        } catch (Exception e) {
            output = "Error while updating";
            System.err.println(e.getMessage());
        }
        return output;
    }

    // Delete PowerConsumption details
    public String deletePowerConsumption(int id) {
        String output = "";
        try {
            Connection con = connect();
            if (con == null) {
                return "Error while connecting to Database";
            }

            // create a prepared statement
            String deletePowerConsumption = "DELETE FROM powercon WHERE id = '" + id + "'";
            PreparedStatement ps = con.prepareStatement(deletePowerConsumption);

            // binding values
            ps.setInt(1, (id));
            // execute the statement
            ps.execute();
            con.close();
            String newPowerConsumptions = getPowerConsumptions();
            output = "{\"status\":\"success\", \"data\": \"" + newPowerConsumptions + "\"}";
        } catch (Exception e) {
            output = "{\"status\":\"error\", \"data\": \"Error while deleting the PowerConsumption.\"}";
            System.err.println(e.getMessage());
        }
        return output;
    }

    public String createPowerConsumption(String parameter, String parameter2, String parameter3, String parameter4,
            String parameter5, String parameter6) {
        return null;
    }

}
