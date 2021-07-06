package com.hcmute.shop.DAO;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hcmute.shop.Model.Item;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Repository
public class Cart implements Serializable {
    private UUID id = UUID.randomUUID();
    private static List<Item> items =new ArrayList<Item>();
    private int totalQuantity;
    private  int totalPrice;
    private int shipPrice ;
    private int total;

    public int getTotalQuantity() {
        return totalQuantity;
    }
    public int getSubPrice() {
        return totalPrice;
    }


    public int getShipPrice() {
        return shipPrice;
    }

    public int getTotal() {
        return total;
    }

    public Cart() {
        items = new ArrayList<>();
    }

    public int getCount() {
        return items.size();
    }

    public  List<Item> getItems() {
        return items;
    }


    public void addItem(Item item) {
        UUID id = item.getShoes().getId();
        int quantity = item.getQuantity();
        for (Item cartItem : items) {
            if (cartItem.getShoes().getId().equals(id)) {
                cartItem.setQuantity(quantity);
                return;
            }
        }
        items.add(item);
    }
    public int getSize(UUID id) {

        for (Item cartItem : items) {
            if (cartItem.getShoes().getId().equals(id)) {

                return cartItem.getSize();
            }
        }
        return 0;
    }
    public void editItem(Item item) {
        UUID id = item.getShoes().getId();
        int quantity = item.getQuantity();
        int size = item.getSize();
        for (Item cartItem : items) {
            if (cartItem.getShoes().getId().equals(id)) {
                cartItem.setSize(size);
                cartItem.setQuantity(quantity);
            }
        }
        return;
    }


    public void removeItem(UUID id) {
        for (int i = 0; i < items.size(); i++) {
            Item item1 = items.get(i);
            if (item1.getShoes().getId().equals(id)) {
                items.remove(i);
                return;
            }
        }
    }
    public String getTotalPrice() {

        DecimalFormat d = new DecimalFormat("###,###,###' ₫'");
        int tong=0;
        for (int i = 0; i < items.size(); i++) {
            Item lineItem = items.get(i);
            tong+=lineItem.getTotal();
        }
        this.totalPrice=tong;
        return d.format(tong);
    }
    public  String getPriceShip(){
        DecimalFormat d = new DecimalFormat("###,###,###' ₫'");
        if(this.totalPrice<5000000&&this.totalPrice!=0){
            this.shipPrice=500000;
            return  d.format(500000);
        }
        else{
            this.shipPrice=0;
            return  d.format(0);
        }
    }
    public  String getFinalPrice(){
        DecimalFormat d = new DecimalFormat("###,###,###' ₫'");
       this.total= this.totalPrice+this.shipPrice;
       return   d.format(this.total);
    }
    public Double ToUSDD(){
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        double dola = (double) this.total/230000;
        String dolaS =n.format(dola);
        return Double.parseDouble(dolaS.substring(1));
    }

    public String ToUSDS(){
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        double dola = (double) this.total/230000;

        return n.format(dola);
    }


    public int getTotalQuanity() {


        int tong=0;
        for (int i = 0; i < items.size(); i++) {
            Item lineItem = items.get(i);
            tong+=lineItem.getQuantity();
        }
    this.totalQuantity=tong;
        return tong;
    }


}



