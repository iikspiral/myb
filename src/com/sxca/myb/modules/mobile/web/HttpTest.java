package com.sxca.myb.modules.mobile.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpTest {

	
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
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
    
   /* public static void main(String[] args) {
        //发送 POST 请求
        String sr=HttpTest.sendPost("http://localhost:6144/Home/RequestPostString", "key=123&v=456");
        System.out.println(sr);
        
        
    }*/
    
    public static void main(String[] args) {
		//a[5]=100;
    	/*List<Map<String, String>> rsList = new ArrayList<>();
    	Map map1 = new HashMap<>();
    	map1.put("UsbKeySerialNumber", "123");
    	map1.put("SequenceSubject", "高");
    	map1.put("CertSN", "123");
    	Map map2 = new HashMap<>();
    	map2.put("UsbKeySerialNumber", "123");
    	map2.put("SequenceSubject", "高");
    	map2.put("CertSN", "456");
    	Map map3 = new HashMap<>();
    	map3.put("UsbKeySerialNumber", "234");
    	map3.put("SequenceSubject", "高");
    	map3.put("CertSN", "123");
    	Map map4 = new HashMap<>();
    	map4.put("UsbKeySerialNumber", "345");
    	map4.put("SequenceSubject", "高");
    	map4.put("CertSN", "123");
    	Map map5 = new HashMap<>();
    	map5.put("UsbKeySerialNumber", "345");
    	map5.put("SequenceSubject", "高");
    	map5.put("CertSN", "456");
    	rsList.add(map1);
    	rsList.add(map2);
    	rsList.add(map3);
    	rsList.add(map4);
    	rsList.add(map5);
		Map<String,List<String>> map6 = new HashMap<>();
		for(int i = 0;i < rsList.size();i++) {
			System.out.println(i);
			Map<String,String> map = rsList.get(i);
			String certNu = map.get("CertSN");// 证书序列号
			String certSub = map.get("SequenceSubject");// 证书主题
			String keyNu = map.get("UsbKeySerialNumber");// key的序列号
			String key = keyNu+":"+certSub;
			System.out.println(key);
			List<String> list = null;
			for(Map.Entry<String, List<String>> entry : map6.entrySet()) {
				if(key.equals(entry.getKey())) {
					list = entry.getValue();
				}
			}
			if(list==null) {
				list = new ArrayList<>();
			}
			list.add(certNu);
			map6.put(key, list);
		}
		for(Map.Entry<String, List<String>> entry : map6.entrySet()) {
			System.out.println(entry.getKey()+"---"+entry.getValue());
		}*/
    	
    	List<String> certSnList = new ArrayList<String>();
    	certSnList.add("1");
    	certSnList.add("2");
    	certSnList.add("3");
    	certSnList.add("4");
	}
    
    public String getIsCancel(List<String> certSnList) {
    	for(String certSn:certSnList) {
    		
    	}
		return null;
    }
}
