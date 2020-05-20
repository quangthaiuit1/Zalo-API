package oa;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import model.OAZalo;

public class SendMessage {
	
	//url = path + access_token
	public static OAZalo oaSendMessage (String url, String userId, String mess) {
		HttpClient httpclient = HttpClients.createDefault();
       //handle params
		JSONObject userObject = new JSONObject();
		JSONObject text = new JSONObject();
		text.put("text", mess);
		userObject.put("user_id", userId);
		
		JSONObject end = new JSONObject();
		end.put("recipient", userObject);
		end.put("message", text);
		
		System.out.println(end.toString());
		try {	
       		HttpPost httppost = new HttpPost(url);
       		httppost.setHeader("Content-Type", "application/json; charset=utf-8");
       		httppost.setEntity(new StringEntity(end.toString(),"UTF-8"));
       		HttpResponse response = httpclient.execute(httppost);
       		
       		HttpEntity entity = response.getEntity();
       		String errorCode = EntityUtils.toString(entity);
       		JSONObject result = new JSONObject(errorCode);
       		OAZalo data = new OAZalo();
       		int err = result.getInt("error");
       		if(err == 0) {
       			data.setErr(err);
       			String message_id = result.getJSONObject("data").getString("message_id");
       			String message = result.getString("message");
       			data.setMessageId(message_id);
       			data.setMessage(message);
		    }else {
		    	data.setErr(err);
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
