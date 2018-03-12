package parse;

import java.util.ArrayList;
import java.util.List;

import model.SchoolScore;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parse {
	public static List<SchoolScore> getData (String html) throws Exception{
        //获取的数据，存放在集合中
        List<SchoolScore> data = new ArrayList<SchoolScore>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
        Elements elements=doc.select("tbody").select("tr");
        Elements info = doc.select("div[class=cont_l in]").select("p[class=btnFsxBox]").select("font");
        for (Element ele:elements) {
            String year=ele.select("td").eq(0).text();
            if(year.length() == 4){
            	String lowScore=ele.select("td").eq(1).text().equals("------") ? "0" : ele.select("td").eq(1).text();
                String higtScore=ele.select("td").eq(2).text().equals("------") ? "0" : ele.select("td").eq(2).text();
                String averageScore=ele.select("td").eq(3).text().equals("------") ? "0" : ele.select("td").eq(3).text();
                String batch=ele.select("td").eq(5).text();
                //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
                SchoolScore schoolScore=new SchoolScore();
                //对象的值
                schoolScore.setName(info.eq(0).text());
                schoolScore.setProvince(info.eq(1).text());
                schoolScore.setClassical(info.eq(2).text());
                schoolScore.setYear(year);
                schoolScore.setLowScore(lowScore);
                schoolScore.setHigtScore(higtScore);
                schoolScore.setAverageScore(averageScore);
                schoolScore.setBatch(batch);
                //将每一个对象的值，保存到List集合中
                data.add(schoolScore);
            }
            
        }
        //返回数据
        return data;
    }
}
