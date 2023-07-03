import java.util.LinkedList;

class CustomerInformation 
{
    // Customer attributes
    private int custId;
    private String custIC;
    private int counterPaid;
    private LinkedList<ItemInformation> itemsPurchased;

    public CustomerInformation(int custId, String custIC, int counterPaid) 
    {
        this.custId = custId;
        this.custIC = custIC;
        this.counterPaid = counterPaid;
        this.itemsPurchased = new LinkedList<>();
    }

    // Add new item to the customer's purchase list
    public void addItem(ItemInformation item) 
    {
        itemsPurchased.add(item);
    }

    // Check if the customer is eligible for fast counters (max 5 items)
    public boolean isEligibleForFastCounter() 
    {
        return itemsPurchased.size() <= 5;
    }
    
    // Getters and setters for other attributes as needed
    public int getCustId() 
    {
        return custId;
    }

    public void setCustId(int custId) 
    {
        this.custId = custId;
    }

    public String getCustIC() 
    {
        return custIC;
    }

    public void setCustIC(String custIC) 
    {
        this.custIC = custIC;
    }

    public int getCounterPaid() 
    {
        return counterPaid;
    }

    public void setCounterPaid(int counterPaid) 
    {
        this.counterPaid = counterPaid;
    }

    public LinkedList<ItemInformation> getItemsPurchased() 
    {
        return itemsPurchased;
    }

    public void setItemsPurchased(LinkedList<ItemInformation> itemsPurchased) 
    {
        this.itemsPurchased = itemsPurchased;
    }
}

class ItemInformation 
{
    // Item attributes
    private int itemId;
    private String itemName;
    private double itemPrice;
    private String datePurchase;

    public ItemInformation(int itemId, String itemName, double itemPrice, String datePurchase) 
    {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.datePurchase = datePurchase;
    }

    // Getters and setters for other attributes as needed
    public int getItemId() 
    {
        return itemId;
    }

    public void setItemId(int itemId) 
    {
        this.itemId = itemId;
    }

    public String getItemName() 
    {
        return itemName;
    }

    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public double getItemPrice() 
    {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) 
    {
        this.itemPrice = itemPrice;
    }

    public String getDatePurchase() 
    {
        return datePurchase;
    }

    public void setDatePurchase(String datePurchase) 
    {
        this.datePurchase = datePurchase;
    }

}
