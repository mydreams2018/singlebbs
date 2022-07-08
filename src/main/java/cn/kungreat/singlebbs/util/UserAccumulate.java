package cn.kungreat.singlebbs.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserAccumulate {

    public static int countVipLevel(int x){
        int rt = 0;
        if(x > 888){
            rt = 9;
        }else if(x > 777){
            rt = 8;
        }else if(x > 666){
            rt = 7;
        }else if(x > 555){
            rt = 6;
        }else if(x > 333){
            rt = 5;
        }else if(x > 222){
            rt = 3;
        }else if(x > 111){
            rt = 2;
        }else if(x > 0){
            rt = 1;
        }
        return rt;
    }

    public static Set<String> hasReplyAlias(String detailsText){
        String st = detailsText;
        Set<String> rt = new HashSet<>();
        if(st.contains("@")){
            int startIndex = 0;
            for(int x = 0;x < st.length(); x++){
                if('@'==st.charAt(x)){
                    startIndex = x+1;
                }
                if(' '== st.charAt(x) && startIndex != 0){
                    String temp = st.substring(startIndex,x);
                    if(StringUtils.isNotEmpty(temp)){
                        rt.add(temp);
                    }
                    startIndex=0;
                }
            }
        }
        return rt;
    }

    public static Map<String,Object> getMapByString(String rt){
        String[] split = rt.split("&");
        Map<String,Object> mp = new HashMap<>();
        for(int x=0;x<split.length;x++){
            String[] split1 = split[x].split("=");
            mp.put(split1[0],split1[1]);
        }
        return mp;
    }

    public static void beanTransforMap(Object bean,Map<String,Object> mp){
        Field[] fields = bean.getClass().getDeclaredFields();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        try {
            for(int x=0;x<fields.length;x++){
                Field field = fields[x];
                field.setAccessible(true);
                if(field.getName() !="account" && field.getName() !="password"){
                    if(field.getType() == Date.class){
                        Object o = field.get(bean);
                        mp.put(field.getName(),simpleDateFormat.format(o));
                    }else{
                        Object o = field.get(bean);
                        mp.put(field.getName(),o);
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
