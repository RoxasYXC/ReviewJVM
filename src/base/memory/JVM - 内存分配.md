
# JVM 内存分配
- TLAB - Thread Local Allocation Buffer
	- 线程本地分配缓存
	- 线程获取堆上的数据涉及到同步，会较低获取效率，故直接给线程分配内存，TLAB本身占用的是eden区的资源
	- 参考UseTLAB.java来看TLAB带来的性能差异
	- refill_waste
		- TLAB空间较小，当超过当前剩余TLAB空间的对象请求分配时，需要jvm做出抉择是放弃TLAB还是做堆上分配，抉择的依据就是refill_waste，当大于rw时，会进行堆上分配，当小于rw时，则放弃当前TLAB
		- -XX:TLABRefillWasteFraction refill_waste=n设置，n值为0-100的整数，代表rw是TLAB的多少分之1。
		- -XX:TLABSize=n n值为b，即TLAB的大小是多少个字节
		- -XX:+ResizeTLAB 是否开启rw的自动调整策略
		- 慢分配slow allocs 指由于TLAB空间不足导致对象直接分配到堆上
		
	- 对象在内存上的分配流程
		- 尝试栈上分配
		- 尝试TLAB分配
		- eden 或 老年代
		
		