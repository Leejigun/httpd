package kr.or.connect.simplehttpd;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Request {
    private String method;
    private String requestTarget;
    public static final String httpVersion = "HTTP/1.1";
    private Map<String, String> headers = new HashMap<>();
    private InputStream bodyInput;

    public void parseRequestLine(String requestLine) {
        String[] requestLineString = requestLine.split(" ");
        this.method = requestLineString[0];
        this.requestTarget = requestLineString[1];
        if("/".equals(requestTarget)){
            requestTarget = "/index.html";
        }
    }

    public void addHeader(String headerLine) {
        String[] headerString = headerLine.split(": ");
        if (headerString != null && headerString.length == 2) {
            headers.put(headerString[0], headerString[1]);
        }
    }

    public String getHeader(String headerName) {
        return this.headers.get(headerName);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    public void setRequestTarget(String requestTarget) {
        this.requestTarget = requestTarget;
    }

    public InputStream getInputStream() {
        return bodyInput;
    }

    public void setBodyInput(InputStream bodyInput) {
        this.bodyInput = bodyInput;
    }

    @Override
    public String toString() {
        final int maxLen = 10;
        return "Request [method=" + method + ", requestTarget=" + requestTarget + ", headers="
                + (headers != null ? toString(headers.entrySet(), maxLen) : null) + "]";
    }

    private String toString(Collection<?> collection, int maxLen) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int i = 0;
        for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
            if (i > 0)
                builder.append(", ");
            builder.append(iterator.next());
        }
        builder.append("]");
        return builder.toString();
    }
}
