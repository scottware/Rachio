package com.scott.app.Rachio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		Map json = this.fetchJsonFromURL("GET", url);
		if (json != null)
			this.personID = (String) json.get("id");
		else
			this.personID = null;
	}

	public void fetchPerson() {
		String url = "https://api.rach.io/1/public/person/" + this.getPersonId();
		Map json = this.fetchJsonFromURL("GET", url);
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

		// devices.iterator()
		// this.printJson(json);
		// System.out.println("-------------");
		// System.out.println(devices.getClass());
		// System.out.println(devices);
	}

	public void testPrint() {
		String jsonText = "{\"id\":\"a28a5069-afdf-45b1-a497-c0070881e84d\",\"username\":\"sware\",\"fullName\":\"Scott Ware\",\"email\":\"sware@mac.com\",\"devices\":[{\"id\":\"7b82c8aa-85d0-48ce-85a5-e900134e380a\",\"status\":\"ONLINE\",\"zones\":[{\"id\":\"9ed966f8-16ae-4301-add7-1a0018e4d869\",\"zoneNumber\":12,\"name\":\"lower front patio\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Shrubs\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/shrub.png\",\"coefficient\":0.5},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":15.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":1.92,\"depthOfWater\":1.28,\"maxRuntime\":28800,\"wateringAdjustmentRuntimes\":{\"1\":5236,\"2\":4364,\"3\":3491,\"4\":2618,\"5\":1745},\"runtime\":3491},{\"id\":\"8ec554a6-797f-48d1-8944-98bb65c32537\",\"zoneNumber\":3,\"name\":\"Pool Left\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Shrubs\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/shrub.png\",\"coefficient\":0.5},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":15.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":1.92,\"depthOfWater\":1.28,\"maxRuntime\":28800,\"wateringAdjustmentRuntimes\":{\"1\":5236,\"2\":4364,\"3\":3491,\"4\":2618,\"5\":1745},\"runtime\":3491},{\"id\":\"f2c1386a-87b5-4825-a175-eba5dd1b4ba3\",\"zoneNumber\":2,\"name\":\"Upper Back Lawn\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Cool Season Grass\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/cool_season_grass.png\",\"coefficient\":0.75},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":6.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":0.77,\"depthOfWater\":0.51,\"maxRuntime\":10800,\"wateringAdjustmentRuntimes\":{\"1\":2086,\"2\":1739,\"3\":1391,\"4\":1043,\"5\":695},\"runtime\":1391},{\"id\":\"0a492114-214f-436f-9aff-67867c034530\",\"zoneNumber\":1,\"name\":\"Behind Pool\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Shrubs\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/shrub.png\",\"coefficient\":0.5},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":15.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":1.92,\"depthOfWater\":1.28,\"maxRuntime\":28800,\"wateringAdjustmentRuntimes\":{\"1\":5236,\"2\":4364,\"3\":3491,\"4\":2618,\"5\":1745},\"runtime\":3491},{\"id\":\"d06ffd94-31b2-47e8-98c3-e24bb9b4ed06\",\"zoneNumber\":16,\"name\":\"lower back grass\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Cool Season Grass\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/cool_season_grass.png\",\"coefficient\":0.75},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":6.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":0.77,\"depthOfWater\":0.51,\"maxRuntime\":10800,\"wateringAdjustmentRuntimes\":{\"1\":2086,\"2\":1739,\"3\":1391,\"4\":1043,\"5\":695},\"runtime\":1391},{\"id\":\"ae7d0e61-a55e-48ac-bd4c-c7a0ddbda751\",\"zoneNumber\":13,\"name\":\"middle front grass\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Cool Season Grass\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/cool_season_grass.png\",\"coefficient\":0.75},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":6.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":0.77,\"depthOfWater\":0.51,\"maxRuntime\":10800,\"wateringAdjustmentRuntimes\":{\"1\":2086,\"2\":1739,\"3\":1391,\"4\":1043,\"5\":695},\"runtime\":1391},{\"id\":\"c736992a-a339-4b77-a64b-c6c189bf7a20\",\"zoneNumber\":7,\"name\":\"back ivy\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Xeriscape\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/xeriscape.png\",\"coefficient\":0.3},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":5.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":0.65,\"depthOfWater\":0.43,\"maxRuntime\":10800,\"wateringAdjustmentRuntimes\":{\"1\":1759,\"2\":1466,\"3\":1173,\"4\":880,\"5\":586},\"runtime\":1173},{\"id\":\"a60bde67-4e90-4f70-b36b-9072e2169ce9\",\"zoneNumber\":11,\"name\":\"Kirkcrest ivy \",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Xeriscape\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/xeriscape.png\",\"coefficient\":0.3},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":5.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":0.65,\"depthOfWater\":0.43,\"maxRuntime\":10800,\"wateringAdjustmentRuntimes\":{\"1\":1759,\"2\":1466,\"3\":1173,\"4\":880,\"5\":586},\"runtime\":1173},{\"id\":\"c2d6d958-9140-4d81-a329-0d21578fa7c2\",\"zoneNumber\":14,\"name\":\"upper front grass\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Cool Season Grass\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/cool_season_grass.png\",\"coefficient\":0.75},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":6.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":0.77,\"depthOfWater\":0.51,\"maxRuntime\":10800,\"wateringAdjustmentRuntimes\":{\"1\":2086,\"2\":1739,\"3\":1391,\"4\":1043,\"5\":695},\"runtime\":1391},{\"id\":\"8fbe2a39-5337-4820-954b-5b64355e6231\",\"zoneNumber\":15,\"name\":\"max grass\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Cool Season Grass\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/cool_season_grass.png\",\"coefficient\":0.75},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":6.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":0.77,\"depthOfWater\":0.51,\"maxRuntime\":10800,\"wateringAdjustmentRuntimes\":{\"1\":2086,\"2\":1739,\"3\":1391,\"4\":1043,\"5\":695},\"runtime\":1391},{\"id\":\"89120eab-529b-474b-b77b-bb5134a3ea5a\",\"zoneNumber\":9,\"name\":\"red wood and gardenias\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Shrubs\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/shrub.png\",\"coefficient\":0.5},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":15.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":1.92,\"depthOfWater\":1.28,\"maxRuntime\":28800,\"wateringAdjustmentRuntimes\":{\"1\":5236,\"2\":4364,\"3\":3491,\"4\":2618,\"5\":1745},\"runtime\":3491},{\"id\":\"089509bb-97f7-4662-a46c-0fb936277be0\",\"zoneNumber\":4,\"name\":\"Front oak and ivy\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Trees\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/tree.png\",\"coefficient\":0.75},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":25.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":3.2,\"depthOfWater\":2.13,\"maxRuntime\":36000,\"wateringAdjustmentRuntimes\":{\"1\":8714,\"2\":7261,\"3\":5809,\"4\":4357,\"5\":2905},\"runtime\":5809},{\"id\":\"6448a6f0-01a7-46ad-b643-7786c90c93e7\",\"zoneNumber\":10,\"name\":\"lower front grass\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Cool Season Grass\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/cool_season_grass.png\",\"coefficient\":0.75},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":6.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":0.77,\"depthOfWater\":0.51,\"maxRuntime\":10800,\"wateringAdjustmentRuntimes\":{\"1\":2086,\"2\":1739,\"3\":1391,\"4\":1043,\"5\":695},\"runtime\":1391},{\"id\":\"873ed34e-7901-45ad-87dd-6f00b30de5bb\",\"zoneNumber\":5,\"name\":\"back patio and roses\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Annual\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/annual.png\",\"coefficient\":0.8},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":4.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":0.51,\"depthOfWater\":0.34,\"maxRuntime\":10800,\"wateringAdjustmentRuntimes\":{\"1\":1391,\"2\":1159,\"3\":927,\"4\":695,\"5\":464},\"runtime\":927},{\"id\":\"5382ff0d-2ec0-47c0-b624-6f27f8d3281d\",\"zoneNumber\":6,\"name\":\"drip\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Cool Season Grass\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/cool_season_grass.png\",\"coefficient\":0.75},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":6.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":0.77,\"depthOfWater\":0.51,\"maxRuntime\":10800,\"wateringAdjustmentRuntimes\":{\"1\":2086,\"2\":1739,\"3\":1391,\"4\":1043,\"5\":695},\"runtime\":1391},{\"id\":\"6c6d3d4d-ccd1-40d5-b6f1-844c97d579dc\",\"zoneNumber\":8,\"name\":\"back oak and ivy\",\"enabled\":true,\"customNozzle\":{\"name\":\"Fixed Spray Head\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/nozzle/fixed_spray.png\",\"category\":\"FIXED_SPRAY_HEAD\",\"inchesPerHour\":1.5},\"customSoil\":{\"createDate\":1411689412453,\"lastUpdateDate\":1411689412453,\"id\":\"a7d63d9c-a445-4d01-b651-652361ad7604\",\"name\":\"Loam\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/soil/loam.png\",\"category\":\"LOAM\",\"infiltrationRate\":0.35,\"editable\":false,\"percentAvailableWater\":0.7},\"customSlope\":{\"name\":\"Flat\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/slope/flat.png\",\"variance\":\"ZERO_THREE\",\"sortOrder\":0},\"customCrop\":{\"name\":\"Trees\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/crop/tree.png\",\"coefficient\":0.75},\"customShade\":{\"name\":\"Lots of sun\",\"description\":\"6-8 hours of sun\",\"imageUrl\":\"https://s3-us-west-2.amazonaws.com/rachio-api-icons/shade/lots_of_sun.png\",\"exposure\":1.0},\"availableWater\":0.17,\"rootZoneDepth\":25.0,\"managementAllowedDepletion\":0.5,\"efficiency\":0.8,\"yardAreaSquareFeet\":1000,\"scheduleDataModified\":false,\"saturatedDepthOfWater\":3.2,\"depthOfWater\":2.13,\"maxRuntime\":36000,\"wateringAdjustmentRuntimes\":{\"1\":8714,\"2\":7261,\"3\":5809,\"4\":4357,\"5\":2905},\"runtime\":5809}],\"timeZone\":\"America/Los_Angeles\",\"latitude\":37.83001,\"longitude\":-122.0229,\"zip\":\"94507\",\"name\":\"Sprinkler\",\"scheduleRules\":[{\"id\":\"04ff20ba-efba-4a18-8c6d-84dcb399ca5b\",\"zones\":[{\"zoneId\":\"c2d6d958-9140-4d81-a329-0d21578fa7c2\",\"zoneNumber\":14,\"duration\":180,\"sortOrder\":2},{\"zoneId\":\"6448a6f0-01a7-46ad-b643-7786c90c93e7\",\"zoneNumber\":10,\"duration\":480,\"sortOrder\":0},{\"zoneId\":\"ae7d0e61-a55e-48ac-bd4c-c7a0ddbda751\",\"zoneNumber\":13,\"duration\":480,\"sortOrder\":1}],\"scheduleJobTypes\":[\"INTERVAL_3\"],\"summary\":\"Every 3 days at 4:00 AM\",\"rainDelay\":true,\"waterBudget\":false,\"cycleSoakStatus\":\"ON\",\"startDate\":1432882800000,\"name\":\"Front\",\"enabled\":false,\"totalDuration\":1140,\"weatherIntelligenceSensitivity\":0.25,\"seasonalAdjustment\":0.0,\"totalDurationNoCycle\":1140,\"cycles\":1,\"externalName\":\"Front\",\"cycleSoak\":true},{\"id\":\"1636d471-6fbc-4db7-be77-58a08c32f09e\",\"zones\":[{\"zoneId\":\"89120eab-529b-474b-b77b-bb5134a3ea5a\",\"zoneNumber\":9,\"duration\":420,\"sortOrder\":7},{\"zoneId\":\"a60bde67-4e90-4f70-b36b-9072e2169ce9\",\"zoneNumber\":11,\"duration\":0,\"sortOrder\":8},{\"zoneId\":\"5382ff0d-2ec0-47c0-b624-6f27f8d3281d\",\"zoneNumber\":6,\"duration\":720,\"sortOrder\":4},{\"zoneId\":\"873ed34e-7901-45ad-87dd-6f00b30de5bb\",\"zoneNumber\":5,\"duration\":420,\"sortOrder\":3},{\"zoneId\":\"089509bb-97f7-4662-a46c-0fb936277be0\",\"zoneNumber\":4,\"duration\":0,\"sortOrder\":2},{\"zoneId\":\"9ed966f8-16ae-4301-add7-1a0018e4d869\",\"zoneNumber\":12,\"duration\":420,\"sortOrder\":9},{\"zoneId\":\"0a492114-214f-436f-9aff-67867c034530\",\"zoneNumber\":1,\"duration\":420,\"sortOrder\":0},{\"zoneId\":\"6c6d3d4d-ccd1-40d5-b6f1-844c97d579dc\",\"zoneNumber\":8,\"duration\":240,\"sortOrder\":6},{\"zoneId\":\"8ec554a6-797f-48d1-8944-98bb65c32537\",\"zoneNumber\":3,\"duration\":420,\"sortOrder\":1},{\"zoneId\":\"c736992a-a339-4b77-a64b-c6c189bf7a20\",\"zoneNumber\":7,\"duration\":0,\"sortOrder\":5}],\"scheduleJobTypes\":[\"INTERVAL_3\"],\"summary\":\"Every 3 days at 5:30 AM\",\"rainDelay\":true,\"waterBudget\":true,\"cycleSoakStatus\":\"OFF\",\"startDate\":1441868400000,\"name\":\"Yard\",\"enabled\":true,\"totalDuration\":2884,\"weatherIntelligenceSensitivity\":0.25,\"seasonalAdjustment\":-0.0580788818142255,\"totalDurationNoCycle\":2884,\"cycles\":1,\"externalName\":\"Yard\",\"cycleSoak\":false},{\"id\":\"41eba2e7-380d-419c-98af-60297b1d3b95\",\"zones\":[{\"zoneId\":\"f2c1386a-87b5-4825-a175-eba5dd1b4ba3\",\"zoneNumber\":2,\"duration\":420,\"sortOrder\":1},{\"zoneId\":\"d06ffd94-31b2-47e8-98c3-e24bb9b4ed06\",\"zoneNumber\":16,\"duration\":480,\"sortOrder\":0}],\"scheduleJobTypes\":[\"INTERVAL_3\"],\"summary\":\"Every 3 days at 4:30 AM\",\"rainDelay\":true,\"waterBudget\":true,\"cycleSoakStatus\":\"OFF\",\"startDate\":1441868400000,\"name\":\"Lawn\",\"enabled\":true,\"totalDuration\":728,\"weatherIntelligenceSensitivity\":0.25,\"seasonalAdjustment\":-0.190953081814225,\"totalDurationNoCycle\":728,\"cycles\":1,\"externalName\":\"Lawn\",\"cycleSoak\":false}],\"serialNumber\":\"V17140030\",\"rainDelayExpirationDate\":1428583199883,\"rainDelayStartDate\":1428578153611,\"macAddress\":\"0C2A690922FD\",\"elevation\":113.868682861328,\"webhooks\":[],\"paused\":false,\"on\":true,\"flexScheduleRules\":[],\"utcOffset\":-25200000}],\"enabled\":true,\"roles\":[],\"managedDevices\":[],\"displayUnit\":\"US\"}";
		Map<String,List> json;
		try {
			json = this.parseText(jsonText);
			this.printJson(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void printJson(Map json) {
		String INDENT = "";
		Iterator<LinkedHashMap<String,Object>> iter = json.entrySet().iterator();
		System.out.println("==iterate result==");
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			if (value instanceof LinkedList) {
				this.printLinkedList(INDENT, key, (LinkedList) value);
			} else if (value instanceof LinkedHashMap) {
				this.printLinkedHashMap(INDENT, key, (LinkedHashMap<String,Object>) value);

			} else {
				System.out.println(INDENT + key + " : " + value);
			}
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void printLinkedList(String ind, String key, LinkedList value) {
		String indent = ind + "  ";
		ListIterator listIterator = value.listIterator();
		while (listIterator.hasNext()) {
			Object value1 = listIterator.next();
			if (value1 instanceof LinkedList)
				this.printLinkedList(indent, key, (LinkedList) value1);
			else if (value1 instanceof LinkedHashMap)
				this.printLinkedHashMap(indent, key, (LinkedHashMap<String,Object>) value1);
			else
				System.out.println(indent + "  (L)" + key + " : " + value1);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void printLinkedHashMap(String ind, String key, LinkedHashMap<String,Object> value) {
		String indent = ind + "  ";
		System.out.println(ind + key + "=>");
		Iterator iter = value.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key1 = (String) entry.getKey();
			Object value1 = entry.getValue();
			if (value1 instanceof LinkedList)
				this.printLinkedList(indent, key1, (LinkedList) value1);
			else if (value1 instanceof LinkedHashMap)
				this.printLinkedHashMap(indent, key1, (LinkedHashMap<String,Object>) value1);
			else
				System.out.println(indent + "(H)" + key1 + " : " + value1);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map fetchJsonFromURL(String requestMethod, String url) {
		URL urlObj;
		HttpURLConnection con = null;
		try {
			urlObj = new URL(url);
			con = (HttpURLConnection) urlObj.openConnection();
			con.setRequestMethod(requestMethod);
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

			Map json = this.parseText(response.toString());
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, List> parseText(String jsonText) throws ParseException {
		JSONParser parser = new JSONParser();
		ContainerFactory containerFactory = new ContainerFactory() {
			public List creatArrayContainer() {
				return new LinkedList();
			}

			public Map<String, LinkedHashMap> createObjectContainer() {
				return new LinkedHashMap<String, LinkedHashMap>();
			}
		};
		return (Map<String, List>) parser.parse(jsonText, containerFactory);
	}

}
