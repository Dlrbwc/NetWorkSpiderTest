package main;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.MySQLConnection;
import db.MYSQLControl;
import model.SchoolScore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import utils.URLFecter;

public class Main {
	static final Log logger = LogFactory.getLog(Main.class);
	public static void main(String[] args) throws Exception{
		//初始化一个httpclient
		HttpClient client = new DefaultHttpClient();

		MYSQLControl.getConnection();
		int university = 1;
		int province = 1;
		int classical = 1;
		List<SchoolScore> schoolScoreList = new ArrayList<SchoolScore>();
		while(university <= 2667){
			while(province <= 39){
				while(classical <= 2){
					//我们要爬取的一个地址
					String url = "http://college.gaokao.com/school/tinfo/" + university + "/result/" + province + "/" + (classical++) + "/";
					//抓取的数据
					List<SchoolScore> temp = URLFecter.URLParser(client, url);

					schoolScoreList.addAll(temp);

					for(SchoolScore sc : temp){
						System.out.println(sc.toString());
						System.out.println(MYSQLControl.insertScore(sc));
					}
				}
				province++;
				classical = 1;
			}
			university++;
			province = 1;
			classical = 1;
		}
		System.out.println("爬虫结束！");
		MYSQLControl.close();
	}
}
