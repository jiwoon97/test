package com.hk.board.daos;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.hk.dtos.HkDto;

//@Component
@Repository //Dao 클레스에는 Repository
public class HkDao implements IHkDao {

   private String namespace="com.hk.board.";
   
   //mybatis 객체 불러옴
   //servlet에서는 nullpointexception이 나지만 spring에서는 @Autowired를 통해 springcontainer가 저절로 주입시켜주기 때문에 에러가 안 난다.
   @Autowired //xml에 등록해놓은 bean을 Autowired로 엮어주어 주입시켜준다. xml에 등록된 객체를 연결해주는 역할, 선언된 타입으로 찾아 주입시켜준다.
   private SqlSessionTemplate sqlSession;//<-application-context.xml에 등록
   
   
   @Override
   public List<HkDto> getBoardList() {
      return sqlSession.selectList(namespace+"getBoardList");
   }
   
   @Override
   public HkDto getBoard(int seq) {
      return sqlSession.selectOne(namespace+"getBoard",seq);
   }

   @Override
   public boolean insertBoard(HkDto dto) {
      int count=sqlSession.insert(namespace+"insertBoard", dto);
      return count>0?true:false;
   }

   @Override
   public boolean updateBoard(HkDto dto) {
      int count=0;
      count=sqlSession.update(namespace+"updateBoard", dto);
      return count>0?true:false;
   }

   @Override
   public boolean delBoard(int seq) {
      int count=0;
      count=sqlSession.delete(namespace+"delBoard", seq);
      return count>0?true:false;
   }

   @Override
   public boolean mulDel(String[] seqs) {
      int count=0;
      Map<String, String[]>map=new HashMap();
      map.put("seqs", seqs);
      count=sqlSession.delete(namespace+"mulDel", map);
      return count>0?true:false;
   }

}