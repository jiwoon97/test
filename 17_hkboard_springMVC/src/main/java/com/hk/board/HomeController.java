package com.hk.board;


import java.text.DateFormat;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.board.service.IHkService;
import com.hk.dtos.HkDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
   
   private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
   
   @Autowired
   private IHkService hkService;
   
   
   @RequestMapping(value = "/home.do", method = RequestMethod.GET)
   public String home(Locale locale, Model model) {
      logger.info("Welcome home! The client locale is {}.", locale);
      
      Date date = new Date();
      DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
      
      String formattedDate = dateFormat.format(date);
      
      model.addAttribute("serverTime", formattedDate );
      
      return "home";
   }
   
   @RequestMapping(value = "/boardlist.do", method = RequestMethod.GET)
   public String boardList(Locale locale, Model model) {
      logger.info("글 목록 조회하기{}", locale);
      
      
      List<HkDto> list = hkService.getBoardList();
      model.addAttribute("list", list );
      
      return "boardlist";
   }
   
//   @RequestMapping(value = "/insertboard.do", method = RequestMethod.GET)
//   public String insertBoard(Locale locale, Model model) {
//      logger.info("글 추가하기{}", locale);
//      
//      
//      List<HkDto> list = hkService.getBoardList();
//      model.addAttribute("list", list );
//      
//      return "insertboard";
//   }
   
   //상세보기 구현 기능
   @RequestMapping(value = "/detailboard.do", method = RequestMethod.GET)
   public String detailBoard(HttpServletRequest request,int seq, Locale locale, Model model) {
      logger.info("글 상세 조회하기{}", locale);
      
//      파라미터를 직접받을수 있기 때문에 getParameter로 받을 필요가 없어짐
//      int seq = Integer.parseInt(request.getParameter("seq")); //기존 방식의 경우.
      HkDto dto = hkService.getBoard(seq);
      model.addAttribute("dto", dto );
      
      return "detailboard";
   }
   
   //글 추가 기능구현                         post:사용자로부터 값을 입력받아 요청할 때(수정작업) redirect
//                                    get:보통...조회할 때(select작업) : 응답은 forward
   //                                 method ={RequestMethod.POST, RequestMethod.GET}:GET,POST방식 모두 사용할 때
      @RequestMapping(value = "/insertboard.do", method = {RequestMethod.POST,RequestMethod.GET})
      public String insertBoard(HkDto dto,Locale locale, Model model) {
                        //parameter에 dto를 선언하면 멤버필드와 동일한 이름이면 모두 받는다.
                        //@RequestParam("seq")int sseq: seq로 전달된 파라미터를 sseq에 저장한다.
         logger.info("글 추가하기{}", locale);
         
         boolean isS = hkService.insertBoard(dto);
         if(isS) {
//            response.sendRedirect("boardlist.do?seq=4&id=hk");
            return "redirect:boardlist.do"; //return "페이지명" --> forward방식으로 응답하는 것과 같다.
         }else {
            model.addAttribute("msg", "글 추가실패" );
            return "error";
         }
      }
      
      @RequestMapping(value = "/insertform.do", method = RequestMethod.GET)
      public String insertForm(Locale locale, Model model) {
         logger.info("글 쓰기 폼으로 이동.", locale);
            
         return "insertboard";
      }
      
      //글수정 폼으로 이동
      @RequestMapping(value = "/updateform.do", method = RequestMethod.GET)
      public String updateForm(int seq, Locale locale, Model model) {
         logger.info("글수정 폼으로 이동 {}.", locale);
         HkDto dto=hkService.getBoard(seq);
         model.addAttribute("dto", dto);
         
         return "updateboard";
      }
      
      @RequestMapping(value = "/updateboard.do", method = RequestMethod.POST)
      public String updateBoard(HkDto dto,Locale locale, Model model) {
         logger.info("수정하기.", locale);
         boolean isS=hkService.updateBoard(dto);
         if(isS) {
        	 return "redirect:detailboard.do?seq="+dto.getSeq();
         }else {
        	 model.addAttribute("msg","수정하기실패");
        	 return "error";
         }
        
      }
      
      @RequestMapping(value = "/muldel.do", method = {RequestMethod.POST,RequestMethod.GET})
      public String mulDel(String[] chk,Locale locale, Model model) {
         //파라미터받기:String[] chk -->name="chk" value="1,2,3,4,5"
    	  logger.info("삭제하기.", locale);
         boolean isS=hkService.mulDel(chk);
         if(isS) {
        	 return "redirect:boardlist.do";
         }else {
        	 model.addAttribute("msg","수정하기실패");
        	 return "error";
         }
        
      }
      
      
}























