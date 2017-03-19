/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.online76.arm_taxi_operator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PostLoad;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

/**
 *
 * @author igor
 */
@Entity()
@Table(name = "ORDERS")
@org.hibernate.annotations.Entity(
        dynamicUpdate = true
)

public class Orders implements Serializable {

// <editor-fold defaultstate="collapsed" desc="Основные поля - из БД">    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @Getter
    @Setter
    private Integer id;

    //пассажир
    @Column(name = "Phone")
    @Getter
    @Setter
    private String phone;
    @Column(name = "ConnectPhone")
    @Getter
    @Setter
    private String connectPhone;
    @Column(name = "ClientName")
    @Getter
    @Setter
    private String clientName;

    //маршрут
    @Column(name = "AddrFrom")
    @Getter
    @Setter
    private String addrFrom;
    @Column(name = "AddrFromName")
    @Getter
    @Setter
    private String addrFromName;
    @Column(name = "HouseFrom")
    @Getter
    @Setter
    private String houseFrom;
    @Column(name = "FlatFrom")
    @Getter
    @Setter
    private String flatFrom;
    @Column(name = "AddrTo")
    @Getter
    @Setter
    private String addrTo;
    @Column(name = "HouseTo")
    @Getter
    @Setter
    private String houseTo;
    @Column(name = "AddrToName")
    @Getter
    @Setter
    private String addrToName;

    @Column(name = "ZoneID")
    @Getter
    @Setter
    private Integer zoneID;

    

    @Column(name = "Lat")
    @Getter
    @Setter
    private Integer lat;
    @Column(name = "Lon")
    @Getter
    @Setter
    private Integer lon;
    @Column(name = "to_lat")
    @Getter
    @Setter
    private Integer toLat;
    @Column(name = "to_lon")
    @Getter
    @Setter
    private Integer toLon;

    //расчет стоимости
    @Column(name = "is_calculated")
    @Getter
    @Setter
    private Integer isCalculated;
    @Column(name = "Sum")
    @Getter
    @Setter
    private Integer sum;
    @Column(name = "order_money")
    @Getter
    @Setter
    private Double orderMoney;
    @Column(name = "DistanceCity")
    @Getter
    @Setter
    private Double distanceCity;
    @Column(name = "DistanceMGor")
    @Getter
    @Setter
    private Double distanceMGor;

    //безнал параметры
    @Column(name = "OrgID")
    @Getter
    @Setter
    private Integer orgID;
    @Column(name = "OrganizationDetailID")
    @Getter
    @Setter
    private Integer organizationDetailID;
    @Column(name = "OrganizationSubDetailID")
    @Getter
    @Setter
    private Integer organizationSubDetailID;

    @Column(name = "BnalMoney")
    @Getter
    @Setter
    private Integer bnalMoney;
    @Column(name = "TalonNumber")
    @Getter
    @Setter
    private String talonNumber;
    @Column(name = "is_beznal")
    @Getter
    @Setter
    private Integer isBeznal;

    //доп инфо по заказу
    @Column(name = "Descr")
    @Getter
    @Setter
    private String descr;
    @Column(name = "DescrForClient")
    @Getter
    @Setter
    private String descrForClient;
    @Column(name = "sms_text")
    @Getter
    @Setter
    private String smsText;
    @Column(name = "Predvar")
    @Getter
    @Setter
    private Integer predvar;
    @Column(name = "dtPredvar")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date dtPredvar;
    @Column(name = "predvShowDelta")
    @Getter
    @Setter
    private Integer predvShowDelta;

    //служебные
    @Column(name = "OpID")
    @Getter
    @Setter
    private Integer opID;
    @Column(name = "fromPhone")
    @Getter
    @Setter
    private String fromPhone;
    @Column(name = "FromLine")
    @Getter
    @Setter
    private Integer fromLine;

    @Column(name = "ServiceID")
    @Getter
    @Setter
    private Integer serviceID;
    @Column(name = "State")
    @Getter
    @Setter
    private Integer state;

    public Orders() {
    }

    public Orders(Integer id) {
        this.id = id;
    }

// </editor-fold>            
// <editor-fold defaultstate="collapsed" desc="дополнительные поля - для журнала">    
    @Getter
    @Formula(value = "dbo.format_date_delta(dtArrive, getdate())")
    private String StringTmArrive;

    @Formula(value = "case when predvar = 1 then dbo.format_date_delta(dtPredvar, getdate()) else '' end")
    @Getter
    private String StringTmPredvar;

    @Formula(value = "dbo.format_date_delta(dtBegin, getdate())")
    @Getter
    private String StringTmBegin;

    @Formula(value = "(   select cast(carid as varchar(10))+' '+cars.color+' '+cars.mark+' '+cars.gosnum+ "
            + " case when cars.dtLastUpdate > getdate()-5.0/(24*60) then "
            + "' '+cast(cars.speed as varchar(10))+' км/ч' else '' end "
            + " from cars where cars.id = carid)")
    @Getter
    private String CarDescr;

    public String GetTmString() {
        return this.getStringTmArrive();
    }

    public String GetCarString() {
        return getCarDescr();/*
        if (this.getCar()!=null){
            return this.getCar().GetCarDescrString(true);
        }
        
        return "";
         */
    }

    //journal functions
    public String GetAddrFromString() {
        return (getAddrFrom() + " " + getHouseFrom() + " " + getFlatFrom()).trim();
    }

    public String GetAddrToString() {
        return (getAddrTo() + " " + getHouseTo()).trim();
    }

    public String GetPhoneString() {
        return (getPhone()).trim();
    }

// </editor-fold>        
    
    

    @Transient
    private ru.online76.arm_taxi_operator.model.helpers.order_calc_path_points_parser calcPathPointsParser = new ru.online76.arm_taxi_operator.model.helpers.order_calc_path_points_parser();

    

    private String calc_path_points;
    

   
    public void PostLoad(){
        System.out.println("PostLoad: "+calc_path_points);
        calcPathPointsParser.split_interpoints(calc_path_points);
    }
  

    

    //ADDRESS=Свободы;HOUSE=1;NAME=;LAT=57625338;LON=39883431;MOVED=0;|ADDRESS=Батова;HOUSE=10;NAME=;LAT=57691847;LON=39782379;MOVED=0;|ADDRESS=;HOUSE=;NAME=;LAT=0;LON=0;MOVED=0;|ADDRESS=Урицкого;HOUSE=38а;NAME=;LAT=57684347;LON=39794147;MOVED=0;|

    public String getInterpointaddress1() {
        System.out.println("Order.getInterpointaddress1");
        return calcPathPointsParser.getAddr(0);
    }

    public String getInterpointhouse1() {
        return calcPathPointsParser.getHouse(0);
    }

    public String getInterpointname1() {
        return calcPathPointsParser.getName(0);
    }
    public String getInterpointaddress2() {
        return calcPathPointsParser.getAddr(1);
    }

    public String getInterpointhouse2() {
        return calcPathPointsParser.getHouse(1);
    }

    public String getInterpointname2() {
        return calcPathPointsParser.getName(1);
    }
    public String getInterpointaddress3() {
        return calcPathPointsParser.getAddr(2);
    }

    public String getInterpointhouse3() {
        return calcPathPointsParser.getHouse(2);
    }

    public String getInterpointname3() {
        return calcPathPointsParser.getName(2);
    }
    public String getInterpointaddress4() {
        return calcPathPointsParser.getAddr(3);
    }

    public String getInterpointhouse4() {
        return calcPathPointsParser.getHouse(3);
    }

    public String getInterpointname4() {
        return calcPathPointsParser.getName(3);
    }

    
    public void setInterpointaddress1(String value) {
        System.out.println("Order.setInterpointaddress1");
         calcPathPointsParser.setAddr(0, value);
    }
    public void setInterpointhouse1(String value) {
        System.out.println("Order.setInterpointaddress1");
         calcPathPointsParser.setHouse(0, value);
    }
    public void setInterpointname1(String value) {
        System.out.println("Order.setInterpointaddress1");
         calcPathPointsParser.setName(0, value);
    }
    
        public void setInterpointaddress2(String value) {
        System.out.println("Order.setInterpointaddress1");
         calcPathPointsParser.setAddr(1, value);
    }
    public void setInterpointhouse2(String value) {
        System.out.println("Order.setInterpointaddress1");
         calcPathPointsParser.setHouse(1, value);
    }
    public void setInterpointname2(String value) {
        System.out.println("Order.setInterpointaddress1");
         calcPathPointsParser.setName(1, value);
    }
    
        public void setInterpointaddress3(String value) {
        System.out.println("Order.setInterpointaddress1");
         calcPathPointsParser.setAddr(2, value);
    }
    public void setInterpointhouse3(String value) {
        System.out.println("Order.setInterpointaddress1");
         calcPathPointsParser.setHouse(2, value);
    }
    public void setInterpointname3(String value) {
        System.out.println("Order.setInterpointaddress1");
         calcPathPointsParser.setName(2, value);
    }
    
        public void setInterpointaddress4(String value) {
        System.out.println("Order.setInterpointaddress1");
         calcPathPointsParser.setAddr(3, value);
    }
    public void setInterpointhouse4(String value) {
        System.out.println("Order.setInterpointaddress1");
         calcPathPointsParser.setHouse(3, value);
    }
    public void setInterpointname4(String value) {
        System.out.println("Order.setInterpointaddress1");
         calcPathPointsParser.setName(3, value);
    }
    
// <editor-fold defaultstate="collapsed" desc="">        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.online76.arm_taxi_operator.model.Orders[ id=" + id + " ]";
    }

// </editor-fold>        
}
