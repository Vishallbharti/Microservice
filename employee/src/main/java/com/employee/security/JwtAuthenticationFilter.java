//package com.employee.security;
//
//import java.io.IOException;
//import java.util.Collections;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//	Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
//
//	@Autowired
//	@Lazy
//	private UserDetailsService userDetailsService;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//
//		// Autherization = Bearer hjwdhqwdiqwehod;
//
//		String requestHeader = request.getHeader("Authorization");
//		logger.info("Header : {} " + requestHeader);
//
//		System.out.println("requestHeader: " + requestHeader);
//		String username = null;
//		String token = null;
//
//		if (requestHeader != null && requestHeader.startsWith("Bearer")) {
//			// getting token
//			token = requestHeader.substring(7);
//			try {
//				username = JwtHelper.getUsernameFromToken(token);
//			} catch (IllegalArgumentException e) {
//				logger.info("Illegal Argument while fetching the username !!");
//				e.printStackTrace();
//			} catch (ExpiredJwtException e) {
//				logger.info("Given jwt token is expired !!");
//				e.printStackTrace();
//			} catch (MalformedJwtException e) {
//				logger.info("Some changed has done in token !! Invalid Token");
//				e.printStackTrace();
//			} catch (Exception e) {
//				e.printStackTrace();
//
//			}
//		} else {
//			logger.info("Invalid Header Value !! ");
//		}
//		
//		System.out.println("username: " + username);
//		System.out.println("token: " + token);
//
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//			Boolean validateToken = JwtHelper.validateToken(token, userDetails);
//
//			System.out.println("validateToken: " + validateToken);
//			
//			if (validateToken) {
//				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//						userDetails, token, Collections.emptyList());
//				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				SecurityContextHolder.getContext().setAuthentication(authentication);
//			} else {
//				logger.info("Validation fails");
//			}
//		}
//
//		System.out.println("SecurityContextHolder: " + SecurityContextHolder.getContext().getAuthentication());
//		filterChain.doFilter(request, response);
//
//	}
//
//}
