package com.hk.board.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hk.board.config.SqlMapConfig;
import com.hk.board.dto.AnsDto;

public class AnsDao extends SqlMapConfig{

	private String namespace="com.hk.board.dao.";
	
	//1.글목록 조회
	public List<AnsDto> getAllList(String pnum){
		List<AnsDto> list=new ArrayList<AnsDto>();
		SqlSession sqlSession=null;
		
		Map<String, String>map=new HashMap<String, String>();
		map.put("pnum", pnum);
		
		try {
			//sqlSession객체를 구하려면 openSession()를 통해 얻어온다
			//openSession(true/false): t(autocommit)
			sqlSession=getSessionFactory().openSession(true);
			// selectList(쿼리ID) 해당 쿼리를 실행시킨다.
			list=sqlSession.selectList(namespace+"boardlist",map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return list;
	}
	
	//1-2.페이지수 구하기
	public int getPCount() {
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSessionFactory().openSession(true);
			count=sqlSession.selectOne(namespace+"getpcount");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count;
	}
	
	//2. 글추가하기
	public boolean insertBoard(AnsDto dto){
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSessionFactory().openSession(true);
			//파라미터 타입: dto, Array, Map, String, Integer 등
			count=sqlSession.insert(namespace+"insertboard",dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	
	//3.글상세 조회
	public AnsDto getBoard(int seq){
		AnsDto dto=new AnsDto();
		SqlSession sqlSession=null;
		//파라미터 전달 방식의 기본은 map에 담아서 전달한다.
//		Map<String, Integer>map=new HashMap<String, Integer>();
//		map.put("seq", seq);
		try {
			sqlSession=getSessionFactory().openSession(true);
			dto=sqlSession.selectOne(namespace+"getboard",seq);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return dto;
	}
	
	//4. 조회수 올리기
	public boolean readCount(int seq){
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSessionFactory().openSession(true);
			//파라미터 타입: dto, Array, Map, String, Integer 등
			count=sqlSession.update(namespace+"readcount",seq);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	
	//5.수정하기
	public boolean boardUpdate(String title, String content, int seq){
		int count=0;
		SqlSession sqlSession=null;
		
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("title", title);
		map.put("content", content);
		map.put("seq", seq);
		try {
			// SessionFactory객체.openSessoin() -> SqlSession객체 얻어옴
			// configuration.xml을 읽어서 생성된 객체
			sqlSession=getSessionFactory().openSession(true);
			//파라미터 타입: dto, Array, Map, String, Integer 등
			count=sqlSession.update(namespace+"boardupdate",map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	//6.삭제하기
	public boolean deleteBoard(int seq){
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSessionFactory().openSession(true);
			//파라미터 타입: dto, Array, Map, String, Integer 등
			count=sqlSession.update(namespace+"boarddelete",seq);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	//7.여러글 삭제하기
	public boolean mulDel(String[] seqs){
		int count=0;
		SqlSession sqlSession=null;
		
		//동적쿼리에 전달되는 파리미터는 Map에 담아서 전달한다.
		Map<String, String[]>map=new HashMap<String, String[]>();
		map.put("seqs", seqs);
		
		try {
			sqlSession=getSessionFactory().openSession(true);
			//파라미터 타입: dto, Array, Map, String, Integer 등
			count=sqlSession.update(namespace+"muldel",map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	
	//8. 답글추가하기: update, insert
	public boolean replyBoard(AnsDto dto){
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			//transaction처리: autocommit -> false로 설정
			sqlSession=getSessionFactory().openSession(false);
			//같은 그룹에서 부모의 step보다 큰 값을 갖는 글들의 step을 +1 해준다
			sqlSession.update(namespace+"replyupdate", dto);
			//답들을 추가한다.
			count=sqlSession.insert(namespace+"replyinsert",dto);
			//쿼리가 정상적으로 모두 실행됐다면 DB 반영
			sqlSession.commit();
		} catch (Exception e) {
			//중간에 작업이 실패하면 성공한 작업이 있어도 모두 되돌림
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
}