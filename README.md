Sipariş Yönetimi API
Bu proje, Spring Boot 3.x, Spring Data JPA ve PostgreSQL kullanılarak geliştirilmiş, Temel Müşteri, Ürün ve Sipariş yönetimi işlevlerini sunan bir RESTful API uygulamasıdır. Özellikle One-to-Many ve Many-to-Many ilişkilerinin DTO (Data Transfer Object) katmanında nasıl yönetileceğini göstermektedir.

🌟 Özellikler
Veri Yönetimi: Müşteri, Ürün ve Sipariş varlıkları için CRUD (Create, Read, Update, Delete) operasyonları.

İlişkisel Yapı:
Customer ↔ Order (One-to-Many)
Order ↔ Product (Many-to-Many)
DTO Kullanımı: Veri tutarlılığını sağlamak ve sonsuz özyinelemeli (recursive) döngüleri engellemek için Entity'den DTO'ya dönüşüm.
Veritabanı: PostgreSQL.

⚙️ Gereksinimler
Java Development Kit (JDK) 17 veya üzeri
Maven
PostgreSQL Veritabanı
Postman (veya benzeri bir HTTP istemcisi)

🛠️ Kurulum ve Çalıştırma
1. Veritabanı Ayarları
Proje, PostgreSQL kullanır. application.properties dosyasını kendi veritabanı ayarlarınıza göre güncelleyin.

src/main/resources/application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.jpa.properties.hibernate.default_schema=order_api
spring.datasource.username=postgres
spring.datasource.password=***

spring.jpa.hibernate.ddl-auto=update
# ...

2. Projeyi Çalıştırma
Terminali projenin ana dizininde (siparis-yonetimi-api) açın ve aşağıdaki komutu çalıştırın:

Bash
# Maven Wrapper kullanarak çalıştırma
./mvnw spring-boot:run
Uygulama varsayılan olarak 8080 portunda çalışmaya başlayacaktır.

🗺️ API Endpoint'leri
Tüm endpoint'ler http://localhost:8080/rest/api/ temel adresiyle başlar.

Müşteri (Customer) Yönetimi - /rest/api/customer
HTTP Metot,Endpoint,Açıklama,JSON (POST/PUT Body Örneği)
GET,/customer/list,Tüm müşterileri ve varsa sığ sipariş listelerini getirir.,-
GET,/customer/list/{id},ID ile Müşteri detayını getirir.,-
POST,/customer,Yeni Müşteri kaydeder.,"{""firstName"": ""Ahmet"", ""lastName"": ""Erdogdu"", ""eMail"": ""a@example.com""}"
PUT,/customer/{id},Müşteri bilgilerini günceller.,"{""firstName"": ""YeniIsim"", ""lastName"": ""YeniSoyisim"", ""eMail"": ""new@mail.com""}"
DELETE,/customer/{id},Müşteriyi siler.,-

Sipariş (Order) Yönetimi - /rest/api/order
NOT: Sipariş oluşturulurken (POST), body'de belirtilen customerId ve productIds veritabanında mevcut olmalıdır.
HTTP Metot,Endpoint,Açıklama,JSON (POST/PATCH Body Örneği)
GET,/order/list,"Tüm siparişleri, ilişkili Müşteri ve Ürün detaylarıyla listeler.",-
GET,/order/list/{id},ID ile Sipariş detaylarını getirir.,-
POST,/order,Yeni bir sipariş oluşturur.,"{""customerId"": 1, ""status"": ""Pending"", ""productIds"": [1, 2]}"
PATCH,/order/{id}/status?status={yeni_durum},Siparişin sadece durumunu (status) günceller.,Query Param: status=Shipped
DELETE,/order/{id},Siparişi siler.,-

Ürün (Product) Yönetimi - /rest/api/product
HTTP Metot,Endpoint,Açıklama,JSON (POST/PUT Body Örneği)
GET,/product/list,Tüm ürünleri listeler.,-
GET,/product/list/{id},ID ile Ürün detayını getirir.,-
POST,/product,Yeni Ürün kaydeder.,"{""name"": ""Java Kitabı"", ""price"": 199.90, ""stock"": 50}"
PUT,/product/{id},Ürün bilgilerini günceller.,"{""name"": ""Spring Kitabı"", ""price"": 249.90, ""stock"": 45}"
DELETE,/product/{id},Ürünü siler.,-






















