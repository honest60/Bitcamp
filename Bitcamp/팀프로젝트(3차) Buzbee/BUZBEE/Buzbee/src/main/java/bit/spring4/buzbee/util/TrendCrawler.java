/* 

사용하려면 jsoup 추가할 것
<dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version>1.11.1</version>
</dependency>

package bit.spring4.buzbee.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bit.spring4.buzbee.model.Google;

public class TrendCrawler {
	ArrayList<Google> queryList = new ArrayList<Google>();
	
	public ArrayList<Google> getTIOBEs(String cssQuery) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
		String date = sdf.format(new Date());
		String monthStr = date.substring(3);
		String day = date.substring(0, 2);
		int month = Integer.parseInt(monthStr);
		
		try {
			Document doc = Jsoup.connect("https://trends.google.co.kr/trends/trendingsearches/daily/rss?geo=KR").get();
			Elements elements = doc.select(cssQuery);
			System.out.println("크롤링한 데이터 수 : " + elements.size() + "개");
			if( elements.size() > 1 ){
				for( Element element : elements ){
					if(element.text().contains(day + " " + setMonth(month))) {
						cutString(element.text());
					}
					// System.out.println("html = " + element.html());
				}
				return queryList;
			} else {
				if(elements.text().contains(day + " " + setMonth(month))) {
					cutString(elements.text());
				}
				return queryList;
				// System.out.println("html = " + elements.html());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String setMonth(int month) {
		switch(month) {
			case 1: return "Jan";
			case 2: return "Feb";
			case 3: return "Mar";
			case 4: return "Apr";
			case 5: return "May";
			case 6: return "Jun";
			case 7: return "Jul";
			case 8: return "Aug";
			case 9: return "Sep";
			case 10: return "Oct";
			case 11: return "Nov";
			case 12: return "Dec";
			default: return "Err";
		}
	}
	
	private void cutString(String element) {
		String text = element.substring(0, element.indexOf("+")+1);
		String hit = text.substring(text.lastIndexOf(" ")+1);
		String query = text.substring(0, text.lastIndexOf(" "));
		queryList.add(new Google(query, hit));
	}
}
*/