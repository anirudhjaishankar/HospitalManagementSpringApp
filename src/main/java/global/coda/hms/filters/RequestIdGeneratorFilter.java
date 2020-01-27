package global.coda.hms.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestIdGeneratorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        request.setAttribute("requestId", token);
        chain.doFilter(request, response);
    }
}
