package base.gc.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
//软引用在堆内存紧张时进行回收 -Xmx10m
public class SoftRefQ {
	public static class User{
		public User(int id, String name) {
			this.id = id;
			this.name = name;
		}
		public int id;
		public String name;
		@Override
		public String toString() {
			return "[id="+String.valueOf(id) + ", name = "+name+"]";
		}
	}
	
	static ReferenceQueue<User> softQueue = null;
	public static class CheckRefQueue implements Runnable{

		public void run() {
			while(true){
				if(softQueue!=null){
					UserSoftReference obj = null;
					try {
						obj = (UserSoftReference) softQueue.remove();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(obj != null){
						System.out.println("user id"+obj.uid+" is delete");
					}
				}
			}
		}
		
	}
	
	public static class UserSoftReference extends SoftReference<User>{
		int uid;
		public UserSoftReference (User referent, ReferenceQueue<? super User> q) {
			super(referent, q);
			uid = referent.id;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new CheckRefQueue());
		t.setDaemon(true);
		t.start();
		User u = new User(1, "geym");
		softQueue = new ReferenceQueue<User>();
		UserSoftReference userSoftRef = new UserSoftReference(u,softQueue);
		u = null;
		
		System.out.println(userSoftRef.get());
		System.gc();
		System.out.println("After GC:");
		System.out.println(userSoftRef.get());
		
		byte[] b = new byte[1024*985*7];
		System.gc();
		System.out.println(userSoftRef.get());
		Thread.sleep(1000);
	}
}
