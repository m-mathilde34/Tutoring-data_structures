package dataStructures.stack;

public class MyStackImp<T> implements MyStack<T>
{

    private T[] list;
    private int lastItemPointer = -1;

    /**
     * Constructor that creates and initialises the object
     */
    public MyStackImp()
    {
        list = (T[]) new Object[4];
    }

    public T[] recreateList() {

        T[] newList = (T[]) new Object[lastItemPointer+2];

        for(int i=0; i<=lastItemPointer; i++) {
            newList[i] = list[i];
        }
        return newList;
    }

    /**
     * Push a new element onto the top of the stack
     * @param element
     */
    @Override
    public void push(T element) {

        //check size list against lastItemPointer
        //if pointer = size-1, need to recreate list
        //else add it to top
        if(lastItemPointer == list.length-1){
            list = recreateList();
        }

        lastItemPointer +=1;
        list[lastItemPointer] = element;

    }

    /**
     * Return the top element from the stack, removing it from the list
     * if the stack is empty, throw a runtime exception
     * @return element
     */
    @Override
    public T pop()
    {
        //check if the stack is empty
        if(lastItemPointer == -1){
            throw new RuntimeException();
        }

        T lastElement = list[lastItemPointer];
        lastItemPointer -=1;

        return lastElement;
    }

    /**
     * Return the top element from the stack, WITHOUT removing it from the list
     * if the stack is empty, throw a runtime exception
     * @return element
     */
    @Override
    public T peek()
    {
        //check whether the stack is empty
        if(lastItemPointer == -1){
            throw new RuntimeException();
        }

        return list[lastItemPointer];
    }

    /**
     * Return a new list at twice the size of the original list with all the elements
     * entered as in the original
     * @param list
     * @return
     */
    private T[] resize(T[] list)
    {
        return null;
    }

    /**
     * @return {@code true} if  the stack is empty, {@code false} if contains elements
     */
    @Override
    public boolean isEmpty()
    {
        if(lastItemPointer == -1){
            return true;
        }
        return false;
    }
}
