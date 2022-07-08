package cn.kungreat.singlebbs.util;

import cn.kungreat.singlebbs.SinglebbsApplication;
import cn.kungreat.singlebbs.security.LoginUser;
import cn.kungreat.singlebbs.secure.CipherUtils;

import java.util.HashMap;
import java.util.Map;

public class JwtToken {

    public static String getJwtToken(LoginUser loginUser) {
        Map<String,String> mapToken = new HashMap<>();
        mapToken.put("username",loginUser.getUsername());
        mapToken.put("alias",loginUser.getAlias());
        mapToken.put("currentTime",String.valueOf(System.currentTimeMillis()));
        try {
            String valueAsString = SinglebbsApplication.MAP_JSON.writeValueAsString(mapToken);
            String encrypt = CipherUtils.DEFAULTA.encryptByIV(valueAsString,
                    "AES/CBC/PKCS5Padding");
            return encrypt;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return "";
    }
}
