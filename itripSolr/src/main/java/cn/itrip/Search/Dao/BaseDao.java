package cn.itrip.Search.Dao;

import cn.itrip.Search.Entity.ItripHotelVO;
import cn.itrip.Search.Entity.Page;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.List;

public class BaseDao {
    static String url="http://localhost:8080/solr-4.9.1/hotel-Core";
    HttpSolrClient solr;
    public BaseDao()
    {
        solr=new HttpSolrClient(url);
        solr.setParser(new XMLResponseParser());
        solr.setConnectionTimeout(1000);
    }

    public Page<ItripHotelVO> getlist1(SolrQuery solrQuery,int pageindex,int pageSize) throws IOException, SolrServerException {

        solrQuery.setStart((pageindex-1)*pageSize);
        solrQuery.setRows(pageSize);

        SolrResponse response=solr.query(solrQuery);



        List<ItripHotelVO> list=((QueryResponse) response).getBeans(ItripHotelVO.class);


        SolrDocumentList solrDocuments=((QueryResponse) response).getResults();
        //一共有多少条数据
        Page<ItripHotelVO> page=
                new Page<>(pageindex,pageSize,new Long(solrDocuments.getNumFound()).intValue());

        page.setRows(list);
        return page;
    }


    public List<ItripHotelVO> Getlist(SolrQuery solrQuery,int pageSize) throws IOException, SolrServerException {
        SolrResponse response=solr.query(solrQuery);
        solrQuery.setStart(0);
        solrQuery.setRows(pageSize);
        List<ItripHotelVO> list=((QueryResponse) response).getBeans(ItripHotelVO.class);
        return list;
    }
}
