package com.example.parkingfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkingfinder.adapter.ParkingAdapter;
import com.example.parkingfinder.model.Parking;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ListView lvParking;
    ParkingAdapter adapter;
    int remainingnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        getParkingFromFirebase();


    }



    private void getParkingFromFirebase(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myRef= firebaseDatabase.getReference("parkings");
        adapter.clear();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dss : dataSnapshot.getChildren())
                {

                    Parking parking=dss.getValue(Parking.class);
                    String key=dss.getKey();
                    parking.setParkingId(key);
                    adapter.add(parking);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




        private void addControls() {
            lvParking=findViewById(R.id.lvParking);
            adapter = new ParkingAdapter(this,R.layout.item);
            lvParking.setAdapter(adapter);


        }

        private void addEvent(){
        lvParking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Parking parking=adapter.getItem(position);
                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                DatabaseReference myRef= firebaseDatabase.getReference("parkings");
                if (position == 0) {

                } else if (position ==1){

                } else if (position ==2){

                } else {

                }
            }
        });
        }

        public void Decrement(View view){
            RelativeLayout parentRow = (RelativeLayout) view.getParent();
            TextView remainingView = (TextView) parentRow.findViewById(R.id.txtRemaining);
            TextView availableView = (TextView) parentRow.findViewById(R.id.txtAvailable);

            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
            DatabaseReference myRef= firebaseDatabase.getReference("parkings");
            String quantityString = remainingView.getText().toString();
            remainingnum = Integer.parseInt(quantityString);
            remainingnum -= 1;

            if (remainingnum == 0) {

                Toast.makeText(MainActivity.this, "Can not get in",
                        Toast.LENGTH_SHORT).show();}
                availableView.setText("N");

        }


}


