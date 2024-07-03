package com.nttdata.steps;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class CrearMascota {

    private static String CREATE_PET = "https://petstore.swagger.io/v2/pet";

    @Step("Crear mascota {0} en PetStore")
    public void crearMascota(String nombre, String status) {
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"name\": \"" + nombre + "\",\n" +
                        "  \"status\": \"" + status + "\"\n" +
                        "}")
                .log().all()
                .post(CREATE_PET)
                .then()
                .log().all();
    }

    public void validarStatus(String status) {
        restAssuredThat(response -> response.body("'status'", equalTo(status)));
        System.out.println("Status: " + SerenityRest.lastResponse().body().path("status").toString());
        System.out.println(SerenityRest.lastResponse().print());
    }

    public void validarCodigoRespuesta(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }
}
