package com.hk.board.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hk.board.dtos.RoleStatus;
import com.hk.board.dtos.UserDto;
import com.hk.datasource.Database;

// 싱글톤 패턴 : 객체를 한번만 생성해서 사용하자
public class UserDao extends Database{

	private static UserDao userDao;
	//new를 사용못하게 생성자에 private을 선언한다.
	private UserDao() {}
	//객체를 한번만 생성해서 사용하는 기능을 구현
	public static UserDao getUserDao() {
		if(userDao==null) {
			userDao=new UserDao();
		}
		return userDao;
	}
	
	//사용자 기능
	
	//1. 회원가입 기능(enabled:"Y", role:"USER",regDate:SYSDATE())
	// insert문
	public boolean insertUser(UserDto dto) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" INSERT INTO USERINFO "
				 + " VALUES(NULL,?,?,?,?,?,'Y',?,SYSDATE()) ";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getPassword());
			psmt.setString(4, dto.getAddress());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, String.valueOf(RoleStatus.USER));
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	
	//2.ID 중복체크하기
	public String idCheck(String id) {
		String resultId=null;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql="SELECT id FROM userinfo WHERE id=?";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			while(rs.next()) {
				resultId=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}

		return resultId;
	}
	
	//3.로그인 기능 : 파리미터 ID, PASSWORD
	public UserDto getLogin(String id,String password) {
		UserDto dto=new UserDto();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql="SELECT id,name,role FROM userinfo "
				 + " WHERE id=? and password=? and enabled='Y' ";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, password);
			rs=psmt.executeQuery();
			while(rs.next()) {
				dto.setId(rs.getString(1));
				dto.setPassword(rs.getString(2));
				dto.setRole(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}

		return dto;
	}
	
	//4. 나의 정보 조회
	public UserDto getUser(String id) {
		UserDto dto=new UserDto();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql="SELECT seq,id,name,address,email,role,regdate"
				 + " FROM userinfo "
				 + " WHERE id=? ";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
	
			rs=psmt.executeQuery();
			while(rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setAddress(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setRole(rs.getString(6));
				dto.setRegDate(rs.getDate(7));
			}
			System.out.println(dto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}

		return dto;
	}
	
	//5.나의 정보 수정하기
	public boolean updateUser(UserDto dto) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE USERINFO SET ADDRESS=? , EMAIL=? "
				 + " WHERE ID=? ";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getAddress());
			psmt.setString(2, dto.getEmail());
			psmt.setString(3, dto.getId());

			count=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	
	//6.탈퇴하기
	public boolean delUser(String id) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE USERINFO SET ENABLED='N' "
				 + " WHERE ID=? ";
		
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
		return count>0?true:false;
	}
	//관리자 기능
}