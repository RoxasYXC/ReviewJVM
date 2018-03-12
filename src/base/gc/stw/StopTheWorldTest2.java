package base.gc.stw;

import java.util.HashMap;
//-Xmx1g -Xms1g -Xmn900m -XX:SurvivorRatio=1 -XX:+UseSerialGC -Xloggc:newgc.log -XX:+PrintGCDetails
public class StopTheWorldTest2 {
	public static class MyThread extends Thread{
		HashMap map = new HashMap();
		@Override
		public void run() {
			try {
				while (true) {
					if(map.size() * 512 /1024/1024>=550){
						map.clear();
						System.out.println("clean map");
					}
					byte[] b1;
					for (int i = 0; i < 100; i++) {
						b1 = new byte[512];
						map.put(System.nanoTime(), b1);
					}
						Thread.sleep(1);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static class PrintThread extends Thread{
		public static final long STARTTIME  = System.currentTimeMillis();
		
		@Override
		public void run() {
			try {
				while(true){
					long t = System.currentTimeMillis() - STARTTIME;
					System.out.println(t/1000+"."+t%1000);
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		MyThread t = new MyThread();
		PrintThread p = new PrintThread();
		t.start();
		p.start();
	}
}
