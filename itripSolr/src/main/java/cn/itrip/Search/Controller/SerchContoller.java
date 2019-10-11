package cn.itrip.Search.Controller;

import cn.itrip.Search.Dao.BaseDao;
import cn.itrip.Search.Entity.ItripHotelVO;
import cn.itrip.Search.Entity.Page;
import cn.itrip.Search.Entity.SearchHotCityVO;
import cn.itrip.Search.Entity.SearchHotelVO;
import cn.itrip.common.Dto;
import cn.itrip.common.DtoUtil;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.tools.DocumentationTool;
import java.io.IOException;
import java.util.List;

@Controller
public class SerchContoller {




    @RequestMapping(value = "/api/hotellist/searchItripHotelPage", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<Page<ItripHotelVO>> Serch1(@RequestBody SearchHotelVO vo) throws IOException, SolrServerException {
        BaseDao dao=new BaseDao();
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.setQuery("*:*");
        if(vo.getKeywords()!="")
        {
            solrQuery.addFilterQuery("keyword:"+vo.getKeywords());
        }
        if(vo.getPageNo()==null)
        {
            vo.setPageNo(1);
        }
        if(vo.getPageSize()==null)
        {
            vo.setPageSize(3);
        }
        if (vo.getMaxPrice()!=null)
        {
            solrQuery.addFilterQuery(" minPrice:[* TO "+vo.getMaxPrice()+"]");
        }
        if(vo.getMinPrice()!=null)
        {
            solrQuery.addFilterQuery(" minPrice:["+vo.getMinPrice()+" TO *]");
        }


        if(vo.getHotelLevel()!=null)
        {

            solrQuery.addFilterQuery(" hotelLevel:"+vo.getHotelLevel());
        }
        if(vo.getFeatureIds()!=null)
        {
            String [] number=vo.getFeatureIds().split(",");
            String str="";
            for(int i=0;i<number.length;i++)
            {
                if(i==0) {
                    str += "featureIds:*," + number[i] + ",*";
                }
                else
                {
                    str += " or featureIds:*," + number[i] + ",*";
                }
            }
            solrQuery.addFilterQuery(str);
        }

        if(vo.getTradeAreaIds()!=null)
        {
            String [] number=vo.getTradeAreaIds().split(",");
            String str="";
            for(int i=0;i<number.length;i++)
            {
                if(i==0) {
                    str += "tradingAreaIds:*," + number[i] + ",*";
                }
                else
                {
                    str += " or tradingAreaIds:*," + number[i] + ",*";
                }
            }
            solrQuery.addFilterQuery(str);
        }

        //排序
        if(vo.getAscSort()!=null)
        {
            solrQuery.setSort(vo.getAscSort(),SolrQuery.ORDER.asc);
        }

        return DtoUtil.returnDataSuccess(dao.getlist1(solrQuery,vo.getPageNo(),vo.getPageSize()));

    }

    @RequestMapping(value = "/api/hotellist/searchItripHotelListByHotCity", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto GetObj(@RequestBody SearchHotCityVO vo) throws IOException, SolrServerException {
         BaseDao dao=new BaseDao();
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.addFilterQuery("cityId:"+vo.getCityId());

        List<ItripHotelVO> list=dao.Getlist(solrQuery,vo.getCount());
         return DtoUtil.returnDataSuccess(list);
    }
}
