package base.gc.finalize;

public class ObjCanRelive {
	public static ObjCanRelive obj;
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("f called");
		obj = this;
	}
	
	@Override
	public String toString() {
		return "this is ObjCanRelive";
	}
	
	public static void main(String[] args) throws InterruptedException {
		obj = new ObjCanRelive();
		
		obj = null;
		System.out.println("gc");
		System.gc();
		Thread.sleep(1000);
		if(obj == null){
			System.out.println("obj is null");
		}else{
			System.out.println("obj available");
		}
		obj = null;
		System.out.println("gc again");
		System.gc();
		
		if(obj == null){
			System.out.println("obj is null");
		}else{
			System.out.println("obj available");
		}
	}
}
