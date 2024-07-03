package com.nttdata.glue;

import com.nttdata.steps.CrearOrden;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class CrearOrdenStepsDefs {

    @Steps
    CrearOrden crearOrden;

    @When("creo una orden con petId {int}, quantity {int}, shipDate {string}, status {string}, complete {string}")
    public void creoUnaOrdenConParametros(int petId, int quantity, String shipDate, String status, String complete) {
        boolean completeValue = Boolean.parseBoolean(complete);
        crearOrden.crearOrden(petId, quantity, shipDate, status, completeValue);
    }

    @Then("el código de respuesta es {int}")
    public void elCodigoDeRespuestaEs(int statusCode) {
        crearOrden.validarCodigoRespuesta(statusCode);
    }

    @Then("el ID de la orden es mayor a 0")
    public void elIDDeLaOrdenEsMayorA0() {
        crearOrden.validarOrderIdMayorA0();
    }
}
