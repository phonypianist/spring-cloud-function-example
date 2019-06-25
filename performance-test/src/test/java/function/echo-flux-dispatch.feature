Feature: echo dispatchFlux

  Background:
    * url functionEchoFluxUri

  Scenario: dispatchFlux
   Given request '{"message":"performance-test"}'
    When method post
    Then status 200
       * print response

