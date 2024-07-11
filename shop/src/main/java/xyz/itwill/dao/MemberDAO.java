package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import xyz.itwill.dto.MemberDTO;

public class MemberDAO extends JdbcDAO {
	private static MemberDAO _dao;
	
	private MemberDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new MemberDAO();		
	}
	
	public static MemberDAO getDAO() {
		return _dao;
	}
	
	//회원정보(MemberDTO 객체)를 전달받아 MEMBER 테이블의 행으로 삽입하고 삽입행의 갯수(int)를 반환하는 메소드
	public int insertMember(MemberDTO member) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="insert into member values(member_seq.nextval,?,?,?,?,?,?,?,?,sysdate,null,null,1)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPasswd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberEmail());
			pstmt.setString(5, member.getMemberMobile());
			pstmt.setString(6, member.getMemberZipcode());
			pstmt.setString(7, member.getMemberAddress1());
			pstmt.setString(8, member.getMemberAddress2());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertMember() 메서드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//아이디(String 객체)를 전달받아 MEMBER 테이블에 저장된 하나의 행을 검색하여 검색된 
	//회원정보(MemberDTO)를 반환하는 메소드
	public MemberDTO selectMemberById(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDTO member=null;
		try {
			con=getConnection();
			
			String sql="select member_num,member_id,member_passwd,member_name,member_email"
				+",member_mobile,member_zipcode,member_address1,member_address2"
				+",member_register_date,member_update_date,member_last_login,member_auth"
				+" from member where member_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();			
			
			if(rs.next()) {
				member=new MemberDTO();
				member.setMemberNum(rs.getInt("member_num"));
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPasswd(rs.getString("member_passwd"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberEmail(rs.getString("member_email"));
				member.setMemberMobile(rs.getString("member_mobile"));
				member.setMemberZipcode(rs.getString("member_zipcode"));
				member.setMemberAddress1(rs.getString("member_address1"));
				member.setMemberAddress2(rs.getString("member_address2"));
				member.setMemberRegisterDate(rs.getString("member_register_date"));
				member.setMemberUpdateDate(rs.getString("member_update_date"));
				member.setMemberLastLogin(rs.getString("member_last_login"));
				member.setMemberAuth(rs.getInt("member_auth"));
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectMemberById() 메서드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return member;
	}
}















