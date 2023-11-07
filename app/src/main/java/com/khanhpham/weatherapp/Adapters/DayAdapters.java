package com.khanhpham.weatherapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khanhpham.weatherapp.Domains.Day;
import com.khanhpham.weatherapp.Functions.OnItemClickListener;
import com.khanhpham.weatherapp.Functions.WeatherImageManager;
import com.khanhpham.weatherapp.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DayAdapters extends RecyclerView.Adapter<DayAdapters.viewHolder> {
    Context context;
    ArrayList<Day> dayArrayList;
    private final OnItemClickListener listener;
    public DayAdapters(Context context, ArrayList<Day> dayArrayList, OnItemClickListener listener) {
        this.context = context;
        this.dayArrayList = dayArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DayAdapters.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewholder_day, parent, false);
        return new DayAdapters.viewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DayAdapters.viewHolder holder, int position) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd-MM");
        try {
            Date time = input.parse(dayArrayList.get(position).getDate());
            assert time != null;
            holder.txtDay.setText(output.format(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String text = dayArrayList.get(position).getWeatherCondition().getText();
        holder.txtStatus.setText(text);
        holder.txtTempMax.setText(dayArrayList.get(position).getTempMax() + "°C");
        holder.txtTempMin.setText(dayArrayList.get(position).getTempMin() + "°C");
        WeatherImageManager weatherImageManager = new WeatherImageManager();
        String code = dayArrayList.get(position).getWeatherCondition().getCode();
        int imgResource = weatherImageManager.getDayImageResource(code);
        Picasso.get().load(imgResource).into(holder.imgDay);
    }

    @Override
    public int getItemCount() {
        return dayArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txtDay, txtStatus, txtTempMax, txtTempMin;
        ImageView imgDay;

        public viewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            txtDay = itemView.findViewById(R.id.txtDay);
            txtStatus = itemView.findViewById(R.id.txtConditionOfDay);
            txtTempMax = itemView.findViewById(R.id.txtTempMax);
            txtTempMin = itemView.findViewById(R.id.txtTempMin);
            imgDay = itemView.findViewById(R.id.imgDay);

            //su kien nhan vao itemView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
