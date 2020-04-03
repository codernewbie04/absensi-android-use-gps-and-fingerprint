package id.sch.sman1garut.app.sman1garut.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import id.sch.sman1garut.app.sman1garut.R;
import id.sch.sman1garut.app.sman1garut.models.model_pengumuman.DataPengumuman;

public class PengumumanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataPengumuman> data= Collections.emptyList();
    DataPengumuman current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public PengumumanAdapter(Context context, List<DataPengumuman> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.rv_pengumuman, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Get current position of item in recyclerview to bind data and assign values from list
        final MyHolder myHolder= (MyHolder) holder;
        final DataPengumuman current=data.get(position);
        myHolder.textJudul.setText(current.Judul);
        myHolder.textIsi.setText(current.IsiPengumuman);
        myHolder.textAuthor.setText("- "+ current.Author);
        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove(current);
                Toast.makeText(context, "This is "+  myHolder.textJudul.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        RelativeLayout cardView;
        TextView textJudul;
        TextView textIsi;
        TextView textAuthor;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            cardView= (RelativeLayout) itemView.findViewById(R.id.cardView);
            textJudul= (TextView) itemView.findViewById(R.id.judul);
            textIsi = (TextView) itemView.findViewById(R.id.desc);
            textAuthor = (TextView) itemView.findViewById(R.id.author);
        }

    }

//    public void remove(DataPengumuman item) {
//        int position = data.indexOf(item);
//        data.remove(position);
//        notifyItemRemoved(position);
//    }

}