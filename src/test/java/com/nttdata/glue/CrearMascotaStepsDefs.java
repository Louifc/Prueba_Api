package com.nttdata.glue;

import com.nttdata.steps.CrearMascota;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class CrearMascotaStepsDefs {

    @Steps
    CrearMascota crearMascota;

    @When("creo la mascota con nombre {string}, status {string}")
    public void crearMascota(String nombre, String status) {
        crearMascota.crearMascota(nombre, status);
    }

    @Then("el código de respuesta es {int}")
    public void elCódigoDeRespuestaEs(int statusCode) {
        crearMascota.validarCodigoRespuesta(statusCode);
    }

    @And("el status es {string}")
    public void elStatusEs(String status) {
        crearMascota.validarStatus(status);
    }
}
