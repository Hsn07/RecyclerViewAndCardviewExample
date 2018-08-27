package hbacak07.example.com.recyclerviewandcardviewexample;

public class Item {

    String baslik;
    String fiyat;
    int resim;

    public Item(String baslik, String fiyat, int resim) {
        this.baslik = baslik;
        this.fiyat = fiyat;
        this.resim = resim;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public int getResim() {
        return resim;
    }

    public void setResim(int resim) {
        this.resim = resim;
    }
}
