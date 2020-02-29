package com.example.evote;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import androidx.appcompat.app.ActionBar;

public class DetailCalon extends AppCompatActivity {

    ImageView fotoku;
    TextView nourut, nama, prodi, angkatan, detail2, visi, misi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_calon);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled( true );
        }
        ButterKnife.bind(this);

        Calon diterima = getIntent().getParcelableExtra("kunci");

        fotoku = (ImageView) findViewById(R.id.img_view);
        nourut= (TextView) findViewById( R.id.no_urut );
        nama = (TextView) findViewById(R.id.txt_namacalon);
        prodi = (TextView) findViewById(R.id.txt_prodi);
        angkatan = (TextView) findViewById(R.id.txt_angkatan);
        detail2 = (TextView) findViewById(R.id.tv_detail_panjang);
        visi = (TextView) findViewById(R.id.tv_visi);
        misi = (TextView) findViewById(R.id.tv_misi);

        Glide.with(this).load(diterima.getFoto()).override(350, 550).into(fotoku);

        nourut.setText( diterima.getNourut());
        nama.setText(diterima.getNama());
        prodi.setText(diterima.getProdi());
        angkatan.setText(diterima.getAngkatan());
        detail2.setText(diterima.getDetail2());
        visi.setText(diterima.getVisi());
        misi.setText(diterima.getMisi());

    }
}