package com.example.myproject.Model;

public class CardViewModel {
    String sMaGV;
    String sHoTenGV;
    String sSDTGV;

    public CardViewModel(String sMaGV, String sHoTenGV, String sSDTGV) {
        this.sMaGV = sMaGV;
        this.sHoTenGV = sHoTenGV;
        this.sSDTGV = sSDTGV;
    }

    public String getsMaGV() {
        return sMaGV;
    }

    public void setsMaGV(String sMaGV) {
        this.sMaGV = sMaGV;
    }

    public String getsHoTenGV() {
        return sHoTenGV;
    }

    public void setsHoTenGV(String sHoTenGV) {
        this.sHoTenGV = sHoTenGV;
    }

    public String getsSDTGV() {
        return sSDTGV;
    }

    public void setsSDTGV(String sSDTGV) {
        this.sSDTGV = sSDTGV;
    }
}
