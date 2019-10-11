package cn.itrip.Search.Dao;

import cn.itrip.Search.Entity.HotelEntity;
import cn.itrip.Search.Entity.ItripHotelVO;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import sun.applet.Main;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class TextSolr {
    static String url="http://localhost:8080/solr-4.9.1/hotel-Core";

    public static void main(String[] args) throws Exception {
        HttpSolrClient solr=new HttpSolrClient(url);
        solr.setParser(new XMLResponseParser());
        solr.setConnectionTimeout(1000);
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.setStart(0);
        solrQuery.setRows(9);
       /* solrQuery.addFilterQuery("id:2");*/
        solrQuery.setSort("id",SolrQuery.ORDER.desc);
        SolrResponse response=solr.query(solrQuery);
        List<ItripHotelVO> list=((QueryResponse) response).getBeans(ItripHotelVO.class);
        for(ItripHotelVO i:list)
        {
            System.out.println(i.getHotelName()+":"+i.getRedundantCityName());
        }
    }
}
