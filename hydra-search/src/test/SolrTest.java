import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 * Sole测试
 *
 * @author 許彬.
 * @creater 2016-08-23 20:07
 */

public class SolrTest {

    @Test
    public void test() throws IOException, SolrServerException {

        SolrServer solrServer = new HttpSolrServer("http://192.168.202.122:8080/solr");

        SolrInputDocument document = new SolrInputDocument();

        document.addField("id",100);

        document.addField("item_title","测试2");

        document.addField("item_price",100);

        solrServer.add(document);

        solrServer.commit();

    }




}
