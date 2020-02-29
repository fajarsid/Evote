package com.example.evote;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListCalonAdapter extends RecyclerView.Adapter<ListCalonAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<Calon> listCalon;
    private OnClick onClick;

    public ListCalonAdapter(Context context, OnClick onClick) {
        this.context = context;
        this.onClick = onClick;
    }


    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.page_user, viewGroup, false);
        CardViewViewHolder viewHolder = new CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder cardViewViewHolder, int i) {

        Calon c = getListCalon().get(i);
        cardViewViewHolder.tampil(c, onClick);

        cardViewViewHolder.btnVote.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {

            @Override
            public void onItemClicked(View v, int position) {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder( context );
                View view;
                view = LayoutInflater.from(context).inflate( R.layout.alert_dialog, null );
                TextView judul = (TextView) view.findViewById( R.id.judul );
                builder.setPositiveButton( "Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Berhasil Vote  " + getListCalon().get(position).getNama(), Toast.LENGTH_SHORT).show();
                    }
                } );
                builder.setNegativeButton( "Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText( context, "Never Mind !", Toast.LENGTH_SHORT ).show();

                    }
                } );

                builder.setView( view );
                builder.show();

            }
        }));

    }

    @Override
    public int getItemCount() {
        return getListCalon().size();
    }

    public ArrayList<Calon> getListCalon() {
        return listCalon;
    }

    public void setListCalon(ArrayList<Calon> listCalon) {
        this.listCalon = listCalon;
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoto;
        TextView  tvNourut, tvNamaCalon,tvProdi,tvAngkatan;
        Button btnVote, btnDetail;
        Calon calon;


        public CardViewViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.img_view);
            tvNourut = (TextView) itemView.findViewById( R.id.no_urut );
            tvNamaCalon = (TextView) itemView.findViewById(R.id.txt_namacalon);
            tvProdi = (TextView) itemView.findViewById(R.id.txt_prodi);
            tvAngkatan = (TextView) itemView.findViewById(R.id.txt_angkatan);
            btnVote = (Button) itemView.findViewById(R.id.btn_vote);
            btnDetail = (Button) itemView.findViewById(R.id.btn_detail);

        }

        public void tampil(final Calon calon, final OnClick OnItemClick) {

            Glide.with(context)
                    .load(calon.getFoto())
                    .override(350, 550)

                    .into(imgFoto);

            tvNourut.setText( calon.getNourut());
            tvNamaCalon.setText(calon.getNama());
            tvProdi.setText(calon.getProdi());
            tvAngkatan.setText(calon.getAngkatan());

            btnDetail.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnItemClick.onClick( calon );
                }
            } );
        }
    }

    public interface OnClick {
        void onClick(Object calon);
    }
}