package com.scott.app.Rachio;

import org.json.simple.parser.ParseException;

public class Rachio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		API api = new API();
		// api.setPersonID();
		// api.fetchPerson();
		api.zoneStart("873ed34e-7901-45ad-87dd-6f00b30de5bb",10);

		// JSONHelper.testPrint();
	}

}
