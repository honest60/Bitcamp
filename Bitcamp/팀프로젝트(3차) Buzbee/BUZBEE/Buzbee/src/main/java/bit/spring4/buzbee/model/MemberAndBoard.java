package bit.spring4.buzbee.model;

import java.util.List;
import lombok.Data;

@Data
public class MemberAndBoard {
   // member
   private long m_no;
   private String m_id;
   private String m_name;
   private String m_email;
   private String m_password;
   private long m_profile;
   private long m_header;
   private int m_delete;
   private String m_rdate;
   // board
   private long b_no;
   private long f_no;
   private String b_content;
   private long b_rebuz;
   private long b_like;
   private long b_ref;
   private List<String> f_saveFiles;
   private String b_rdate;
   // additional
   private String img_profile;
   private String img_header;
   private boolean following;
   private boolean board_like;
   private long reply_count;
   
   public MemberAndBoard() {} 

   public MemberAndBoard(long m_no, String m_id, String m_name, String m_email,  String m_password,
         long f_profile, long f_header, int m_delete, String m_rdate, long b_no, long f_no, String b_content,
         long b_rebuz, long b_like, long b_ref, String b_rdate, List<String> f_saveFiles) {
      this.m_no = m_no;
      this.m_id = m_id;
      this.m_name = m_name;
      this.m_email = m_email;
      this.m_password = m_password;
      this.m_profile = f_profile;
      this.m_header = f_header;
      this.m_delete = m_delete;
      this.m_rdate = m_rdate;
      this.b_no = b_no;
      this.f_no = f_no;
      this.b_content = b_content;
      this.b_rebuz = b_rebuz;
      this.b_like = b_like;
      this.b_ref = b_ref;
      this.b_rdate = b_rdate;
      this.f_saveFiles = f_saveFiles;
   }
}