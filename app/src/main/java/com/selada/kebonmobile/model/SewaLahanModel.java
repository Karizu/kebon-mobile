package com.selada.kebonmobile.model;

public class SewaLahanModel {
    private String id;
    private String namaLahan;
    private String jumlahKavling;
    private String lamaSewa;
    private String harga;
    private String totalHarga;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaLahan() {
        return namaLahan;
    }

    public void setNamaLahan(String namaLahan) {
        this.namaLahan = namaLahan;
    }

    public String getJumlahKavling() {
        return jumlahKavling;
    }

    public void setJumlahKavling(String jumlahKavling) {
        this.jumlahKavling = jumlahKavling;
    }

    public String getLamaSewa() {
        return lamaSewa;
    }

    public void setLamaSewa(String lamaSewa) {
        this.lamaSewa = lamaSewa;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }
}
