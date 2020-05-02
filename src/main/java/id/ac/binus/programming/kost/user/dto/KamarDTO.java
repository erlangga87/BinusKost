package id.ac.binus.programming.kost.user.dto;

import id.ac.binus.programming.kost.user.entity.EnumStatusKamar;

public class KamarDTO {
    private String kamarid;
    private String penghuni;
    private String harga;
    private String luaskamar;
    private Boolean kasurdanlemari;
    private Boolean kmdalam;
    private Boolean ac;
    private EnumStatusKamar status;

    public String getKamarid() {
        return kamarid;
    }

    public void setKamarid(String kamarid) {
        this.kamarid = kamarid;
    }

    public String getPenghuni() {
        return penghuni;
    }

    public void setPenghuni(String penghuni) {
        this.penghuni = penghuni;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getLuaskamar() {
        return luaskamar;
    }

    public void setLuaskamar(String luaskamar) {
        this.luaskamar = luaskamar;
    }

    public Boolean getKasurdanlemari() {
        return kasurdanlemari;
    }

    public void setKasurdanlemari(Boolean kasurdanlemari) {
        this.kasurdanlemari = kasurdanlemari;
    }

    public Boolean getKmdalam() {
        return kmdalam;
    }

    public void setKmdalam(Boolean kmdalam) {
        this.kmdalam = kmdalam;
    }

    public Boolean getAc() {
        return ac;
    }

    public void setAc(Boolean ac) {
        this.ac = ac;
    }

    public EnumStatusKamar getStatus() {
        return status;
    }

    public void setStatus(EnumStatusKamar status) {
        this.status = status;
    }
}
