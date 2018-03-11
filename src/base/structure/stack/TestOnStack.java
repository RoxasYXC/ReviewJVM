package base.structure.stack;

   
/**      
 *       
 * @desc 描述 栈上分配测试，-server -Xmx10m -Xms10m -XX:+PrintGC -XX:+DoEscapeAnalysis -XX:-UseTLAB -XX:+EliminateAllocations   
 * @author yuxichen        
 * @version 1.0      
 * @created 2018年3月11日 下午5:39:47     
 */       
public class TestOnStack {
	public static class User{
		String name  = "";
		int id = 0;
	}
	 public static void alloc(){
		 User u = new User();
		 u.id = 1;
		 u.name = "test";
	 }
	 public static void main(String[] args) {
		long beg = System.currentTimeMillis();
		for (int i = 0; i < 100000000; i++) {
			alloc();
		}
		System.out.println(System.currentTimeMillis() - beg);
	}
}
