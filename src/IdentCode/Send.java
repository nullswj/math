package IdentCode;

import java.util.Random;

/**
 * 发送验证码
 * @author Administrator
 *
 */
public class Send {
	/**
	 * 发送验证码
	 * @param number - 要发送的号码
	 * @return - 发送的验证码
	 * @return - 发送的验证码
	 */
	@SuppressWarnings("static-access")
	public String sendCode(String number) throws Exception
	{
		Sms  sms = new Sms();
		int code = (int)(new Random().nextDouble()*1000000);
		sms.sendSms(code+"", number);
		return code+"";
	}
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		String code = new Send().sendCode("15273199298");
		System.out.println(code );
	}
}
