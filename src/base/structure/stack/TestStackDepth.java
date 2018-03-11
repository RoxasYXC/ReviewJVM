package base.structure.stack;

/**      
 *       
 * @desc 描述   通过-Xss参数的更改来观察Stack Overflow时能到达的最大深度
 * @author yuxichen        
 * @version 1.0      
 * @created 2013年3月11日 下午4:30:32     
 */       
public class TestStackDepth {
	
	private static int count = 0;
	
	public static void calc(){
		count ++;
		calc();
	}
	
	public static void main(String[] args) {
		try {
			TestStackDepth.calc();
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("Max Depth" + count);
		}
	}
}
