package dataStructures.queue;

import java.util.Stack;

public class MyQueueImp<T> implements MyQueue<T>
{

    private Stack<T> stack1;
    private Stack<T> stack2;

    /**
     * A two stack implementation of a First In First Out queue
     */
    public MyQueueImp()
    {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /**
     * Add a new element to the back of the queue
     * @param element
     */
    @Override
    public void enqueue(T element)
    {
        stack1.push(element);
    }

    /**
     * Return the front element of the queue and remove it from
     * the data structure. Throw a runtime exception if the queue is empty
     * @return the front element
     */
    @Override
    public T dequeue()
    {
        if(stack1.isEmpty() && stack2.isEmpty()){
            throw new RuntimeException();
        }

        if(stack2.isEmpty()){
            transferStacks();
        }

        return stack2.pop();
    }

    /**
     * Using two stacks, figure out when each stack must be used
     */
    private void transferStacks()
    {
        while(!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

    }

    /**
     * Take a look at the front of the queue without removing it
     * @return the first element of the queue
     */
    @Override
    public T first()
    {
        if(stack2.isEmpty()){
            transferStacks();
        }
        return stack2.peek();
    }

    /**
     * @return the number of elements in the queue
     */
    @Override
    public int size()
    {
        return stack1.size() + stack2.size();
    }

    /**
     * @return true if both stacks are empty
     */
    @Override
    public boolean isEmpty()
    { return size() == 0; }
}
