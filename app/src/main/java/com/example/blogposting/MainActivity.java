package com.example.blogposting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerArea;
    private PostAdapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //(1) The RecyclerView is where the recycling will be done. In this case, this has already
        //    been declared in the activity_main.xml
        recyclerArea = findViewById(R.id.recyclerArea);

        //(2) The LinearLayoutManager is in-charge of the layout of the RecyclerView
        manager = new LinearLayoutManager(this);
        recyclerArea.setLayoutManager(manager);

        new Model().readPosts(new Model.DataStatus(){

            @Override
            public void DataIsLoaded(List<Post> posts, List<String> keys) {
                adapter = new PostAdapter((ArrayList) posts);
                recyclerArea.setAdapter(adapter);
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
    }

    public void createPost(View view) {
        Intent intent = new Intent(MainActivity.this, Blog_Create.class);
        startActivity(intent);
    }

    public void createCategory(View view) {
        Intent intent = new Intent(MainActivity.this, Blog_Create_Category.class);
        startActivity(intent);
    }

    public void deletePost(View view) {

    }

    public void viewByDate(View view) {
        //(1) The RecyclerView is where the recycling will be done. In this case, this has already
        //    been declared in the activity_main.xml
        recyclerArea = findViewById(R.id.recyclerArea);

        //(2) The LinearLayoutManager is in-charge of the layout of the RecyclerView
        manager = new LinearLayoutManager(this);
        recyclerArea.setLayoutManager(manager);

        new Model().readPosts(new Model.DataStatus(){

            @Override
            public void DataIsLoaded(List<Post> posts, List<String> keys) {
                Collections.sort(posts);
                adapter = new PostAdapter((ArrayList) posts);
                recyclerArea.setAdapter(adapter);
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
    }

    public void viewByCategory(View view) {

    }
}
