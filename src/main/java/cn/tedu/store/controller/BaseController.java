package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;
/**
 * ����ֻ���ڿ�������ʹ��,
 * @author qzgr
 *
 */
public abstract class BaseController {
	/**
	 * �˷������ܱ���д,ֻ�ڱ�����������ʹ��.
	 * @param session HttpSession session
	 * @return  id
	 */
		protected final  Integer getUidFromSession(HttpSession session) {
			return Integer.valueOf(session.getAttribute("uid").toString());
		}
}
