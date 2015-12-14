/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.comun;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luis Medina
 */
public class SeguridadSessionFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                HttpSession httpSession= httpRequest.getSession(false);
                HttpServletResponse httpResponse = (HttpServletResponse) response;

                if(httpRequest.getRequestURI().equals("/sid/")||
                   httpRequest.getRequestURI().equals("/sid/faces/signin.xhtml")
                   )
                {     
                    chain.doFilter(request, response);
                }else{
                     if (httpSession==null || httpSession.getAttribute("usuario") == null) {                            
                            httpResponse.sendRedirect("/sid/cerrarSesion.jsp");                           
                        } else {
                            chain.doFilter(request, response);
                          }
                  }
               
    }

    @Override
    public void destroy() {

    }

}
