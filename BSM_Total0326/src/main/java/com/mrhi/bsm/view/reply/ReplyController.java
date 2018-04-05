package com.mrhi.bsm.view.reply;
 
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mrhi.bsm.model.board.BoardCategoryService;
import com.mrhi.bsm.model.board.BoardService;
import com.mrhi.bsm.model.board.BoardVO;
import com.mrhi.bsm.model.board.ReplyService;
import com.mrhi.bsm.model.board.ReplyVO;
import com.mrhi.bsm.model.user.UserVO;
 
// REST : Representational State Transfer
// 하나의 URI가 하나의 고유한 리소스를 대표하도록 설계된 개념
 
// http://localhost/spring02/list?bno=1 ==> url+파라미터
// http://localhost/spring02/list/1 ==> url
// RestController은 스프링 4.0부터 지원
// @Controller, @RestController 차이점은 메서드가 종료되면 화면전환의 유무

@Controller
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    @Autowired
    private BoardService bService;
	@Autowired
	private BoardCategoryService BCService;
    
    // 댓글 입력
    @RequestMapping(value="/insertReply.do", method=RequestMethod.POST)
    public String insert(ReplyVO vo, BoardVO board, HttpSession session, Model model){
        UserVO user = (UserVO) session.getAttribute("userVO2");
        vo.setReplyer(user.getName());
        vo.setReplyerId(user.getId());
        System.out.println(vo);
        replyService.insertReply(vo);
        System.out.println("at insertReply.do 다음으로 댓글 입력  " + vo);
        List<ReplyVO> list = replyService.list(board);
        model.addAttribute("replylist", list);
//        BoardVO boardVO = bService.getBoard(board);
//        model.addAttribute("board", boardVO);
        return "getBoard.do?id=" + board.getId();
    }
    
	//글 상세 조회
	@RequestMapping("/getReply.do")
	public String getBoard(ReplyVO vo, Model model) {
		ReplyVO reply = replyService.getReply(vo);
		BoardVO board = new BoardVO();
		board.setId(reply.getParentId());
	    // 뷰에 전달할 데이터 지정
	    model.addAttribute("reply", reply);
	    model.addAttribute("boardCategory", BCService.getBoardCategory(bService.getBoard(board)));
		return "updateReply.jsp";
	}
   
    //댓글 수정
  	@RequestMapping("/updateReply.do")
  	public String updateReply(ReplyVO vo) {
  		System.out.println("updateReply.do 수행합니다.");
  		replyService.updateReply(vo);
  		return "getBoard.do?id="+vo.getParentId()+"&replyModify=true";
  	}
    
	//댓글 삭제
	@RequestMapping("/deleteReply.do")
	public String deleteReply(ReplyVO vo) {		
		replyService.deleteReply(vo);
		return "getBoard.do?id="+vo.getParentId()+"&replyModify=true";	
	}
    // 댓글 목록(@Controller방식 : veiw(화면)를 리턴)
//    @RequestMapping("list.do")
//    public String list(@RequestParam int parent_idx, Model model){
//        List<ReplyVO> list = replyService.list(parent_idx);
//        // 뷰에 전달할 데이터 지정
//        model.addAttribute("replylist", list);
//        // replyList.jsp로 포워딩
//        return "getBoard.jsp";
//    }
    
//    // 댓글 목록(@RestController Json방식으로 처리 : 데이터를 리턴)
//    @RequestMapping("listJson.do")
//    @ResponseBody // 리턴데이터를 json으로 변환(생략가능)
//    public List<ReplyVO> listJson(@RequestParam int parent_idx){
//        List<ReplyVO> list = replyService.list(parent_idx);
//        return list;
//    }
}
