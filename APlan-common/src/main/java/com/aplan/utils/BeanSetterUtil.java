package com.aplan.utils;

import com.aplan.annotation.BeanSetter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 切割图片地址中的服务器地址
 */
@Component
public class BeanSetterUtil {

    //静态端口获取
    @Getter
    private static String port;

    @Value("${server.port}")
    public void setPort(String sp) {
        port = sp;
    }


    /**
     * 服务器地址切割
     * @param object
     * @return
     */
    public static Object removeAddress(Object object) {
        Class<?> clazz = object.getClass();
        //获得对象的所有字段
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            //设置让私有字段也可以访问
            field.setAccessible(true);
            //判断字段是否实现了AddressAnnotation注解
            boolean flag = field.isAnnotationPresent(BeanSetter.class);
            if (flag) {
                if (field.getType().getName().equals("java.lang.String")) {
                    try {
                        String value = (String) field.get(object);
                        String baseUrl = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + "/";
                        if (value.contains(baseUrl)){
                            value = value.substring(baseUrl.length(),value.length());
                            field.set(object,value);
                        }

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return object;
    }
}
