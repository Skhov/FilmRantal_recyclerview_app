package com.sk.filmrantal_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sk.filmrantal_app.model.Film;

public class EditFilmActivity extends AppCompatActivity {

    private EditText edTitle,edVisitor;
    private Button saveChange;
    private Film film;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_film);

        setContentView(R.layout.activity_add_item);
        initUI();
        saveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChange();
            }
        });
    }
    private void initUI(){

        edTitle=findViewById(R.id.edtitle);
        edVisitor=findViewById((R.id.edvisitor));
        saveChange=findViewById(R.id.btnAdd);

        // get data from intent
        if(getIntent()!=null){
            film=getIntent().getParcelableExtra("data");
            edTitle.setText(film.getTitle());
            edVisitor.setText(film.getVisitor());


        }
    }
    private void saveChange(){
        film.setTitle(edTitle.getText().toString());
        film.setVisitor(edVisitor.getText().toString());
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putParcelable("data",film);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}
