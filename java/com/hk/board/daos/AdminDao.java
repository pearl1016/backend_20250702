//package com.hk.board.daos;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.hk.board.dtos.user_dtos;
//import com.hk.datasource.database;
//
//public class AdminDao extends database{
//
//	private static AdminDao adminDao;
//	// new를 사용 못하게 생성자에 private을 선언합니다.
//	private AdminDao() {}
//	// 객체를 한 번만 생성해서 사용하는 기능을 구현합니다.
//	public static AdminDao getAdminDao() {
//		if(adminDao==null) {
//			adminDao=new AdminDao();
//		}
//		return adminDao;
//	}
//	
//	// 1. 회원목록 전체 조회[사용회원,탈퇴회원 모두]
//	// getAllUserList: select문
//	public List<user_dtos> getAllUserList() {
//		List<user_dtos>list=new ArrayList<user_dtos>();
//		
//		Connection conn=null;
//		PreparedStatement psmt=null;
//		ResultSet rs=null;
//		
//		// SQL 쿼리 수정: T 접두사를 붙여 컬럼명 통일
//		String sql="SELECT TSEQ, TID, TNAME, TADDRESS, TEMAIL, TROLE, TENABLED, REGDATE"
//				 + " FROM USERINFO ";
//		
//		try {
//			// 오타 수정: getConnetion() -> getConnection()
//			conn=getConnetion();
//			psmt=conn.prepareStatement(sql);
//	
//			rs=psmt.executeQuery();
//			while(rs.next()) {
//				user_dtos dto=new user_dtos();
//				// DTO의 setter와 ResultSet의 컬럼명 통일
//				dto.setTseq(rs.getInt("TSEQ"));
//				dto.setTid(rs.getString("TID"));
//				dto.setTname(rs.getString("TNAME"));
//				dto.setTaddress(rs.getString("TADDRESS"));
//				dto.setTemail(rs.getString("TEMAIL"));
//				dto.setTrole(rs.getString("TROLE"));
//				dto.setTenabled(rs.getString("TENABLED"));
//				dto.setRegdate(rs.getDate("REGDATE"));
//				list.add(dto);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs, psmt, conn);
//		}
//
//		return list;
//	}
//	
//	// 2. 회원목록 전체 조회[사용중] ---> 등급수정을 위한 목록 조회
//	// getUserList: select문
//	public List<user_dtos> getUserList() {
//		List<user_dtos>list=new ArrayList<user_dtos>();
//		
//		Connection conn=null;
//		PreparedStatement psmt=null;
//		ResultSet rs=null;
//		
//		// SQL 쿼리 수정: T 접두사를 붙여 컬럼명 통일
//		String sql="SELECT TSEQ, TID, TNAME, TROLE, REGDATE"
//				 + " FROM USERINFO "
//				 + " WHERE TENABLED='Y' ";
//		
//		try {
//			// 오타 수정: getConnetion() -> getConnection()
//			conn=getConnetion();
//			psmt=conn.prepareStatement(sql);
//	
//			rs=psmt.executeQuery();
//			while(rs.next()) {
//				user_dtos dto=new user_dtos();
//				// DTO의 setter와 ResultSet의 컬럼명 통일
//				dto.setTseq(rs.getInt("TSEQ"));
//				dto.setTid(rs.getString("TID"));
//				dto.setTname(rs.getString("TNAME"));
//				dto.setTrole(rs.getString("TROLE"));
//				dto.setRegdate(rs.getDate("REGDATE"));
//				list.add(dto);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs, psmt, conn);
//		}
//
//		return list;
//	}
//	
//	// 3. 회원상세조회(1명에 대한)
//	// getUserRole: select문
//	public user_dtos getUserRole(String id) {
//		user_dtos dto=null;
//		
//		Connection conn=null;
//		PreparedStatement psmt=null;
//		ResultSet rs=null;
//		
//		// SQL 쿼리 수정: T 접두사를 붙여 컬럼명 통일
//		String sql="SELECT TSEQ, TID, TNAME, TROLE"
//				 + " FROM USERINFO "
//				 + " WHERE TID=? ";
//		
//		try {
//			// 오타 수정: getConnetion() -> getConnection()
//			conn=getConnetion();
//			psmt=conn.prepareStatement(sql);
//			psmt.setString(1, id);
//	
//			rs=psmt.executeQuery();
//			if(rs.next()) {
//				dto=new user_dtos();
//				// DTO의 setter와 ResultSet의 컬럼명 통일
//				dto.setTseq(rs.getInt("TSEQ"));
//				dto.setTid(rs.getString("TID"));
//				dto.setTname(rs.getString("TNAME"));
//				dto.setTrole(rs.getString("TROLE"));
//			}
//			System.out.println(dto);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs, psmt, conn);
//		}
//
//		return dto;
//	}
//	
//	// 4. 회원등급수정
//	// getUpdateRole: update문
//	public boolean getUpdateRole(String id, String role) {
//		int count=0;
//		
//		Connection conn=null;
//		PreparedStatement psmt=null;
//		
//		// SQL 쿼리 수정: T 접두사를 붙여 컬럼명 통일
//		String sql=" UPDATE USERINFO SET TROLE=? "
//				 + " WHERE TID=? ";
//		
//		try {
//			// 오타 수정: getConnetion() -> getConnection()
//			conn=getConnetion();
//			psmt=conn.prepareStatement(sql);
//			psmt.setString(1, role);
//			psmt.setString(2, id);
//
//			count=psmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(null, psmt, conn);
//		}
//		return count > 0;
//	}
//}