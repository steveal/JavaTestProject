package com.test.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

public class TestHttp {

	public static void test() throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();

		httpClient.getCredentialsProvider().setCredentials(
				// new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
				new AuthScope("10.45.6.255", 8888),
				new UsernamePasswordCredentials("admin", "Harbor12345"));

		HttpGet httpget = new HttpGet(
				"http://10.45.6.255:8888/api/targets?name=ssx");

		System.out.println("executing request" + httpget.getRequestLine());
		HttpResponse response = httpClient.execute(httpget);
		HttpEntity entity = response.getEntity();

		System.out.println("----------------------------------------");
		System.out.println(response.getStatusLine());
		if (entity != null) {
			System.out.println("Response content length: "
					+ entity.getContentLength());
		}
		if (entity != null) {
			entity.consumeContent();
		}

		httpClient.getConnectionManager().shutdown();
	}

	public static void test2() throws Exception {
		HttpHost targetHost = new HttpHost("10.45.6.255", 8888, "http");
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			httpclient.getCredentialsProvider().setCredentials(
					new AuthScope(targetHost.getHostName(),
							targetHost.getPort()),
					new UsernamePasswordCredentials("admin", "Harbor12345"));
			AuthCache authCache = new BasicAuthCache();
			BasicScheme basicAuth = new BasicScheme();
			authCache.put(targetHost, basicAuth);
			BasicHttpContext localcontext = new BasicHttpContext();
			localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);
			HttpGet httpget = new HttpGet("http://10.45.6.255:8888/api/targets?name=ss");
			System.out
					.println("executing request: " + httpget.getRequestLine());
			System.out.println("to target: " + targetHost);
			for (int i = 0; i < 3; i++) {
				HttpResponse response = httpclient.execute(targetHost, httpget,
						localcontext);
				HttpEntity entity = response.getEntity();
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				if (entity != null) {
					System.out.println("Response content length: "
							+ entity.getContentLength());
					String str = EntityUtils.toString(entity);
					System.out.println(str);
				}
				EntityUtils.consume(entity);
			}
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}
    public static void test3() throws ClientProtocolException, IOException {
    	HttpHost target = new HttpHost("10.45.6.255", 8888, "http");
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                new UsernamePasswordCredentials("admin", "Harbor12345"));
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider).build();
        try {

            // Create AuthCache instance
            AuthCache authCache = new BasicAuthCache();
            // Generate BASIC scheme object and add it to the local
            // auth cache
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(target, basicAuth);

            // Add AuthCache to the execution context
            HttpClientContext localContext = HttpClientContext.create();
            localContext.setAuthCache(authCache);

            HttpGet httpget = new HttpGet("http://10.45.6.255:8888/api/targets?name=ss");

            System.out.println("Executing request " + httpget.getRequestLine() + " to target " + target);
            for (int i = 0; i < 3; i++) {
                CloseableHttpResponse response = httpclient.execute(target, httpget, localContext);
                try {
                    System.out.println("----------------------------------------");
                    System.out.println(response.getStatusLine());
                    System.out.println(EntityUtils.toString(response.getEntity()));
                } finally {
                    response.close();
                }
            }
        } finally {
            httpclient.close();
        }
    }
	public static void main(String[] args) {
		try {
			test3();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
