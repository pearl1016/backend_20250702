package com.hk.board.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.board.dtos.board_dtos;
import com.hk.datasource.database;

public class board_daos extends database {

    public board_daos() {
        super();
    }
    
    // 글 목록 조회 기능: 여러 개의 행이 반환
    public List<board_dtos> getAllList() {
        List<board_dtos> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        
        String sql = " SELECT TSEQ, TID, TTITLE, TCONTENT, TREGDATE, DELFLAG FROM T_BOARD ORDER BY TREGDATE DESC ";
       
        try {
            conn = getConnetion();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            
            while (rs.next()) {
            	board_dtos dto = new board_dtos();
                dto.setTseq(rs.getInt("TSEQ"));
                dto.setTid(rs.getString("TID"));
                dto.setTtitle(rs.getString("TTITLE"));
                dto.setTcontent(rs.getString("TCONTENT"));
                dto.setTregdate(rs.getDate("TREGDATE"));
                dto.setDelflag(rs.getString("DELFLAG"));
                list.add(dto);
                System.out.println(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, psmt, conn);
        }
        return list;
    }
    
    // 글 추가하기: insert문 실행, 반환값 boolean
    public boolean insertBoard(board_dtos dto) {
        int count = 0;
        Connection conn = null;
        PreparedStatement psmt = null;
        
        // T_BOARD 테이블 구조에 맞춰 컬럼명을 수정하고, DELFLAG는 'N'으로 설정
        String sql = " INSERT INTO T_BOARD(TID, TTITLE, TCONTENT, TREGDATE, DELFLAG) "
                   + " VALUES(?, ?, ?, SYSDATE(), 'N') ";
        
        try {
            conn = getConnetion();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getTid());
            psmt.setString(2, dto.getTtitle());
            psmt.setString(3, dto.getTcontent());
            
            count = psmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, psmt, conn);
        }
        
        return count > 0;
    }
    
    // 글 상세보기: select where절, 반환값은 BoardDto
    public board_dtos getBoard(int tseq) {
    	board_dtos dto = null;
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        
        String sql = " SELECT TSEQ, TID, TTITLE, TCONTENT, TREGDATE, DELFLAG FROM T_BOARD WHERE TSEQ = ? ";
        
        try {
            conn = getConnetion();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, tseq);
            rs = psmt.executeQuery();
            
            if (rs.next()) {
                dto = new board_dtos();
                dto.setTseq(rs.getInt("TSEQ"));
                dto.setTid(rs.getString("TID"));
                dto.setTtitle(rs.getString("TTITLE"));
                dto.setTcontent(rs.getString("TCONTENT"));
                dto.setTregdate(rs.getDate("TREGDATE"));
                dto.setDelflag(rs.getString("DELFLAG"));
                System.out.println(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, psmt, conn);
        }
        return dto;
    }
    
    // 글 수정하기: update문 실행, 반환타입 boolean
    public boolean updateBoard(board_dtos dto) {
        int count = 0;
        Connection conn = null;
        PreparedStatement psmt = null;
        
        String sql = " UPDATE T_BOARD SET TTITLE=?, TCONTENT=? WHERE TSEQ=? ";
        
        try {
            conn = getConnetion();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getTtitle());
            psmt.setString(2, dto.getTcontent());
            psmt.setInt(3, dto.getTseq());
            
            count = psmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, psmt, conn);
        }
        
        return count > 0;
    }
    
    // 글 삭제: delete문 실행, 반환타입 boolean
    public boolean deleteBoard(int tseq) {
        int count = 0;
        Connection conn = null;
        PreparedStatement psmt = null;
        
        String sql = " DELETE FROM T_BOARD WHERE TSEQ=? ";
        
        try {
            conn = getConnetion();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, tseq);
            
            count = psmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, psmt, conn);
        }
        
        return count > 0;
    }
    
    // 여러 글 삭제: delete문, batch 작업
    public boolean mulDel(String[] tseqs) {
        boolean isS = true;
        Connection conn = null;
        PreparedStatement psmt = null;
        
        String sql = " DELETE FROM T_BOARD WHERE TSEQ=? ";
        
        try {
            conn = getConnetion();
            conn.setAutoCommit(false);
            
            psmt = conn.prepareStatement(sql);
            for (String tseq : tseqs) {
                psmt.setString(1, tseq);
                psmt.addBatch();
            }
            
            psmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            isS = false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            close(null, psmt, conn);
        }
        
        return isS;
    }
}