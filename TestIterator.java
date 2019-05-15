package test.haha;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;



public class TestIterator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Collection<String> collection = new ArrayList<>();
		collection.add("New York");
		collection.add("Atlanta");
		collection.add("Dallas");
		collection.add("mADISON");
		
		/*
		 * Iterator<String> iterator = collection.iterator(); while (iterator.hasNext())
		 * { System.out.println(iterator.next()); }
		 */
		
		for (String ele: collection) {
			System.out.println(ele);
		}
		
	}

}
