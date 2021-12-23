package com.aplan.controller.dict;

import com.aplan.bean.model.TzDictModel;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.request.DictRequest;
import com.aplan.common.Oauth2Response;
import com.aplan.service.system.dict.TzDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "字典接口组")
@RestController
@RequestMapping("/tz/dict")
public class TzDictController {

    @Resource
    private TzDictService tzDictService;
    @Resource
    private MapperFacade mapperFacade;

    @ApiOperation("获取字典信息")
    @GetMapping("/info/{dictId}")
    public Oauth2Response info(
            @ApiParam(value = "字典id",required = true) @PathVariable("dictId") String dictId) {
        return this.tzDictService.queryById(dictId);
    }


    @ApiOperation("查询信息")
    @GetMapping("/dictCode/{dictCode}")
    public Oauth2Response dictCode(
            @ApiParam(value = "字典编码",required = true) @PathVariable("dictCode") String dictCode) {
        return this.tzDictService.queryByDictCode(dictCode);
    }


    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Oauth2Response page(DictRequest sq
    ) {
        PageParam<TzDictModel> page = new PageParam<>();
        page.setCurrent(sq.getCurrent());
        page.setSize(sq.getSize());
        TzDictModel sp = mapperFacade.map(sq, TzDictModel.class);
        return this.tzDictService.queryAllByLimit(sp,page);
    }


    @ApiOperation("插入字典")
    @PostMapping()
    public Oauth2Response save(
            @ApiParam(value = "字典model",required = true) @RequestBody TzDictModel sp) {
        return this.tzDictService.insert(sp);
    }


    @ApiOperation("更新字典")
    @PutMapping()
    public Oauth2Response update(
            @ApiParam(value = "字典model",required = true) @RequestBody TzDictModel sp) {
         return this.tzDictService.update(sp);
    }


    @ApiOperation("批量删除字典")
    @DeleteMapping("/btc")
    public Oauth2Response additiveBtc(
            @ApiParam(value = "字典id数组",required = true) @RequestParam("ids") List<String> ids) {
        return this.tzDictService.deleteBtc(ids);
    }


    @ApiOperation(value = "删除数据")
    @DeleteMapping("{dictId}")
    public Oauth2Response del(
            @ApiParam(value = "字典id",required = true) @PathVariable("dictId") String dictId) {
        List<String> arr = new ArrayList<>();
        arr.add(dictId);
        return this.tzDictService.deleteBtc(arr);
    }



}