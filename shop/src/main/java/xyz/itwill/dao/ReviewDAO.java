package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.itwill.dto.ReviewDTO;

public class ReviewDAO extends JdbcDAO {
	private static ReviewDAO _dao;
	
	private ReviewDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new ReviewDAO();
	}
	
	public static ReviewDAO getDAO() {
		return _dao;
	}
	
	//조회정보(조회대상과 조회단어)를 전달받아 REVIEW 테이블에 저장된 행에서 조회정보가 포함된 
	//행의 갯수를 검색하여 반환하는 메소드
	// => 조회기능을 사용하지 않을 경우 REVIEW 테이블에 저장된 모든 행의 갯수를 검색하여 반환
	public int selectTotalReview(String search, String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			con = getConnection();
			
			//매개변수에 저장된 값을 비교하여 DBMS 서버에 다른 SQL 명령을 전달하여 실행
			// => 동적 SQL(Dynamic SQL)
			if(keyword.equals("")) {//조회기능을 사용하지 않을 경우
				String sql="select count(*) from review";
				pstmt=con.prepareStatement(sql);
			} else {
				String sql="select count(*) from review join member on review_member_num=member_num"
						+ " where "+search+" like '%'||?||'%' and review_status=1";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, keyword);
			}
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectReviewCount() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return count;
	}
	
	//페이징 관련 정보(시작행번호, 종료행번호)와 게시글 조회기능 관련 정보(조회대상과 조회단어)를
	//전달받아 REVIEW 테이블에 저장된 행에서 조회정보가 포함된 행을 페이징 처리로 검색하여
	//검색된 게시글 목록(List 객체)을 반환하는 메소드 //NEED
	public List<ReviewDTO> selectReviewList(int startRow, int endRow, String search, String keyword) { //NEED
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ReviewDTO> reviewList=new ArrayList<ReviewDTO>();
		try {
			con=getConnection();
			
			if(keyword.equals("")) { //NEED
				String sql="select * from (select rownum rn, temp.* from (select review_num"
					+ ",review_member_num,member_name,review_subject,review_content,review_image"
					+ ",review_register_date,review_update_date,review_ip,review_count,review_ref"
					+ ",review_restep,review_relevel,review_status from review join member on"
					+ " review_member_num=member_num order by review_ref desc,review_restep) temp)"
					+" where rn between ? and ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			} else {
				String sql="select * from (select rownum rn, temp.* from (select review_num"
					+ ",review_member_num,member_name,review_subject,review_content,review_image"
					+ ",review_register_date,review_update_date,review_ip,review_count,review_ref"
					+ ",review_restep,review_relevel,review_status from review join member on"
					+ " review_member_num=member_num where "+search+" like '%'||?||'%' and review_status=1"
					+ " order by review_ref desc,review_restep) temp) where rn between ? and ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewDTO review=new ReviewDTO();
				review.setReviewNum(rs.getInt("review_num"));
				review.setReviewMemberNum(rs.getInt("review_member_num"));
				review.setMemberName(rs.getString("member_name"));
				review.setReviewSubject(rs.getString("review_subject"));
				review.setReviewContent(rs.getString("review_content"));
				review.setReviewImage(rs.getString("review_image"));
				review.setReviewRegisterDate(rs.getString("review_register_date"));
				review.setReviewUpdateDate(rs.getString("review_update_date"));
				review.setReviewIp(rs.getString("review_ip"));
				review.setReviewCount(rs.getInt("review_count"));
				review.setReviewRef(rs.getInt("review_ref"));
				review.setReviewRestep(rs.getInt("review_restep"));
				review.setReviewRelevel(rs.getInt("review_relevel"));
				review.setReviewStatus(rs.getInt("review_status"));
				
				reviewList.add(review);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectReviewList() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return reviewList;
	}
	
	//REVIEW_SEQ 시퀸스의 다음값을 검색하여 반환하는 메소드
	public int selectReviewNextNum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int nextNum=0;
		try {
			con=getConnection();
			
			String sql="select review_seq.nextval from dual";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				nextNum=rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectReviewNextNum() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return nextNum;
	}
	
	//게시글(ReviewDTO 객체)을 전달받아 REVIEW 테이블의 행으로 삽입하고 삽입행의 갯수를 반환하는 메소드 //NEED
	public int insertReview(ReviewDTO review) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="insert into review values(?,?,?,?,?,sysdate,null,?,0,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, review.getReviewNum());
			pstmt.setInt(2, review.getReviewMemberNum());
			pstmt.setString(3, review.getReviewSubject());
			pstmt.setString(4, review.getReviewContent());
			pstmt.setString(5, review.getReviewImage());
			pstmt.setString(6, review.getReviewIp());
			pstmt.setInt(7, review.getReviewRef());
			pstmt.setInt(8, review.getReviewRestep());
			pstmt.setInt(9, review.getReviewRelevel());
			pstmt.setInt(10, review.getReviewStatus());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertReview() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//부모글 관련 정보를 전달받아 REVIEW 테이블에서 저장된 행에서 전달값을 비교하여
	//REVIEW_REF 컬럼값을 1 증가되도록 변경하고 변경행의 갯수를 반환하는 메소드
	public int updateReviewRestep(int ref, int restep) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update review set review_restep=review_restep+1"
					+ " where review_ref=? and review_restep>?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, restep);
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateReviewRestep() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//글번호(int)를 전달받아 REVIEW 테이블에 저장된 하나의 행을 검색하여 게시글(ReviewDTO 객체)로
	//반환하는 메소드
	public ReviewDTO selectReviewByNum(int reviewNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ReviewDTO review=null;
		try {
			con=getConnection();
			
			String sql="select review_num,review_member_num,member_name,review_subject"
					+ ",review_content,review_image,review_register_date,review_update_date"
					+ ",review_ip,review_count,review_ref,review_restep,review_relevel"
					+ ",review_status from review join member on review_member_num=member_num"
					+ " where review_num=? and review_status<>0";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reviewNum);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				review=new ReviewDTO();
				review.setReviewNum(rs.getInt("review_num"));
				review.setReviewMemberNum(rs.getInt("review_member_num"));
				review.setMemberName(rs.getString("member_name"));
				review.setReviewSubject(rs.getString("review_subject"));
				review.setReviewContent(rs.getString("review_content"));
				review.setReviewImage(rs.getString("review_image"));
				review.setReviewRegisterDate(rs.getString("review_register_date"));
				review.setReviewUpdateDate(rs.getString("review_update_date"));
				review.setReviewIp(rs.getString("review_ip"));
				review.setReviewCount(rs.getInt("review_count"));
				review.setReviewRef(rs.getInt("review_ref"));
				review.setReviewRestep(rs.getInt("review_restep"));
				review.setReviewRelevel(rs.getInt("review_relevel"));
				review.setReviewStatus(rs.getInt("review_status"));
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectReviewByNum() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return review;
	}
	
	//글번호를 전달받아 REVIEW 테이블에 저장된 행의 게시글조횟수를 1 증가되도록 변경하고
	//변경행의 갯수를 반환하는 메소드
	public int updateReviewCount(int reviewNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update review set review_count=review_count+1 where review_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reviewNum);
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateReviewCount() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	//게시글(ReviewDTO 객체)을 전달받아 REVIEW 테이블에 저장된 행을 변경하고 변경행의 갯수를 /NEED
	//반환하는 메소드
	public int updateReview(ReviewDTO review) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update review set review_subject=?,review_content=?,review_image=?"
					+ ",review_status=?,review_update_date=sysdate where review_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, review.getReviewSubject());
			pstmt.setString(2, review.getReviewContent());
			pstmt.setString(3, review.getReviewImage());
			pstmt.setInt(4, review.getReviewStatus());
			pstmt.setInt(5, review.getReviewNum());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateReview() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
}












