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
    
    // �޼����� ������ ȣ��ȴ�.
    @OnMessage
    public void onMessage(Session session, String msg) {
        
        // Ŭ���̾�Ʈ���� ������ �����ٴ� ��ȣ�� "end" ���ڿ��� ������.
        // msg�� end�� �ƴ϶�� ���Ϸ� ����� ��Ʈ���� ����.
        if(!msg.equals("end")){
        	System.out.println(msg);
        	// Ŭ���̾�Ʈ���� ������ �����ϱ��� �����̸��� "file name:aaa.aaa" �������� ������.
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
            
        // msg �� end�� �Դٸ� ������ �Ϸ�Ǿ����� �˸��� ��ȣ�̹Ƿ� outputstream �� �ݾ��ش�.
        }else{
            try {
            	bos.flush();
                bos.close();
                System.out.println("���ۿϷ�");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    // ���̳ʸ� �����Ͱ� ���ԵǸ� ȣ��ȴ�.
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
        System.out.println("��������........");
    }
    
} 