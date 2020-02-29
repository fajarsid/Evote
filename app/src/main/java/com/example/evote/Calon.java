package com.example.evote;

import android.os.Parcel;
import android.os.Parcelable;

public class Calon implements Parcelable{

    private String nourut, nama, prodi, angkatan, foto, detail2, visi, misi;

    protected Calon(Parcel in) {
        nourut=in.readString();
        nama = in.readString();
        prodi = in.readString();
        angkatan = in.readString();
        foto = in.readString();
        detail2 = in.readString();
        visi = in.readString();
        misi = in.readString();

    }

    public static final Creator<Calon> CREATOR = new Creator<Calon>() {
        @Override
        public Calon createFromParcel(Parcel in) {
            return new Calon(in);
        }

        @Override
        public Calon[] newArray(int size) {
            return new Calon[size];
        }
    };

    public Calon() {

    }
    public String getNourut() {

        return nourut;
    }

    public void setNourut(String nourut) {
        this.nourut = nourut;
    }

    public String getNama() {

        return nama;
    }

    public void setName(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }
    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDetail2() {
        return detail2;
    }

    public void setDetail2(String detail2) {
        this.detail2 = detail2;
    }

    public String getVisi() {
        return visi;
    }

    public void setVisi(String visi) {
        this.visi = visi;
    }

    public String getMisi() {
        return misi;
    }

    public void setMisi(String misi) {
        this.misi = misi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString( this.nourut );
        parcel.writeString(this.nama);
        parcel.writeString(this.prodi );
        parcel.writeString(this.angkatan );
        parcel.writeString(this.foto);
        parcel.writeString(this.detail2);
        parcel.writeString(this.visi);
        parcel.writeString(this.misi);

    }
}