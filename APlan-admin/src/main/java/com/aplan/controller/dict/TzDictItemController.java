package com.aplan.controller.dict;

import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.TzDictItemModel;
import com.aplan.service.system.dict.TzDictItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "字典项接口组")
@RestController
@RequestMapping("/sys/dict/item")
public class TzDictItemController {


    @Resource
    private TzDictItemService tzDictItemService;



    @ApiOperation("获取信息")
    @GetMapping("/info/{dictItemId}")
    public Oauth2Response info(
            @ApiParam(value = "字典项id",required = true) @PathVariable("dictItemId") String dictItemId) {
        return this.tzDictItemService.queryById(dictItemId);
    }


    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Oauth2Response page(
            @ApiParam(value = "字典项model",required = true) TzDictItemModel sp,
            @ApiParam(value = "字典项分页参数",required = true) PageParam<TzDictItemModel> page) {
        return this.tzDictItemService.queryAllByLimit(sp,page);
    }


    @ApiOperation("新增")
    @PostMapping()
    public Oauth2Response save(
            @ApiParam(value = "字典项model",required = true) @RequestBody TzDictItemModel sp) {
        return this.tzDictItemService.insert(sp);
    }


    @ApiOperation("更新")
    @PutMapping()
    public Oauth2Response update(
            @ApiParam(value = "字典项model",required = true)@RequestBody TzDictItemModel sp) {
        return this.tzDictItemService.update(sp);
    }
    

    @ApiOperation("批量删除")
    @DeleteMapping("/btc")
    public Oauth2Response additiveBtc(
            @ApiParam(value = "字典项id数组",required = true)@RequestParam("ids") List<String> ids) {
        return this.tzDictItemService.deleteBtc(ids);
    }


    @ApiOperation("删除")
    @DeleteMapping("{dictItemId}")
    public Oauth2Response del(
            @ApiParam(value = "字典项id",required = true)@PathVariable("dictItemId") String dictItemId) {
        return this.tzDictItemService.deleteById(dictItemId);
    }



}