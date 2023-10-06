/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <
 * Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 *  https://catalog.upenn.edu/pennbook/code-of-academic-integrity/
 * >
 * Signed,
 * Author: YOUR NAME HERE
 * Penn email: <YOUR-EMAIL-HERE@seas.upenn.edu>
 * Date: <YYYY-MM-DD>
 */


public class MyArrayList<E> {

    private static final int INITIAL_CAPACITY = 4;
    
    protected Object[] data;
    protected int size = 0;
/*Step 1:Java Generics */
    public MyArrayList() {
        data = new Object[INITIAL_CAPACITY];
    }
/*Step 5. Create constructor to initialize underlying array */
    public MyArrayList(E[] arr) {
        if (arr == null || arr.length == 0) {
            data = new Object[INITIAL_CAPACITY];
        }
        else { 
            data = new Object[Math.max(arr.length, INITIAL_CAPACITY)];
            for (int i = 0; i < arr.length; i++) {
                data[i] = arr[i];
                size++;
            }
        }
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else
            return (E) data[index];
    }

    private void increaseCapacity() {
        Object[] newData = new Object[Math.max(2 * data.length, INITIAL_CAPACITY)];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    public void add(E value) {
        if (size == data.length) {
            increaseCapacity();
        }
        data[size++] = value;
    }

    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == data.length) {
            increaseCapacity();
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }
/*Step 2. Implement remove method */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E target = (E) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;
    /*Step 3. Shrink array when it is too large */
        if (size <= data.length * 0.25) {
            Object[] newData = new Object[data.length / 2];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
        return target;
    }

    public boolean remove(E obj) {
        for (int i = 0; i < size; i++) {
            if (obj == data[i] || (data[i] != null && data[i].equals(obj))) {
                for (int j = i; j < size - 1; j++) {
                    data[j] = data[j + 1];
                }
                size--;
                data[size] = null;
                if (size <= data.length * 0.25) {
                    Object[] newData = new Object[data.length / 2];
                    System.arraycopy(data, 0, newData, 0, size);
                    data = newData;
                }
                return true;
            }
        }
        return false;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ": " + data[i]);
        }
    }

    public boolean contains(E obj) {
        for (int i = 0; i < size; i++) {
            if (obj == data[i] || (data[i] != null && data[i].equals(obj)))
                return true;
        }
        return false;
    }
/*Step 4. Implement set(index, value) method */
    public E set(int index, E obj) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E target = (E) data[index];
        data[index] = obj;
        return target;
    }

}