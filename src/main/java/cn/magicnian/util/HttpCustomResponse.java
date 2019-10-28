package cn.magicnian.util;

import lombok.Data;
import org.apache.http.Header;
import org.apache.http.impl.client.BasicCookieStore;

@Data
public class HttpCustomResponse {

    private String charset = "utf-8";

    private int statusCode;

    private byte[] responseBytes;

    private Header[] headers;

    private BasicCookieStore basicCookieStore;

    private String responseBody;
}
