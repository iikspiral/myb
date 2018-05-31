package com.sxca.myb.modules.mobile.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author lihuilong
 * @date : 2017年5月10日 下午2:06:20
 */
public class HttpRequest {

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				// System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {

		// getIsMobileApply 身份验证接口（新制待申请）
		// 发送 GET 请求
		// String s = HttpRequest
		// .sendGet(
		// "http://localhost:8081/myb/mobile/validateIdentityNew",
		// "certType=userinfo&idCard=142301198901260033&whiteListId=a0b291fc0df943929d167a1be5175bf1&phoneNum=15234122750&verify=565645");
		// System.out.println(s);

		// 是否可申请证书接口
//		 String s = HttpRequest
//		 .sendPost(
//		 "http://localhost:8081/myb/mobile/getIsMobileApply",
//		 "phoneNum=18335185395&idCard=140000199903111111&name=花姑娘&deviceNum=11111111111");
//		 System.out.println(s);

		// 获取当前用户待申请证书
		// String s = HttpRequest.sendGet(
		// "http://localhost:8081/myb/mobile/getWaitApplyCert",
		// "phoneNum=18335185395&idCard=140000199903111111");
		// System.out.println(s);

		// 获取当前用户可变更证书
		// String s = HttpRequest
		// .sendPost(
		// "http://localhost:8081/myb/mobile/getChangeCert",
		// "phoneNum=18335185395&idCard=140000199903111111");
		// System.out.println(s);
		
		String p10 = "MIIBWzCBxQIBADAcMQswCQYDVQQGEwJDTjENMAsGA1UEAxMEdGVzdDCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAqdtkvoIAGtBXMmiADj2Er55VDmIzD7leHCvLyiBgz0RYfaYWm5/453g3B68T/hPKOZyaSBVYRxX96vdkBJcCsxJR2nKokrPXM3eh5Pa6qivcTintbIdHtUg0F08q8IGJc5zdDTTNtVhReD2WahdHkAt8tZ9bEWjBLzBe0csGON0CAwEAAaAAMA0GCSqGSIb3DQEBBQUAA4GBABj40WEXocMlXpCM6NHeZRYrXnJtw7ZvKN51jux2kQcWZNlVEeiHNOGflWsHHxMd1u27w8BKuo709ximryIOllUKaiqK6CPvdtAFyhcL12IJXoC/453WXZrw1Eg4T5NSrlZ4OnDKVsntSHOxEFxw0tx6+NA382ZocGsaETrG4BSP";

		p10 = p10.replace("+", "%2B");
		
		// 证书更新申请下载接口
//		String s = HttpRequest
//				.sendPost(
//						"http://localhost:8081/myb/mobile/updateMobileCert",
//						"certSn=7947A53079DF56D5&CN=ihjkhjk,OU=34oiuoiuoiu,C=CN&p10="+p10);
//		System.out.println(s);

		//证书申请接口
//		String s = HttpRequest
//				.sendPost(
//						"http://localhost:8081/myb/mobile/getMobileCertOther",
//						"userId=b05b7f029f274f2e95149915a5199f96&deviceType=华为Mate9&deviceNum=1345615897987984&p10="+p10);
//		System.out.println(s);		
		
		//待申请证书申请下载接口
//		String s = HttpRequest
//		.sendPost(
//				"http://localhost:8081/myb/mobile/getMobileCert",
//				"certType=userinfo&id=c57f70e54f6f4445b33c0d67ed80310a&deviceType=华为Mate9&deviceNum=1345615897987984&p10="+p10);
//		System.out.println(s);
		
		//证书注销申请接口
//		String s = HttpRequest
//		.sendPost(
//				"http://localhost:8081/myb/mobile/cancelMobileCert",
//				"certSn=7F259FCB5726D9AF&cancelReason=不好用");
//		System.out.println(s);
//		
		
		//证书变更申请下载接口
//		String s = HttpRequest
//		.sendPost(
//				"http://localhost:8081/myb/mobile/changeMobileCert",
//				"certSn=7947A53079DF56D5&certType=userinfo&deviceType=华为Mate10&deviceNum=457542133456&p10="+p10);
//		System.out.println(s);
		
		
		//忘记证书密码申请接口
//		String s = HttpRequest
//		.sendPost(
//				"http://localhost:8081/myb/mobile/getMobileCertByForgetPass",
//				"certSn=1E1E01753D2A9D3D&subject=CN=山西CA,OU=ASDL,UID=20170517110150262,C=CN&deviceType=华为Mate10&deviceNum=457542133456&p10="+p10);
//		System.out.println(s);	
		
		//生成随机数
//		String s = HttpRequest
//		.sendPost(
//				"http://localhost:8081/myb/mobile/getRdmnum","");
//		System.out.println(s);
		
//		String s = HttpRequest
//		.sendPost(
//				"http://localhost:8081/myb/f/mobile/getAllowUpdate",
//				"certSn=6BD7C4F38EA95F9C");
//		System.out.println(s);
		
//		String s = HttpRequest
//		.sendPost(
//				"http://192.168.0.78:8081/myb/f/mobile/getIsUseByAppid",
//				"appId=OATest");
//		System.out.println(s);
		
		String s = HttpRequest
				.sendPost(
						"http://192.168.0.78:8081/myb/f/mobile/getCertappLogs",
						"certSn=6BD7C4F38EA95F9C");
		System.out.println(s);
	}

}
