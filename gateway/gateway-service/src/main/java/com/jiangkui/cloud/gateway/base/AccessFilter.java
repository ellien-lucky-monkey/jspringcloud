package com.jiangkui.cloud.gateway.base;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletRequest;

/**
 * package:    com.jiangkui.cloud.gateway.base
 * className:  AccessFilter
 * date:       2017/11/07 14:59
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
public class AccessFilter extends ZuulFilter{
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String token = request.getParameter("token");
		return null;
	}
}