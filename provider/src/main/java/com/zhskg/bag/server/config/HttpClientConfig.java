package com.zhskg.bag.server.config;

import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.message.BasicHeader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author jean
 * @date 2018/10/7
 * desc: http client 配置
 */
@Configuration
public class HttpClientConfig {



    private static PoolingHttpClientConnectionManager manager = null;

    private static CloseableHttpClient httpClient = null;


    @Bean(name = "httpClient")
    public CloseableHttpClient createHttpClient() {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", SSLConnectionSocketFactory.getSystemSocketFactory())
                .build();
        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory =
                new ManagedHttpClientConnectionFactory(
                        DefaultHttpRequestWriterFactory.INSTANCE,
                        DefaultHttpResponseParserFactory.INSTANCE);
        //DNS解析器
        DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
        //创建池化链接管理器
        manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connFactory, dnsResolver);

        SocketConfig defaultSocketConfig = SocketConfig
                .custom()
                .setTcpNoDelay(true)
                .build();
        manager.setDefaultSocketConfig(defaultSocketConfig);
        manager.setMaxTotal(300);
        manager.setDefaultMaxPerRoute(200);
        manager.setValidateAfterInactivity(5 * 1000);

        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(2 * 1000)
                .setSocketTimeout(5 * 1000)
                .setConnectionRequestTimeout(2 * 1000).build();
        //设置默认头
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6"));
        headers.add(new BasicHeader("Connection", "keep-alive"));

        httpClient = HttpClients.custom()
                .setConnectionManager(manager)
                .setConnectionManagerShared(false)
                //添加默认头
                .setDefaultHeaders(headers)
                .evictIdleConnections(60, TimeUnit.SECONDS)
                .evictExpiredConnections()
                .setConnectionTimeToLive(60, TimeUnit.SECONDS)
                .setDefaultRequestConfig(defaultRequestConfig)
                .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
                .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                //设置重试机制
                .setRetryHandler(new DefaultHttpRequestRetryHandler(2, true))
                .build();


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }));
        return httpClient;
    }

}
