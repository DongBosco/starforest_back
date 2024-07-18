//package com.project.starforest.security.filter;
//
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Log4j2
//public class JWTCheckFilter extends OncePerRequestFilter{
//
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//
//        String path = request.getRequestURI();
//
//        log.info("check uri" + path);
//
//        if(path.startsWith("/api/member/")) {
//            return true;
//        }
//
//        return false;
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        log.info("---------------doFilterInternal-----------------");
//        log.info("---------------doFilterInternal-----------------");
//        log.info("---------------doFilterInternal-----------------");
//
//
//        String authHeaderStr = request.getHeader("Authorization");
//
//        try {
//            //Bearer accestoken...
//            String accessToken = authHeaderStr.substring(7);
//            Map<String, Object> claims = JWTUtil.validateToken(accessToken);
//
//            log.info("JWT claims: " + claims);
//
//            String email = (String) claims.get("email");
//            String pw = (String) claims.get("pw");
//            String nickname = (String) claims.get("nickname");
//            Boolean social = (Boolean) claims.get("social");
//            List<String> roleNames = (List<String>) claims.get("roleNames");
//
//            MemberDTO memberDTO = new MemberDTO(email, pw, nickname, social.booleanValue(), roleNames);
//            log.info("-----------------------------------");
//            log.info(memberDTO);
//            log.info(memberDTO.getAuthorities());
//            UsernamePasswordAuthenticationToken authenticationToken
//                    = new UsernamePasswordAuthenticationToken(memberDTO, pw, memberDTO.getAuthorities());
//
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//
//            filterChain.doFilter(request, response);
//
//        }catch(Exception e){
//            log.error("JWT Check Error..............");
//            log.error(e.getMessage());
//
//            Gson gson = new Gson();
//            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));
//
//            response.setContentType("application/json");
//            PrintWriter printWriter = response.getWriter();
//            printWriter.println(msg);
//            printWriter.close();
//        }
//    }
//
//}
