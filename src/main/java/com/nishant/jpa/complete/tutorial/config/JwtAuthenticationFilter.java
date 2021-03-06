package com.nishant.jpa.complete.tutorial.config;

import com.nishant.jpa.complete.tutorial.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    // Request before hitting any end-point will be passed through this:
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, BadCredentialsException {

        // get JWT Token -> Check starts with "Bearer" -> Validate

        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){

            jwtToken = requestTokenHeader.substring(7); //after Bearer token

            logger.info("JWT Token---> "+jwtToken);

            try {

                //get username from token:
                username = jwtUtil.extractUsername(jwtToken);

                logger.info("USERNAME in TOKEN---> "+ username);

            }catch (Exception e){
                e.printStackTrace();
            }

            //Security code:

            // Get user details:
            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);

            logger.info("SecurityContextHolder.getContext().getAuthentication() ---> "+ SecurityContextHolder.getContext().getAuthentication());

            // Initially SecurityContextHolder.getContext().getAuthentication() should be null
            // using UserDetails object created using username fetched from token
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

                //Validate that token id valid
                if(jwtUtil.validateToken(jwtToken, userDetails)){

                    // get auth token from user details obtained from JWT
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    // setting token details in Security Context Holder, so that
                    // spring know that authentication is done(allow access to API hit by user)
                    // Internally Spring Security also sets in SecurityContextHolder after correct cred. entered by user
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            else{
                //Token not validated
                logger.error("Token not validated in filter");
                throw new BadCredentialsException("Bad Credentials in filter");
            }
        }

        // Forward request & response after filtering
        filterChain.doFilter(request,response);
    }
}
