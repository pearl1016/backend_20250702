package com.hk.board.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.board.dtos.UserDto;
import com.hk.datasource.Database;

public class AdminDao extends Database{

	private static AdminDao adminDao;
	//new를 사용못하게 생성자에 private을 선언한다.
	private AdminDao() {}
	//객체를 한번만 생성해서 사용하는 기능을 구현
	public static AdminDao getAdminDao() {
		if(adminDao==null) {
			adminDao=new AdminDao();
		}
		return adminDao;
	}
	//1.회원목록 전체 조회[사용회원,탈퇴회원 모두]
	// getAllUserList: select문
	public List<UserDto> getAllUserList() {
		List<UserDto>list=new ArrayList<UserDto>();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql="SELECT SEQ,ID,NAME,ADDRESS,EMAIL,ROLE,ENABLED,REGDATE"
				 + " FROM USERINFO ";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
	
			rs=psmt.executeQuery();
			while(rs.next()) {
				UserDto dto=new UserDto();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setAddress(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setRole(rs.getString(6));
				dto.setEnabled(rs.getString(7));
				dto.setRegDate(rs.getDate(8));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}

		return list;
	}
	//2.회원목록 전체 조회[사용중] ---> 등급수정을 위한 목록 조회
	// getUserList: select문
	public List<UserDto> getUserList() {
		List<UserDto>list=new ArrayList<UserDto>();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql="SELECT SEQ,ID,NAME,ROLE,REGDATE"
				 + " FROM USERINFO "
				 + " WHERE ENABLED='Y' ";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
	
			rs=psmt.executeQuery();
			while(rs.next()) {
				UserDto dto=new UserDto();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setRole(rs.getString(4));
				dto.setRegDate(rs.getDate(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}

		return list;
	}
	//3.회원상세조회(1명에 대한)
	// getUserRole: select문
	public UserDto getUserRole(String id) {
		UserDto dto=new UserDto();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql="SELECT seq,id,name,role"
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
				dto.setRole(rs.getString(4));
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
	//4.회원등급수정
	// getUpdateRole: update문
	public boolean getUpdateRole(String id, String role) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE USERINFO SET ROLE=? "
				 + " WHERE ID=? ";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, role);
			psmt.setString(2, id);

			count=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
}