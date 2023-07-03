import java.util.LinkedList;
import java.util.Queue;

class CounterQueues
{
    private Queue<CustomerInformation> counter1Queue;
    private Queue<CustomerInformation> counter2Queue;
    private Queue<CustomerInformation> counter3Queue;

    public CounterQueues() 
    {
        counter1Queue = new LinkedList<>();
        counter2Queue = new LinkedList<>();
        counter3Queue = new LinkedList<>();
    }

    public Queue<CustomerInformation> getCounter1Queue() 
    {
        return counter1Queue;
    }

    public Queue<CustomerInformation> getCounter2Queue() 
    {
        return counter2Queue;
    }

    public Queue<CustomerInformation> getCounter3Queue()
    {
        return counter3Queue;
    }
    
    public Queue<CustomerInformation> getQueueByCounterNumber(int counterNumber) 
    {
        switch (counterNumber) 
        {
            case 1:
                return counter1Queue;
            case 2:
                return counter2Queue;
            case 3:
                return counter3Queue;
            default:
                return null;
        }

}
}
