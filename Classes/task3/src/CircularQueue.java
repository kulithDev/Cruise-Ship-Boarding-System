public class CircularQueue {
    int SIZE = 6; // Size of Circular Queue
    int front, rear;
    Passenger items[] = new Passenger[SIZE];

    public CircularQueue(){
        front = -1;
        rear = -1;
    }

    // Check if the queue is full
    private boolean isFull() {
        if (front == 0 && rear == SIZE - 1) {
            return true;
        }
        if (front == rear + 1) {
            return true;
        }
        return false;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        if (front == -1)
            return true;
        else
            return false;
    }

    public void enQueue(Passenger element) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            if (front == -1)
                front = 0;
            rear = (rear + 1) % SIZE;
            items[rear] = element;
            System.out.println("Inserted " + element);
        }
    }

    public Passenger deQueue() {
        Passenger element;
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        } else {
            element = items[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } /* Q has only one element, so we reset the queue after deleting it. */
            else {
                front = (front + 1) % SIZE;
            }
            return (element);
        }
    }

    public void show(){
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.println(items[i].getFirstName());
            }
        }
    }
}
