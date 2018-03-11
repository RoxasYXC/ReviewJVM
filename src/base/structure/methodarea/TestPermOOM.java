package base.structure.methodarea;

import java.util.HashMap;

   
/**      
 *       
 * @desc 描述  方法区OOM,1.8以下设置-XX:+PrintGCDetails -XX:PermSize=5m -XX:MaxPermSize=5m，1.8以上设置-XX:+PrintGCDetails -XX:MaxMetaspaceSize=5m
 * @author yuxichen        
 * @version 1.0      
 * @created 2018年3月11日 下午6:28:33     
 */       
public class TestPermOOM {
	public static void main(String[] args) {
		int i = 0;
		try {
			for (int j = 0; j < 1000000000; j++) {
				CglibBean bean = new CglibBean(new HashMap<String, String>());
			}
		} catch (Exception e) {
			System.out.println("Total create:"+ i );
		}
	} 
}
