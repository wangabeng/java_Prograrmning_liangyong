package test.haha;

import java.util.*;

public class TestArrayAndLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ArrayList
		List<Integer> arrayList = new ArrayList<>();
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		arrayList.add(1);
		arrayList.add(4);
		arrayList.add(6);
		System.out.println(arrayList);
		
		// linkedList
		LinkedList<Object> linkedList = new LinkedList<>(arrayList);
		linkedList.add(1, "red");
		linkedList.removeLast();
		linkedList.addFirst("green");
		System.out.println(linkedList);
		
		// 迭代器
		ListIterator<Object> listIterator = linkedList.listIterator();
		/*
		 * while (listIterator.hasNext()) { System.out.println(listIterator.next()); }
		 */
		
		// 带指针的迭代器
		listIterator = linkedList.listIterator(linkedList.size());
		while (listIterator.hasPrevious()) {
			System.out.println(listIterator.previous());
		}
	}

}
