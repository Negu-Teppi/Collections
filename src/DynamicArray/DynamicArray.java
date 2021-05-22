import java.util.Iterator;

/**
 * DynamicArray
 */
public class DynamicArray<T> implements Iterable<T> {

    private T[] arr;
    private int capacity=0;
    private int size=0;

    
    

    public DynamicArray() {
        this(10);
    }

    public DynamicArray(int capacity) {
        
        if(capacity<0) throw new IllegalArgumentException("Capacity cannot be negative: "+capacity);
        this.capacity=capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public T get (int index){
        return arr[index];
    }

    public void set(int index, T element){
        arr[index]=element;
    }

    public void clean(){
        for(int i=0; i<size; i++){
            arr[i]=null;
        }
        size=0;
    }

    public void add(T element){
        if(size>=capacity-1){
            if(capacity==0) capacity=1;
            else{
                capacity*=2;
            } 
            T[] newArr= (T[])new Object[capacity];
            for(int i=0; i<arr.length; i++){
                newArr[i]= arr[i];
            
            }
            //orveride newArr on arr
            arr= newArr;
        }

        // capacity> size
        arr[size++]=element;
    }

    public void removeAt(int removeIndex){
        if(removeIndex>size || removeIndex<=0) throw new IndexOutOfBoundsException();
        T[] newArr = (T[])new Object[size-1];


        for( int oldArrIndex =0, newArrayIndex=0; oldArrIndex<size; oldArrIndex++, newArrayIndex++){
            if(oldArrIndex==removeIndex) newArrayIndex--;
            else newArr[newArrayIndex]=arr[oldArrIndex];
        }
        arr=newArr;
        capacity=--size;
    }

    public void remove(Object o){
        int removeIndex = indexOf(o);

        removeAt(removeIndex);
        
    }

    public int indexOf(Object object) {
        for(int i=0; i<size;i++){
            if(object==null){
                if(arr[i]==null)
                    return i;
            }    
            else{
                if(object.equals(arr[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean contains(Object object){
        return indexOf(object)!=-1;
    }

    @Override
    public Iterator<T> iterator() {

        //anonymous class
        return new Iterator<T>(){
            int index=0;
            @Override
            public boolean hasNext() {
                
                return index<size;
            }

            @Override
            public T next() {
                // return arr[index]=> index++
                return arr[index++];
            }
            
        };
    }

    @Override
    public String toString() {
        if(isEmpty()) return"[]";
        else{
            StringBuilder sp= new StringBuilder(size);
            sp.append("[");
            for(int i=0; i<size-1; i++){
                sp.append(arr[i]).append(",");
            }
            sp.append(arr[size-1]).append("]");
            return sp.toString();
        }
    }

    
}