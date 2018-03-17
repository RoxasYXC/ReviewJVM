
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
 	- -XX:PretenureSizeThreshold 大对象进入老年代的阈值
	
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
	
- GC
	- -XX:+UseSerialGC 新生代老年代都使用串行回收器，client模式下的默认收集器
	- -XX:+UseParNewGC 新生代使用ParNew回收器，来年代使用串行回收器
	- -XX:+UseParallelGC 新生代使用ParallelGC回收器，老年代使用串行回收器
	- -XX:+UseConcMarkSweepGC 新生代使用ParNew回收器，老年代使用CMS
	- -XX:ParallelGCThreads 执行垃圾回收的线程数量，一般当CPU小于8核时对应核数，超过则等于3+5*CPU/8
	- -XX:MaxPauseMillis 最大垃圾收集停顿时间
	- -XX:+UseParallelOldGC 新生代使用ParallelGC回收器，老年代使用ParallelOldGC回收器
	- -XX:MaxPauseMillis 最大垃圾收集停顿时间，由于ParallelGC会根据设定时间来调整堆大小来适应，所以过小的数值可能反而会降低系统的吞吐量
	- -XX:GCTimeRatio 吞吐量大小，0-100整数，假设值为n，系统将不超过1/(1+n)的时间用于垃圾收集
	- -XX:+UseAdaptiveSizePolicy 虚拟机自行调节eden/s0/s1/老年代的大小、晋升老年代的次数等，自动调优策略。
	- -XX:-CMSPrecleaningEnabled 不使用预清理
	- -XX:ConcGCThreads 设定CMS并发线程数
	- -XX:ParallelCMSThreads 同上
	- -XX:CMSInitiatingOccupancyFraction CMS回收时老年代占用阈值，默认68
	- -XX:+UseCMSCompactAtFullCollection CMS完成后，进行一次内存整理，非并发。
	- -XX:+CMSFullGCsBeforeCompaction 指定次数的CMS回收后，进行一次内存整理。
	- -XX:+CMSClassUnloadingEnabled 开启使用CMS回收Perm区Class数据的功能
	- -XX:+UseG1GC 启用G1
	- -XX:MaxGCPauseMillis 目标最大停顿时间，不满足时G1会自动进行堆内比例、堆大小、晋升年龄等 
	- -XX:ParallelGCThreads 执行垃圾回收的线程数量，建议数量上面提到过了。
	- -XX:InitiatingHeapOccupancy 在堆内存达到多少时，进行并发标记周期。
	- -XX:+DisableExplicitGC 禁止显示调用GC
	- -XX:+ExplicitGCInvokeConcurrent 允许显式调用GC进行并发操作
	- -XX:-ScavengeBeforeFullGC 可以关闭并行GC前额外触发的新生代GC

- TLAB
	- -XX:TLABRefillWasteFraction refill_waste=n设置，n值为0-100的整数，代表rw是TLAB的多少分之1。
	- -XX:TLABSize=n n值为b，即TLAB的大小是多少个字节
	- -XX:+ResizeTLAB 是否开启rw的自动调整策略