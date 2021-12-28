package com.aplan.controller.chat;

import com.aplan.bean.model.SysChatModel;
import com.aplan.common.Oauth2Response;
import com.aplan.mapper.SysChatMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static cn.hutool.poi.excel.sax.AttributeName.s;

@Api(tags = "聊天接口组")
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Resource
    private SysChatMapper sysChatMapper;

    @GetMapping
    public Oauth2Response chat(@RequestParam("input") String input) {
        return Oauth2Response.querySuccess(demo2(input));
    }

    public String demo2(String input) {
        List<SysChatModel> sysChatModels = sysChatMapper.selectList(new LambdaQueryWrapper<SysChatModel>().like(SysChatModel::getKey, "%" + input + "%"));
        if (sysChatModels.size() == 0) {
            return "没有找到合适的回复记录！";
        }
        int i = new Random().nextInt(sysChatModels.size());
        SysChatModel sysChatModel = sysChatModels.get(i);
        return sysChatModel.getValue();
    }

    @PostMapping
    public Oauth2Response save(@RequestBody SysChatModel sysChatModel) {
        sysChatModel.setCreateTime(new Date());
        sysChatMapper.insert(sysChatModel);
        return Oauth2Response.saveSuccess();
    }


    @SneakyThrows
    @PostMapping("/btc")
    public Oauth2Response btcSave() {
        String s = "";
        File file = new File("D:\\project\\APlan-back-end\\APlan-admin\\src\\main\\resources\\demo.txt");
        InputStreamReader in = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader br = new BufferedReader(in);
        StringBuffer content = new StringBuffer();
        while ((s = br.readLine()) != null) {
            content = content.append("&");
            content = content.append(s);
        }
        s = content.toString().substring(1);
        String[] split = s.split("&");
        if (split.length % 2 != 0) {
            return Oauth2Response.saveFail();
        }
        for (int i = 0; i < split.length; ) {
            SysChatModel sysChatModel = new SysChatModel();
            sysChatModel.setKey(split[i]);
            sysChatModel.setValue(split[i + 1]);
            sysChatModel.setCreateTime(new Date());
            sysChatMapper.insert(sysChatModel);
            i += 2;
        }
        return Oauth2Response.saveSuccess();
    }

}
