import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.util.Stack;
import java.text.DecimalFormat;

class CheckoutSystem 
{
    private CustomerList customerList;
    private CounterQueues counterQueues;
    private Stack<CustomerInformation> completeStack;

    public CheckoutSystem() 
    {
        customerList = new CustomerList();
        counterQueues = new CounterQueues();
        completeStack = new Stack<>();
    }

    // Method to read customer information and items purchased from input file and add them to the system
    public void readInputFile(String filePath)
    // Rest of the method implementation remains the same
    {
        try (BufferedReader br = new BufferedReader(new FileReader("cust.txt")))
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] data = line.split(",");
                int customerId = Integer.parseInt(data[0]);
                String custIC = data[1];
                int counterPaid = Integer.parseInt(data[2]);
                int itemId = Integer.parseInt(data[3]);
                String itemName = data[4];
                double itemPrice = Double.parseDouble(data[5]);
                String datePurchase = data[6];

                CustomerInformation customer = customerList.getCustomerById(customerId);
                if (customer == null) 
                {
                    customer = new CustomerInformation(customerId, custIC, counterPaid);
                    customerList.addCustomer(customer);
                }
                customer.addItem(new ItemInformation(itemId, itemName, itemPrice, datePurchase));
            }
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to process customer placement at the counters based on the rules
    public void processCustomers() 
    {
        Queue<CustomerInformation> counter1Queue = counterQueues.getCounter1Queue();
        Queue<CustomerInformation> counter2Queue = counterQueues.getCounter2Queue();
        Queue<CustomerInformation> counter3Queue = counterQueues.getCounter3Queue();

        for (CustomerInformation customer : customerList.getCustomers()) 
        {
            if (customer.isEligibleForFastCounter()) 
            {
                if (counter1Queue.size() <= counter2Queue.size()) 
                {    
                    customer.setCounterPaid(1);
                    counter1Queue.add(customer);
                } 
                else 
                {
                    customer.setCounterPaid(2);
                    counter2Queue.add(customer);
                }
                
            } 
            else 
            {
                customer.setCounterPaid(3);
                counter3Queue.add(customer);
            }
        }
    }

    // Method to display customer information and total amount paid for each counter
    public void displayCounterDetails() 
    {
        Queue<CustomerInformation> counter1Queue = counterQueues.getCounter1Queue();
        Queue<CustomerInformation> counter2Queue = counterQueues.getCounter2Queue();
        Queue<CustomerInformation> counter3Queue = counterQueues.getCounter3Queue();

        System.out.println("*************************************************************");
        System.out.println("COUNTER 1");
        System.out.println("*************************************************************");
        
        for (CustomerInformation customer : counter1Queue) 
        {
            displayCustomerDetails(customer);
        }
        
        System.out.println("\n");
        System.out.println("*************************************************************");
        System.out.println("COUNTER 2");
        System.out.println("*************************************************************");
    
        for (CustomerInformation customer : counter2Queue) 
        {
            displayCustomerDetails(customer);
        }

        System.out.println("\n");
        System.out.println("*************************************************************");
        System.out.println("COUNTER 3");
        System.out.println("*************************************************************");
        for (CustomerInformation customer : counter3Queue) 
        {
            displayCustomerDetails(customer);
        }
    }

    // Helper method to display customer details
    private void displayCustomerDetails(CustomerInformation customer) 
    {   
        System.out.println("------------------------------------------------------------------");
        System.out.println("Customer ID: " + customer.getCustId());
        System.out.println("IC: " + customer.getCustIC());
        System.out.println("Counter: " + customer.getCounterPaid());

        double totalAmount = 0.0;
        for (ItemInformation item : customer.getItemsPurchased()) 
        {
            System.out.println("Item ID: " + item.getItemId());
            System.out.println("Item Name: " + item.getItemName());
            System.out.println("Item Price: RM " + item.getItemPrice());
            System.out.println("Date Purchase: " + item.getDatePurchase());
            totalAmount += item.getItemPrice();
        }
        
        
        // Format the total amount with two decimal places
    DecimalFormat df = new DecimalFormat("#0.00");
    String formattedTotalAmount = df.format(totalAmount);
    
    System.out.println("Total Amount Paid: RM " + formattedTotalAmount);
    System.out.println("------------------------------------------------------------------");
    }

    // Other methods for the class as needed
    public void processPayments()
    {
        Queue<CustomerInformation> counter1Queue = counterQueues.getCounter1Queue();
        Queue<CustomerInformation> counter2Queue = counterQueues.getCounter2Queue();
        Queue<CustomerInformation> counter3Queue = counterQueues.getCounter3Queue();

        for (int i = 0; i < 5; i++) 
        {
            if (!counter1Queue.isEmpty()) 
            {
                CustomerInformation customer = counter1Queue.poll();
                completeStack.push(customer);
            }
            if (!counter2Queue.isEmpty()) 
            {
                CustomerInformation customer = counter2Queue.poll();
                completeStack.push(customer);
            }
            if (!counter3Queue.isEmpty())
            {
                CustomerInformation customer = counter3Queue.poll();
                completeStack.push(customer);
            }
        }
    }
    
        public void addNewCustomer(CustomerInformation customer)
        {
        if (customer.isEligibleForFastCounter()) 
        {
            Queue<CustomerInformation> counter1Queue = counterQueues.getCounter1Queue();
            Queue<CustomerInformation> counter2Queue = counterQueues.getCounter2Queue();

            if (counter1Queue.size() <= counter2Queue.size()) 
            {
                customer.setCounterPaid(1);
                counter1Queue.add(customer);
            } 
            else 
            {
                customer.setCounterPaid(2);
                counter2Queue.add(customer);
            }
        } 
        
        else
        {
            customer.setCounterPaid(3);
            counterQueues.getCounter3Queue().add(customer);
        }
    }

    // Method to remove the respective customer/items from the queues
    public void removeCustomerFromQueue(CustomerInformation customer) 
    {
        Queue<CustomerInformation> queue = getQueueByCounterNumber(customer.getCounterPaid());
        if (queue != null) 
        {
            queue.remove(customer);
        }
    }

    // Helper method to get the queue based on the counter number
    private Queue<CustomerInformation> getQueueByCounterNumber(int counterNumber) 
    {
        switch (counterNumber) 
        {
            case 1:
                return counterQueues.getCounter1Queue();
            case 2:
                return counterQueues.getCounter2Queue();
            case 3:
                return counterQueues.getCounter3Queue();
            default:
                return null;
        }
    }
    
    // Method to display each customer information and the amount purchase for each customer from the completeStack
    public void displayCompleteStackDetails() 
    {
        System.out.println("*************************************************************");
        System.out.println("PAID CUSTOMER");
        System.out.println("*************************************************************");
        while (!completeStack.isEmpty()) 
        {
            CustomerInformation customer = completeStack.pop();
            displayCustomerDetails(customer);
        }
    }

}
