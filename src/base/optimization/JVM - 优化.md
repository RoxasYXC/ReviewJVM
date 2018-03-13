# JVM 优化
- 打开逃逸分析，以获得如下好处
	- 栈上分配
	- 同步取消
	- 标量替换
- 使用ParallelGC的-XX:+UseAdaptiveSizePolicy让JVM自优化
- 在采用CMS回收器的情况下，根据实际的内存增长场景来控制触发CMS的阈值，减少老年代回收会有效提高性能			