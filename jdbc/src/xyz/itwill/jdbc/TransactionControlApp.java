package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC 프로그램은 기본적으로 자동 커밋 기능(Auto Commit)이 활성화 되어 있는 DML 명령을 전달하여
//실행하면 무조건 자동으로 커밋 처리
// => 프로그램 실행시 예외가 발생될 경우 예외 발생전에 전달되어 실행된 DML 명령에 대한 롤백 처리 불가능

//JDBC 프로그램에서는 자동 커밋 기능을 비활성화 처리하고 프로그램 실행시 예외가 발생되지 않은
//경우 커밋 처리하고 예외가 발생된 경우 롤백 처리하는 것을 권장

//STUDENT 테이블에 저장된 행(학생정보)에서 NO 컬럼값(학번)이 [2000]인 행의 NAME 컬럼값(이름)을
//[임꺽정]으로 변경하는 JDBC 프로그램 작성
public class TransactionControlApp { 
	public static void main(String[] args) {
		Connection con=null;
		Statement stmt=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String username="scott";
			String password="tiger";
			con=DriverManager.getConnection(url, username, password);
			
			//Connection.setAutoCommit(boolean autoCommit) : 자동 커밋 기능을 활성화 상태
			//(true - 기본값) 또는 비활성화 상태(false)로 변경하는 메소드
			con.setAutoCommit(false);
			
			stmt=con.createStatement();
			
			String sql="update student set name='임꺽정' where no=2000";
			int rows=stmt.executeUpdate(sql);
			
			//if(con!=null) throw new Exception();//인위적인 예외 발생
			
			if(rows > 0) {//변경행이 있는 경우
				System.out.println("[메세지]"+rows+"명의 학생정보를 변경 하였습니다.");
			} else {//변경행이 없는 경우
				System.out.println("[메세지]해당 학번의 학생정보를 찾을 수 없습니다.");
			}
			
			//Connection.commit() : COMMIT 명령을 전달하여 실행하는 메소드 - 커밋 처리
			con.commit();
		} catch (ClassNotFoundException e) {
			System.out.println("[에러]OracleDriver 클래스를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("[에러]DBMS 관련 오류 = "+e.getMessage());
		} catch (Exception e) {
			System.out.println("[에러]프로그램 실행에 예기치 못한 오류가 발생 되었습니다.");
			
			//Connection.rollback() : ROLLBACK 명령을 전달하여 실행하는 메소드 - 롤백 처리
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}