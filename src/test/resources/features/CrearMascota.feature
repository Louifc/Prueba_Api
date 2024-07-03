Feature: Crear Mascota PetStore

  @test1
  Scenario: Crear mascota

    When creo la mascota con nombre "doggie", status "available"
    Then el código de respuesta es 200
    And el status es "available"
