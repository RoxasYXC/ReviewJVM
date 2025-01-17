
# JVM GC
- 常用GC算法
	- 引用计数法
		- 原理
			- 对象被引用即在整型引用计数器上+1；
		- 问题
			- 无法处理循环引用；
			- 每次对象生成或回收都必须需要进行加或减的运算操作；
	- 标记清除法
		- 原理
			- 标记阶段：通过根节点，标记所有根节点开始的可达对象；
			- 清除阶段，清除未在标记阶段标记的对象；
		- 问题
			- 回收后产生内存碎片，内存不连续，导致性能降低；
	- 复制算法
		- 原理
			- 将内存一分为二，每次只操作一块内存进行回收，将存活对象复制到另一块内存中，然后清楚当前内存中的垃圾对象。
			- 不会产生内存碎片。
		- 问题
			- 内存折半
		- 应用
			- Java新生代使用复制算法的思想
				- copy eden -> s0 s1 -> s0 if(obj.size > s0.size or s0.full) -> tenur 
				- gc eden s1
	- 标记压缩法
		- 原理
			- 优化标记清除算法，在完成清除算法后，将剩余的存活对象移动到内存一段，避免内存碎片的产生
		- 问题
			- gc时的开销比标记清除法大
		- 应用
			- 老年代的gc
	- 分代算法
		- 新生代 复制算法，因为会有大量的对象会被回收，复制的代价很小。
			- 卡表 比特位集合，记录每4KB的老年代空间中是否有对象持有新生代的引用（有记1，无记0），这样就不用扫描全部的老年代对象而只需要扫卡表。提高新生代的gc效率
		- 老年代 标记清除或标记压缩
	- 分区算法
		- 原理
			- 将整个堆空间划分成连续的不同小区间，每个小区间都独立使用，独立回收。这种算法的好处在于可以控制一次回收多少个小区间。

- 可触及性
	- 可触及的
		- 从根节点开始扫描，可以到达这个对象
	- 可复活的
		- 对象实现了finalize方法，在对象的所有引用都释放后，对象有可能复活
	- 不可触及的
		- 对象的finalize方法已经被调用过，并且未复活，进入不可触及状态，对象只有在该状态才能被回收
		- 参考ObjCanRelive.java
		
- 引用和可触及性强度
	- 强引用
		- 程序中一般使用的引用类型，强引用的对象是可触及的，不会回收的。
	- 软引用
		- 在堆空间不足时会被回收的引用
			- 参考SoftRef.java
		- 软引用队列：在可达性从可达变为不可达时，软引用的对象就会进入该队列，可以通过这个队列跟踪对象的回收情况
			- 参考SoftRefQ.java
	- 弱引用
		- gc时发现即回收
			- 参考WeakRef.java
		- 弱引用也有队列，原理同软引用
		- 弱引用和软引用常用来实现一些不重要的缓存，合理利用可以减少OOM发生的可能性
	- 虚引用
		- 资源释放操作

- STW
	- GC时会让程序停顿，整个系统会进入卡死状态。
	- 参考StopTheWorldTest.java、StopTheWorldTest2.java
	
- 垃圾回收器
	- 串行回收器 SerialGC
		- 单线程进行垃圾回收
		- 独占式的垃圾回收
		- 会产生STW
		- 逻辑简单，没有线程切换的开销，适合在硬件条件差的场合
		- -XX:+UseSerialGC 新生代老年代都使用串行回收器，client模式下的默认收集器
		- 新生代中串行回收器用的是复制算法，老年代用的是标记压缩法
	- 并行回收器 ParNewGC
		- 多线程串行回收器
		- 算法和新生代串行回收器一致
		- 多核情况下，性能优于串行回收器，单核情况下反而不如
		- -XX:+UseParNewGC 新生代使用ParNew回收器，来年代使用串行回收器
		- -XX:ParallelGCThreads 执行垃圾回收的线程数量，一般当CPU小于8核时对应核数，超过则等于3+5*CPU/8
	- ParallelGC回收器
		- 使用复制算法
		- 多线程、独占式
		- 关注吞吐量
		- -XX:+UseParallelGC 新生代使用ParallelGC回收器，老年代使用串行回收器
		- -XX:MaxPauseMillis 最大垃圾收集停顿时间，由于ParallelGC会根据设定时间来调整堆大小来适应，所以过小的数值可能反而会降低系统的吞吐量
		- -XX:GCTimeRatio 吞吐量大小，0-100整数，假设值为n，系统将不超过1/(1+n)的时间用于垃圾收集
		- -XX:+UseAdaptiveSizePolicy 虚拟机自行调节eden/s0/s1/老年代的大小、晋升老年代的次数等，自动调优策略。
		- ParallelOldGC回收器
			- 使用标记压缩算法
			- 关注吞吐量
			- -XX:+UseParallelOldGC 新生代使用ParallelGC回收器，老年代使用ParallelOldGC回收器
	- CMS
		- 多线程并行
		- 非独占式
		- 使用标记清除算法
		- 关注系统停顿时间
		- 流程 
			- 初始标记 STW标记根对象
			- 并发标记 标记所有对象
			- 预清理 清理前准备以及控制停顿时间
			- 重新标记 STW修正并发标记数据
			- 并发清理
			- 并发重置
		- 并发标记、并发清理、并发重置都是可以和应用一起执行的
		- -XX:-CMSPrecleaningEnabled 不使用预清理
		- 预清理会根据历史性能数据来预测下一次新生代GC发生的时间，并尽量在当前时间和预测时间的中间点进行重新标记，以尽量避免新生代GC和重新标记阶段的重合。减少一次停顿锁需要的时间。
		- -XX:+UseConcMarkSweepGC 新生代使用ParNew回收器，老年代使用CMS
		- CMS的默认线程数是(ParallelGCThreads +3)/4
		- -XX:ConcGCThreads 设定CMS并发线程数
		- -XX:ParallelCMSThreads 同上
		- 由于CMS的执行原理导致无法确保回收时内存不增长，所以不会等待堆内存饱和才进行垃圾回，而是根据设定的阈值进行回收。
		- -XX:CMSInitiatingOccupancyFraction CMS回收时老年代占用阈值，默认68
		- 如果达到阈值触发回收，回收过程中出现内存不足，则取消该次CMS回收，切换成备用的串行回收。
		- 可根据实际系统的内存增长速度来调整XX:CMSInitiatingOccupancyFraction，阈值和增长速度呈反比，起到性能调优的目的。
		- -XX:+UseCMSCompactAtFullCollection CMS完成后，进行一次内存整理，非并发。
		- -XX:+CMSFullGCsBeforeCompaction 指定次数的CMS回收后，进行一次内存整理。
		- -XX:+CMSClassUnloadingEnabled 开启使用CMS回收Perm区Class数据的功能
	- G1
		- since JDK1.7
		- 分区算法 青年代和老年代不要求连续
		- 并行性 多线程回收
		- 并发性 与应用交替执行
		- 分代GC 兼顾年轻代和老年代
		- 空间整理 G1在回收过程中会进行适当的对象复制
		- 可预见性 分区回收，对全局停顿进行了较好的控制
		- 4个可能的阶段
			- 新生代GC
				- 仍旧采用复制算法，和其他收集器没有特别大的区别
			- 并发标记周期
				- 并发阶段类似CMS，有如下阶段
					- 初始标记（initial mark） 标记从根节点直接可达的对象，触发一次young gc，stw
					- 根区域扫描 (root region scan) 扫描survivor区直接可达的老年代对象，如该动作和young gc同时发生的话，young gc会暂停等待，导致gc时间延长
					- 并发标记（conc mark） 和CMS类似，查找全堆的存活对象进行标记，可能会被young gc打断
					- 重新标记 (remark)  STW SATB（snapshot at the beginning） 修正并发标记过程中的新状况
					- 独占清理 (clean up) STW 计算所有分区的存活对象和GC回收比例，并进行排序；识别标记可供混合回收的区域。
					- 并发清理阶段(conc clean up) 清理完全空闲的分区
				- 该阶段只回收完全空闲的分区，所以可能性比较低，该阶段能够回收的内存空间是很有限的
			- 混合收集
				- 根据独占清理阶段的排序结果识别需要优先清理的分区
				- young gc和old gc一通进行
				- gc完成后，清理区域内的存活对象会复制到其他分区当中
				- 混合gc会执行多次直到释放了足够多的内存，之后再触发一次young gc，进入循环
			- Full GC
				- 特定情况下触发Full GC
		-  Remembered Set
			- 更新记忆集，每一个G1分区都有一个RS关联，用来记录本分区中被其他分区对象引用的对象。
		- -XX:+UseG1GC 启用G1
		- -XX:MaxGCPauseMillis 目标最大停顿时间，不满足时G1会自动进行堆内比例、堆大小、晋升年龄等 
		- -XX:ParallelGCThreads 执行垃圾回收的线程数量，建议数量上面提到过了。
		- -XX:InitiatingHeapOccupancy 在堆内存达到多少时，进行并发标记周期。
- 细节问题
	- 禁用system.gc()
		- 触发FullGC，新生代老年代同时进行。
		- -XX:+DisableExplicitGC 禁止显式调用GC
		- -XX:+ExplicitGCInvokeConcurrent 允许显式调用GC进行并发操作
	- 并行GC前额外触发的新生代GC
		- 在使用ParallelGC时，会在FullGC前进行一次YoungGC，以此来起到缩短停顿的目的。
		- -XX:-ScavengeBeforeFullGC 可以关闭并行GC前额外触发的新生代GC
		