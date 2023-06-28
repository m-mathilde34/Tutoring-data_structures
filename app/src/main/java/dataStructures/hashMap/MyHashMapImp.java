package dataStructures.hashMap;

import java.lang.Math;

public class MyHashMapImp<K, V> implements MyHashMap<K, V>
{
    /** An array of HashNodes that are the buckets for multiple values (or linked list heads per bucket) */
    private HashNode<K, V>[] array;             // Take a look at HashNode implementation to see what you can do with it
    private int size;

    /**
     * Constructor, initialise the list of HashNodes that hold the values
     */
    public MyHashMapImp()
    {
        array = new HashNode[10];
    }


    /**
     * Based on the hash value of our Key/Value pair, create a new array big enough to accommodate our hash as
     * the index of this pair.
     * @param array - The original array which is going to be copied into a bigger array.
     * @param hash - The hash of our key which will serve as our key/value pair's index
     * */
    private HashNode<K, V>[] resize(HashNode<K, V>[] array, int hash){

        HashNode<K, V>[] new_array = new HashNode[hash+1];
        System.arraycopy(array, 0, new_array, 0, array.length);

        return new_array;

    }

    /**
     * Put a ket/value pair into the hashMap, use the {@code hashCode()} method that is implemented in every object as
     * default.  This will return an integer value hash of the object.
     * @param key - A unique key that will be hashed (potentially two keys hash to the same integer)
     * @param value - The value of type `V` that will need to be stored
     */
    @Override
    public void put(K key, V value)
    {

        // calculate the hash using HashCode() inbuilt method
        int hash = Math.abs(key.hashCode());

        // check is a resize is needed
        if(hash >= array.length){
            array = resize(array, hash);
        }

        // check for collisions
        if(array[hash] != null){
            HashNode<K, V> currentNode = array[hash];
            while(currentNode.next != null){
                currentNode = currentNode.next;
            }
            currentNode.next = new HashNode<>(key, value);
        } else{
            array[hash] = new HashNode<>(key, value);
        }

        size++;

    }


    /**
     * Find the value associated with the key
     * @param key - A unique hashable object
     * @return V value (the associated value of type V) Ensure that you return the right value in cases where two objects
     * hash to the same integer (Hint: use the keys to check the buckets)
     */
    @Override
    public V get(K key)
    {
        // find the key's hash
        int hash = Math.abs(key.hashCode());
        HashNode<K, V> node = array[hash];

        if(node.next == null){
            return node.value;
        }

        // check for collisions
        while(node.key != key){
            node = node.next;
        }

        return node.value;
    }


    @Override
    public boolean contains(K key)
    {
        return false;
    }


    @Override
    public boolean isEmpty()
    {
        return (size == 0);
    }


    @Override public int size()
    {
        return size;
    }


    @Override public boolean remove(K key)
    {
        return false;
    }


    @Override public boolean remove(K key, V value)
    {
        return false;
    }
}
