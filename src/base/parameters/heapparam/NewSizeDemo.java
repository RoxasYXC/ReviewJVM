package base.parameters.heapparam;

public class NewSizeDemo {
	public static void main(String[] args) {
		byte[] b = null;
		for (int i = 0; i < 10; i++) {
			b = new byte[1 * 1024 * 1024];
		}
		//-Xmx20m -Xms20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
		/* Java HotSpot(TM) 64-Bit Server VM warning: NewSize (1536k) is greater than the MaxNewSize (1024k). A new max generation size of 1536k will be used.
			Heap
			PSYoungGen      total 1024K, used 475K [0x00000007bfe80000, 0x00000007c0000000, 0x00000007c0000000)
			eden space 512K, 92% used [0x00000007bfe80000,0x00000007bfef6dc0,0x00000007bff00000)
			from space 512K, 0% used [0x00000007bff80000,0x00000007bff80000,0x00000007c0000000)
			to   space 512K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007bff80000)
			ParOldGen       total 18944K, used 10240K [0x00000007bec00000, 0x00000007bfe80000, 0x00000007bfe80000)
			object space 18944K, 54% used [0x00000007bec00000,0x00000007bf6000a0,0x00000007bfe80000)
			Metaspace       used 2865K, capacity 4486K, committed 4864K, reserved 1056768K
			class space    used 289K, capacity 386K, committed 512K, reserved 1048576K
		 */
		//eden区无法容纳byte数组10m全部分配到了老年代
		
		//-Xmx20m -Xms20m -Xmn7m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
		/*[GC (Allocation Failure) [PSYoungGen: 3678K->1488K(5632K)] 3678K->1496K(18944K), 0.0011204 secs] [Times: user=0.00 sys=0.01, real=0.00 secs] 
			[GC (Allocation Failure) [PSYoungGen: 4640K->1440K(5632K)] 4648K->1456K(18944K), 0.0010372 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
			[GC (Allocation Failure) [PSYoungGen: 4664K->1424K(5632K)] 4680K->1440K(18944K), 0.0004964 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
			Heap
			 PSYoungGen      total 5632K, used 2489K [0x00000007bf900000, 0x00000007c0000000, 0x00000007c0000000)
			  eden space 4096K, 26% used [0x00000007bf900000,0x00000007bfa0a558,0x00000007bfd00000)
			  from space 1536K, 92% used [0x00000007bfd00000,0x00000007bfe64020,0x00000007bfe80000)
			  to   space 1536K, 0% used [0x00000007bfe80000,0x00000007bfe80000,0x00000007c0000000)
			 ParOldGen       total 13312K, used 16K [0x00000007bec00000, 0x00000007bf900000, 0x00000007bf900000)
			  object space 13312K, 0% used [0x00000007bec00000,0x00000007bec04000,0x00000007bf900000)
			 Metaspace       used 2866K, capacity 4486K, committed 4864K, reserved 1056768K
			  class space    used 289K, capacity 386K, committed 512K, reserved 1048576K
		 * */
		//eden可以容纳byte数组，但由于不能满足全部10m的分配，故进行了三次minorGC
		
		//-Xmx20m -Xms20m -Xmn18m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
		/*
		Heap
		 PSYoungGen      total 16896K, used 11469K [0x00000007bee00000, 0x00000007c0000000, 0x00000007c0000000)
		  eden space 15360K, 74% used [0x00000007bee00000,0x00000007bf933690,0x00000007bfd00000)
		  from space 1536K, 0% used [0x00000007bfe80000,0x00000007bfe80000,0x00000007c0000000)
		  to   space 1536K, 0% used [0x00000007bfd00000,0x00000007bfd00000,0x00000007bfe80000)
		 ParOldGen       total 2048K, used 0K [0x00000007bec00000, 0x00000007bee00000, 0x00000007bee00000)
		  object space 2048K, 0% used [0x00000007bec00000,0x00000007bec00000,0x00000007bee00000)
		 Metaspace       used 2865K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 289K, capacity 386K, committed 512K, reserved 1048576K
		 * */
		//eden完全可以容纳10m，故没有gc
		
		//-Xmx20m -Xms20m -XX:NewRatio=2 -XX:+PrintGCDetails
		/*
		[GC (Allocation Failure) [PSYoungGen: 4772K->448K(6144K)] 4772K->1480K(19968K), 0.0010391 secs] [Times: user=0.00 sys=0.01, real=0.00 secs] 
		[GC (Allocation Failure) [PSYoungGen: 5678K->448K(6144K)] 6710K->2504K(19968K), 0.0008923 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
		Heap
		 PSYoungGen      total 6144K, used 1697K [0x00000007bf980000, 0x00000007c0000000, 0x00000007c0000000)
		  eden space 5632K, 22% used [0x00000007bf980000,0x00000007bfab86b0,0x00000007bff00000)
		  from space 512K, 87% used [0x00000007bff80000,0x00000007bfff0000,0x00000007c0000000)
		  to   space 512K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007bff80000)
		 ParOldGen       total 13824K, used 2056K [0x00000007bec00000, 0x00000007bf980000, 0x00000007bf980000)
		  object space 13824K, 14% used [0x00000007bec00000,0x00000007bee02020,0x00000007bf980000)
		 Metaspace       used 2866K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 289K, capacity 386K, committed 512K, reserved 1048576K
		 * */
		//新生代6m，类似第二种情况
	}
}
