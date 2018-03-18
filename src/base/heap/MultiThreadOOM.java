package base.heap;

public class MultiThreadOOM {
	public static class SleepThread implements Runnable{

		public void run() {
			try {
				Thread.sleep(1000000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 20000; i++) {
			new Thread(new SleepThread()).start();
			System.out.println(i);
		}
	}
}
