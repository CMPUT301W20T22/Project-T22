package com.example.easyride.ui.driver;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyride.R;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class RideRequestListAdapter extends RecyclerView.Adapter<RideRequestListAdapter.ViewHolder> implements Filterable {

    private List<RideRequest> rideRequestsList;


    public RideRequestListAdapter(List<RideRequest> rideRequestsList) {
        this.rideRequestsList = rideRequestsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ride_content_display,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.riderName.setText("Rider: " + rideRequestsList.get(position).getRiderUserName());
        holder.pickupLocation.setText("Pick Up Location: " + rideRequestsList.get(position).getPickupPoint());
        holder.destination.setText("Destination: " + rideRequestsList.get(position).getTargetPoint());
        holder.fee.setText("Fare: " + rideRequestsList.get(position).getCost().toString());

    }

    @Override
    public int getItemCount() {
        return rideRequestsList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<RideRequest> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0 ) {
                filteredList.addAll(rideRequestsList);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (RideRequest rideRequest : rideRequestsList) {
                    if (rideRequest.getRiderUserName().toLowerCase().equals(filterPattern)) {
                        filteredList.add(rideRequest);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            rideRequestsList.clear();
            rideRequestsList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public TextView riderName, pickupLocation, destination, fee;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;

            riderName = mView.findViewById(R.id.riderName);
            pickupLocation = mView.findViewById(R.id.pickUp);
            destination = mView.findViewById(R.id.target);
            fee = mView.findViewById(R.id.cost);
        }
    }
}
