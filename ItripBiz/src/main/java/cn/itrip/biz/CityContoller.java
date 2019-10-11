package cn.itrip.biz;

import cn.itrip.common.Dto;
import cn.itrip.common.DtoUtil;
import cn.itrip.dao.itripAreaDic.ItripAreaDicMapper;
import cn.itrip.dao.itripLabelDic.ItripLabelDicMapper;
import cn.itrip.pojo.ItripAreaDic;
import cn.itrip.pojo.ItripAreaDicVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api")
public class CityContoller {

    @Resource
    ItripAreaDicMapper dao;


    @Resource
    ItripLabelDicMapper dao1;



    @RequestMapping(value = "/hotel/queryhotelfeature", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public  Dto<ItripAreaDicVO> GetCity() {
        return DtoUtil.returnDataSuccess(dao1.GetList());
    }

    @RequestMapping(value = "/hotel/querytradearea/{id}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public  Dto<ItripAreaDicVO> GetCity(@PathVariable("id") Integer id) {
        return DtoUtil.returnDataSuccess(dao.GetCity(id));
    }
    @RequestMapping(value = "/hotel/queryhotcity/{type}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public  Dto GetHot(@PathVariable("type") Integer type)
    {
        Map<String,Object> map=new HashMap<>();
        map.put("tt",type);
        List<ItripAreaDic> list=dao.ishot(map);
        return DtoUtil.returnDataSuccess(list);
    }
}
