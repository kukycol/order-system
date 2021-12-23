/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.aplan.serializer.json;

import cn.hutool.core.util.StrUtil;
import com.aplan.utils.IPHelper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Inet4Address;

/**
 * 拼接图片地址
 */
@Component
public class ImgJsonSerializer extends JsonSerializer<String> {

    // 端口获取
    @Value("${server.port}")
    private Integer port;


    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        // 服务器地址拼接
//        String fileUrl = "http://" + IPHelper.getInet4Address() + ":" + port + "/";
        String fileUrl = "http://" + IPHelper.getInet4Address() + ":" + port + "/";

        if (StrUtil.isBlank(value)) {
            gen.writeString(StrUtil.EMPTY);
            return;
        }
        String[] imgs = value.split(StrUtil.COMMA);
        StringBuilder sb = new StringBuilder();
        for (String img : imgs) {
            sb.append(fileUrl).append(img).append(StrUtil.COMMA);
        }
        sb.deleteCharAt(sb.length() - 1);
        gen.writeString(sb.toString());
    }
}
