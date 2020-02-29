package com.example.evote;


import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.evote.menu.DrawerAdapter;
import com.example.evote.menu.DrawerItem;
import com.example.evote.menu.SimpleItem;
import com.example.evote.menu.SpaceItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import butterknife.ButterKnife;

public class MainUser extends AppCompatActivity implements TabLayout.OnTabSelectedListener, DrawerAdapter.OnItemSelectedListener {

    TextView profil;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    String userId;

    private SlidingRootNav slidingRootNav;
    private static final int POS_DASHBOARD = 0;
    private static final int POS_CART      = 1;
    private static final int POS_ACCOUNT   = 2;
    private static final int POS_MESSAGES  = 3;
    private static final int POS_LOGOUT    = 5;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private RecyclerView recyclerView;
    private ArrayList<Calon> list;
    List<Calon> calonpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_user );
        ButterKnife.bind( this );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        recyclerView = (RecyclerView) findViewById( R.id.rv_category );
        recyclerView.setHasFixedSize( true );

        list = new ArrayList<>();
        list.addAll( CalonBEM.getListData() );

        showRecyclerList();

        slidingRootNav = new SlidingRootNavBuilder( this )
                .withToolbarMenuToggle( toolbar )
                .withMenuOpened( false )
                .withContentClickableWhenMenuOpened( true )
                .withSavedState( savedInstanceState )
                .withMenuLayout( R.layout.menuprofil )
                .inject();

        screenIcons  = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter drawadapter = new DrawerAdapter( Arrays.asList(
                createItemFor( POS_DASHBOARD ).setChecked( true ),
                createItemFor( POS_CART ),
                createItemFor( POS_ACCOUNT ),
                createItemFor( POS_MESSAGES ),
                new SpaceItem( 48 ),
                createItemFor( POS_LOGOUT ) ) );
        drawadapter.setListener( this );

        RecyclerView list = findViewById( R.id.listmenu );
        list.setNestedScrollingEnabled( false );
        list.setLayoutManager( new LinearLayoutManager( this ) );
        list.setAdapter( drawadapter );

        drawadapter.setSelected( POS_DASHBOARD );
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        nama   = findViewById( R.id.tvFullName );
        profil   = findViewById( R.id.tvprofil );
//        prodi  = findViewById( R.id.tvprodi );

        db  = FirebaseFirestore.getInstance( );
        firebaseAuth = FirebaseAuth.getInstance( );
        userId = firebaseAuth.getCurrentUser().getUid();


        DocumentReference user = db.collection("users").document("profil");

        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override

            public void onComplete(@NonNull Task< DocumentSnapshot > task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot doc = task.getResult();

                    StringBuilder fields = new StringBuilder("");

                    fields.append("").append(doc.get("nama"));

                    fields.append("\n").append(doc.get("nim"));

                    fields.append("\n").append(doc.get("prodi"));

//                    nama.setText(fields.toString());
                    profil.setText( fields.toString() );
//                    prodi.setText( fields.toString() );

                }

            }

        })

                .addOnFailureListener(new OnFailureListener() {

                    @Override

                    public void onFailure(@NonNull Exception e) {

                    }

                });
//        DatabaseReference databaseReference = db.getReference(firebaseAuth.getUid());
//
//
//        databaseReference.addValueEventListener( new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                UserAdapter userProfile = dataSnapshot.getValue(UserAdapter.class);
//                nama.setText( "Nama :" + userProfile.getNama() );
//                nim2.setText( "NIM : " + userProfile.getNIM() );
//                prodi.setText( "Prodi : " + userProfile.getProdi() );
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        } )

    }
            @Override
    public void onItemSelected(int position) {
        if (position == POS_LOGOUT) {
            finish();
        }

        if (position == POS_CART

        ) {
            Intent i =new Intent(getApplication(), Hasil.class);
            startActivity(i);
        }

        if (position == POS_ACCOUNT

        ) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            About newFragment = new About();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
        }

        if (position == POS_MESSAGES

        ) {
            Intent sendIntent = new Intent();
            sendIntent.setAction( Intent.ACTION_SEND );
            sendIntent.putExtra( Intent.EXTRA_TEXT, " Click to download Colors Soda app from wwww. " );
            sendIntent.putExtra( android.content.Intent.EXTRA_SUBJECT, "G E E N  B O X" );
            sendIntent.setType( "text/plain" );
            startActivity( sendIntent );

        }

        slidingRootNav.closeMenu();
        // Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
        // showFragment(selectedScreen);
    }


    private DrawerItem createItemFor(int position) {
        return new SimpleItem( screenIcons[position], screenTitles[position] )
                .withIconTint( color( R.color.textColorSecondary ) )
                .withTextTint( color( R.color.textColorSecondary ) )
                .withSelectedIconTint( color( R.color.navfun ) )
                .withSelectedTextTint( color( R.color.navfun ) );
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray( R.array.ld_activityScreenTitles );
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray( R.array.ld_activityScreenIcons );
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId( i, 0 );
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable( this, id );
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor( this, res );
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    private void showRecyclerList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListCalonAdapter listCalonAdapter = new ListCalonAdapter(this, new ListCalonAdapter.OnClick() {
            @Override
            public void onClick(Object calon) {
                Calon calon1 = (Calon) calon;
                Intent intent = new Intent(MainUser.this, DetailCalon.class);
                intent.putExtra("kunci", calon1);
                startActivity(intent);
            }
        });
        listCalonAdapter.setListCalon(list);
        recyclerView.setAdapter(listCalonAdapter);

    }
}


