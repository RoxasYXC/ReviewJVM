package base.gc.ref;

import java.lang.ref.SoftReference;
//软引用在堆内存紧张时进行回收 -Xmx10m
public class SoftRef {
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
	
	public static void main(String[] args) {
		User u = new User(1, "geym");
		SoftReference<User> userSoftRef = new SoftReference<User>(u);
		u = null;
		
		System.out.println(userSoftRef.get());
		System.gc();
		System.out.println("After GC:");
		System.out.println(userSoftRef.get());
		
		byte[] b = new byte[1024*985*7];
		System.gc();
		System.out.println(userSoftRef.get());
	}
}
