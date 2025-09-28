package com.hk.board.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.board.dtos.HkDto;
import com.hk.datasource.Database;

public class HkDao extends Database{

	public HkDao() {
		super();//생략되어 있음
	}
	
	//글목록 조회 기능: 여러개의 행이 반환 --> 반환타입? List
	public List<HkDto> getAllList(){
		List<HkDto> list=new ArrayList<>();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT SEQ, ID, TITLE, CONTENT, REGDATE "
				+ " FROM HKBOARD ORDER BY REGDATE DESC ";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()) {
				//java  <==  DB : DB에 값들을 java에서 사용할 수 있게 처리
				HkDto dto=new HkDto();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegDate(rs.getDate(5));
				list.add(dto);
				System.out.println(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return list;
	}
	
	//글추가하기: insert문실행 , 반환값 boolean
	public boolean insertBoard(HkDto dto) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" INSERT INTO HKBOARD "
				+ " VALUES(NULL,?,?,?,SYSDATE()) ";
		
		try {
			conn=getConnetion();
			
			//3단계:쿼리준비, (1,dto.getId()) 여기서 1은 ?의 순서
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			
			count=psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		
		return count>0?true:false;
	}
	
	//글상세보기: select where절  반환값은 HkDto
	public HkDto getBoard(int seq) {
		HkDto dto=new HkDto();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT SEQ, ID, TITLE, CONTENT, REGDATE "
				+ " FROM HKBOARD WHERE SEQ = ? ";
		
		try {
			conn=getConnetion();
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);// seq의 타입이 int형-> setInt()사용
			rs=psmt.executeQuery();
			while(rs.next()) {
				//java  <==  DB : DB에 값들을 java에서 사용할 수 있게 처리
				// 인덱스 순서는 select절에 작성한 컬럼순서와 일치
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegDate(rs.getDate(5));
				System.out.println(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return dto;
	}
	
}