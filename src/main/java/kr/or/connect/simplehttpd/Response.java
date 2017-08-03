package kr.or.connect.simplehttpd;

import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Response {
    public static final String httpVersion = "HTTP/1.1";
    private String statusCode;
    private String reasonPhrase;
    private Map<String, String> headers = new HashMap<>();
    private OutputStream outputStream;

    public String getStatusLine() {
        return httpVersion + " " + statusCode + " " + reasonPhrase;
    }

    public String getHeaderString() {
        StringBuilder builder = new StringBuilder();
        for (String key : headers.keySet()) {
            builder.append(key);
            builder.append(": ");
            builder.append(headers.get(key));
            builder.append("\n");
        }
        return builder.toString();
    }

    public void addHeader(String headerLine) {
        String[] headerString = headerLine.split(": ");
        if (headerString != null && headerString.length == 2) {
            headers.put(headerString[0], headerString[1]);
        }
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public String toString() {
        final int maxLen = 10;
        return "Response [statusCode=" + statusCode + ", reasonPhrase=" + reasonPhrase + ", headers="
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
