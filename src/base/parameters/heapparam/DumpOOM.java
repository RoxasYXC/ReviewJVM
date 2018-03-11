package base.parameters.heapparam;

import java.util.ArrayList;
import java.util.List;
//-Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./test.dump 
public class DumpOOM {
	public static void main(String[] args) {
		List l = new ArrayList();
		for (int i = 0; i < 25; i++) {
			l.add(new byte[1 * 1024 * 1024 ]);
		}
	}
}
