package base.memory.tlab;
/**
 * -XX:+UseTLAB -Xcomp -XX:-BackgroundCompilation -XX:-DoEscapeAnalysis  cost 78ms
 * -XX:-UseTLAB -Xcomp -XX:-BackgroundCompilation -XX:-DoEscapeAnalysis cost 156ms
 *  -XX:+UseTLAB -XX:+PrintTLAB -XX:+PrintGC -XX:TLABSize=102400 -XX:-ResizeTLAB -XX:TLABRefillWasteFraction=100 -XX:-DoEscapeAnalysis 
 * 栈上分配比TLAB NB多了 
 * */
public class UseTLAB {
	public static void alloc(){
		byte[] b = new byte[2];
		b[0] = 1;
	}
	
	public static void main(String[] args) {
		long b = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			alloc();
		}
		System.out.println(System.currentTimeMillis() - b);
	}
}
