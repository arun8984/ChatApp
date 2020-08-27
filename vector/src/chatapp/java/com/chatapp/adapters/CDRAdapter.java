package com.chatapp.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import im.vector.R;

public class CDRAdapter extends RecyclerView.Adapter<CDRAdapter.CDRHolder> {

    ArrayList<CDRModel> list = new ArrayList<>();
    Context context;

    public CDRAdapter(ArrayList<CDRModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CDRHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cdr_row, null);
        return new CDRHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CDRHolder holder, int position) {
        CDRModel model = list.get(position);
        holder.calledUser.setText(Html.fromHtml("<b>User : </b>" + model.getName()));
        holder.cost.setText(Html.fromHtml("<b>Cost : </b>" + model.getCost()));
        holder.duration.setText(Html.fromHtml("<b>Duration : </b>" + model.getDuration()));
        holder.country.setText(Html.fromHtml("<b>Country : </b>" + model.getCountry()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CDRHolder extends RecyclerView.ViewHolder {

        TextView calledUser, country, duration, cost, rates;

        public CDRHolder(@NonNull View itemView) {
            super(itemView);
            calledUser = itemView.findViewById(R.id.calledUser);
            country = itemView.findViewById(R.id.country);
            duration = itemView.findViewById(R.id.duration);
            cost = itemView.findViewById(R.id.cost);

        }
    }
}
