/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.online76.arm_taxi_operator.model.helpers;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class order_calc_path_points_parser {
    private class order_interpoint {

        public String address;
        public String house;
        public String name;
        public int lat;
        public int lon;

        }
        public order_calc_path_points_parser(){
            generateemptyarray();
        }
        private ArrayList<order_interpoint> interpoints = new ArrayList<order_interpoint>();
        private void generateemptyarray(){
             for(int i=0; i<4;i++){
                interpoints.add(new order_interpoint());
            }
        }

        public void split_interpoints(String points) {

            String[] ipoints = points.split("\\|");
            interpoints.clear();
            for (int i=0; i<4; i++) {
                order_interpoint point = new order_interpoint();
                interpoints.add(point);
                
                if (i<ipoints.length){
                    String ipoint = ipoints[i];
                
                    String[] fields = ipoint.split(";");

                    point.address = fields[0].substring(fields[0].indexOf("=")+1);
                    point.house = fields[1].substring(fields[1].indexOf("=")+1);
                    point.name = fields[2].substring(fields[2].indexOf("=")+1);
                    point.lat = Integer.valueOf(fields[3].substring(fields[3].indexOf("=")+1));
                    point.lon = Integer.valueOf(fields[4].substring(fields[4].indexOf("=")+1));         
                }
            }
        }
        
        public String toString(){
            String result = "";
            for(int i=0;i<4;i++){
                order_interpoint point = interpoints.get(i);
                result+="ADDRESS="+point.address+";";
                result+="HOUSE="+point.house+";";
                result+="NAME="+point.name+";";
                result+="LAT="+point.lat+";";
                result+="LON="+point.lon+";";
                result+="|";
            }
            return result;
        }
        
        public String getAddr(int index){
            System.out.println("getAddr: "+index);
            return interpoints.get(index).address;
        }
        public String getHouse(int index){
            return interpoints.get(index).house;
        }
        public String getName(int index){
            return interpoints.get(index).address;
        }
        public String getLat(int index){
            return interpoints.get(index).address;
        }
        public String getLon(int index){
            return interpoints.get(index).address;
        }
        
        public void setAddr(int index, String value){
            interpoints.get(index).address=value;
        }
        public void setHouse(int index, String value){
            interpoints.get(index).house=value;
        }
        public void setName(int index, String value){
            interpoints.get(index).name=value;
        }
        public void setLat(int index, int value){
            interpoints.get(index).lat=value;
        }
        public void setLon(int index, int value){
            interpoints.get(index).lon=value;
        }
}
