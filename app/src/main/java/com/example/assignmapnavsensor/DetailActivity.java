package com.example.assignmapnavsensor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    //DatabaseReference databaseReference1;
   // DatabaseReference databaseReference2;
    //DatabaseReference databaseReference3;
    private List<Store> storeList;
    private List<Store> storeList1;

    private CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        databaseReference= FirebaseDatabase.getInstance().getReference("Gyroscope");
        storeList=new ArrayList<>();
        // storeList1=new ArrayList<>();

        customAdapter=new CustomAdapter(DetailActivity.this,storeList);
        // customAdapter1=new CustomAdapter(DetailActivity.this,storeList1);
        listView=findViewById(R.id.listViewId);
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                storeList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Store store=dataSnapshot1.getValue(Store.class);
                    storeList.add(store);
                }

                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


        super.onStart();
    }
}
