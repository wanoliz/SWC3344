
public class Main 
{
    public static void main(String[] args) 
    {
        CheckoutSystem checkoutSystem = new CheckoutSystem();
        
        System.out.println("\t\t WELCOME TO HWAA OUTLET ");        
        
        // Read customer information and items purchased from the input file
        checkoutSystem.readInputFile("cust.txt");

        // Process customers and place them at the counters based on the rules
        checkoutSystem.processCustomers();

        // Display customer information and total amount paid for each counter
        checkoutSystem.displayCounterDetails();

        // Process payments for each counter and store the paid customers in the completeStack
        checkoutSystem.processPayments();

        // Display each customer information and the amount purchase from the completeStack
        checkoutSystem.displayCompleteStackDetails();
        
        System.out.println("\t\t THANK YOU FOR COMING :D ");
        
    }
}
