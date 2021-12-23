package com.aplan.service.system.position;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.dto.DepartPositionDto;
import com.aplan.bean.model.SysDepartPositionModel;
import com.aplan.exception.custom.Oauth2Exception;
import com.aplan.mapper.SysDepartPositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SysDepartPositionServiceImpl extends ServiceImpl<SysDepartPositionMapper, SysDepartPositionModel> implements SysDepartPositionService {

    @Autowired
    private SysDepartPositionMapper sysDepartPositionMapper;

    @Override
    public Oauth2Response queryAllByLimit(DepartPositionDto sp, PageParam<DepartPositionDto> page) {
        List<DepartPositionDto> records = this.sysDepartPositionMapper.selectDepartPositionPage(page, sp);
        return Oauth2Response.pageQuerySuccess(records, page.getTotal());
    }

    @Override
    public Oauth2Response insert(SysDepartPositionModel sp) {
        sp.setCreate_by("当前登录的用户名");
        sp.setCreateTime(new Date());
        int row = this.sysDepartPositionMapper.insert(sp);
        if (row == 1) {
            return Oauth2Response.saveSuccess();
        }
        throw Oauth2Exception.removeException();
    }

    @Override
    public Oauth2Response updateDepartPosition(SysDepartPositionModel sp) {
        int row = this.sysDepartPositionMapper.updateById(sp);
        if (row == 1) {
            return Oauth2Response.updateSuccess();
        }
        throw Oauth2Exception.updateException();
    }

    @Override
    public Oauth2Response deleteBtc(List<String> ids) {
        int row = this.sysDepartPositionMapper.btcDel(ids);
        if (row == ids.size()) {
            return Oauth2Response.removeSuccess();
        }
        throw Oauth2Exception.removeException();
    }

    @Override
    public Oauth2Response queryAll(String departId) {
        List<SysDepartPositionModel> positionModels = sysDepartPositionMapper.selectList(new LambdaQueryWrapper<SysDepartPositionModel>().eq(SysDepartPositionModel::getDepartId, departId));
        return Oauth2Response.querySuccess(positionModels);
    }


}
