package mvc.model;

import java.util.ArrayList;

import mvc.database.DBConnection;

public class BoardDAO extends DBConnection{

   public BoardDAO() {
		super();
	}
//board 테이블의 레코드 개수
   public int getListCount(String items, String text) {

      int x = 0;

      String sql;
      
      if (items == null && text == null)
         sql = "select  count(*) from board";
      else
         sql = "SELECT   count(*) FROM board where " + items + " like '%" + text + "%'";
      
      try {
         psmt = conn.prepareStatement(sql);
         rs = psmt.executeQuery();

         if (rs.next()) 
            x = rs.getInt(1);
         
      } catch (Exception ex) {
         System.out.println("getListCount() 에러: " + ex);
      } 
      return x;
   }
   //board 테이블의 레코드 가져오기
   public ArrayList<BoardDTO> getBoardList(int page, int limit, String items, String text) {

      int total_record = getListCount(items, text );
      int start = (page - 1) * limit;
      int index = start + 1;

      String sql;

      if (items == null && text == null)
         sql = "select * from board ORDER BY num DESC";
      else
         sql = "SELECT  * FROM board where " + items + " like '%" + text + "%' ORDER BY num DESC ";

      ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();

      try {
         psmt = conn.prepareStatement(sql);
         rs = psmt.executeQuery();

         while (rs.absolute(index)) {
            BoardDTO board = new BoardDTO();
            board.setNum(rs.getInt("num"));
            board.setId(rs.getString("id"));
            board.setName(rs.getString("name"));
            board.setSubject(rs.getString("subject"));
            board.setContent(rs.getString("content"));
            board.setRegist_day(rs.getString("regist_day"));
            board.setHit(rs.getInt("hit"));
            board.setIp(rs.getString("ip"));
            list.add(board);

            if (index < (start + limit) && index <= total_record)
               index++;
            else
               break;
         }
         return list;
      } catch (Exception ex) {
         System.out.println("getBoardList() 에러 : " + ex);
      }
      return null;
   }
   //member 테이블에서 인증된 id의 사용자명 가져오기
   public String getLoginNameById(String id) {

      String name=null;
      String sql = "select * from member where id = ? ";

      try {
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);
         rs = psmt.executeQuery();

         if (rs.next()) 
            name = rs.getString("name");   
         
         return name;
      } catch (Exception ex) {
         System.out.println("getBoardByNum() 에러 : " + ex);
      } 
      return null;
   }

   //board 테이블에 새로운 글 삽입히가
   public void insertBoard(BoardDTO board)  {
      
      try {      

         String sql = "insert into board values(?, ?, ?, ?, ?, ?, ?, ?)";
      
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, board.getNum());
         psmt.setString(2, board.getId());
         psmt.setString(3, board.getName());
         psmt.setString(4, board.getSubject());
         psmt.setString(5, board.getContent());
         psmt.setString(6, board.getRegist_day());
         psmt.setInt(7, board.getHit());
         psmt.setString(8, board.getIp());

         psmt.executeUpdate();
      } catch (Exception ex) {
         System.out.println("insertBoard() 에러 : " + ex);
      }       
   } 
   //선택된 글의 조회수 증가하기
   public void updateHit(int num) {

      try {

         String sql = "select hit from board where num = ? ";
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, num);
         rs = psmt.executeQuery();
         int hit = 0;

         if (rs.next())
            hit = rs.getInt("hit") + 1;
      

         sql = "update board set hit=? where num=?";
         psmt = conn.prepareStatement(sql);      
         psmt.setInt(1, hit);
         psmt.setInt(2, num);
         psmt.executeUpdate();
      } catch (Exception ex) {
         System.out.println("updateHit() 에러 : " + ex);
      } 
   }
   //선택된 글 상세 내용 가져오기
   public BoardDTO getBoardByNum(int num, int page) {
      BoardDTO board = null;

      updateHit(num);
      String sql = "select * from board where num = ? ";

      try {
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, num);
         rs = psmt.executeQuery();

         if (rs.next()) {
            board = new BoardDTO();
            board.setNum(rs.getInt("num"));
            board.setId(rs.getString("id"));
            board.setName(rs.getString("name"));
            board.setSubject(rs.getString("subject"));
            board.setContent(rs.getString("content"));
            board.setRegist_day(rs.getString("regist_day"));
            board.setHit(rs.getInt("hit"));
            board.setIp(rs.getString("ip"));
         }
         
         return board;
      } catch (Exception ex) {
         System.out.println("getBoardByNum() 에러 : " + ex);
      } 
      return null;
   }
   //선택된 글 내용 수정하기
   public void updateBoard(BoardDTO board) {

      try {
         String sql = "update board set name=?, subject=?, content=? where num=?";

         psmt = conn.prepareStatement(sql);
         
         conn.setAutoCommit(false);

         psmt.setString(1, board.getName());
         psmt.setString(2, board.getSubject());
         psmt.setString(3, board.getContent());
         psmt.setInt(4, board.getNum());

         psmt.executeUpdate();         
         conn.commit();

      } catch (Exception ex) {
         System.out.println("updateBoard() 에러 : " + ex);
      }
   } 
   //선택된 글 삭제하기
   public void deleteBoard(int num) {
      String sql = "delete from board where num=?";   

      try {
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, num);
         psmt.executeUpdate();

      } catch (Exception ex) {
         System.out.println("deleteBoard() 에러 : " + ex);
      }
   }   
}