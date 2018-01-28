package rubberduckies.fims.adapters;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Etienne on 27/01/2018.
 */

public class User {

    private String id;
    private String name;
    private ArrayList<Products> products = new ArrayList<>();

    public User(){
        name = "Default";
        id = "default_id";
    }

    public User(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProductList(ArrayList<Products> products) {
        this.products = products;
    }

    public void addProductInList(Products product){
        this.products.add(product);
    }
}
