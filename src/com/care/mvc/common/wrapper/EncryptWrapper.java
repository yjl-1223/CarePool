package com.care.mvc.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.care.mvc.common.util.EncryptUtil;

public class EncryptWrapper extends HttpServletRequestWrapper {

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {

		String value = "";

		if (name.equals("userPwd")) {

			value = EncryptUtil.oneWays(super.getParameter(name), "SHA-256");
		} else {
			value = super.getParameter(name);
		}
		return value;
	}

}
