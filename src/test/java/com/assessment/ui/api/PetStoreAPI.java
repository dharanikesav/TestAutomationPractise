package com.assessment.ui.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

@RunWith(Parameterized.class)
public class PetStoreAPI {
    private final String petName;
    private final String imageURL;
    private int statusCode;
    private final String status;
    private final String baseURL;
    private final RequestSpecification request = given().headers(Map.of ("api_key", "1234567", "Content-type", "application/json"));


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "doggie", "sample.png", 200, "available", "https://petstore.swagger.io/v2"},
                { "rabbit", "'.$_GET['id'].'", 200, "\\$@", "https://petstore.swagger.io/v2"},
                { "rabbit", "'.$_GET['id'].'", 301, "\\$@", "http://petstore.swagger.io/v2"},
                { "rabbit", "'.$_GET['id'].'", 404, "\\$@", "https://petstore.swagger.io/v1"}
        });
    }

    public PetStoreAPI(String petName, String imageURL, int statusCode, String status, String baseURL) {
            this.petName = petName;
            this.imageURL = imageURL;
            this.statusCode = statusCode;
            this.status = status;
            this.baseURL = baseURL;

        }

    private Map<String, Object> buildBasicRequestBody() {
        Map<String, Object> request_body = new HashMap<>();
        request_body.put("photoUrls", List.of(imageURL));
        request_body.put("name", petName);
        request_body.put("status", status);
        return request_body;
    }

    private Response addPetTest(Map<String, Object> request_body) {
        Response addPetResponse =  request.baseUri(baseURL).body(request_body).post("/pet");
        System.out.println("************ Add Pet **********");
        System.out.println(addPetResponse.asString());
        System.out.println(addPetResponse.statusCode());
        if (statusCode / 100 == 2) {
            addPetResponse.then().assertThat()
                    .statusCode(statusCode)
                    .body("name", equalTo(petName))
                    .body("photoUrls", hasItems(imageURL));
        } else {
            addPetResponse.then().assertThat().statusCode(statusCode);
        }
        return addPetResponse;
    }

    private void updatePetTest(Map<String, Object> request_body, long id) {
        request_body.put("id", id);
        request_body.put("name", petName + "_updated");
        System.out.println(request_body);
        Response updateResponse = request.body(request_body).put("/pet");
        System.out.println("\n\n\n************ update Pet **********");
        System.out.println(updateResponse.asString());
        System.out.println(updateResponse.statusCode());

        if (statusCode / 100 == 2) {
            updateResponse.then().assertThat()
                    .statusCode(statusCode)
                    .body("name", equalTo(petName + "_updated"))
                    .body("id", equalTo(id));
        } else {
            updateResponse.then().assertThat().statusCode(statusCode);
        }
    }

    @Test
    public void addUpdateDeletePet() {
        Map<String, Object> request_body = buildBasicRequestBody();
        Response addPetResponse =  addPetTest(request_body);

        if(statusCode == 200) {
            long id = addPetResponse.jsonPath().getLong("id");
            updatePetTest(request_body, id);
            deletePetTest(addPetResponse);
        }

    }

    private void deletePetTest(Response response) {
        String id;
        if(petName.equalsIgnoreCase("rabbit")) {
            id = String.valueOf(response.jsonPath().getLong("id"));
        } else {
            id = "123_456";
            statusCode = 404;
        }

        Response deleteResponse = request.delete("/pet/" + id);
        System.out.println("\n\n\n************ delete Pet **********");
        System.out.println(deleteResponse.asString());
        System.out.println(deleteResponse.statusCode());
        deleteResponse.then().assertThat().statusCode(statusCode);
    }
}
