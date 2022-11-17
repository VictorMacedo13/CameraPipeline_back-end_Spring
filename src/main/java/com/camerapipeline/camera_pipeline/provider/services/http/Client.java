package com.camerapipeline.camera_pipeline.provider.services.http;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Service;

import com.camerapipeline.camera_pipeline.provider.exception.BusinessException;
import com.camerapipeline.camera_pipeline.provider.exception.file.CustomIOException;

@Service
public class Client {
    public String post(String uri, String requestJson) {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(uri);

        StringEntity requestEntity = new StringEntity(
            requestJson,
            "UTF-8");
        requestEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httppost.setEntity(
            requestEntity
        );

        try {
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            String data;
            if (entity != null) {
                try (InputStream instream = entity.getContent()) {
                    data = IOUtils.toString(instream, "utf-8");
                }
                return data;
            } else {
                throw new BusinessException("No entity response");
            }
        } catch (ClientProtocolException e) {
            throw new BusinessException("Error in the HTTP protocol");
        } catch (IOException e) {
            throw new CustomIOException(
                e.getMessage(), 
                "IO_EXCEPTION",
                Integer.toString(e.hashCode()), 
                e.getCause()
            );
        }
    }
}
