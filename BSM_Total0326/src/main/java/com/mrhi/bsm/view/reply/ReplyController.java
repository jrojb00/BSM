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
// �ϳ��� URI�� �ϳ��� ������ ���ҽ��� ��ǥ�ϵ��� ����� ����
 
// http://localhost/spring02/list?bno=1 ==> url+�Ķ����
// http://localhost/spring02/list/1 ==> url
// RestController�� ������ 4.0���� ����
// @Controller, @RestController �������� �޼��尡 ����Ǹ� ȭ����ȯ�� ����

@Controller
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    @Autowired
    private BoardService bService;
	@Autowired
	private BoardCategoryService BCService;
    
    // ��� �Է�
    @RequestMapping(value="/insertReply.do", method=RequestMethod.POST)
    public String insert(ReplyVO vo, BoardVO board, HttpSession session, Model model){
        UserVO user = (UserVO) session.getAttribute("userVO2");
        vo.setReplyer(user.getName());
        vo.setReplyerId(user.getId());
        System.out.println(vo);
        replyService.insertReply(vo);
        System.out.println("at insertReply.do �������� ��� �Է�  " + vo);
        List<ReplyVO> list = replyService.list(board);
        model.addAttribute("replylist", list);
//        BoardVO boardVO = bService.getBoard(board);
//        model.addAttribute("board", boardVO);
        return "getBoard.do?id=" + board.getId();
    }
    
	//�� �� ��ȸ
	@RequestMapping("/getReply.do")
	public String getBoard(ReplyVO vo, Model model) {
		ReplyVO reply = replyService.getReply(vo);
		BoardVO board = new BoardVO();
		board.setId(reply.getParentId());
	    // �信 ������ ������ ����
	    model.addAttribute("reply", reply);
	    model.addAttribute("boardCategory", BCService.getBoardCategory(bService.getBoard(board)));
		return "updateReply.jsp";
	}
   
    //��� ����
  	@RequestMapping("/updateReply.do")
  	public String updateReply(ReplyVO vo) {
  		System.out.println("updateReply.do �����մϴ�.");
  		replyService.updateReply(vo);
  		return "getBoard.do?id="+vo.getParentId()+"&replyModify=true";
  	}
    
	//��� ����
	@RequestMapping("/deleteReply.do")
	public String deleteReply(ReplyVO vo) {		
		replyService.deleteReply(vo);
		return "getBoard.do?id="+vo.getParentId()+"&replyModify=true";	
	}
    // ��� ���(@Controller��� : veiw(ȭ��)�� ����)
//    @RequestMapping("list.do")
//    public String list(@RequestParam int parent_idx, Model model){
//        List<ReplyVO> list = replyService.list(parent_idx);
//        // �信 ������ ������ ����
//        model.addAttribute("replylist", list);
//        // replyList.jsp�� ������
//        return "getBoard.jsp";
//    }
    
//    // ��� ���(@RestController Json������� ó�� : �����͸� ����)
//    @RequestMapping("listJson.do")
//    @ResponseBody // ���ϵ����͸� json���� ��ȯ(��������)
//    public List<ReplyVO> listJson(@RequestParam int parent_idx){
//        List<ReplyVO> list = replyService.list(parent_idx);
//        return list;
//    }
}
