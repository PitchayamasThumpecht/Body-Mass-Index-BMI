package randomnumber.su.ac.th.projectbmi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList data_name, data_id, data_weight, data_height;

    CustomAdapter(Activity activity, Context context, ArrayList data_name, ArrayList data_id, ArrayList data_weight,
                  ArrayList data_height){
        this.activity = activity;
        this.context = context;
        this.data_name = data_name;
        this.data_id = data_id;
        this.data_weight = data_weight;
        this.data_height = data_height;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_activity, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        holder.name_txt.setText(String.valueOf(data_name.get(position)));
        holder.id_txt.setText(String.valueOf(data_id.get(position)));
        holder.weight_txt.setText(String.valueOf(data_weight.get(position)));
        holder.height_txt.setText(String.valueOf(data_height.get(position)));
    }


    @Override
    public int getItemCount() {
        return data_id.size();
    } //บอกจำนวนข้อมูลที่ต้องการแสดงผล

    class MyViewHolder extends RecyclerView.ViewHolder { //ส่งข้อมูลไปยังที่ๆจะแสดงผล

        TextView  name_txt, id_txt, weight_txt, height_txt;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_txt = itemView.findViewById(R.id.showName);
            id_txt = itemView.findViewById(R.id.showId);
            weight_txt = itemView.findViewById(R.id.showWeight);
            height_txt = itemView.findViewById(R.id.showHeight);
        }
    }
}
