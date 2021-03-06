package org.face.identify.yitu.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class YituHttpRequestUtil {
	// private static final Logger logger =
	// Logger.getLogger(YituHttpRequestUtil.class);

	private YituHttpRequestUtil() {
	}

	private static final String APPLICATION_JSON = "application/json";
	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

	/**
	 * httpclient发送post请求
	 * 
	 * @param json
	 *            json字符串
	 * @param config
	 *            配置信息
	 * @throws Exception
	 * @Author yujinshui
	 * @createTime 2016年7月1日 下午4:25:16
	 */
	public static String httpPostWithJSON(String json, YituConfig config, String url) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		;
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
		httpPost.addHeader("x-access-id", config.getACCESS_ID());
		httpPost.addHeader("x-signature", config.getACCESS_KEY());

		StringEntity se = new StringEntity(json);
		se.setContentType(CONTENT_TYPE_TEXT_JSON);
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
		httpPost.setEntity(se);
		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, Consts.UTF_8.toString());

	}

}