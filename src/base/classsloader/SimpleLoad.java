package base.classsloader;

public class SimpleLoad {
	public static class Parent{
		public static final String AAA = "aaa";
		static{
			System.out.println("parent init");
		}
		public static int c = 1000;
	}
	
	public static class Child extends Parent{
		static{
			System.out.println("child init");
		}
	}
	
	public static void main(String[] args) {
		//直接引用常量不会使类初始化
		System.out.println(Parent.AAA);
		//间接引用不会初始化
		System.out.println(Child.c);
		Child c = new Child();
	}
}
