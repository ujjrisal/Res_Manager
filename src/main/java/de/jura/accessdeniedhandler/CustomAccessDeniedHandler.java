package de.jura.accessdeniedhandler;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component("CustomAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {

		System.out.println("This is access denied handler");

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("access denied"));

		// regarding this one......consult with pawan ji.
		/*
		 * log.info("############### Access Denied Handler!");
		 * setErrorPage("/accessDenied"); super.handle(request, response,
		 * exception);
		 */

	}

}
