package base.heap;

import java.util.ArrayList;

public class HeapSpaceOOM {
	public static void main(String[] args) {
		ArrayList<byte[]> list = new ArrayList<byte[]>();
		try {
			for (int i = 0; i < 1024; i++) {
				list.add(new byte[10240*1024]);
			}
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			System.out.println("OOM是可以被捕捉的");
		}
		try {
			Thread.sleep(300000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("捕捉后是可以继续执行的");
	}
}
