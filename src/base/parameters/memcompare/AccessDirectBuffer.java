package base.parameters.memcompare;

import java.nio.ByteBuffer;
//堆外读写对堆内读写优势明显
public class AccessDirectBuffer {
	public void directAccess(){
		long beg = System.currentTimeMillis();
		ByteBuffer b = ByteBuffer.allocateDirect(500);
		for (int i = 0; i < 100000; i++) {
			for (int j = 0; j < 99; j++) {
				b.putInt(j);
			}
			b.flip();
			for (int j = 0; j < 99; j++) {
				b.getInt();
			}
			b.clear();
		}
		System.out.println("testDirectWrite:" + (System.currentTimeMillis() - beg));
	}
	
	public void bufferAccess(){
		long beg = System.currentTimeMillis();
		ByteBuffer b = ByteBuffer.allocate(500);
		for (int i = 0; i < 100000; i++) {
			for (int j = 0; j < 99; j++) {
				b.putInt(j);
			}
			b.flip();
			for (int j = 0; j < 99; j++) {
				b.getInt();
			}
			b.clear();
		}
		System.out.println("testBufferWrite:" + (System.currentTimeMillis() - beg));
	}
	
	public static void main(String[] args) {
		AccessDirectBuffer adb = new AccessDirectBuffer();
		adb.bufferAccess();
		adb.directAccess();
		
		adb.bufferAccess();
		adb.directAccess();
	}
}
