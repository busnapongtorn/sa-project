package cs.ku.sa_project.entities;

import java.util.ArrayList;

public class DBConnect {
    public ArrayList<Item> getItems(String str){return new ArrayList<>();}
    public void setOrder(Order order){}
    public void setOrder(OrderItems order_item){}
    public Customer getCustomer(String customer_id){return new Customer();}
    public void setItem(String sql){}
    public Invoice getInvoice(String sql){return new Invoice();}
    public Item getItemsFromOrderItems(Item item){return new Item();}
    public void setOrder(String sql){}
    public void setOrderItems(String sql){}
    public Customer getCustomerFromEmail(String email){return new Customer();}
    public Customer getCustomerFromUsername(String username){return new Customer();}
    public Customer getCustomerFromID(String id){return new Customer();}
    public void updateCustomer(String id){}
    public ArrayList<Order> getOrderByCustomerID(String sql){return new ArrayList<>();}
    public ArrayList<Item> getItems(){return new ArrayList<>();}
    public void insertItems(String sql){}
    public void updateItems(String sql){}
    public Invoice getInvoiceFromDate(String date_s, String date_e){return new Invoice();}
}
