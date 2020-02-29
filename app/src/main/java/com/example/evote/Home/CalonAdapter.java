package com.example.evote.Home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evote.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalonAdapter extends RecyclerView.Adapter<CalonAdapter.ViewHolder> {

    public List<ModelCalon> model_calon;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    public String TAG;

    public CalonAdapter(List<ModelCalon> model_calon){
        this.model_calon = model_calon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calon_item, parent, false);
        Button button = view.findViewById( R.id.btn_v );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            mAuth = FirebaseAuth.getInstance();
            String userId = mAuth.getCurrentUser().getUid();
            db = FirebaseFirestore.getInstance();
            Object norut = 1;
            String idBtn = button.getText().toString();

            db.collection( "calon" ).document(userId).get().addOnSuccessListener( new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    String btnId = documentSnapshot.getString("nourut"  );
                    if (idBtn.equals( norut )){
                        System.out.println(idBtn);

                    }else {
                        System.out.println("lainnya");
                    }
                }
            } );



//            db.collection( "users" ).document(userId).get().addOnSuccessListener( new OnSuccessListener<DocumentSnapshot>() {
//                @Override
//                public void onSuccess(DocumentSnapshot documentSnapshot) {
//                    Map<String,Object> vote = new HashMap<>( );
//                    vote.put( "nama", "fajar");
//                    vote.put( "nim", documentSnapshot.getString( "nim" ) );
//                    vote.put( "id", userId );
//
//                    db = FirebaseFirestore.getInstance( );
//                    db.collection( "votes" ).add( vote ).addOnSuccessListener( new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                            Log.d( TAG,"id:"+ documentReference.getId() );
//                        }
//                    } ).addOnFailureListener( new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.w( TAG,"error",e );
//                        }
//                    } );
//                }
//            } );
            }
        } );
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String desc_data = model_calon.get( position ).getDetail2();
        String nama_data = model_calon.get( position ).getNourut();
        String tombol_data = model_calon.get( position ).getNourut();
        holder.setDescView(desc_data);
        holder.setNamaview( nama_data );
        holder.setTombol( tombol_data );

    }

    @Override
    public int getItemCount() {
        return model_calon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Button setTombol;
        private TextView descView;
        private TextView namaview;
        private Button tombolview;
        private View mviews;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            mviews = itemView;

        }
        public void setDescView(String desText){
            descView = mviews.findViewById( R.id.blog_desc );
            descView.setText( desText );
        }
        public void setNamaview(String namaText){
            namaview = mviews.findViewById( R.id.blog_user_name );
            namaview.setText( namaText );
        }
        public void setTombol(String tombolText){
            tombolview = mviews.findViewById( R.id.btn_v );
            tombolview.setText(tombolText);
        }
    }
}
