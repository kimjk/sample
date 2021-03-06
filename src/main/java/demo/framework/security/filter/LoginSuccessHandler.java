/**
 * <PRE>
 *  Project : demo
 *  Package : demo.framework.security.filter
 * </PRE>
 * @file   : LoginSuccessHandler.java
 * @date   : 2015. 5. 15. 오후 3:02:17
 * @author : jkkim
 * @brief  :
 *  변경이력    :
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        jkkim  : 2015. 5. 15.       :            : 신규 개발.
 */
package demo.framework.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import demo.framework.response.BaseResponse;
import demo.framework.security.userdetails.CustomUserDetails;
import demo.framework.security.userdetails.UserDetailsVO;

/**
 * <PRE>
 *  ClassName : LoginSuccessHandler
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 5. 15. 오후 3:02:17
 * @author  : jkkim
 * @brief   :
 */

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		logger.debug("LOGIN SUCCESS : onAuthenticationSuccess");

		if ("application/json".equals(request.getHeader("Content-Type"))) {
			ObjectMapper mapper = new ObjectMapper();
			BaseResponse baseResponse = new BaseResponse();
			CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
			UserDetailsVO user = (UserDetailsVO)principal.getUserVO();
			baseResponse.setData(user);
			baseResponse.setResponseOK();
			response.setCharacterEncoding("UTF-8");
			mapper.writeValue(response.getWriter(), baseResponse);
		}
	}

}
