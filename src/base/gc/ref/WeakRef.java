package base.gc.ref;

import java.lang.ref.WeakReference;
//弱引用在gc时进行回收 -Xmx10m
public class WeakRef {
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
		WeakReference<User> userSoftRef = new WeakReference<User>(u);
		u = null;
		
		System.out.println(userSoftRef.get());
		System.gc();
		System.out.println("After GC:");
		System.out.println(userSoftRef.get());
		
	}
}
