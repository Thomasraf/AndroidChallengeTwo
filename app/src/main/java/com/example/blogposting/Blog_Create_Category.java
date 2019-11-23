package com.example.blogposting;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Blog_Create_Category extends AppCompatActivity {

    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog__createcategory);

        name = findViewById(R.id.name);
    }


    public void createCategory(View view) {
        Category category = new Category(name.getText().toString());
        new Model().addCategory(category, new Model.DataStatus() {

            @Override
            public void DataIsLoaded(List<Post> posts, List<String> keys) {

            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
        finish();
    }
}
