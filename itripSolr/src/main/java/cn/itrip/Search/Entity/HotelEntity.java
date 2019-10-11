package cn.itrip.Search.Entity;


import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;


public class HotelEntity implements Serializable {




    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Field
    private String id;
    @Field
    private String hotelName;
}
