package com.pn.home.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pn.home.constants.AppConstants;
import com.pn.home.service.JwtUserDetailsService;
import com.pn.home.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader(AppConstants.AUTHORISATION_HEADER);
		String username = null;
		String jwtToken = null;
		UserDetails userDetails = null;
		// get request payload from request object
		// String payload =
		// request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		// JWT Token is in the form "Bearer token...". Remove 'Bearer' and get only the
		// token part
		if (requestTokenHeader != null && requestTokenHeader.startsWith(AppConstants.BEARER)) {
			jwtToken = requestTokenHeader.substring(7);

			try {
				userDetails = jwtUserDetailsService
						.getUserDetailsObject(jwtTokenUtil.getUserDetailsObjectFromToken(jwtToken));
				username = userDetails.getUsername();
			} catch (IllegalArgumentException e) {
				System.out.println(AppConstants.ILLEGAL_TOKEN);
			} catch (ExpiredJwtException e) {
				System.out.println(AppConstants.EXPIRE_TOKEN);
			}
		} else {
			// logger.warn(AppConstants.INCORRECT_AUTH);
		}

		// once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// if token is valid configure Spring Security to manually set authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// after setting Authentication in context, authenticate user
				// so it passes Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		chain.doFilter(request, response);
	}
}