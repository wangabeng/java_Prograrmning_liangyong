package test.haha;

import java.util.ArrayList;

public class TestCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// collection1
		ArrayList<String> collection1 = new ArrayList<>();
		collection1.add("New York");
		collection1.add("Atlanta");
		collection1.add("Dallas");
		collection1.add("Madison");
		
		System.out.println("collection1" + collection1);
		
		// collection2
		ArrayList<String> collection2 = new ArrayList<>();
		collection2.add("Seattle");
		collection2.add("Portland");
		collection2.add("Los Angeles");
		collection2.add("Atlanta");
		
		System.out.println("collection1" + collection2);
		
		// c1
		ArrayList<String> c1 = (ArrayList<String>)(collection1.clone());
		System.out.println("c1" + c1);
		
		// �ϲ�c1��collection2
		c1.addAll(collection2);
		System.out.println("c1�ϲ�collection2" + c1);
		
		// �
		c1.removeAll(collection2);
		System.out.println("c1�collection2" + c1);
		
		// ����
		ArrayList<String> c2 = (ArrayList<String>)(collection1.clone());
		c2.retainAll(collection2);
		System.out.println("c2�collection2" + c2);
	}

}
