package base.structure.stack;

/**      
 *       
 * @desc 描述  局部变量和垃圾回收的关系，使用-XX:+PrintGC运行
 * @author yuxichen        
 * @version 1.0      
 * @created 2018年3月11日 下午5:09:30     
 */       
public class TestLocalVarGC {
	
	public void localVarGc1(){
		//被b引用，无法回收
		byte[] b = new byte[6*1024*1024];
		System.gc();
	}
	
	public void localVarGc2(){
		byte[] b = new byte[6*1024*1024];
		//指向null，失去强引用，可以回收
		b = null;
		System.gc();
	}
	
	public void localVarGc3(){
		{
			byte[] b = new byte[6*1024*1024];
		}
		//b失效，但槽位没有被复用，仍指向6M内存，无法回收
		System.gc();
	}
	
	public void localVarGc4(){
		{
			byte[] b = new byte[6*1024*1024];
		}
		int a = 1;
		//b失效，槽位被复用，可以回收
		System.gc();
	}
	
	public void localVarGc5(){
		localVarGc1();
		//栈帧被销毁，可以回收
		System.gc();
	}
	
	public static void main(String[] args) {
		TestLocalVarGC t = new TestLocalVarGC();
		System.out.println("call localVarGc1()");
		t.localVarGc1();
		System.out.println("call localVarGc2()");
		t.localVarGc2();
		System.out.println("call localVarGc3()");
		t.localVarGc3();
		System.out.println("call localVarGc4()");
		t.localVarGc4();
		System.out.println("call localVarGc5()");
		t.localVarGc5();
	}
}
