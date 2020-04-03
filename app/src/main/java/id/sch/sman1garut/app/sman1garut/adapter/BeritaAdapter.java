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

import id.sch.sman1garut.app.sman1garut.BeritaAct;
import id.sch.sman1garut.app.sman1garut.R;
import id.sch.sman1garut.app.sman1garut.models.models_berita.DataBerita;

public class BeritaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<DataBerita> data= Collections.emptyList();
    DataBerita current;
    int currentPos=0;

    public BeritaAdapter(Context context, List<DataBerita> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.rv_kegiatan, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        final MyHolder myHolder= (MyHolder) holder;
        final DataBerita current=data.get(position);
        myHolder.textJudul.setText(current.Judul);
        myHolder.textIsi.setText(current.Isi);
        myHolder.textAuthor.setText("- "+ current.Author);
        final int idKegiatan = current.Id;

        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent goto_detail_kegiatan = new Intent(context, DetailKegiatanAct.class);
//                goto_detail_kegiatan.putExtra("id", idKegiatan);
//                context.startActivity(goto_detail_kegiatan);
                Toast.makeText(context, "Belum bisa ditampilkan!", Toast.LENGTH_LONG).show();
            }
        });
    }

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
