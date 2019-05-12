package test.haha;

public class FanxingMethod {
	public static void main(String[] args) {
		Integer[] integers = {1,3, 4};
		String[] strings = {"1222","ben"};
		FanxingMethod.<Integer>print(integers);
		FanxingMethod.<String>print(strings);
	}
	public static <E> void print (E[] lists) {
		System.out.print(lists[1]);
	}
}
