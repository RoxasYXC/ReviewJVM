
# JVM常用参数
- 垃圾回收的跟踪
	- -XX:+PrintGC 打印GC概要
	- -XX: +PrintGCDetails 打印GC详细信息
	- -XX:+PrintHeapAtGC 打印每次GC前后的堆信息
	- -XX:+PrintGCTimeStamp 额外打印GC发生的时间，输出的是vm启动后的时间偏移量
	- -XX:+PrintGCApplicationConcurrentTime 打印程序的执行时间
	- -XX:+PrintGCApplicationStoppedTime 打印GC产生的停顿时间
	- -XX:+PrintReferenceGC 跟踪软引用、弱引用、虚引用和Finalize队列
	- -Xloggc:<path> gc信息输出到指定path

- 类加载、卸载的跟踪
	- -verbose:class 跟踪类的加载和卸载
	- -XX:+TraceClassLoading 单独跟踪类的加载
	- -XX:+TraceClassUnloading 单独跟踪类的卸载

- 系统参数查看
	- -XX:+PrintVMOptions 打印虚拟机从命令行接收到的显示参数
	- -XX:+PrintCommandLineFlags 打印虚拟机从命令行接收到的显式参数和虚拟机自行设置的隐式参数	
	- -XX:+PrintFlagsFinal 查看系统详细参数

- 堆参数
	- Xms 初始堆大小
	- Xmx 最大堆空间
		-  初始堆和最大堆可以设置相等，这样可以减少垃圾回收的次数，从而提高程序的性能
 	- -Xmn 设置新生代的大小，一般新生代和老年代的比例为1：3或1：4
 	- -XX:SurvivorRatio=(eden/s0=eden/s1) 设置eden和s0、s1的比例关系	 
 	- -XX:NewRatio=(tenured/young) 指定老年代和新生代的比例关系
 	- -参考NewSizeDemo.java
 	- -XX:+HeapDumpOnOutOfMemoryError OOM是导出HeapDump
 	- -XX:HeapDumpPath=<path> HeapDump的导出存放路径
 	- -XX:OnOutOfMemoryError=<cmd> <arg> 在OOM时执行命令
	
- 方法区
	- -XX:PermSize
	- -XX:MaxPermSize
	- -XX:MaxMetaspaceSize
	
- 栈配置
	- -Xss

- 直接内存配置
	- -XX:MaxDirectMemorySize
	- 直接内存和堆内存的比较 AccessDirectBuffer.java 
	
- 工作方式
	- -server
	- -client