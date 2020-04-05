package admin.phuc141.com.appweathers.model.business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import admin.phuc141.com.appweathers.R;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private Context context;
    private List<WeatherOfDay> mweatherofdays;

    public WeatherAdapter(Context context, List<WeatherOfDay> mweatherofdays) {
        this.context = context;
        this.mweatherofdays = mweatherofdays;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View WeatherView =inflater.inflate(R.layout.item_weather,parent,false);
        ViewHolder viewHolder = new ViewHolder(WeatherView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherOfDay weatherOfDay = mweatherofdays.get(position);
        holder.Date.setText(weatherOfDay.getDate());
        holder.TempMax.setText(weatherOfDay.getTempMax());
        holder.TempMin.setText(weatherOfDay.getTempMin());
        holder.Status.setText(weatherOfDay.getStatus());
        Picasso.get().load("https://openweathermap.org/img/w/"+weatherOfDay.getImageStage()+".png").into(holder.imgWeather);
    }

    @Override
    public int getItemCount() {
        return mweatherofdays!=null ? mweatherofdays.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgWeather;
        private TextView Date, Status, TempMax, TempMin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgWeather = itemView.findViewById(R.id.imgState2);
            Date = itemView.findViewById(R.id.tvDate);
            Status = itemView.findViewById(R.id.tvStatus);
            TempMax = itemView.findViewById(R.id.tvTempMax);
            TempMin = itemView.findViewById(R.id.tvTempMin);

        }
    }
}
