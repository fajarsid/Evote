package com.example.evote.Home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.evote.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeUser extends AppCompatActivity {
    private RecyclerView home_listview;
    private List<ModelCalon> calon_model;
    private CalonAdapter calonAdapter;
    private FirebaseFirestore db;
    public String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_user );

        calon_model   = new ArrayList<>();
        home_listview = this.findViewById( R.id.home_listview );
        calonAdapter  = new CalonAdapter(calon_model);
        home_listview.setLayoutManager( new LinearLayoutManager( this ) );
        home_listview.setAdapter( calonAdapter );



        db = FirebaseFirestore.getInstance( );
        db.collection( "calon" ).addSnapshotListener( new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange doc:queryDocumentSnapshots.getDocumentChanges()){
                    if (doc.getType()== DocumentChange.Type.ADDED){
                        ModelCalon modelCalon = doc.getDocument().toObject( ModelCalon.class );
                        calon_model.add( modelCalon );

                        calonAdapter.notifyDataSetChanged();
                    }
                }
            }
        } );
    }
}
