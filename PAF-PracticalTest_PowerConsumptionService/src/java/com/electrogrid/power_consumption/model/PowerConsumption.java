package java.com.electrogrid.power_consumption.model;

public class PowerConsumption {

    //implementing variables for create model
    private int id;

    private String accountNo;

    private String invoiceNo;

    private String userName;

    private int usedUnits;

    private double totalCost;

    //implemnting the constructor
    public PowerConsumption() {
    }

    //implementing getters and setter for asssign user inputs for the created variables
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUsedUnit() {
        return usedUnits;
    }

    public void setUsedUnit(int usedUnit) {
        this.usedUnits = usedUnit;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    //returning data in string format
    @Override
    public String toString() {
        return "PowerConsumption{" +
                "id=" + id +
                ", invoiceNo='" + accountNo + '\'' +
                ", accountNo='" + invoiceNo + '\'' +
                ", userName='" + userName + '\'' +
                ", usedUnit='" + usedUnits + '\'' +
                ", totalCost='" + totalCost + '\'' +
                '}';
    }
}
