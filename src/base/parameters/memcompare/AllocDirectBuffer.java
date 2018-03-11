package base.parameters.memcompare;

import java.nio.ByteBuffer;
//堆外分配对堆内分配没特别优势，甚至更慢
public class AllocDirectBuffer {
	public void directAllocate(){
		long beg = System.currentTimeMillis();
		for (int i = 0; i < 200000; i++) {
			ByteBuffer b = ByteBuffer.allocateDirect(1000);
		}
		System.out.println("directAllocate:" + (System.currentTimeMillis() - beg));
	}
	
	public void bufferAllocate(){
		long beg = System.currentTimeMillis();
		for (int i = 0; i < 200000; i++) {
			ByteBuffer b = ByteBuffer.allocate(1000);
		}
		System.out.println("bufferAllocate:" + (System.currentTimeMillis() - beg));
	}
	
	public static void main(String[] args) {
		AllocDirectBuffer adb = new AllocDirectBuffer();
		adb.bufferAllocate();
		adb.directAllocate();
		adb.bufferAllocate();
		adb.directAllocate();
	}
}
