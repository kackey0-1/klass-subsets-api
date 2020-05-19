package no.ssb.subsetsservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SubsetsServiceApplicationTests {

	String ldsURL = SubsetsController.LDS_LOCAL;

	@Test
	void postToLDSLocal() {
		System.out.println("TESTING POST SUBSET BY ID '1' TO LDS LOCAL INSTANCE");
		try {
			String filename = "subset1.json";
			String path = new File("").getAbsolutePath();
			File myObj = new File(path +"\\src\\test\\java\\no\\ssb\\subsetsservice\\"+filename);
			Scanner myReader = new Scanner(myObj);
			StringBuilder sb = new StringBuilder();
			while (myReader.hasNextLine()) {
				sb.append(myReader.nextLine());
			}
			myReader.close();
			String subsetJSON = sb.toString();
			System.out.println(subsetJSON);
			ResponseEntity<String> response = SubsetsController.putTo(ldsURL, "/1", subsetJSON);

			System.out.println("RESPONSE HEADERS:");
			System.out.println(response.getHeaders());
			System.out.println("RESPONSE BODY");
			System.out.println(response.getBody());

			assertEquals(response.getStatusCodeValue(), 201);
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	@Test
	void getFromLDSlocal() {
		System.out.println("TESTING GET SUBSET BY ID 1 FROM LDS LOCAL INSTANCE");
		ResponseEntity<String> response = SubsetsController.getFrom(ldsURL, "/1");

		System.out.println("GET "+ldsURL+"/1");
		System.out.println("RESPONSE HEADERS:");
		System.out.println(response.getHeaders());
		System.out.println("RESPONSE BODY");

		System.out.println(response.getBody());
		assertEquals(response.getStatusCodeValue(), 200);
	}

	@Test
	void getAllFromLDSlocal() {
		System.out.println("TESTING GET ALL SUBSETS FROM LDS LOCAL INSTANCE");
		ResponseEntity<String> response = SubsetsController.getFrom(ldsURL, "");
		System.out.println("GET "+ldsURL);
		System.out.println("RESPONSE HEADERS:");
		System.out.println(response.getHeaders());
		System.out.println("RESPONSE BODY");
		System.out.println(response.getBody());
		assertEquals(response.getStatusCodeValue(), 200);
	}

}