package com.sumerge.automation.task.dataModel;

import java.security.PrivateKey;

public class mainData {

    private String DateFrom;
    private String DateTo;
    private String HotelName;

    public String getDateFrom() {
        return DateFrom;
    }

    public String getHotelName() {
        return HotelName;
    }

    public String getDateTo() {
        return DateTo;
    }

    public void setDateFrom(String dateFrom) {
        DateFrom = dateFrom;
    }

    public void setDateTo(String dateTo) {
        DateTo = dateTo;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

}
