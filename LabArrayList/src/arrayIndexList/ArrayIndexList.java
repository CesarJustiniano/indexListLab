package arrayIndexList;

import java.lang.reflect.Array;

import indexList.IndexList;

public class ArrayIndexList<E> implements IndexList<E> {
	private static final int INITCAP = 1; 
	private static final int CAPTOAR = 1; 
	private static final int MAXEMPTYPOS = 2; 
	private E[] element; 
	private int size; 

	public ArrayIndexList() { 
		element = (E[]) new Object[INITCAP]; 
		size = 0; 
	} 
	

	public void add(int index, E e) throws IndexOutOfBoundsException {
		// ADD CODE AS REQUESTED BY EXERCISES
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		}
		if(size == element.length){
			changeCapacity(CAPTOAR);
		}
		moveDataOnePositionTR(index, element.length-2);
		element[index] = e;
		size++;
	}


	public void add(E e) {
		// ADD CODE AS REQUESTED BY EXERCISES
		if(size == element.length){
			changeCapacity(CAPTOAR);
		}
		element[size] = e;
		size++;
	}


	public E get(int index) throws IndexOutOfBoundsException {
		// ADD AND MODIGY CODE AS REQUESTED BY EXERCISES
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		}
		return element[index]; 
	}


	public boolean isEmpty() {
		return size == 0;
	}


	public E remove(int index) throws IndexOutOfBoundsException {
		// ADD AND MODIFY CODE AS REQUESTED BY EXERCISES
		if(index < 0 || index > size || isEmpty()){
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		}
		this.moveDataOnePositionTL(index, element.length-1);
		size--;
		return element[index];
	}


	public E set(int index, E e) throws IndexOutOfBoundsException {
		// ADD AND MODIFY CODE AS REQUESTED BY EXERCISES
		if(index < 0 || index > element.length){
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		}
		element[index] = e;
		return e;
	}


	public int size() {
		return size;
	}	
	
	
	
	// private methods  -- YOU CAN NOT MODIFY ANY OF THE FOLLOWING
	// ... ANALYZE AND USE WHEN NEEDED
	
	// you should be able to decide when and how to use
	// following method.... BUT NEED TO USE THEM WHENEVER
	// NEEDED ---- THIS WILL BE TAKEN INTO CONSIDERATION WHEN GRADING
	
	private void changeCapacity(int change) { 
		int newCapacity = element.length + change; 
		E[] newElement = (E[]) new Object[newCapacity]; 
		for (int i=0; i<size; i++) { //then why they use size here?
			newElement[i] = element[i]; 
			element[i] = null; 
		} 
		element = newElement; 
	}
	
	// useful when adding a new element with the add
	// with two parameters....
	private void moveDataOnePositionTR(int low, int sup) { 
		// pre: 0 <= low <= sup < (element.length - 1)
		for (int pos = sup; pos >= low; pos--)
			element[pos+1] = element[pos]; 
	}

	// useful when removing an element from the list...
	private void moveDataOnePositionTL(int low, int sup) { 
		// pre: 0 < low <= sup <= (element.length - 1)
		for (int pos = low; pos <= sup; pos++)
			element[pos-1] = element[pos]; 
	}


	// The following two methods are to be implemented as part of an exercise
	public Object[] toArray() {
		Object[] array = new Object[size]; 
	    for(int i=0;i<size;i++){
	    	array[i] = element[i];
	    	i++;
	    }
	    return array;
	}


	@Override
	public <T1> T1[] toArray(T1[] array) {
		if (array.length < size) { 
	    	array = (T1[]) Array.newInstance(array.getClass().getComponentType(), size);
	    } else if (array.length > size) 
	    	for (int j=size; j< array.length; j++) 
	    		array[j] = null;
	    
		for(int i=0;i<size;i++){
			array[i] = (T1) element[i];
		}
	    return array;	
	}


	@Override
	public int capacity() {
		return element.length;
	}

}
