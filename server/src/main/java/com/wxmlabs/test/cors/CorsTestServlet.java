package com.wxmlabs.test.cors;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.regex.Pattern;

@WebServlet(name = "CorsTestServlet", urlPatterns = "/test")
public class CorsTestServlet extends GenericServlet {

    private void corsInterceptor(HttpServletRequest req, HttpServletResponse res) {
        /* CORS support BEGIN */
        // SEE http://javascript.ruanyifeng.com/bom/cors.html
        if (ConfigHolder.isEnableCORS()) {
            String origin = req.getHeader("Origin");
            if (origin == null) {
                System.out.println("No Origin, ignore CORS interceptor.");
                return;
            }
            Pattern pattern = Pattern.compile(ConfigHolder.getAllowOriginPattern());
            System.out.println("Check CORS Rule...");
            System.out.println("pattern:" + pattern.pattern());
            System.out.println("origin :" + origin);
            System.out.println("matches:" + pattern.matcher(origin).matches());
            if (pattern.matcher(origin).matches()) {
                res.setHeader("Access-Control-Allow-Origin", origin); // 允许的跨域请求来源
                res.setHeader("Access-Control-Allow-Credentials", ConfigHolder.getAllowCredentials()); // 不接受跨域cookie
                res.setHeader("Access-Control-Allow-Headers", ConfigHolder.getAllowHeaders());
                res.setHeader("Access-Control-Allow-Methods", ConfigHolder.getAllowMethods());
                res.setIntHeader("Access-Control-Max-Age", ConfigHolder.getMaxAge()); // 跨域Access-Control响应的有效期
            }
        } else { // disable CORS
            res.setHeader("Access-Control-Allow-Origin", null);
            res.setHeader("Access-Control-Allow-Credentials", null);
            res.setHeader("Access-Control-Allow-Headers", null);
            res.setHeader("Access-Control-Allow-Methods", null);
            res.setHeader("Access-Control-Max-Age", null);
        }
        /* CORS support END */
    }

    private void service(HttpServletRequest req, HttpServletResponse res)
        throws IOException {
        traceRequest(req);

        // CORS interceptor
        corsInterceptor(req, res);

        // build response
        res.setHeader("Content-Type", "application/json");

        String origin = req.getHeader("Origin");
        String contentType = req.getHeader("Content-Type");
        String method = req.getMethod();

        PrintWriter writer = res.getWriter();
        writer.print("{");
        writer.printf("\"Method\":\"%s\",", method);
        writer.printf("\"Origin\":\"%s\",", origin);
        writer.printf("\"Content-Type\":\"%s\",", contentType);
        writer.print("\"Echo\":\"");
        // echo request body to client
        BufferedReader reader = req.getReader();
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            writer.println(line.replaceAll("\"", "\\\""));
        }
        writer.print("\"");
        writer.println("}");

        res.flushBuffer();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
        throws ServletException, IOException {
        HttpServletRequest request;
        HttpServletResponse response;

        if (!(req instanceof HttpServletRequest &&
            res instanceof HttpServletResponse)) {
            throw new ServletException("non-HTTP request or response");
        }

        request = (HttpServletRequest) req;
        response = (HttpServletResponse) res;

        service(request, response);
    }

    private void traceRequest(HttpServletRequest req) throws IOException {
        System.out.println();
        System.out.println("====== Http Request Begin ======");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.printf("%s %s %s\r\n", req.getMethod(), req.getRequestURI(), req.getProtocol());
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Enumeration<String> headerValues = req.getHeaders(headerName);
            while (headerValues.hasMoreElements()) {
                String headerValue = headerValues.nextElement();
                pw.printf("%s: %s\r\n", headerName, headerValue);
            }
        }
        BufferedReader requestBodyReader = req.getReader();
        while (true) {
            String line = requestBodyReader.readLine();
            if (line == null) break;
            pw.println(line);
        }
        System.out.println(sw.toString());
        System.out.println("====== Http Request End ======");
        System.out.println();
    }

}
