/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongln.utils;

import java.io.Serializable;
import java.util.HashMap;
import phuongln.dtos.BookingDetailDTO;

/**
 *
 * @author nhatp
 */
public class CartObject implements Serializable {

    private String customerName;
    private HashMap<String, BookingDetailDTO> cart;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<String, BookingDetailDTO> getCart() {
        return cart;
    }

    public CartObject() {
        this.cart = new HashMap<>();
    }

    public CartObject(String customerName) {
        this.customerName = customerName;
        this.cart = new HashMap<>();
    }

    public int size() {
        return cart.size();
    }

    public void addToCart(BookingDetailDTO dto) throws Exception {
        if (this.cart.containsKey(dto.getItemID())) {
            dto.setQuantity(dto.getQuantity());
        }
        this.cart.put(dto.getItemID(), dto);
    }

    public boolean delete(String id) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean update(String id, int quantity) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.get(id).setQuantity(quantity);
            return true;
        }
        return false;
    }

}
