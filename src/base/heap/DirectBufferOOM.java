package base.heap;

import java.nio.ByteBuffer;
//-XX:MaxDirectMemorySize=1024m
public class DirectBufferOOM {
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 10240; i++) {
				ByteBuffer.allocateDirect(10240*1024);
				System.out.println(i);
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
