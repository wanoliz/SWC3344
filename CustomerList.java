import java.util.LinkedList;

class CustomerList
 {
    private LinkedList<CustomerInformation> customers;

    public CustomerList() 
    {
        customers = new LinkedList<>();
    }

    public void addCustomer(CustomerInformation customer) 
    {
        customers.add(customer);
    }

    // Additional methods to manipulate the customer list as needed
    public LinkedList<CustomerInformation> getCustomers() 
    {
        return customers;
    }

    public CustomerInformation getCustomerById(int customerId)
    {
        for (CustomerInformation customer : customers)
        {
            if (customer.getCustId() == customerId)
            {
                return customer;
            }
        }
        return null; // Customer with given ID not found
    }

    public void removeCustomerById(int customerId)
    {
        CustomerInformation customerToRemove = null;
        for (CustomerInformation customer : customers) 
        {
            if (customer.getCustId() == customerId)
            {
                customerToRemove = customer;
                break;
            }
        }
        if (customerToRemove != null) 
        {
            customers.remove(customerToRemove);
        }
    }
}
