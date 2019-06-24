package com.sk.filmrantal_app;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sk.filmrantal_app.adapter.FilmAdapter;
import com.sk.filmrantal_app.model.Film;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FilmAdapter.OnAdapterItemLongClickListener {

    private RecyclerView rvFilm;
    private List<Film> filmList=new ArrayList<>();
    private FilmAdapter filmAdapter;
    private int positionItem;
    private FloatingActionButton btnAddItem;
    public static final int CODE_REQUEST=99;
    public static final int EDIT_REQUEST_CODE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddItem=findViewById(R.id.btnAddItem);
        setUI();
        getFilm();
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this,AddItemActivity.class)
                ,CODE_REQUEST);
            }
        });
    }
    private void setUI(){
        rvFilm=findViewById(R.id.rvFilm);
        rvFilm.setLayoutManager(new LinearLayoutManager(this));
        filmAdapter=new FilmAdapter(filmList,this);
        rvFilm.setAdapter(filmAdapter);
    }
    private void getFilm(){
        for(int i=0;i<20;i++){
            filmList.add(new Film(i+ " Title of Film","Visitor amount",R.drawable.ic_more_black_24dp));
        }
  filmAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contact_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.edit:
                Intent intent=new Intent(this,EditFilmActivity.class);
                Film film=filmList.get(this.positionItem);
                Bundle bundle=new Bundle();
                bundle.putParcelable("data",film);
                intent.putExtras(intent);
                startActivityForResult(intent,EDIT_REQUEST_CODE);

                return true;
            case R.id.remove:
                removeRecyclerViewItem();

                return true;
                default:  return false;

        }

    }

    @Override
    public void onAdapterLongClicked(int position) {
        this.positionItem=position;
       // Toast.makeText(this,"" +position,Toast.LENGTH_SHORT).show();
    }
    private void removeRecyclerViewItem(){
        this.filmList.remove(this.positionItem);
        filmAdapter.notifyItemRemoved(this.positionItem);
        Toast.makeText(this,"remove",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(CODE_REQUEST==requestCode && resultCode== RESULT_OK){
            // get new data from add itemActivity
            Film film=data.getParcelableExtra("data");
            // Add new item
            this.filmList.add(0,film);
            filmAdapter.notifyItemInserted(0);
            scroll(0);
        }
        if(EDIT_REQUEST_CODE==requestCode && resultCode==RESULT_OK){
            Film film=data.getParcelableExtra("data");
            this.filmList.set(this.positionItem,film);
            filmAdapter.notifyItemChanged(this.positionItem);

        }

    }
    private void scroll(int position){

        rvFilm.smoothScrollToPosition(position);


    }
}
