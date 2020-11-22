package com.example.parkingfinder;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
//import com.google.firebase.database.core.view.View;

//import com.example.parkingfinder.R;
//import com.example.parkingfinder.model.Parking;

public class ParkingAdapter extends ArrayAdapter<Parking> {
    Activity context;
    int resource;
    private DatabaseReference mDatabase;

    public ParkingAdapter(Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull final ViewGroup parent) {

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference myRef= firebaseDatabase.getReference("parkings");
        


        View custom=context.getLayoutInflater().inflate(resource,null);
        TextView txtId=custom.findViewById(R.id.txtId);
        TextView txtCapacity=custom.findViewById(R.id.txtCapacity);
        final TextView txtRemaining=custom.findViewById(R.id.txtRemaining);
        final TextView txtAvailable=custom.findViewById(R.id.txtAvailable);
        final Button btnGetIn = custom.findViewById((R.id.btnIn));
        final Button btnGetOut = custom.findViewById(R.id.btnOut);
        final Parking parking=getItem(position);
        txtId.setText("Location: "+ parking.getParkingId());
        txtCapacity.setText("Capacity: " + parking.getCapacity());
        txtRemaining.setText("Remaining: " + parking.getRemaining());
        txtAvailable.setText("Available Status: " + parking.getAvailable());
        btnGetIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int remaining = Integer.parseInt(parking.getRemaining()) -1;
                parking.setRemaining(""+remaining);
                txtRemaining.setText("Remaining: " + parking.getRemaining());
                if(Integer.parseInt(parking.getRemaining()) == 0){
                    btnGetIn.setVisibility(View.INVISIBLE);
                    btnGetOut.setVisibility(View.VISIBLE);
                    parking.setAvailable("N");
                    txtAvailable.setText("Available Status: " + parking.getAvailable());
                }
            }
        });

        btnGetOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int remaining = Integer.parseInt(parking.getRemaining()) +1;
                parking.setRemaining(""+remaining);
                txtRemaining.setText("Remaining: " + parking.getRemaining());
//                btnGetOut.setVisibility(View.INVISIBLE);
                if(Integer.parseInt(parking.getRemaining()) < Integer.parseInt(parking.getCapacity())){
                    btnGetIn.setVisibility(View.VISIBLE);
                    parking.setAvailable("Y");
                    txtAvailable.setText("Available Status: " + parking.getAvailable());
                } else if (Integer.parseInt(parking.getRemaining()) == Integer.parseInt(parking.getCapacity())){
                    btnGetOut.setVisibility(View.INVISIBLE);

                }
            }
        });
        return custom;
    }
}