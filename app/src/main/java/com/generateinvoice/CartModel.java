package com.generateinvoice;

public class CartModel
{
    String itemName, itemDescription ;
    String itemPrice;

    public CartModel(){}

    public CartModel(String itemName,String itemPrice, String itemDescription)
    {
        this.itemName=itemName;
        this.itemPrice=itemPrice;
        this.itemDescription=itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
