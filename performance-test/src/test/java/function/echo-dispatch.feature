Feature: echo dispatch

  Background:
    * url functionEchoUri

  Scenario: dispatch
   Given request '{"message":"performance-test"}'
    When method post
    Then status 200
       * print response

