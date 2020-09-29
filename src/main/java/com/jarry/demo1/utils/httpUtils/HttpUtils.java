package com.jarry.demo1.utils.httpUtils;


import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpUtils {

    private String host;
    private int port;
    private String authUser;
    private String authPasswd;

    // set HTTP authenticator. default negotiation order: GSS/SPNEGO -> Digest -> NTLM -> Basic
    static {
        Authenticator.setDefault(new DefaultAuthenticator());
    }

    public HttpUtils(String host, int port, String authUser, String authPasswd) {
        this.host = host;
        this.port = port;
        this.authUser = authUser;
        this.authPasswd = authPasswd;
        DefaultAuthenticator.addAuthentication(this.host, this.port, this.authUser, this.authPasswd);
    }

    /**
     * @param strUrl
     * @param jsonParams
     * @return
     */
    public String sendPostRequest(String strUrl, String jsonParams) {
        try {
            URL url = new URL(strUrl);
            log.info(strUrl);
            log.info(jsonParams);
            HttpURLConnection httpConnection = (HttpURLConnection) url
                    .openConnection();
            // setting HTTP property
            httpConnection.setDoInput(true);
            httpConnection.setDoOutput(true);
            httpConnection.setUseCaches(false);
            httpConnection.setRequestMethod("POST");
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            // ADD BASIC authorization
            String Authorization = "Basic " + Base64.getEncoder().encodeToString((this.authUser + ":" + this.authPasswd).getBytes("utf-8"));
            httpConnection.setRequestProperty("Authorization", Authorization);
            httpConnection.connect();

            OutputStreamWriter os = new OutputStreamWriter(
                    httpConnection.getOutputStream(), "UTF-8");
            os.write(jsonParams);
            os.flush();
            os.close();

            if (httpConnection.getResponseCode() != 200) {
                Map<String, List<String>> headers = httpConnection.getHeaderFields();
                for (String key : headers.keySet()) {
                    System.out.println(key + ":" + headers.get(key));
                }
                return null;
            }

            InputStream is = httpConnection.getInputStream();
            ByteArrayOutputStream arrayos = new ByteArrayOutputStream();
            byte[] temp = new byte[512];
            int readLen = 0;
            while ((readLen = is.read(temp)) > 0) {
                arrayos.write(temp, 0, readLen);
            }
            String result = new String(arrayos.toByteArray(), "UTF-8");
            arrayos.close();
            System.out.println(result);
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param strUrl
     * @param param
     * @return
     */
    public String sendGetRequest(String strUrl, String param) {
        try {
            // URL 拼接参数
            strUrl = strUrl + URLEncoder.encode(param, "UTF-8");
            URL url = new URL(strUrl);
            log.info(strUrl);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            // 设置HTTP连接信息
            httpConnection.setDoInput(true);
            httpConnection.setUseCaches(false);
            httpConnection.setRequestMethod("GET");
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestProperty("Accept", "application/json");
            // BASIC认证
            String Authorization = "Basic " + Base64.getEncoder().encodeToString((this.authUser + ":" + this.authPasswd).getBytes("utf-8"));
            httpConnection.setRequestProperty("Authorization", Authorization);
            httpConnection.connect();

            if (httpConnection.getResponseCode() != 200) {
                Map<String, List<String>> headers = httpConnection.getHeaderFields();
                for (String key : headers.keySet()) {
                    log.info(key + ":" + headers.get(key));
                }
                return null;
            }

            // 获取响应JSON
            InputStream is = httpConnection.getInputStream();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] temp = new byte[512];
            int readLen = 0;
            while ((readLen = is.read(temp)) > 0) {
                os.write(temp, 0, readLen);
            }
            String result = new String(os.toByteArray(), "UTF-8");
            os.close();
            is.close();
            log.info(result);
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
