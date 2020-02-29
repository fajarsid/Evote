package com.example.evote;

import java.util.ArrayList;

public class CalonBEM  {
    public static String[][] data = new String[][]{
            {
                    "CANDIDAT 1",
                    "FAJAR SIDIK SUGANDA",
                    "Teknik Informatika",
                    "2015",
                    "https://firebasestorage.googleapis.com/v0/b/evote-3eb91.appspot.com/o/IMG_20200118_075650.jpg?alt=media&token=ccde6245-0a43-44b2-bd17-590ca2a0cb84",
                    "Siap Jungkir Balik Untuk Nusa Putra.",
                    "Menjemput anggota yang tertinggal ",
                    "Memajukan undur-undur"
            },

            {
                    "CANDIDAT 2",
                    "SIDIK SUGANDA",
                    "Teknik Sipil",
                    "2015",
                    "https://firebasestorage.googleapis.com/v0/b/evote-3eb91.appspot.com/o/fajar.jpg?alt=media&token=090508b5-5fdc-4e22-9285-84316fa9f9a8",
                    "Siap Jungkir Balik Untuk Nusa Putra.",
                    "Menjemput anggota yang tertinggal ",
                    "Memajukan undur-undur Memajukan undur-undurMemajukan undur-undur Memajukan undur-undur Memajukan undur-undur Memajukan undur-undur Memajukan undur-undur Memajukan undur-undur Memajukan undur-undur Memajukan undur-undur"
            }

    };

    public static ArrayList<Calon> getListData() {
        Calon calon = null;
        ArrayList<Calon> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            calon = new Calon();
            calon.setNourut(data[i][0]);
            calon.setName(data[i][1]);
            calon.setProdi(data[i][2]);
            calon.setAngkatan(data[i][3]);
            calon.setFoto(data[i][4]);
            calon.setDetail2(data[i][5]);
            calon.setVisi(data[i][6]);
            calon.setMisi(data[i][7]);

            list.add(calon);

        }
        return list;

    }
}