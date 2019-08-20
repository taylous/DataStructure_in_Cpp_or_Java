
public class RadixSort {
	private final static int ARRSIZE = 20;

	public static void main(String[] args) {
		int[] arr = new int[ARRSIZE];
		for(int i = 0; i < ARRSIZE; i++) {
			arr[i] = (int)(Math.random() * 1000);
		}
		
		System.out.print("sorting 전 : ");
		for(int i = 0; i < 20; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		int reNum = getRadixMax(arr);
		for(int i = 0, j = 1; i < reNum; i++, j *= 10) {
			radixSort(arr, j);
		}
		
		System.out.print("sorting 후 : ");
		for(int i = 0; i < 20; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	private static void radixSort(int[] list, int position) {
		MyLinkedList[] count = new MyLinkedList[10];
		for(int i = 0; i < 10; i++) {
			count[i] = new MyLinkedList();
		}
		
		int index = -1;
		MyLinkedList temp;
		for(int i = 0; i < ARRSIZE; i++) {
			index = (list[i] / position) % 10;
			temp = count[index];
			while(temp.next != null) temp = temp.next;
			temp.next = new MyLinkedList(list[i], null);
		}
		
		int start = 0;
		for(int i = 0; i < 10; i++) {
			temp = count[i].next;
			while(temp != null) {
				list[start++] = temp.data;
				temp = temp.next;
			}
		}
	}
	
	private static int getRadixMax(int[] list) {
		int max = list[0];
		int returnNum = 1;
		for(int i = 1; i < ARRSIZE; i++) {
			if(max < list[i]) {
				max = list[i];
			}
		}
		
		while((max /= 10) > 0) returnNum++;
		
		return returnNum;
	}
	
	static class MyLinkedList {
		int data;
		MyLinkedList next;
		
		public MyLinkedList() {
			super();
		}

		public MyLinkedList(int data, MyLinkedList next) {
			super();
			this.data = data;
			this.next = next;
		}
	}
}
