package com.aplan.service.system.param;

import com.aplan.bean.dto.EmployeeScoreDto;
import com.aplan.bean.dto.StatisticsDto;
import com.aplan.bean.model.ScoreModel;
import com.aplan.bean.model.StatisticsModel;
import com.aplan.bean.param.PageParam;
import com.aplan.common.Oauth2Response;
import com.aplan.exception.custom.Oauth2Exception;
import com.aplan.mapper.ScoreMapper;
import com.aplan.mapper.StatisticsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Description:   
 * @Author: kuky
 * @Date: 2021/12/20 11:29        
 * @Version: 0.0.1      
 */
@Service
@Transactional
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, ScoreModel> implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private StatisticsMapper statisticsMapper;

    
    /**
     * @Description:
     * @Author: kuky
     * @Date: 2021/12/20 13:38
     * @Param:  * @param null	        
     * @Version: 0.0.1      
     */
    @Override
    public Oauth2Response queryAllByLimit(ScoreModel sp, PageParam<ScoreModel> page) {
        List<ScoreModel> records = this.scoreMapper.selectScorePage(page, sp);
        return Oauth2Response.pageQuerySuccess(records, page.getTotal());
    }

    @Override
    public Oauth2Response insert(ScoreModel sp) {
        sp.setCreateTime(new Date());
        int row = this.scoreMapper.insert(sp);
        if (row == 1) {
            return Oauth2Response.saveSuccess();
        }
        throw Oauth2Exception.removeException();
    }

    @Override
    public Oauth2Response updateScore(ScoreModel sp) {
        int row = this.scoreMapper.updateById(sp);
        if (row == 1) {
            return Oauth2Response.updateSuccess();
        }
        throw Oauth2Exception.updateException();
    }

    @Override
    public Oauth2Response deleteBtc(List<String> ids) {
        int row = this.scoreMapper.btcDel(ids);
        if (row == ids.size()) {
            return Oauth2Response.removeSuccess();
        }
        throw Oauth2Exception.removeException();
    }

    @Override
    public Oauth2Response getDepartEmployees(String departId, String userId) {
        List<EmployeeScoreDto> usernameArr = scoreMapper.selectByDepartId(departId, userId);
        return Oauth2Response.querySuccess(usernameArr);
    }

    @Override
    public Oauth2Response submitDepartEmployees(StatisticsDto scores) {
        Map map = scoreMapper.selectStatus();
        Integer status = (Integer) map.get("status");
        Date createTime = (Date) map.get("createTime");

        if (status == 1){
            throw new Oauth2Exception(30000,"评分活动未开启");
        }

        List<StatisticsModel> statisticsModels = statisticsMapper.selectByCreateTimeAndUserId(scores.getUserId(), createTime);
        if (statisticsModels.size()>0){
            throw new Oauth2Exception(30000,"您已经评过分，请勿重复评分");
        }

        for (EmployeeScoreDto score : scores.getScores()) {
            StatisticsModel statisticsModel = new StatisticsModel();
            statisticsModel.setScore1(score.getScore1());
            statisticsModel.setScore2(score.getScore2());
            statisticsModel.setScore3(score.getScore3());
            statisticsModel.setUserId(score.getUserId());
            statisticsModel.setCreateTime(new Date());
            statisticsModel.setScoreUserId(scores.getUserId());
            statisticsMapper.insert(statisticsModel);
        }
        return Oauth2Response.saveSuccess();
    }

    @Override
    public Oauth2Response updateStatus() {
        Map map = scoreMapper.selectStatus();
        scoreMapper.updateStatus((Integer) map.get("status") == 0 ? 1 : 0,new Date());
        return new Oauth2Response(20000,(Integer) map.get("status")  == 0 ? "评分活动已经关闭" : "评分活动开始成功");
    }

    @Override
    public Oauth2Response getActivityStatus() {
        Map map = scoreMapper.selectStatus();
        return Oauth2Response.querySuccess((Integer) map.get("status") );
    }


}
