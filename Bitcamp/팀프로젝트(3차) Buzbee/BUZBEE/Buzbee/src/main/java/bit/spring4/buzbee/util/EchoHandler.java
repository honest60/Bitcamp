package bit.spring4.buzbee.util;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import bit.spring4.buzbee.board.model.service.BoardService;
import bit.spring4.buzbee.login.model.service.LoginService;

public class EchoHandler extends TextWebSocketHandler {
	  private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	  private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	  @Autowired
	  private BoardService boardService;
	  @Autowired
	  private LoginService loginService;
	  @Autowired
	  private Analyzer analyzer;
	 
	  @Override
	  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		  sessionList.add(session);
		  session.setTextMessageSizeLimit(1024*1024*5);
		  logger.info("{} 연결됨", session.getPrincipal().getName());
	  }
	 
	  @Override
	  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		  long b_no = -1;
		  long b_ref = -1;
		  long m_no = -1;
		  String msg = message.getPayload();

		  if(msg.startsWith("#")) {
			  b_ref = Long.parseLong(msg.substring(1, msg.indexOf("$")));
			  msg = msg.substring(msg.indexOf("$") + 1);
		  }
		  
		  String m_content = null;
		  String fileMsg = "";
		  String name = null;
		  List<String> follower = null;
		  HashMap<String, Long> ids = new HashMap<String, Long>();
		  int idx = msg.lastIndexOf("[FILES]");
		  
		  m_content = msg.substring(msg.indexOf(":") + 1, idx);
		  m_content = m_content.trim();
		  if(m_content.equals("")) m_content="";
		  name = msg.substring(0, msg.indexOf(":"));
		  
		  m_no = loginService.selectM_NOByIdService(session.getPrincipal().getName()); 
		  
		  if(b_ref == -1) b_no = boardService.insertService(m_no, m_content);
		  else b_no = boardService.insertReplyService(m_no, b_ref, m_content);
		  
		  String temp = msg.substring(idx + 7);
		  String[] files = temp.split("#");

		  if(files[0].length() != 0) {
			  for(int i = 0; i < files.length; i++) {
				  boardService.insertBoardFileService(b_no, Long.parseLong(files[i]));
			  }
		  }
		  
		  List<String> fileNames = boardService.selectFileByBNoService(b_no);
		  for(String fileName : fileNames) {
			  fileMsg += "#" + fileName; 
		  }
		  
		  follower = boardService.followerOnlineService(m_no);
		  ArrayList<Long> idList = pickIds(m_content);
		  if(!idList.contains(m_no)) idList.add(m_no);
		  else {
			  String user = loginService.selectByNoService(m_no).getM_id();
			  m_content = m_content.replace("@"+user, "<a class='a-color' href='"+user+"'>@"+user+"</a>");
		  }
		  
		  for(Long id : idList) {
			  if(id != -1 && id != m_no) {
				  String mention = loginService.selectByNoService(id).getM_id();
				  boardService.insertMentionService(b_no, id);
				  ids.put(mention, b_no);
				  System.out.println(mention);
				  m_content = m_content.replace("@"+mention, "<a class='a-color' href ='"+mention+"'>@"+mention+"</a>");
			  }
		  }
		  
		  for (WebSocketSession sess : sessionList) {
			  if(sess.getPrincipal().getName().equals(session.getPrincipal().getName())) {
				  sess.sendMessage(new TextMessage(session.getPrincipal().getName() + "/" + name + ":" + m_content + "$" + b_no + fileMsg));
			  } else {
				  if(follower != null) {
					  System.out.println(sess.getPrincipal().getName());
					  System.out.println(follower);
					  if(follower.contains(sess.getPrincipal().getName())) {
						  sess.sendMessage(new TextMessage(session.getPrincipal().getName() + "/" + name + ":" + m_content + "$" + b_no + fileMsg));
					  }
				  }
				  
				  if(ids != null) {
					  if(ids.containsKey(sess.getPrincipal().getName()) && !follower.contains(sess.getPrincipal().getName())) {
						  sess.sendMessage(new TextMessage(session.getPrincipal().getName() + "/" + name + ":" + m_content + "$" + b_no + fileMsg));
					  }
				  }
			  }
		  }
		  
		  analyzer.analyze(m_content);
	  }
	 
	  @Override
	  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		  sessionList.remove(session);
	  }
	  
	  private ArrayList<Long> pickIds(String msg) {
		  String[] ids = msg.split("@");
		  ArrayList<Long> idsList = new ArrayList<Long>();
		  if(ids == null) return null;
		  else {
			  for(int i = 1; i < ids.length; i++) {
				  String id = null;
				  try {
					  id = ids[i].substring(0, ids[i].indexOf(" "));
					  long m_no = loginService.selectM_NOByIdService(id);
					  idsList.add(m_no);
				  } catch (Exception e) {
					  id = ids[i].substring(0);
					  long m_no = loginService.selectM_NOByIdService(id);
					  idsList.add(m_no);
				  }
			  }
		  }
		  return idsList;
	  }
}

