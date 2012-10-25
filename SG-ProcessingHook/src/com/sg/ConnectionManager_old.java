package com.sg;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.sg.json.JSONArray;
import com.sg.json.JSONException;
import com.sg.json.JSONObject;
import com.sg.utils.FileIO;

import com.sg.models.Model;
import com.sg.models.Note;
import com.sg.models.Project;
import com.sg.models.Space;
import com.sg.models.User;

/**
 * 
 * Connection Manager class encapsulates the methods using HTTP requests
 * for communicating with the Sunglass server
 *
 */
@SuppressWarnings("deprecation")
public class ConnectionManager_old {

	public enum CONNECTIONTYPE {
		HTTP, HTTPS
	};

	public static CONNECTIONTYPE currentConnectType = CONNECTIONTYPE.HTTP;
	public static int currentConnectPort = 80;
	public FileIO io;
	public boolean VERBOSE = false;

	private final int _POST = 1;
	private final int _GET = 2;
	private final int _PUT = 3;
	private final int _DELETE = 4;

	private final int _OBJ = 1;
	private final int _STL = 2;
	private final int _3DS = 3;
	private final int _DAE = 4;

	private String baseURL = "https://sunglass.io/api/v1/";
	private String sid = "";
	private String token = "";

	/**
	 * Constructor
	 * 
	 * @param String
	 * @param String
	 * 
	 */
	public ConnectionManager_old(String _sid, String _token) {
		this.sid = _sid;
		this.token = _token;
		ConnectionManager_old.currentConnectType = CONNECTIONTYPE.HTTPS;
		ConnectionManager_old.currentConnectPort = 443;
		baseURL = "https://sunglass.io/api/v1/";
	}

	/**
	 * Constructor
	 * 
	 * @param String
	 * @param String
	 * @param CONNECTIONTYPE
	 * 				HTTP or HTTPS 
	 * 
	 */
	public ConnectionManager_old(String _sid, String _token, CONNECTIONTYPE _connectType) {
		this.sid = _sid;
		this.token = _token;
		ConnectionManager_old.currentConnectType = _connectType;
		baseURL = (_connectType == CONNECTIONTYPE.HTTPS) ? "https://sunglass.io/api/v1/"
				: "http://sunglass.io/api/v1/";
		ConnectionManager_old.currentConnectPort = (_connectType == CONNECTIONTYPE.HTTPS) ? 443
				: 80;
	}
	
	//------------------SUNGLASS SPECIFIC FUNCTIONS
	
	/**
	 * get user id
	 * @throws Exception 
	 */
	public int getUserId() throws Exception {
		
		String endpoint = "users"; 
		JSONObject jsonUsers = this.httpGetRequestURL(endpoint);
		
		if (jsonUsers.length() >0 ) {
			return jsonUsers.getInt("id");				
		} else {
			if(VERBOSE) System.out.println("[SGClient] --Authentication Failed");
			return -1;
		}
	}
	

	

	/**
	 * create a new project
	 * 
	 * @param name
	 *            the name for the new project
	 * @throws JSONException 
	 */
//	public Project createProject(String name) throws JSONException {
//
//		JSONObject projectName = new JSONObject();
//		try {
//			projectName.put("name", name);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		JSONObject projectJSON = this.httpPostRequest("projects", projectName);
//		try {
//			JSONObject linksJSON = projectJSON.getJSONObject("links");
//			Project project = new Project(
//					projectJSON.getInt("id"),
//					projectJSON.getString("name"),
//					projectJSON.getString("description"),
//					//linksJSON.getString("spaces"),
//					linksJSON.getString("rootSpace"),
//					projectJSON.getString("createdAt"),
//					projectJSON.getString("modifiedAt"));
//			return project;
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

	
//	public Project getProject(int projectId) throws Exception {
//
//		String endpoint = baseURL + "projects/" + projectId;
//		JSONObject projectJSON = this.httpGetRequestURL(endpoint);
//
//		try {
//			JSONObject linksJSON = projectJSON.getJSONObject("links");
//			Project project = new Project(
//					projectJSON.getInt("id"),
//					projectJSON.getString("name"),
//					projectJSON.getString("description"),
//					//linksJSON.getString("spaces"),
//					linksJSON.getString("rootSpace"),
//					projectJSON.getString("createdAt"),
//					projectJSON.getString("modifiedAt"));
//			return project;
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

	
//	public Space getSpace(int projectId, int spaceId) throws Exception {
//
//		String endpoint = baseURL + "projects/" + projectId + "/spaces/" + spaceId;
//		JSONObject spaceJSON = this.httpGetRequestURL(endpoint);
//
//		ArrayList<Space> subSpaceList = new ArrayList<Space>();
//		ArrayList<Note> noteList = new ArrayList<Note>();
//
//
//		try {
//			JSONObject links = new JSONObject(spaceJSON.get("links").toString());
//			if(VERBOSE) System.out.println("Space" + spaceJSON.toString());
//			if(VERBOSE) System.out.println("Spacelinks" + links.toString());
//			Space s = new Space(spaceJSON.getInt("id"),
//					spaceJSON.getInt("parentSpaceId"),
//					spaceJSON.getInt("projectId"), 
//					spaceJSON.getString("href"),
//					subSpaceList, 
//					noteList, 
//
//					links.getString("project"), 
//					links.getString("subSpaces"),
//					links.getString("models"), 
//					links.getString("views"),
//					spaceJSON.getString("createdAt"),
//					spaceJSON.getString("modifiedAt"));
//
//			return s;
//
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

	
//	public Model getModel(int projectId, int spaceId, int modelId)
//			throws Exception {
//
//		String endpoint = "projects/" + projectId + "/spaces/" + spaceId
//				+ "/models/" + modelId;
//		JSONObject jsonModel = this.httpGetRequestURL(endpoint);
//
//		try {
//
//			JSONArray tMatrix = new JSONArray(jsonModel.get("tMatrix")
//					.toString());
//
//			float[] transformMatrix = new float[16];
//
//			for (int i = 0; i < tMatrix.length(); i++) {
//				transformMatrix[i] = (float) tMatrix.getDouble(i);
//			}
//
//			Model m = new Model(jsonModel.getInt("id"), projectId,
//					jsonModel.getInt("spaceId"), jsonModel.getString("name"),
//					jsonModel.getString("description"), transformMatrix,
//					jsonModel.getString("filePath"),
//					jsonModel.getString("createdAt"),
//					jsonModel.getString("modifiedAt"));
//
//			return m;
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return null;
//	}

	
//	public ArrayList<Model> getModels(int projectId, int spaceId) throws Exception {
//
//		String endpoint = "projects/" + projectId + "/spaces/" + spaceId
//				+ "/models";
//		
//		ArrayList<Model> modelList = new ArrayList<Model>();
//		JSONObject jsonModels = this.httpGetRequestURL(endpoint);
//
//		try {
//			JSONArray modelArray = new JSONArray(jsonModels.get("models").toString());
//			
//			for (int i = 0; i < modelArray.length(); i++) {
//				
//				JSONObject jsonModel = modelArray.getJSONObject(i);
//				JSONArray tMatrix = new JSONArray(jsonModel.get("tMatrix").toString());
//
//				float[] transformMatrix = new float[16];
//				for (int j = 0; j < tMatrix.length(); j++) {
//					transformMatrix[j] = (float) tMatrix.getDouble(j);
//				}
//
//				Model m = new Model(jsonModel.getInt("id"), projectId,
//						jsonModel.getInt("spaceId"), 
//						jsonModel.getString("name"),
//						jsonModel.getString("description"), 
//						transformMatrix,
//						jsonModel.getString("filePath"),
//						jsonModel.getString("createdAt"),
//						jsonModel.getString("modifiedAt"));
//
//				modelList.add(m);
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return modelList;
//	}

	/**
	 * get a user from the unique id
	 * 
	 * @param userId
	 * 			  the unique id of the user

	 * @return User
	 * 			  Sunglass User
	 * 
	 * @throws Exception
	 * 
	 */
//	public User getUser(int userId) throws Exception {
//		String endpoint = baseURL + "users/" + userId;
//		JSONObject userJSON = this.httpGetRequestURL(endpoint);
//
//		JSONObject links;
//		try {
//			links = userJSON.getJSONObject("links");
//
//			User u = new User(userJSON.getInt("id"),
//					userJSON.getString("name"), userJSON.getString("email"),
//					userJSON.getString("href"), links.getString("projects"),
//					userJSON.getString("createdAt"),
//					userJSON.getString("modifiedAt"));
//
//			return (u);
//
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	//------------------ GENERIC TRANSFER FUNCTIONS
	
	/**
	 * makes an http put request to the api
	 * 
	 * @param endpoint
	 *            the url endpoint for the httprequest
	 */
	public JSONObject httpPutRequest(String endpoint, JSONObject jsonEntity) {
		HttpClient httpClient = new DefaultHttpClient();

		try {

			URI putURI = new URI(baseURL + endpoint);
			HttpPut request = new HttpPut();
			StringEntity entity = new StringEntity(jsonEntity.toString());
			request.addHeader("Content-Type", "application/json");
			request.setEntity(entity);
			request.setURI(putURI);
			HttpResponse response = httpClient.execute(request);
			JSONObject responseJSON = new JSONObject(
					FileIO.convertStreamToString(response.getEntity()
							.getContent()));
			if (VERBOSE) {
				System.out.println(responseJSON.toString());
			}
			return responseJSON;

		} catch (Exception e) {

			if(VERBOSE) System.out.println("[ConnMgr/httpPutRequest] --Connection Failed");
			e.printStackTrace();

		} finally {

			httpClient.getConnectionManager().shutdown();
		}

		return null;
	}

	/**
	 * makes an http post request to the api
	 * 
	 * @param endpoint
	 *            the url endpoint for the httprequest
	 */
	public JSONObject httpPostRequest(String endpoint, JSONObject jsonEntity) {
		DefaultHttpClient httpClient = new DefaultHttpClient();

		httpClient.getCredentialsProvider().setCredentials(
				new AuthScope("sunglass.io",
						ConnectionManager_old.currentConnectPort),
				new UsernamePasswordCredentials(this.sid, this.token));
		
		try {
			URI postURI = new URI(baseURL + endpoint);
			HttpPost request = new HttpPost();
			StringEntity entity = new StringEntity(jsonEntity.toString());
			request.addHeader("Content-Type", "application/json");
			request.setEntity(entity);
			request.setURI(postURI);
			if (VERBOSE) System.out.println("[ConnMgr/httpPostRequest] --Executing request "
					+ request.getRequestLine());
			HttpResponse response = httpClient.execute(request);
			if (VERBOSE) System.out.println("[ConnMgr/httpPostRequest] --Response details "
					+ response.getEntity().getContent());
			JSONObject responseJSON = new JSONObject(
					FileIO.convertStreamToString(response.getEntity()
							.getContent()));
			if (VERBOSE) {
				System.out.println(responseJSON.toString());
			}
			return responseJSON;

		} catch (Exception e) {

			if(VERBOSE) System.out.println("[ConnMgr/httpPostRequest] --Connection Failed");
			e.printStackTrace();

		} finally {

			httpClient.getConnectionManager().shutdown();
		}

		return null;

	}

	/**
	 * makes an http get request to the api
	 * 
	 * @param endpoint
	 *            the url endpoint for the httprequest
	 */
	public JSONObject httpGetRequest(String endpoint) {
		HttpClient httpClient = new DefaultHttpClient();

		try {

			URI getURI = new URI(baseURL + endpoint);
			HttpGet request = new HttpGet();
			request.setURI(getURI);
			request.addHeader("Content-Type", "application/json");
			HttpResponse response = httpClient.execute(request);
			JSONObject responseJSON = new JSONObject(
					FileIO.convertStreamToString(response.getEntity()
							.getContent()));
			if (VERBOSE) {
				System.out.println(responseJSON.toString());
			}
			return responseJSON;

		} catch (Exception e) {

			if(VERBOSE) System.out.println("[ConnMgr/httpGetRequest] --Connection Failed");
			e.printStackTrace();

		} finally {

			httpClient.getConnectionManager().shutdown();
		}

		return null;
	}

	/**
	 * makes an http get request to the api
	 * 
	 * @param endpoint
	 *            the url endpoint for the httprequest
	 */
	public JSONObject httpGetRequestURL(String endpoint) throws Exception {
		return httpGetRequestURL(this.sid, this.token, endpoint);
	}

	/**
	 * makes an http get request to the api
	 * 
	 * @param endpoint
	 *            the url endpoint for the httprequest
	 */
	public JSONObject httpGetRequestURL(String username, String password,
			String endpoint) throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			//incase SSL Cert errors use wrapClient to gain trust and move on
			httpclient = (DefaultHttpClient) wrapClient(httpclient);
			httpclient.getCredentialsProvider().setCredentials(
					new AuthScope("sunglass.io",
							ConnectionManager_old.currentConnectPort),
					new UsernamePasswordCredentials(username, password));

			HttpGet httpget = new HttpGet(baseURL + endpoint);

			if(VERBOSE) System.out
					.println("[ConnMgr/httpGetRequestURL] --Executing request "
							+ httpget.getRequestLine());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if(VERBOSE) System.out
					.println("[ConnMgr/httpGetRequestURL] --Response status: "
							+ response.getStatusLine());
			if (entity != null) {
				if(VERBOSE) System.out
						.println("[ConnMgr/httpGetRequestURL] --Response content length: "
								+ entity.getContentLength());
			}

			if (entity.getContentLength() > 0) {
				InputStream stream = entity.getContent();
				String content = FileIO.convertStreamToString(stream);
				JSONObject responseJSON = new JSONObject(content);

				if (VERBOSE) {
					System.out
							.println("[ConnMgr/httpGetRequestURL] --Response: "
									+ responseJSON.toString());
				}

				EntityUtils.consume(entity);
				return responseJSON;
			} else {

				EntityUtils.consume(entity);
				return new JSONObject();
			}

		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	 * Temporary hack to work around SSL / cert checks
	 * 
	 * @param HttpClient
	 * @return HttpClient
	 */
	private HttpClient wrapClient(final HttpClient base) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { (TrustManager) tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = base.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", (SocketFactory) ssf, 443));
			return new DefaultHttpClient(ccm, base.getParams());
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * makes an http delete request to the api
	 * 
	 * @param endpoint
	 *            the url endpoint for the httprequest
	 */
	public JSONObject httpDeleteRequest(String endpoint) {
		HttpClient httpClient = new DefaultHttpClient();

		try {

			URI deleteURI = new URI(baseURL + endpoint);
			HttpDelete request = new HttpDelete();
			request.setURI(deleteURI);
			request.addHeader("Content-Type", "application/json");
			HttpResponse response = httpClient.execute(request);
			JSONObject responseJSON = new JSONObject(
					FileIO.convertStreamToString(response.getEntity()
							.getContent()));
			if (VERBOSE) {
				System.out.println(responseJSON.toString());
			}
			return responseJSON;

		} catch (Exception e) {

			if(VERBOSE) System.out.println("httpDelete failure");
			e.printStackTrace();

		} finally {

			httpClient.getConnectionManager().shutdown();
		}

		return null;
	}

	/**
	 * @param endpoint
	 *            the url endpoint for the httprequest
	 * 
	 */
	public JSONObject uploadFile(String endpoint, String name, String[] filePath) {

		DefaultHttpClient httpClient = new DefaultHttpClient();

		try {
			httpClient = (DefaultHttpClient) wrapClient(httpClient);
			httpClient.getCredentialsProvider().setCredentials(
					new AuthScope("sunglass.io",
							ConnectionManager_old.currentConnectPort),
					new UsernamePasswordCredentials(this.sid, this.token));

			URI postURI = new URI(baseURL + endpoint);
			HttpPost request = new HttpPost();
			MultipartEntity entity = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE);
			for (String filep : filePath) {
				FileBody file = new FileBody(new File(filep));
				entity.addPart(name, file);
			}
			request.setEntity(entity);
			request.setURI(postURI);
			if(VERBOSE) System.out.println("[ConnMgr/upload] --Executing request "
					+ request.getRequestLine());
			HttpResponse response = httpClient.execute(request);
			JSONObject responseJSON = new JSONObject(
					FileIO.convertStreamToString(response.getEntity()
							.getContent()));
			if (VERBOSE) {
				System.out.println("[ConnMgr/upload] --Response: "
						+ responseJSON.toString());
			}
			return responseJSON;

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}
	
//	public Model uploadModel(String endpoint, String name, String[] filePath, int projID) {
//
//		DefaultHttpClient httpClient = new DefaultHttpClient();
//
//		try {
//			httpClient = (DefaultHttpClient) wrapClient(httpClient);
//			httpClient.getCredentialsProvider().setCredentials(
//					new AuthScope("sunglass.io",
//							ConnectionManager_old.currentConnectPort),
//					new UsernamePasswordCredentials(this.sid, this.token));
//
//			URI postURI = new URI(baseURL + endpoint);
//			HttpPost request = new HttpPost();
//			MultipartEntity entity = new MultipartEntity(
//					HttpMultipartMode.BROWSER_COMPATIBLE);
//			for (String filep : filePath) {
//				FileBody file = new FileBody(new File(filep));
//				entity.addPart(name, file);
//			}
//			request.setEntity(entity);
//			request.setURI(postURI);
//			
//			if(VERBOSE) System.out.println("[ConnMgr/uploadModel] --Executing request "
//					+ request.getRequestLine());
//			HttpResponse response = httpClient.execute(request);
//			
//			if(VERBOSE) System.out.println("[ConnMgr/uploadModel] --Response details "
//					+ response.getEntity().getContent());
//
//			JSONObject jsonModels = new JSONObject(
//					FileIO.convertStreamToString(response.getEntity()
//							.getContent()));
//			
//			JSONArray modelArray = new JSONArray(jsonModels.get("models")
//					.toString());
//			
//			JSONObject jsonModel = (JSONObject) modelArray.get(0);
//			
//			if (VERBOSE) {
//				System.out.println("[ConnMgr/uploadModel] --Response: "
//						+ jsonModel.toString());
//			}
//			
//			JSONArray tMatrix = new JSONArray(jsonModel.get("tMatrix")
//					.toString());
//
//			float[] transformMatrix = new float[16];
//
//			for (int i = 0; i < tMatrix.length(); i++) {
//				transformMatrix[i] = (float) tMatrix.getDouble(i);
//			}
//
//			Model m = new Model(
//					jsonModel.getInt("id"), 
//					projID,
//					jsonModel.getInt("spaceId"), 
//					jsonModel.getString("name"),
//					jsonModel.getString("description"), 
//					transformMatrix,
//					jsonModel.getString("filePath"),
//					jsonModel.getString("createdAt"),
//					jsonModel.getString("modifiedAt"));
//
//			return m;
//			
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//
//			httpClient.getConnectionManager().shutdown();
//		}
//		return null;
//	}

	/**
	 * Downloads a file using a multipart form request and save it to location
	 * 
	 * @param endpoint
	 *            the url endpoint for the httprequest
	 */
	public File saveToContainer(String endpoint, File container) {
		if(VERBOSE) System.out
				.println("[ConnMgr/saveToContainer] --Saving remote file: "
						+ endpoint + " to container:" + container.getName());
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient = (DefaultHttpClient) wrapClient(httpClient);
		httpClient.getCredentialsProvider().setCredentials(
				new AuthScope("sunglass.io", ConnectionManager_old.currentConnectPort),
				new UsernamePasswordCredentials(this.sid, this.token));

		URI getURI;

		try {
			getURI = new URI(endpoint);

			HttpGet request = new HttpGet();

			request.setURI(getURI);

			HttpResponse response;

			if(VERBOSE) System.out.println("[ConnMgr/saveToContainer] --Executing request "
					+ request.getRequestLine());
			response = httpClient.execute(request);

			if(VERBOSE) System.out.println("[ConnMgr/saveToContainer] --Response details"
					+ response.getEntity().getContent());
			
			File f = FileIO.convertStreamToFile(response.getEntity()
					.getContent(), container);

			return f;

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return null;
	}

	/**
	 * Downloads a file using a multipart form request and writes the contents
	 * of that file to a string
	 * 
	 * @param endpoint
	 *            the url endpoint for the httprequest
	 */
	protected String downloadFileToString(String endpoint) {

		HttpClient httpClient = new DefaultHttpClient();

		try {

			URI getURI = new URI(baseURL + endpoint);
			HttpGet request = new HttpGet();
			request.setURI(getURI);
			HttpResponse response;
			response = httpClient.execute(request);
			String s = FileIO.convertStreamToString(response.getEntity()
					.getContent());

			return s;

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	/**
	 * Called when the parent applet shuts down
	 * 
	 */
	public void dispose() {
		// anything in here will be called automatically when
		// the parent applet shuts down. for instance, this might
		// shut down a thread used by this library.
	}

}

