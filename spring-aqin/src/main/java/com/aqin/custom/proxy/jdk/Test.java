package com.aqin.custom.proxy.jdk;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 12/25/22 4:15 PM
 * @Version 1.0
 */
public class Test {
	public static void main(String[] args) {
		/** 将生成的代理对象的字节码文件保存到本地 */
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		/** 获取MyService类的代理对象 */
		IService proxy = MyServiceProxy.getProxy(new MyService());
		System.out.println(proxy.getClass());
		String b = proxy.B(1);
		System.out.println(b);
	}
}
