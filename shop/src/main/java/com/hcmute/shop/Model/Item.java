package com.hcmute.shop.Model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Item implements Serializable {
    private Shoes shoes;
    private int quantity;
    private int size;

    public int getPrice(){
        int tong = shoes.getPrice()*quantity;
        return tong;
    }
    public Item(Shoes shoes) {
        this.shoes = shoes;
    }

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public double getTotal() {
        return shoes.getPrice() * this.quantity;
    }
    public String getTotalCurrencyFormat() {

        DecimalFormat d = new DecimalFormat("###,###,###'₫'");
        return d.format(this.getTotal());
    }

    @Override
    public String toString() {
        return "Item{" +
                "shoes=" + shoes +
                ", quantity=" + quantity +
                ", size=" + size +
                '}';
    }
}
