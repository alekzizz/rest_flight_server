package filter;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class LoginFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        final List<String> authorization = requestContext.getHeaders().get("Authorization");
        final String encodedUserPassword = authorization.get(0).replaceFirst("Basic" + " ", "");

        String usernameAndPassword;
        try {
            usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            System.out.println("Auth: ");
            System.out.println("USERNAME: " + username);
            System.out.println("PASSWORD: " + password);
        } catch (Base64DecodingException ex) {
            Logger.getLogger(LoginFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        System.out.println("Response Filter: ");
        System.out.println("Headers: " + containerResponseContext.getHeaders());
    }
}
