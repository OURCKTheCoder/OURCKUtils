package ourck.utils;

import java.io.IOException;
import java.util.*;
import org.jsoup.*;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

import com.sun.istack.internal.Nullable;

public class HTTPPoster {
	
	private final String targetURL;
	
	public HTTPPoster(String targetURL) {
		this.targetURL = targetURL;
	}
	
	public Response sendPOST(@Nullable Map<String, String> body, @Nullable Map<String, String> headers)
	throws IOException {
		Connection ct = Jsoup.connect(targetURL);

		if(headers != null) {
			Set<String> keySet = headers.keySet();
			for(String key : keySet) {
				ct.header(key, headers.get(key));
			}
		}
		
		Response repo = null;
		if(body != null) {
			repo = ct.ignoreContentType(true)
					.data(body)
					.method(Method.POST)
					.execute();
		}
		else if(body == null) {
			repo = ct.ignoreContentType(true)
					.method(Method.POST)
					.execute();
		}
		
		return repo;
	}
	
	public Response sendGET(@Nullable Map<String, String> body, @Nullable Map<String, String> headers)
	throws IOException {
		Connection ct = Jsoup.connect(targetURL);

		if(headers != null) {
			Set<String> keySet = headers.keySet();
			for(String key : keySet) {
				ct.header(key, headers.get(key));
			}
		}
		
		Response repo = null;
		if(body != null) {
			repo = ct.ignoreContentType(true)
					.data(body)
					.method(Method.GET)
					.execute();
		}
		else if(body == null) {
			repo = ct.ignoreContentType(true)
					.method(Method.GET)
					.execute();
		}
		
		return repo;
	}
	
	public static void main(String[] args) {
		HTTPPoster p = new HTTPPoster("http://localhost:8983/");
		
		Map<String, String> body = new HashMap<String, String>();
		body.put("CoreName", "ourckCORE");
		body.put("MaskCode", "64");
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) "
			+ "AppleWebKit/537.36 (KHTML, like Gecko) "
			+ "Chrome/63.0.3239.84 Safari/537.36");
		
		try {
			Response response = p.sendPOST(body, headers);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
