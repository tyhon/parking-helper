package com.example.parkingfinder.adapter;

import android.widget.ArrayAdapter;

import com.example.parkingfinder.R;
import com.example.parkingfinder.model.Parking;
import android.app.Activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ParkingAdapter extends ArrayAdapter<Parking> {
    Activity context;
    int resource;
    public ParkingAdapter(Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View custom=context.getLayoutInflater().inflate(resource,null);
        TextView txtId=custom.findViewById(R.id.txtId);
        TextView txtCapacity=custom.findViewById(R.id.txtCapacity);
        TextView txtRemaining=custom.findViewById(R.id.txtRemaining);
        TextView txtAvailable=custom.findViewById(R.id.txtAvailable);
        Button btnGetIn = custom.findViewById((R.id.btnIn));
        Button btnGetOut = custom.findViewById(R.id.btnOut);
        Parking parking=getItem(position);
        txtId.setText("Location: "+ parking.getParkingId());
        txtCapacity.setText("Capacity: " + parking.getCapacity());
        txtRemaining.setText("Remaining: " + parking.getRemaining());
        txtAvailable.setText("Available Status: " + parking.getAvailable());
        return custom;
    }
}
