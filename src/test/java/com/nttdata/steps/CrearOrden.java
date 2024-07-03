package com.nttdata.steps;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.greaterThan;

public class CrearOrden {

    private static final String CREATE_ORDER_URL = "https://petstore.swagger.io/v2/store/order";

    @Step("Crear una orden en PetStore con petId {0}, quantity {1}, shipDate {2}, status {3}, complete {4}")
    public void crearOrden(int petId, int quantity, String shipDate, String status, boolean complete) {
        ZonedDateTime shipDateTime = ZonedDateTime.parse(shipDate, DateTimeFormatter.ISO_DATE_TIME);

        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"petId\": " + petId + ",\n" +
                        "  \"quantity\": " + quantity + ",\n" +
                        "  \"shipDate\": \"" + shipDateTime + "\",\n" +
                        "  \"status\": \"" + status + "\",\n" +
                        "  \"complete\": " + complete + "\n" +
                        "}")
                .log().all()
                .post(CREATE_ORDER_URL)
                .then()
                .log().all();
    }

    @Step("Validar el código de respuesta {0}")
    public void validarCodigoRespuesta(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }

    @Step("Validar que el ID de la orden es mayor a 0")
    public void validarOrderIdMayorA0() {
        restAssuredThat(response -> {
            int orderId = SerenityRest.lastResponse().path("id");
            if (orderId <= 0) {
                throw new AssertionError("El ID de la orden no es mayor a 0. ID actual: " + orderId);
            }
        });
    }
}
