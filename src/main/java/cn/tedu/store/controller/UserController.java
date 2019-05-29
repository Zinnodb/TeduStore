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
		// TODO 验证数据的有效性
		ResponseResult<Void> rr;
		try {
			//把各参数封装到User对象中
			User user=new User(username, password, gender, phone, email);
			//调用业务层的对象的reg()方法实现注册
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

		// TODO 验证数据有效性

		//声明返回值对象
		ResponseResult< Void> rr;
		try {
			//调用业务层对象执行登录
			User user=UserService.login(username, password);
			//将用户信息封装到session中
			session.setAttribute("uid", user.getId());
			session.setAttribute("uname", user.getUsername());
			session.setAttribute("user", user);
			//创建返回值对象:登录成功
			rr=new ResponseResult<Void>(ResponseResult.STATE_OK);
		} catch (UsernameNotFountException e) {
			// 用户名错误
			rr=new ResponseResult<Void>(ResponseResult.STATE_ERR,e.getMessage());
		}catch (PasswordNotMatchException e) {
			// 密码错误
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
		//声明ResponseResult<Void> 返回值
		ResponseResult<String> rr;
		String avatarPath=null;
		Integer uid=getUidFromSession(session);
		// ======== 上传头像 ===========
		//判断用户是否选择上传头像
		if (!avatar.isEmpty()) {
			//验证文件的类型
			String contextType=avatar.getContentType();
			if (!"image/png".equals(contextType)
					&&!"image/jpeg".equals(contextType)
					&&!"image/bmp".equals(contextType)) {
				// png jpg gif  bmp
				//认为错误
				rr=new ResponseResult<String>();
				rr.setState(ResponseResult.STATE_ERR);
				rr.setMessage("不支持上传"+contextType+"类型文件");
				return rr;
			}
			//验证文件大小
			long size=avatar.getSize();
			if (size>MAX_AVATAR_SIZE*1024) {
				rr=new ResponseResult<String>(ResponseResult.STATE_ERR,"上传的头像文件不允许大于"+MAX_AVATAR_SIZE+"KB");
			}
			//保存头像的文件夹,所有头像文件都在这个文件夹
			String avatarDirPath=request.getServletContext().getRealPath("/upload/image");
			File avatarDir=new File(avatarDirPath);
			//获取文件的原始扩展名
			String originalFilename=avatar.getOriginalFilename();
			String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
			//目标文件的文件名,每个用户的头像文件名都应该不同
			Date date=new Date();
			String filename=getDateString(date)+uid+suffix;
			File dest=new File(avatarDir, filename);
			//将用户长传的头像数据保存到文件
			try {
				avatar.transferTo(dest);
				//上传成功以后
				avatarPath="upload/image/"+filename;
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		//  ===== 修改个人信息 ======
		try {
			//从session中获取id
			Integer id=getUidFromSession(session);
			//调用业务层的changeInfo方法
			UserService.changeInfo(id, username, gender, phone, email,avatarPath);
			//创建成功时的返回对象.
			rr=new ResponseResult<String>(ResponseResult.STATE_OK);
			// 将头像路径封装在返回对象的data属性中
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
		// 声明返回值
		ResponseResult<Void> rr;
		try {
			// -- 通过session获取uid
			Integer uid=Integer.valueOf(session.getAttribute("uid").toString()) ;
			// -- 调用业务层对象的changePassword(uid, oldPassword, newPassword)方法
			UserService.changePassword(uid, newPassword, oldPassword);
			// -- 创建rr对象，表示成功
			rr=new ResponseResult<Void>(ResponseResult.STATE_OK);
		} catch (UsernameNotFountException e) {
			// -- 创建rr对象，-1，e.getMessage()
			rr=new ResponseResult<Void>(-1,e.getMessage());
		} catch (PasswordNotMatchException e) {
			// -- 创建rr对象，-2，e.getMessage()
			rr=new ResponseResult<Void>(-2,e.getMessage());
		}

		// 执行返回
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
		//根据session获取用户数据
		User user=UserService.findUserById(getUidFromSession(session));
		//将用户数据进行封装,准备转发
		modelMap.addAttribute("user",user);
		//执行转发
		return "user_change_info";
	}

	private	final		String pattern="yyyyMMddHHmmss";
	private    final	SimpleDateFormat sdf=new SimpleDateFormat(pattern,Locale.CHINA);
	private String getDateString(Date date) {
		return sdf.format(date);
	}
}
