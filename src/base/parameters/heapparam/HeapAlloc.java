package base.parameters.heapparam;

public class HeapAlloc {
	public static void main(String[] args) {
		System.out.println("max mem = ");//Xmx
		System.out.println(Runtime.getRuntime().maxMemory() + " bytes");
		System.out.println("free mem = ");
		System.out.println(Runtime.getRuntime().freeMemory() + " bytes");
		System.out.println("total mem = ");//>=Xms
		System.out.println(Runtime.getRuntime().totalMemory() + " bytes");
		
		byte[] bytes = new byte[1 * 1024 * 1024];
		System.out.println("分配了1m空间给数组");
		
		System.out.println("max mem = ");
		System.out.println(Runtime.getRuntime().maxMemory() + " bytes");
		System.out.println("free mem = ");
		System.out.println(Runtime.getRuntime().freeMemory() + " bytes");
		System.out.println("total mem = ");
		System.out.println(Runtime.getRuntime().totalMemory() + " bytes");
		
		bytes = new byte[4 * 1024 * 1024];
		System.out.println("分配了4m空间给数组");
		
		System.out.println("max mem = ");
		System.out.println(Runtime.getRuntime().maxMemory() + " bytes");
		System.out.println("free mem = ");
		System.out.println(Runtime.getRuntime().freeMemory() + " bytes");
		System.out.println("total mem = ");
		System.out.println(Runtime.getRuntime().totalMemory() + " bytes");
	}
}
