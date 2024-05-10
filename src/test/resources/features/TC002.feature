@APItest
Feature: Herokuapp API Test Case

  Scenario: Yeni bir kayit olusturma ve token ile olusturulan kaydi silme
    Given Yeni bir kayit olusturulur
    Then Yapilan kaydin silinmesi icin token alinir
    Then Yeni kaydin varligi kontrol edilir
    Then Yapilan kayit token kullanilarak silinir