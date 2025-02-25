# Where Can I Watch

**Where Can I Watch**, bir **Compose Multiplatform** projesidir. Tek kod tabanıyla **Android**, **iOS** ve **Desktop** platformlarında çalışacak şekilde tasarlanmıştır. Uygulama, kullanıcıların aradıkları film veya dizinin **hangi abonelik platformlarında** mevcut olduğunu gösterir. Ayrıca **ana sayfada** çeşitli film/dizi önerileri sunar. Bu proje **deneysel** amaçla hazırlanmıştır.

> **Not:** iOS sürümü teorik olarak desteklense de, Mac ortamım olmadığı için **test edilememiştir**. **Android** ve **Desktop** sürümleri ise sorunsuz çalışmaktadır.
---

## Uygulama Ekran Görüntüleri (GIF)

### Android Kullanımı
![android-gif](https://github.com/user-attachments/assets/ce2e4e79-68b4-4f64-89a4-60c8615b3e2d)


### Desktop Kullanımı
![desktop-gif-2](https://github.com/user-attachments/assets/b1c4e6f7-460f-4a0a-a8b5-d362aab286c3)

---

## Özellikler

1. **Kapsamlı Platform Desteği**  
   - **Android**: Dinamik tema (Dynamic Color) desteği ve **ModalNavigationDrawer** kullanımı.  
   - **Desktop**: Büyük ekranlar için **dismissibleNavigationDrawer**, bir **LazyColumn** için kaydırma çubuğu (scrollbar) ve **LazyRow** için sağ/sol **kaydırma okları** (Netflix benzeri yatay liste).  
   - **iOS**: Compose Multiplatform’un iOS desteği teorik olarak mevcut, ancak test edilmemiştir.

2. **API Entegrasyonu**  
   - [`Streaming Availability`](https://rapidapi.com/movie-of-the-night-movie-of-the-night-default/api/streaming-availability)  
   - Kullanıcı, film veya dizi araması yaptığında bu API üzerinden **hangi platformlarda** (Netflix, Amazon Prime, vb.) bulunduğu gösterilir.  
   - Ana sayfada rastgele **öneriler** sunulur.

3. **Kullanılan Teknolojiler**  
   - **Ktor Client**: HTTP istekleri ve veri alma.  
   - **Koin**: Dependency Injection yönetimi.  
   - **Coil**: Görsel yükleme ve önbellekleme.  
   - **Material 3**: Güncel tasarım yönergelerine uygun arayüz bileşenleri.  
   - **Coroutines & Flow**: Asenkron veri işleme ve reaktif akışlar.  

4. **Deneysel Proje**  
   - Kod ve tasarım **deneysel** amaçla oluşturulmuştur.  
   - Geliştirmeye ve iyileştirmeye açıktır.



