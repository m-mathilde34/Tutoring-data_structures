package dataStructures.queue;

import java.util.Stack;

public class ReverseQueueExersize {
    /*
    This file provides an opportunity to practice using the Queue implementation.
    Your task is to reverse a Queue.
    The ReverseQueue test will provide the Queue assuming the MyQueueImp is completely implemented.
    Please complete the below method to reverse the Queue. You can complete this in any manner you would like.
    Hint: What data structures could be helpful in reversing the Queue order?
     */

    public static <T> MyQueue<T> reverseQueue(MyQueue<T> queue)
    {
        Stack<T> myStack = new Stack<>();

        // Empty queue in the stack following Queue's FIFO.
        while(!queue.isEmpty()){
            myStack.push(queue.dequeue());
        }

        // Reverse the queue using stack's FILO
        while(!myStack.isEmpty()){
            queue.enqueue(myStack.pop());
        }

        return queue;
    }
}
