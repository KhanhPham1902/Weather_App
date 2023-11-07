package com.khanhpham.weatherapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khanhpham.weatherapp.Domains.Hour;
import com.khanhpham.weatherapp.Domains.WeatherCondition;
import com.khanhpham.weatherapp.Functions.OnItemClickListener;
import com.khanhpham.weatherapp.Functions.WeatherImageManager;
import com.khanhpham.weatherapp.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HourAdapters extends RecyclerView.Adapter<HourAdapters.viewHolder> {
    Context context;
    ArrayList<Hour> hourArrayList;
    private final OnItemClickListener listener;

    public HourAdapters(Context context, ArrayList<Hour> hourArrayList, OnItemClickListener listener) {
        this.context = context;
        this.hourArrayList = hourArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HourAdapters.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewholder_hour, parent, false);
        return new HourAdapters.viewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull HourAdapters.viewHolder holder, int position) {
        holder.txtTemp.setText(hourArrayList.get(position).getTemp() + "°C");
        holder.txtWindSpeed.setText(hourArrayList.get(position).getWindSpeed() + "km/h");
        holder.txtHumidity.setText(hourArrayList.get(position).getHumidity() + "%");
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat output = new SimpleDateFormat("HH:mm");
        try {
            Date t = input.parse(hourArrayList.get(position).getTime());
            assert t != null;
            holder.txtHour.setText(output.format(t));
        } catch (Exception e) {
            e.printStackTrace();
        }
        WeatherImageManager weatherImageManager = new WeatherImageManager();
        Integer isDay = hourArrayList.get(position).getIsDay();
        String code = hourArrayList.get(position).getWeatherCondition().getCode();
        if (isDay == 1) {
            int imgDayResource = weatherImageManager.getDayImageResource(String.valueOf(code));
            Picasso.get().load(imgDayResource).into(holder.img);
        } else {
            int imgNightResource = weatherImageManager.getNightImageResource(String.valueOf(code));
            Picasso.get().load(imgNightResource).into(holder.img);
        }
    }

    @Override
    public int getItemCount() {
        return hourArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txtHour, txtTemp, txtWindSpeed, txtHumidity;
        ImageView img;

        public viewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            txtHour = itemView.findViewById(R.id.txtHour);
            txtTemp = itemView.findViewById(R.id.txtTempOfHour);
            img = itemView.findViewById(R.id.imgWeatherOfHour);
            txtWindSpeed = itemView.findViewById(R.id.txtWindSpeedOfHour);
            txtHumidity = itemView.findViewById(R.id.txtHumidityOfHour);

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
