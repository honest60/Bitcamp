package soo.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class EncodingFilter implements Filter {
	
	private String encoding=null;
	protected FilterConfig filterConfig =null;
		
	
	public void init(FilterConfig filterConfig) 
		throws ServletException {
		this.filterConfig=filterConfig;
		this.encoding=filterConfig.getInitParameter("encoding");		
	}
	
	/*
	 * HttpServletRequest ��ü�� web.xml���� ���޵� ����  ���ڵ��� �����ϴ� �޼ҵ�.
	 */	 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
		throws IOException,ServletException {
		if(request.getCharacterEncoding()==null) {
			if(encoding!=null) {
				request.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(request,response);		
	}
    
	/*
	 *  web.xml���� ���޵� ���ڵ� ���� �ʱ�ȭ�ϴ� �޼ҵ�
	 */
	
	public void destroy() {
		this.encoding=null;
		this.filterConfig=null;		
	}	
	
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}
	
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig=filterConfig;
	}
}//end of class
