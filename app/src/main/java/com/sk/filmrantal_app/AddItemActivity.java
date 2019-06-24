package com.sk.filmrantal_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sk.filmrantal_app.model.Film;

public class AddItemActivity extends AppCompatActivity {
    private EditText edTitle;
    private  EditText edVisitor;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
       edTitle=findViewById(R.id.edtitle);
       edVisitor=findViewById((R.id.edvisitor));
       btnAdd=findViewById(R.id.btnAdd);
       btnAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Film film=new Film(edTitle.getText().toString(),
                       edVisitor.getText().toString(),
                       R.drawable.ic_more_black_24dp);
               Intent intent=new Intent();
               Bundle bundle=new Bundle();
               bundle.putParcelable("data",film);
               intent.putExtras(bundle);
               setResult(RESULT_OK,intent);
               finish();

           }


       });
    }
}
