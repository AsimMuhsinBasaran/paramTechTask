##### Merhabalar

#### Hazırlayan : [Asım Muhsin Başaran]()

Bu proje kapsamında hem UI test otomasyonu hem de API test otomasyonu yapıldı. Projede **_`Java 11`_** programlama dili,
UI testler için **_`SeleniumWebDriver`_**, API testler için **_`RESTful API`_** kütüphanesi ve **_`Cucumber BBD`_** framework kullanıldı .
Proje, **_`Page Object Model`_** yapısı kullanılarak oluşturuldu. Ve testler  src/test/java/runners klasörü içersinde ki runnerUI ve runnerAPI
classları kullanılarak çalışıtrılmakta.

# **UI testler kapsamında ;**

Kullanılan url : https://param.com.tr

Senaryo 1 : Kullanıcı ilgili url'e gidip rastgele bilgilerle giriş yapmaya çalışır

Beklenen Sonuç : Giriş yapılamadığını görmek

Senaryo 2 : Kullanıcı ilgili url'e giderek rastgele bilgilerle kayıt olamaya çalışır

Beklenen Sonuç : Girilen OTP mesaj yanlış olduğu için kayıt olamadığını görmek


# **API testleri kapsamında ;**

Kullanılan endpoint : https://restful-booker.herokuapp.com

**Senaryo 1 :** Kullanıcı ilgili endpoint'e bilgileri ile birlikte bir kayıt requesti atar

Beklenen Sonuç : Kaydın oluşturulması

**Senaryo 2 :** Kullanıcı ilgili endpoint'e bilgileri ile birlikte bir önce ki adımda oluşturulan kaydı kontrol etmek için requesti atar

Beklenen Sonuç : Oluşturulan kayda ait bilgiler doğru şekilde gelmeli

**Senaryo 3 :** İlgili endpoint'te kayıt silme işlemi yapılabilmesi için gereken tokeni almak için request atılır

Beklenen Sonuç : Yeni bir token elde edilmeli

**Senaryo 4 :** Kullanıcı ilgili endpoint'e bilgileri ile birlikte bir önce ki adımda oluşturulan token ile delete requesti atar

Beklenen Sonuç : 202 HTTP status code ve Created response dönmeli


![1_6DL9gdn0hFDiMrK2Fw7E1g.png](1_6DL9gdn0hFDiMrK2Fw7E1g.png) ![selenium-logo-A1B53CEFB0-seeklogo.com.png](selenium-logo-A1B53CEFB0-seeklogo.com.png)

