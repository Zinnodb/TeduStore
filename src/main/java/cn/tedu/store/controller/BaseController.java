package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;
/**
 * 此类只能在控制器层使用,
 * @author qzgr
 *
 */
public abstract class BaseController {
	/**
	 * 此方法不能被重写,只在本包和子类中使用.
	 * @param session HttpSession session
	 * @return  id
	 */
		protected final  Integer getUidFromSession(HttpSession session) {
			return Integer.valueOf(session.getAttribute("uid").toString());
		}
}
