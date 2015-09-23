package com.scott.app.Rachio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class API {

	private final String USER_AGENT = "Mozilla/5.0";
	private String APIAccessToken = "3a1c8cb8-b5fc-4a0d-b09c-e9a13a6d8d24";
	private String personID;

	public String getPersonId() {
		return this.personID;
	}

	public void setPersonID() {
		String url = "https://api.rach.io/1/public/person/info";
		Map json = this.getURL(url);
		if (json != null)
			this.personID = (String) json.get("id");
		else
			this.personID = null;
	}

	public void fetchPerson() {
		String url = "https://api.rach.io/1/public/person/" + this.getPersonId();
		Map json = this.getURL(url);
		System.out.println(json);
		LinkedList<LinkedHashMap> devices = (LinkedList<LinkedHashMap>) json.get("devices");
		if (devices.size() > 1)
			System.out.println("Not handling all devices");

		// devices.getFirst()
		ListIterator<LinkedHashMap> listIterator = devices.listIterator();
		while (listIterator.hasNext()) {
			Object o = listIterator.next();
			System.out.println(devices.size() + " | " + o.getClass() + " | " + o.toString());
		}
	}

	public void zoneStart(String zone, int seconds) {
		String url = "https://api.rach.io/1/public/zone/start";
		JSONObject obj = new JSONObject();
		obj.put("id", zone);
		obj.put("duration", new Integer(seconds));
		int response = this.putURL(url, obj.toString());

		// System.out.println(obj);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getURL(String url) {
		URL urlObj;
		HttpURLConnection con = null;
		try {
			urlObj = new URL(url);
			con = (HttpURLConnection) urlObj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Authorization", "Bearer " + this.APIAccessToken);

			int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			Map json = JSONHelper.parseText(response.toString());
			return json;
			// System.out.println(json);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int putURL(String url, String payload) {
		URL urlObj;
		HttpURLConnection con = null;
		try {
			urlObj = new URL(url);
			con = (HttpURLConnection) urlObj.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("PUT");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", "Bearer " + this.APIAccessToken);

			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
			osw.write(payload);
			osw.flush();
			osw.close();
//			con.getInputStream();
			System.err.println(con.getDoOutput());
			System.err.println(con.getResponseCode());
			System.err.println(url);
			System.err.println(payload);
			System.err.println(con.getResponseMessage());
			return con.getResponseCode();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
