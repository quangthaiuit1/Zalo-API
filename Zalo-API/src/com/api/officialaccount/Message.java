package com.api.officialaccount;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.api.model.OAZalo;
import com.api.model.User;

public class Message {

	// url = path + access_token
	public static OAZalo sendMessage(String url,String accessToken , String userId, String mess) {
		HttpClient httpclient = HttpClients.createDefault();
		// handle params
		JSONObject userObject = new JSONObject();
		JSONObject text = new JSONObject();
		text.put("text", mess);
		userObject.put("user_id", userId);

		JSONObject end = new JSONObject();
		end.put("recipient", userObject);
		end.put("message", text);
		//Encode accessToken 
		String accessTokenEncode = URLEncoder.encode(accessToken);
		String urlEnd = url + "?access_token=" + accessTokenEncode;
		try {
			HttpPost httppost = new HttpPost(urlEnd);
			httppost.setHeader("Content-Type", "application/json; charset=utf-8");
			httppost.setEntity(new StringEntity(end.toString(), "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);

			HttpEntity entity = response.getEntity();
			String errorCode = EntityUtils.toString(entity);
			JSONObject result = new JSONObject(errorCode);
			OAZalo data = new OAZalo();
			int err = result.getInt("error");
			if (err == 0) {
				data.setError(err);
				String message_id = result.getJSONObject("data").getString("message_id");
				String message = result.getString("message");
				data.setMessageId(message_id);
				data.setMessage(message);
			} else {
				data.setError(err);
			}
			return data;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (JSONException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static User getUserId(String url, String accessToken, String phoneNumber) {
		HttpClient httpclient = HttpClients.createDefault();
		JSONObject userIdJson = new JSONObject();
		//Create param user_id by phone number
		userIdJson.put("user_id", phoneNumber);
		//Encode format url 
		String accessTokenEncode = URLEncoder.encode(accessToken);
		String userIdEncode = URLEncoder.encode(userIdJson.toString());
		String urlEnd = url +  "?access_token=" + accessTokenEncode + "&data=" + userIdEncode;
		try {
			HttpGet httpGet = new HttpGet(urlEnd);
			// add request header
			httpGet.setHeader("Content-Type", "application/json; charset=utf-8");
			HttpResponse response = httpclient.execute(httpGet);

			HttpEntity entity = response.getEntity();
			String errorCode = EntityUtils.toString(entity);
			JSONObject result = new JSONObject(errorCode);
			User data = new User();
			int err = result.getInt("error");
			if (err == 0) {
				data.setError(err);
				String userId = result.getJSONObject("data").getString("user_id");
				String displayName = result.getJSONObject("data").getString("display_name");
				data.setUserId(userId);
				data.setDisplayName(displayName);
			} else {
				data.setError(err);
			}
			return data;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (JSONException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
