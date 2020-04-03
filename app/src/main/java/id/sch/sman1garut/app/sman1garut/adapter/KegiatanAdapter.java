package id.sch.sman1garut.app.sman1garut.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import id.sch.sman1garut.app.sman1garut.Daftar2Act;
import id.sch.sman1garut.app.sman1garut.DaftarAct;
import id.sch.sman1garut.app.sman1garut.DashboardAct;
import id.sch.sman1garut.app.sman1garut.DetailKegiatanAct;
import id.sch.sman1garut.app.sman1garut.KegiatanAct;
import id.sch.sman1garut.app.sman1garut.R;
import id.sch.sman1garut.app.sman1garut.models.model_kegiatan.DataKegiatan;

public class KegiatanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataKegiatan> data= Collections.emptyList();
    DataKegiatan current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public KegiatanAdapter(Context context, List<DataKegiatan> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.rv_kegiatan, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        final MyHolder myHolder= (MyHolder) holder;
        final DataKegiatan current=data.get(position);
        myHolder.textJudul.setText(current.Judul);
        myHolder.textIsi.setText(current.Deskripsi);
        myHolder.textAuthor.setText("- "+ current.Author);
        final int idKegiatan = current.Id;

        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goto_detail_kegiatan = new Intent(context, DetailKegiatanAct.class);
                goto_detail_kegiatan.putExtra("id", idKegiatan);
                context.startActivity(goto_detail_kegiatan);
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
            cardView = (RelativeLayout) itemView.findViewById(R.id.cardView);
            textJudul= (TextView) itemView.findViewById(R.id.judul);
            textIsi = (TextView) itemView.findViewById(R.id.desc);
            textAuthor = (TextView) itemView.findViewById(R.id.author);
        }

    }

}