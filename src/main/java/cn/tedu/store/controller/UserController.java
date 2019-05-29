package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UsernameConflictException;
import cn.tedu.store.service.ex.UsernameNotFountException;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	@Autowired
	IUserService  UserService;
	public static final long MAX_AVATAR_SIZE=50;
	@ResponseBody
	@RequestMapping(value="handle_reg.do",method=RequestMethod.POST)
	public ResponseResult<Void> handleReg(@RequestParam("username") String username,@RequestParam("password") String password,String email,String phone,Integer gender){
		// TODO ��֤���ݵ���Ч��
		ResponseResult<Void> rr;
		try {
			//�Ѹ�������װ��User������
			User user=new User(username, password, gender, phone, email);
			//����ҵ���Ķ����reg()����ʵ��ע��
			UserService.reg(user);
			rr=new ResponseResult<Void>(ResponseResult.STATE_OK);
		} catch (UsernameConflictException e) {
			rr=new ResponseResult<Void>(e);
		}
		return rr;
	}

	@ResponseBody
	@RequestMapping(value="handle_login.do",method=RequestMethod.POST)
	public ResponseResult<Void> handleLogin( @RequestParam("username")String username, @RequestParam("password")String password,HttpSession session){

		// TODO ��֤������Ч��

		//��������ֵ����
		ResponseResult< Void> rr;
		try {
			//����ҵ������ִ�е�¼
			User user=UserService.login(username, password);
			//���û���Ϣ��װ��session��
			session.setAttribute("uid", user.getId());
			session.setAttribute("uname", user.getUsername());
			session.setAttribute("user", user);
			//��������ֵ����:��¼�ɹ�
			rr=new ResponseResult<Void>(ResponseResult.STATE_OK);
		} catch (UsernameNotFountException e) {
			// �û�������
			rr=new ResponseResult<Void>(ResponseResult.STATE_ERR,e.getMessage());
		}catch (PasswordNotMatchException e) {
			// �������
			rr=new ResponseResult<Void>(-2,e.getMessage());
		}
		return rr;

	}

	@ResponseBody
	@RequestMapping(value="handle_change_info.do",method=RequestMethod.POST)
	public ResponseResult<String> handleChangeInfo(
			MultipartHttpServletRequest request,
			MultipartFile avatar,
			String username,
			Integer gender,
			String phone,
			String email,
			HttpSession session){
		//����ResponseResult<Void> ����ֵ
		ResponseResult<String> rr;
		String avatarPath=null;
		Integer uid=getUidFromSession(session);
		// ======== �ϴ�ͷ�� ===========
		//�ж��û��Ƿ�ѡ���ϴ�ͷ��
		if (!avatar.isEmpty()) {
			//��֤�ļ�������
			String contextType=avatar.getContentType();
			if (!"image/png".equals(contextType)
					&&!"image/jpeg".equals(contextType)
					&&!"image/bmp".equals(contextType)) {
				// png jpg gif  bmp
				//��Ϊ����
				rr=new ResponseResult<String>();
				rr.setState(ResponseResult.STATE_ERR);
				rr.setMessage("��֧���ϴ�"+contextType+"�����ļ�");
				return rr;
			}
			//��֤�ļ���С
			long size=avatar.getSize();
			if (size>MAX_AVATAR_SIZE*1024) {
				rr=new ResponseResult<String>(ResponseResult.STATE_ERR,"�ϴ���ͷ���ļ����������"+MAX_AVATAR_SIZE+"KB");
			}
			//����ͷ����ļ���,����ͷ���ļ���������ļ���
			String avatarDirPath=request.getServletContext().getRealPath("/upload/image");
			File avatarDir=new File(avatarDirPath);
			//��ȡ�ļ���ԭʼ��չ��
			String originalFilename=avatar.getOriginalFilename();
			String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
			//Ŀ���ļ����ļ���,ÿ���û���ͷ���ļ�����Ӧ�ò�ͬ
			Date date=new Date();
			String filename=getDateString(date)+uid+suffix;
			File dest=new File(avatarDir, filename);
			//���û�������ͷ�����ݱ��浽�ļ�
			try {
				avatar.transferTo(dest);
				//�ϴ��ɹ��Ժ�
				avatarPath="upload/image/"+filename;
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		//  ===== �޸ĸ�����Ϣ ======
		try {
			//��session�л�ȡid
			Integer id=getUidFromSession(session);
			//����ҵ����changeInfo����
			UserService.changeInfo(id, username, gender, phone, email,avatarPath);
			//�����ɹ�ʱ�ķ��ض���.
			rr=new ResponseResult<String>(ResponseResult.STATE_OK);
			// ��ͷ��·����װ�ڷ��ض����data������
			rr.setData(avatarPath);
		} catch (UsernameNotFountException e) {
			rr=new ResponseResult<String>(-1,e.getMessage());
		}catch (UsernameConflictException e) {
			rr=new ResponseResult<String>(-2,e.getMessage());
		}
		return rr;
	}

	@ResponseBody
	@RequestMapping(value="/handle_change_password.do",
	method=RequestMethod.POST)
	public ResponseResult<Void> handleChangePassword(
			@RequestParam("old_password") String oldPassword,
			@RequestParam("new_password") String newPassword,
			HttpSession session) {
		// ��������ֵ
		ResponseResult<Void> rr;
		try {
			// -- ͨ��session��ȡuid
			Integer uid=Integer.valueOf(session.getAttribute("uid").toString()) ;
			// -- ����ҵ�������changePassword(uid, oldPassword, newPassword)����
			UserService.changePassword(uid, newPassword, oldPassword);
			// -- ����rr���󣬱�ʾ�ɹ�
			rr=new ResponseResult<Void>(ResponseResult.STATE_OK);
		} catch (UsernameNotFountException e) {
			// -- ����rr����-1��e.getMessage()
			rr=new ResponseResult<Void>(-1,e.getMessage());
		} catch (PasswordNotMatchException e) {
			// -- ����rr����-2��e.getMessage()
			rr=new ResponseResult<Void>(-2,e.getMessage());
		}

		// ִ�з���
		return rr;
	}

	@RequestMapping("reg.do")
	public String showReg() {
		return "user_reg";
	}

	@RequestMapping("login.do")
	public String  showLogin() {
		return "user_login";
	}

	@RequestMapping("/change_password.do")
	public String showChangePassword() {
		return "handle_change_password";
	}

	@RequestMapping("/change_info.do")
	public String showChangeInfo(HttpSession session,ModelMap modelMap){
		//����session��ȡ�û�����
		User user=UserService.findUserById(getUidFromSession(session));
		//���û����ݽ��з�װ,׼��ת��
		modelMap.addAttribute("user",user);
		//ִ��ת��
		return "user_change_info";
	}

	private	final		String pattern="yyyyMMddHHmmss";
	private    final	SimpleDateFormat sdf=new SimpleDateFormat(pattern,Locale.CHINA);
	private String getDateString(Date date) {
		return sdf.format(date);
	}
}
