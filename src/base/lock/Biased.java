package base.lock;

import java.util.List;
import java.util.Vector;
// -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
// -XX:-UseBiasedLocking 
public class Biased {
	public static List<Integer> list = new Vector<Integer>();
	public static void main(String[] args) {
		long beg = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			list.add(i);
		}
		System.out.println(System.currentTimeMillis() - beg);
	}
}
