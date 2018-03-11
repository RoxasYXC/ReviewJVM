package base.structure.stack;

/**      
 *       
 * @desc 描述   通过-Xss参数的更改来观察Stack Overflow时能到达的最大深度，会比同条件下的TestStackDepth.java深度浅，因为局部变量会占用到栈空间。
 * @author yuxichen        
 * @version 1.0      
 * @created 2013年3月11日 下午4:30:32     
 */       
public class TestStackDepthWithSomeLocalVariables {
	
	private static int count = 0;
	
	public static void calc(long a, long b, long c){
		long e = 1L,f = 1L,g= 1L,h= 1L,i= 1L,j= 1L,k= 1L,l= 1L,m= 1L,n= 1L,o= 1L,p= 1L,q= 1L,r= 1L,s= 1L,t= 1L;
		count ++;
		calc(a,b,c);
	}
	
	public static void main(String[] args) {
		try {
			TestStackDepthWithSomeLocalVariables.calc(1L,2L,3L);
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("Max Depth" + count);
		}
	}
}
