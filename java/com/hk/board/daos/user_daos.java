package com.hk.board.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.board.dtos.RoleStatus;
import com.hk.board.dtos.user_dtos; 
import com.hk.datasource.database;

// 싱글톤 패턴 : 객체를 한번만 생성해서 사용하자
public class user_daos extends database{

	private static user_daos userDao;
	
	// new를 사용못하게 생성자에 private을 선언한다.
	private user_daos() {}
	
	// 객체를 한번만 생성해서 사용하는 기능을 구현
	public static user_daos getUserDao() {
		if(userDao==null) {
			userDao=new user_daos();
		}
		return userDao;
	}
	
	// 사용자 기능
	
	// 1. 회원가입 기능(enabled:"Y", role:"USER",regDate:SYSDATE())
	// insert문
	public boolean insertUser(user_dtos dto) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" INSERT INTO USERINFO (TID, TNAME, TPASSWORD, TADDRESS, TPHONE, TEMAIL, TENABLED, TROLE, REGDATE) "
				 + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, SYSDATE()) ";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getTid());
			psmt.setString(2, dto.getTname());
			psmt.setString(3, dto.getTpassword());
			psmt.setString(4, dto.getTaddress());
			psmt.setString(5, dto.getTphone());
			psmt.setString(6, dto.getTemail());
			psmt.setString(7, dto.getTenabled());
			psmt.setString(8, dto.getTrole());
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count > 0;
	}
	
	// 2. ID 중복체크하기
	public String idCheck(String id) {
		String resultId=null;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql="SELECT TID FROM userinfo WHERE TID=?";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			while(rs.next()) {
				resultId=rs.getString("TID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}

		return resultId;
	}
	
	// 3. 로그인 기능 : 파리미터 ID, PASSWORD
	public user_dtos getLogin(String id,String password) {
		user_dtos dto = null;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql="SELECT TID, TNAME, TROLE FROM userinfo "
				 + " WHERE TID=? and TPASSWORD=? and TENABLED='Y' ";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, password);
			rs=psmt.executeQuery();
			if(rs.next()) {
				dto = new user_dtos();
				dto.setTid(rs.getString("TID"));
				dto.setTname(rs.getString("TNAME"));
				dto.setTrole(rs.getString("TROLE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}

		return dto;
	}
	
	// 4. 나의 정보 조회
	public user_dtos getUser(String id) {
		user_dtos dto = null;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql="SELECT TID, TPASSWORD, TNAME, TADDRESS, TPHONE, TEMAIL, TENABLED, TROLE, REGDATE"
				 + " FROM USERINFO "
				 + " WHERE TID=? ";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
	
			rs=psmt.executeQuery();
			if(rs.next()) {
				dto = new user_dtos();
				dto.setTid(rs.getString("TID"));
				dto.setTpassword(rs.getString("TPASSWORD"));
				dto.setTname(rs.getString("TNAME"));
				dto.setTaddress(rs.getString("TADDRESS"));
				dto.setTphone(rs.getString("TPHONE"));
				dto.setTemail(rs.getString("TEMAIL"));
				dto.setTenabled(rs.getString("TENABLED"));
				dto.setTrole(rs.getString("TROLE"));
				dto.setRegdate(rs.getDate("REGDATE"));
			}
			System.out.println(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}

		return dto;
	}
	
	// 5. 나의 정보 수정하기
	public boolean updateUser(user_dtos dto) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE USERINFO SET TADDRESS=?, TPHONE=?, TEMAIL=? "
				 + " WHERE TID=? ";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getTaddress());
			psmt.setString(2, dto.getTphone());
			psmt.setString(3, dto.getTemail());
			psmt.setString(4, dto.getTid());

			count=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count > 0;
	}
	
	// 6. 탈퇴하기
	public boolean delUser(String id) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE USERINFO SET TENABLED='N' "
				 + " WHERE TID=? ";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);

			count=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count > 0;
	}
	// 관리자 기능
}