package com.yanik.todolist.config.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse httpServletResponse,
			AuthenticationException authException) throws IOException, ServletException {

		JsonObject errMessage = new JsonObject();
		//errMessage.addProperty("message", "Invalid Username and Password");
		errMessage.addProperty("message", authException.getMessage());
		logger.error("Unauthorized error: {}", authException.getMessage());
		httpServletResponse.setContentType("application/json");
		//httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(401);
        httpServletResponse.getWriter().print(errMessage);
	}

}