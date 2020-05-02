package id.ac.binus.programming.kost.user.entity;

import javax.persistence.*;

@Entity
@Table(name = "kamar_kost")
public class Kamar {
    @Id
    @Column(name = "kamarid")
    private String kamarid;

    @Column(name = "penghuni")
    private String penghuni;

    @Column(name = "harga")
    private String harga;

    @Column(name = "luaskamar")
    private String luaskamar;

    @Column(name = "kasurdanlemari")
    private Boolean kasurdanlemari;

    @Column(name = "kmdalam")
    private Boolean kmdalam;

    @Column(name = "ac")
    private Boolean ac;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EnumStatusKamar status;

    @Column(name = "no_kamar")
    private Integer no_kamar;

    @Column(name = "gambar_kamar")
    private String gambar_kamar;

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

    public Integer getNo_kamar() {
        return no_kamar;
    }

    public void setNo_kamar(Integer no_kamar) {
        this.no_kamar = no_kamar;
    }

    public String getGambar_kamar() {
        return gambar_kamar;
    }

    public void setGambar_kamar(String gambar_kamar) {
        this.gambar_kamar = gambar_kamar;
    }
}
