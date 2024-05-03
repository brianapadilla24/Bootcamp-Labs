package org.example;
import java.util.Date;

//transaction filter class
public class TransactionFilter {
    private double amountMin;
    private double amountMax;
    private String vendor;
    private String description;
    private Date startDate;
    private Date endDate;

    //transaction filter constructor
    public TransactionFilter() {
        this.amountMin = -Double.MAX_VALUE;
        this.amountMax = Double.MAX_VALUE;
        this.vendor = "";
        this.description = "";
        this.startDate = new Date(Long.MIN_VALUE);
        this.endDate = new Date(Long.MAX_VALUE);
    }

    //transaction getters and setters
    public Date getStartDate() {

        return startDate;
    }

    public void setStartDate(Date startDate) {

        this.startDate = startDate;
    }

    public Date getEndDate() {

        return endDate;
    }

    public void setEndDate(Date endDate) {

        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmountMin() {
        return amountMin;
    }

    public void setAmountMin(double amountMin) {
        this.amountMin = amountMin;
    }

    public double getAmountMax() {
        return amountMax;
    }

    public void setAmountMax(double amountMax) {
        this.amountMax = amountMax;
    }
}