package bit.spring4.buzbee.util;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.util.common.model.Pair;

public class Analyzer {
	@Autowired
	private SqlSession sqlSession;
	private String ns = "bit.spring4.buzbee.model.Board";
	
	public Analyzer(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public void analyze(String msg) {
		String path = "C:\\Users\\1027\\Documents\\GitHub\\BUZBEE\\Buzbee\\src\\main\\webapp\\WEB-INF\\lib\\KOMORAN-3.3.4\\";
		// String path = "C:\\Users\\User\\Documents\\GitHub\\BUZBEE\\Buzbee\\src\\main\\webapp\\WEB-INF\\lib\\KOMORAN-3.3.4\\";
		Komoran komoran = new Komoran(path + "models_light");
		KomoranResult result = komoran.analyze(msg);
		List<Pair<String, String>> list = result.getList();
		for(Pair<String, String> str : list) {
			if(str.getSecond().equals("NNG")) {
				String t_word = str.getFirst();
				try {
					sqlSession.insert(ns + ".insertTrend", t_word);
				} catch (Exception e) {
					sqlSession.update(ns + ".updateTrend", t_word);
				}
			}
		}
	}
}
