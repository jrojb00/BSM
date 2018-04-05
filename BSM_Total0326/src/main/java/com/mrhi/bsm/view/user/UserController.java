package com.mrhi.bsm.view.user;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrhi.bsm.model.item.ItemVO;
import com.mrhi.bsm.model.user.UserService;
import com.mrhi.bsm.model.user.UserVO;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/logout.do", method=RequestMethod.POST)
	public String logout(HttpSession session) {
		session.invalidate();
		return "main.jsp";
	}
	
	@RequestMapping(value="/checkID.do")
	@ResponseBody
	public String checkID(UserVO vo) {
		System.out.println("�ߺ� �˻� ��");
		int checkID = userService.checkID(vo);
		return checkID + "";
	}

	@RequestMapping(value="/join.do")
	public String join(UserVO vo) {
		System.out.println("ȸ�� ���� ��");
		userService.insertUser(vo);
		return "join.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(UserVO vo) {
		System.out.println("�α��� ȭ������ �̵�");
		return "login.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, HttpSession session) {
		UserVO user = userService.LogIn(vo);
		if(user.getId() != 0) {
			session.setAttribute("userVO2", user);
			System.out.println("�α��� ����");
		}
		return "redirect:/main.jsp";
	}
	
	@RequestMapping(value="/getUserList.do")
	public String getUserList(UserVO vo, Model model) {
		System.out.println("ȸ�� ����Ʈ ���� ��");		
		List<UserVO> userList = userService.getUserList(vo);
		model.addAttribute("userList", userList);
		System.out.println("ȸ�� ����Ʈ ���� ����");
		return "adminUser.jsp";
	}
	
	@RequestMapping(value="/updateInfo.do", method=RequestMethod.GET)
	public String updateInfo() {
		return "updateMyInfo.jsp";
	}
	
	@RequestMapping(value="/updateInfo.do", method=RequestMethod.POST)
	public String updateInfo(UserVO vo, HttpSession session) {
		System.out.println("�� ���� ���� ��");
		userService.updateInfo(vo);
		UserVO user = userService.getMyInfo(vo);
		session.setAttribute("userVO2", user);
		return "getMyInfo.jsp";
	}
	
	@RequestMapping(value="/deleteUser.do")
	public String deleteUser(UserVO vo, HttpSession session) {
		System.out.println("ȸ�� Ż�� ��");
		userService.deleteUser(vo);
		session.invalidate();
		System.out.println("ȸ�� Ż�� ����");
		return "login.jsp";
	}
	
	@RequestMapping(value="/getMyInfo.do")
	public String getMyInfo(HttpSession session) {
		System.out.println("�� ���� ���� ��");
		UserVO user = (UserVO) session.getAttribute("userVO2");
		session.setAttribute("userVO2", userService.getMyInfo(user));
		return "getMyInfo.jsp";
	}
}
