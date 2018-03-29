package com.redlongcitywork.easytourlite.http;

import com.redlongcitywork.easytourlite.utils.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 */
@Service
@Profile("prodaction")
public class ProdactionHttpService implements ItToursUrls, HttpService {

    private static final Logger LOG = Logger.getLogger(ProdactionHttpService.class.getName());

    public static final int CONNECT_TIMEOUT = 60 * 1000;
    public static final int SOCKET_TIMEOUT = 60 * 1000;

    private static final ObjectMapper OM = new ObjectMapper();

    private static final ResponseHandler<JsonNode> JSONNODE_CONTENT_HANDLER = (HttpResponse response) -> {
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() >= 300) {
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        }

        HttpEntity entity = response.getEntity();
        if (entity == null) {
            throw new ClientProtocolException("Response contains no content");
        }

        String content = EntityUtils.toString(entity, StandardCharsets.UTF_8.name());
        return OM.readTree(content);
    };

    public String cleanURLFromParameters(String url) throws URISyntaxException {
        if (url != null) {
            url = new URIBuilder(url)
                    .clearParameters()
                    .build()
                    .toString();
        }
        return url;
    }

    public Content getRequest(String url) throws IOException {
        Response response = null;
        try {
            response = Request.Get(url)
                    .connectTimeout(CONNECT_TIMEOUT)
                    .socketTimeout(SOCKET_TIMEOUT)
                    .addHeader(authorization, authorization_token)
                    .addHeader(acceptLanguage, response_language)
                    .execute();
        } catch (IOException e) {
            StatusLine line = response.returnResponse().getStatusLine();
            LOG.log(Level.WARNING, "Response Info: Code: "
                    + line.getStatusCode() + "; ReasonPhrase: "
                    + line.getReasonPhrase() + "; ProtocolVersion: "
                    + line.getProtocolVersion());
            LOG.log(Level.WARNING, e.toString());
        }
        LOG.log(Level.INFO, response.toString());
        return response.returnContent();
    }

    @Override
    public JsonNode getJsonNodeFromUrl(String url) {
        Response response = null;
        try {
            response = Request.Get(url)
                    .connectTimeout(CONNECT_TIMEOUT)
                    .socketTimeout(SOCKET_TIMEOUT)
                    .addHeader(authorization, authorization_token)
                    .addHeader(acceptLanguage, response_language)
                    .execute();

            return response.handleResponse(JSONNODE_CONTENT_HANDLER);

        } catch (IOException e) {
            try {
                StatusLine line = response.returnResponse().getStatusLine();
                LOG.log(Level.WARNING, "Response Info: Code: "
                        + line.getStatusCode() + "; ReasonPhrase: "
                        + line.getReasonPhrase() + "; ProtocolVersion: "
                        + line.getProtocolVersion());
                LOG.log(Level.WARNING, e.toString());
            } catch (IOException ex) {
                LOG.log(Level.WARNING, ex.toString());
            }
        }
        return null;
    }

    public Content postWithBodyAsRawRequestAsContent(String url, String body) throws IOException {
        try {
            return Request.Post(url)
                    .connectTimeout(CONNECT_TIMEOUT)
                    .socketTimeout(SOCKET_TIMEOUT)
                    .addHeader(authorization, authorization_token)
                    .addHeader(acceptLanguage, response_language)
                    .bodyString(body, ContentType.APPLICATION_FORM_URLENCODED)
                    .execute()
                    .returnContent();
        } catch (IOException e) {
            throw e;
        }
    }

    public JsonNode postWithBodyAsJsonRequestAsJson(String url, String body) throws IOException {
        try {
            return Request.Post(url)
                    .connectTimeout(CONNECT_TIMEOUT)
                    .socketTimeout(SOCKET_TIMEOUT)
                    .addHeader(authorization, authorization_token)
                    .addHeader(acceptLanguage, response_language)
                    .bodyString(body, ContentType.APPLICATION_JSON)
                    .execute()
                    .handleResponse(JSONNODE_CONTENT_HANDLER);
        } catch (IOException e) {
            throw e;
        }
    }
}
