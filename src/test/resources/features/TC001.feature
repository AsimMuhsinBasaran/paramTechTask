@prmUI
Feature: Param Tech Otomasyon Case

  Scenario: Anasayfaya gidilir ve yanlis bilgilerle giris yapilmadigi dogrulanir
    Given Param anasayfaya gidilir
    Then  Giris yap butonu tiklanir
    And   Kurumsal giris butonuna tiklanir
    Then  GSM,TCKN ya da Kart Numarasi ve password alanina rastgele degerler girilir
    And   Login butonuna tiklanir
    Then  Giris yapilmadigi dogrulanir


  Scenario: Kayit ekranina gidilir kayit olunur OTP hatasi dogrulanir
    Given Param anasayfaya gidilir
    Then Giris yap butonu tiklanir
    And Kurumsal giris butonuna tiklanir
    Then Hesap olustur butonuna tiklanir
    And Ad,soyad,eposta,GSM doldurulur
    Then Checkboxlar isaretlenir ve devam butonuna tiklanir
    And Onay kodu kismina 6 haneli random bir sayi girilir ve onayla butonu tiklanir
    Then Kayit olunamadigi dogrulanir

