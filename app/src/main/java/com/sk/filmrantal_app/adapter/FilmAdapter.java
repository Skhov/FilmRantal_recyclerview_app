package com.sk.filmrantal_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sk.filmrantal_app.FilmDetailActivity;
import com.sk.filmrantal_app.R;
import com.sk.filmrantal_app.model.Film;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {
    private List<Film> filmList;
    private AppCompatActivity context;
    private OnAdapterItemLongClickListener listener;

    public FilmAdapter(List<Film> filmList, AppCompatActivity context) {
        this.filmList = filmList;
        this.context = context;
        this.listener=(OnAdapterItemLongClickListener)context;
    }

    @Override
    public int getItemCount() {
        return this.filmList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.layout_film_item,viewGroup,false);
        return new FilmAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        // Get Register Contac
        context.registerForContextMenu(viewHolder.itemView);
        final  Film film=filmList.get(position);
        viewHolder.title.setText(film.getTitle());
        viewHolder.visitor.setText(film.getVisitor());
        viewHolder.btnMore.setImageResource(film.getMoreIcon());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new  Intent(context, FilmDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable("Data",film);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        viewHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  viewHolder.btnMore.setImageResource(R.drawable.ic_cloud_done_black_24dp);
                Film filmUpdate=filmList.get(viewHolder.getAdapterPosition());
                if(filmUpdate.getMoreIcon()==R.drawable.ic_more_black_24dp)
                    filmUpdate.setMoreIcon(R.drawable.ic_cloud_done_black_24dp);
                else
                    filmUpdate.setMoreIcon(R.drawable.ic_more_black_24dp);
                filmList.set(viewHolder.getAdapterPosition(),filmUpdate);
                notifyItemChanged(viewHolder.getAdapterPosition());


            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onAdapterLongClicked(viewHolder.getAdapterPosition());
                return false;
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,visitor;
        ImageView btnMore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            visitor=itemView.findViewById(R.id.visited);
            btnMore=itemView.findViewById(R.id.btnMore);

        }
    }

    public  interface OnAdapterItemLongClickListener{
        void onAdapterLongClicked(int position);
    }
}
