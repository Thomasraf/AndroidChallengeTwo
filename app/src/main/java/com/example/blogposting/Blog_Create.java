package com.example.blogposting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Blog_Create extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText title;
    private EditText description;
    private Spinner category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog__create);

        category = findViewById(R.id.category);
        title = findViewById(R.id.name);
        description = findViewById(R.id.description);
        final List<String> target = new ArrayList<String>();
        new Model().readCategories(new Model.DataStatusCategory(){

            @Override
            public void DataIsLoaded(List<String> categories, List<String> keys) {
                target.addAll(categories);
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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        category.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void createPost(View view) {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = Long.toString(tsLong);
        //String category, String description, long timestamp, String title
        Post post = new Post(category.getSelectedItem().toString(),description.getText().toString(),ts,title.getText().toString());
        new Model().addPost(post, new Model.DataStatus() {

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
