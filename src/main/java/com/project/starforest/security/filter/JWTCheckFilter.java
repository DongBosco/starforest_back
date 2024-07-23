package com.project.starforest.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.project.starforest.dto.MemberDTO;
import com.project.starforest.util.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import static com.project.starforest.domain.MemberRole.MEMBER;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter{


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        String path = request.getRequestURI();

        log.info("check uri" + path);

        return path.startsWith("/api/member/");
//        if(path.startsWith("/api/products/") || path.startsWith("/api/products/list") || path.startsWith("/api/products/view/") ) {
//            return true;
//        }
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("---------------doFilterInternal-----------------");

        String authHeaderStr = request.getHeader("Authorization");

        try {
            String accessToken = authHeaderStr.substring(7);
            Map<String, Object> claims = JWTUtil.validateToken(accessToken);

            log.info("JWT claims: " + claims);

            String email = (String) claims.get("email");
            String pass_word = (String) claims.get("pass_word");
            String introduce = (String) claims.get("introduce");
            String nick_name = (String) claims.get("nick_name");
            String profile_url = (String) claims.get("profile_url");
            Long id = (Long) claims.get("id");
            Integer login_type = (Integer) claims.get("login_type");
            Integer grade = (Integer) claims.get("grade");

            List<String> roleNames = (List<String>) claims.get("roleNames");

            MemberDTO memberDTO = new MemberDTO(email,pass_word,introduce,nick_name,profile_url,id,login_type,grade, roleNames);
            log.info("-----------------------------------");
            log.info(memberDTO);
            log.info(memberDTO.getAuthorities());
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(memberDTO, pass_word, Collections.singleton(new SimpleGrantedAuthority("ROLE_" + MEMBER)));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);

        }catch(Exception e){
            log.error("JWT Check Error..............");
            log.error(e.getMessage());

            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));

            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }
    }

}
