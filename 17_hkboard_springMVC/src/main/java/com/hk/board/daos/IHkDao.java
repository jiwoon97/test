package com.hk.board.daos;

import java.util.List;


import com.hk.dtos.HkDto;

public interface IHkDao {

   public List<HkDto> getBoardList();
   public boolean insertBoard(HkDto dto);
   public HkDto getBoard(int seq);
   public boolean updateBoard(HkDto dto);
   public boolean delBoard(int seq);
   public boolean mulDel(String[] seqs);
   
}