package com.sxca.myb.common.BTK.conversion;

import com.sxca.myb.common.BTK.entity.CertBase;
import com.sxca.myb.common.BTK.entity.CertBaseBTK;
import com.sxca.myb.common.BTK.stringutilBTK.StringUtilBTK;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @Author Wang Jiyu
 * Created by admin on 2017/3/23.
 * 证书业务实体类转换为BTK实体类
 * 要求：业务实体类字段名称必须与BTK实体类名称完全相同
 */
@Service(value = "bussinessToBTK")
public class BussinessToBTKImpl {
    /**
     * 将业务实体转换为BTK实体类
     *
     * @param certBase    业务实体类子类
     * @param certBaseBTK BTK实体类子类
     * @return
     * @throws IllegalAccessException
     * @throws Exception
     */
    public CertBaseBTK mapToEntityBTK(CertBase certBase, CertBaseBTK certBaseBTK) {
        Map<String, String> map = null;
        try {
            map = getBussinessProperty(certBase);
            Class c = certBaseBTK.getClass();
            Method[] methods = c.getMethods();
            //CertBaseBTK的实例化
            Object certBaseBTKInstance = c.newInstance();
            List<Method> setMethods = new ArrayList<Method>();
            for (Method method : methods) {
                if (method.getName().startsWith("set")) {
                    setMethods.add(method);
                }
            }
            Iterator iterator = map.entrySet().iterator();
            String key;
            String value;
            //循环遍历业务实体类转换的map
            while (iterator.hasNext()) {
                //获取map对应的key和value
                Map.Entry entry = (Map.Entry) iterator.next();
                key = entry.getKey().toString();
                value = entry.getValue().toString();
                //获取业务实体类每个数据的类型
                Object dataType = value.substring(0, value.indexOf(","));
                //获取业务实体类传过来的数据
                String data = value.substring(value.indexOf(",") + 1);
                String methodName = "set" + key;
                //遍历业务实体
                for (Method setMethod : setMethods) {
                    if (setMethod.getName().toString().equals(methodName)) {
                        //将业务实体类的数据放入BTK实体类中
                        if ("class java.lang.String".equals(dataType)) {
                            setMethod.invoke(certBaseBTKInstance, data);
                        } else if ("class java.lang.Boolean".equals(dataType)) {
                            setMethod.invoke(certBaseBTKInstance, StringUtilBTK.convertBoolean(data));
                        } else if ("class java.lang.Integer".equals(dataType)) {
                            setMethod.invoke(certBaseBTKInstance, StringUtilBTK.convertInteger(data));
                        } else if ("long".equals(dataType)) {
                            setMethod.invoke(certBaseBTKInstance, StringUtilBTK.convertLong(data));
                        } else if ("float".equals(dataType)) {
                            setMethod.invoke(certBaseBTKInstance, StringUtilBTK.convertFloat(data));
                        } else if ("class java.lang.Double".equals(dataType)) {
                            setMethod.invoke(certBaseBTKInstance, StringUtilBTK.convertDouble(data));
                        } else if ("class java.math.BigDecimal".equals(dataType)) {
                            setMethod.invoke(certBaseBTKInstance, StringUtilBTK.convertBigDecimal(data));
                        }
                    }
                }
            }
            certBaseBTK = (CertBaseBTK) certBaseBTKInstance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return certBaseBTK;
    }

    /**
     * 将业务实体类转换为MAP
     *
     * @param certBase
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Map<String, String> getBussinessProperty(CertBase certBase) throws InvocationTargetException, IllegalAccessException {
        Class c = certBase.getClass();
        Method[] methods = c.getMethods();
        //对原有实体的方法进行过滤
        String bussinessPropertyName = null;
        String bussinessPropertyType = null;
        Object bussinessPropertyValue = null;
        Type[] types = null;
        List<Method> getMethods = new ArrayList<Method>();
        List<Method> setMethods = new ArrayList<Method>();
        Map<String, String> map = new HashMap<String, String>();
        for (Method method : methods) {
            if (method.getName().startsWith("get") && !method.getName().equals("getClass")) {
                getMethods.add(method);
            } else if (method.getName().startsWith("set")) {
                setMethods.add(method);
            }
        }
        for (Method getMethod : getMethods) {
            bussinessPropertyName = getMethod.getName().substring(3);
            bussinessPropertyValue = getMethod.invoke(certBase) + "";
            for (Method m2 : setMethods) {
                if (m2.getName().equals("set" + bussinessPropertyName)) {
                    types = m2.getGenericParameterTypes();
                    bussinessPropertyType = types[0].toString();
                }
            }
            map.put(bussinessPropertyName, bussinessPropertyType + "," + bussinessPropertyValue);
        }
        return map;
    }


    /**
     * 将BTK实体类转换为业务实体
     * @param certBase
     * @param certBaseBTK
     * @return
     */
    public CertBase mapToBussinessEntity(CertBase certBase, CertBaseBTK certBaseBTK) {
        Map<String, String> map = null;
        try {
            map = getBTKProperty(certBaseBTK);
            Class c = certBase.getClass();
            Method[] methods = c.getMethods();
            //CertBaseBTK的实例化
            Object certBaseBTKInstance = c.newInstance();
            List<Method> setMethods = new ArrayList<Method>();
            for (Method method : methods) {
                if (method.getName().startsWith("set")) {
                    setMethods.add(method);
                }
            }
            Iterator iterator = map.entrySet().iterator();
            String key;
            String value;
            //循环遍历业务实体类转换的map
            while (iterator.hasNext()) {
                //获取map对应的key和value
                Map.Entry entry = (Map.Entry) iterator.next();
                key = entry.getKey().toString();
                value = entry.getValue().toString();
                //获取业务实体类每个数据的类型
                Object dataType = value.substring(0, value.indexOf(","));
                //获取业务实体类传过来的数据
                String data = value.substring(value.indexOf(",") + 1);
                String methodName = "set" + key;
                //遍历业务实体
                for (Method setMethod : setMethods) {
                    if (setMethod.getName().toString().equals(methodName)) {
                        //将业务实体类的数据放入BTK实体类中
                        if ("class java.lang.String".equals(dataType)) {
                            setMethod.invoke(certBaseBTKInstance, data);
                        } else if ("class java.lang.Boolean".equals(dataType)) {
                            setMethod.invoke(certBaseBTKInstance, StringUtilBTK.convertBoolean(data));
                        } else if ("class java.lang.Integer".equals(dataType)) {
                            setMethod.invoke(certBaseBTKInstance, StringUtilBTK.convertInteger(data));
                        } else if ("long".equals(dataType)) {
                            setMethod.invoke(certBaseBTKInstance, StringUtilBTK.convertLong(data));
                        } else if ("float".equals(dataType)) {
                            setMethod.invoke(certBaseBTKInstance, StringUtilBTK.convertFloat(data));
                        } else if ("class java.lang.Double".equals(dataType)) {
                            setMethod.invoke(certBaseBTKInstance, StringUtilBTK.convertDouble(data));
                        } else if ("class java.math.BigDecimal".equals(dataType)) {
                            setMethod.invoke(certBaseBTKInstance, StringUtilBTK.convertBigDecimal(data));
                        }
                    }
                }
            }
            certBase = (CertBase) certBaseBTKInstance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return certBase;
    }

    /**
     * 将BTK实体类转换为MAP
     * @param certBaseBTK
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Map<String, String> getBTKProperty(CertBaseBTK certBaseBTK) throws InvocationTargetException, IllegalAccessException {
        Class c = certBaseBTK.getClass();
        Method[] methods = c.getMethods();
        //对原有实体的方法进行过滤
        String bussinessPropertyName = null;
        String bussinessPropertyType = null;
        Object bussinessPropertyValue = null;
        Type[] types = null;
        List<Method> getMethods = new ArrayList<Method>();
        List<Method> setMethods = new ArrayList<Method>();
        Map<String, String> map = new HashMap<String, String>();
        for (Method method : methods) {
            if (method.getName().startsWith("get") && !method.getName().equals("getClass")) {
                getMethods.add(method);
            } else if (method.getName().startsWith("set")) {
                setMethods.add(method);
            }
        }
        for (Method getMethod : getMethods) {
            bussinessPropertyName = getMethod.getName().substring(3);
            bussinessPropertyValue = getMethod.invoke(certBaseBTK) + "";
            for (Method m2 : setMethods) {
                if (m2.getName().equals("set" + bussinessPropertyName)) {
                    types = m2.getGenericParameterTypes();
                    bussinessPropertyType = types[0].toString();
                }
            }
            map.put(bussinessPropertyName, bussinessPropertyType + "," + bussinessPropertyValue);
        }
        return map;
    }
    /*
    //测试
    public void main(String[] args) throws InvocationTargetException, Exception {
        CertApplyBTK certApplyBTK1 = new CertApplyBTK();
        certApplyBTK1.setCertSN("11111QQQ");
        certApplyBTK1.setReqSN("222AAA");
        certApplyBTK1.setCertSubject("333ZZZ");
        certApplyBTK1.setCertCtmlName("444WWWW");
        certApplyBTK1.setCertValidity(515);
        certApplyBTK1.setNotBefore(20170520122583000L);
        CertApplyBTK certApplyBTK2 = new CertApplyBTK();
        CertApplyBTK certApplyBTK = (CertApplyBTK)mapToEntityBTK(certApplyBTK1,certApplyBTK2);
        System.out.println("ReqSN:"+certApplyBTK.getReqSN());
        System.out.println("certSN:"+certApplyBTK.getCertSN());
        System.out.println("CertSubject:"+certApplyBTK.getCertSubject());
        System.out.println("CertCtmlName:"+certApplyBTK.getCertCtmlName());
        System.out.println("CertValidity:"+certApplyBTK.getCertValidity());
        System.out.println("CertType:"+certApplyBTK.getCertType());
        System.out.println("NotBefore:"+certApplyBTK.getNotBefore());
    }
*/

}
