Feature: Automatizar Servicio de Store de PetStore

  @test1
  Scenario Outline: Creación de Order en PetStore
    When creo una orden con petId <petId>, quantity <quantity>, shipDate "<shipDate>", status "<status>", complete "<complete>"
    Then el código de respuesta es <statusCode>
    And el ID de la orden es mayor a 0

    Examples:
      | petId | quantity | shipDate                  | status   | complete | statusCode |
      | 1     | 3        | 2024-07-03T04:31:10.853Z  | placed   | true     | 200        |
      | 2     | 5        | 2024-08-01T12:00:00.000Z  | delivered| false    | 200        |



  @test2
  Scenario Outline: Consulta de Order existente
    Given que tengo una orden creada con ID {int}
    When consulto la orden con ID {int}
    Then el código de respuesta es {int}
    And la orden contiene la siguiente información:
      | ID       | {int}    |
      | petId    | {int}    |
      | quantity | {int}    |
      | shipDate | "{string}" |
      | status   | "{string}" |
      | complete | "{string}" |
