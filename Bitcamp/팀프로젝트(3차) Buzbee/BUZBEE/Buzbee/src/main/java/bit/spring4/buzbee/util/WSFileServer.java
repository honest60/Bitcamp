package bit.spring4.buzbee.util;

import java.io.*;
import java.nio.ByteBuffer;
import javax.websocket.*;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;
import bit.spring4.buzbee.board.model.service.BoardService;

@ServerEndpoint(value="/file/upload", configurator = SpringConfigurator.class)
public class WSFileServer {
	@Autowired
	BoardService service;
    BufferedOutputStream bos;
    String path = "C:\\Users\\User\\Documents\\GitHub\\BUZBEE\\Buzbee\\src\\main\\webapp\\resources\\store\\";
    
    // 메세지를 받으면 호출된다.
    @OnMessage
    public void onMessage(Session session, String msg) {
        
        // 클라이언트에서 파일이 끝났다는 신호로 "end" 문자열을 보낸다.
        // msg가 end가 아니라면 파일로 연결된 스트림을 연다.
        if(!msg.equals("end")){
        	System.out.println(msg);
        	// 클라이언트에서 파일을 전송하기전 파일이름을 "file name:aaa.aaa" 형식으로 보낸다.
        	String fileName = msg.substring(msg.indexOf(":")+1, msg.lastIndexOf("$"));
        	int idx = fileName.lastIndexOf(".");
        	String fName = fileName.substring(0,  idx);
        	String fExt = fileName.substring(idx+1);

        	long fileSize = Long.parseLong(msg.substring(msg.indexOf("#")+1));
        	long b_no = Long.parseLong(msg.substring(msg.lastIndexOf("$")+1, msg.lastIndexOf("#")));

        	File file = new File(path + fileName);

            if(file.exists())  file = new File(path + fName + "_" + System.currentTimeMillis() + "." + fExt);
            
            service.insertFileService(fileName, file.getName(), fExt, fileSize);
            long f_no = service.selectFileNoBySaveNameService(file.getName());
            service.insertBoardFileService(b_no, f_no);
            
            try {
                bos = new BufferedOutputStream(new FileOutputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            
        // msg 가 end가 왔다면 전송이 완료되었음을 알리는 신호이므로 outputstream 을 닫아준다.
        }else{
            try {
            	bos.flush();
                bos.close();
                System.out.println("전송완료");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    // 바이너리 데이터가 오게되면 호출된다.
    @OnMessage
    public void processUpload(ByteBuffer msg, boolean last, Session session) {
        
        while(msg.hasRemaining()){
            try {
                bos.write(msg.get());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnOpen
    public void open(Session session) {
        System.out.println("WebSocket File Server Open......");
    }

    @OnError
    public void error(Session session, Throwable t) {
        System.out.println("error.......");
    }

    @OnClose
    public void closedConnection(Session session) {
        System.out.println("연결종료........");
    }
    
} 