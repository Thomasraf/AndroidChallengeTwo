package com.example.blogposting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerArea;
    private PostAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private ChipGroup categoryFilter;
    private Context context;
    boolean postsLoaded = false;
    boolean categoriesLoaded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        Toast.makeText(this,"Loading...",Toast.LENGTH_LONG).show();

        categoryFilter = findViewById(R.id.categoryFilter);

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
                postsLoaded = true;
                Toast.makeText(context,"Posts loaded!",Toast.LENGTH_SHORT).show();
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
        new Model().readCategories(new Model.DataStatusCategory(){

            @Override
            public void DataIsLoaded(List<String> categories, List<String> keys) {
                for(String categoryName : categories){
                    Chip chip = new Chip(context);
                    ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(context, null, 0, R.style.Widget_MaterialComponents_Chip_Filter);
                    chip.setChipDrawable(chipDrawable);
                    chip.setCheckable(true);
                    chip.setText(categoryName);
                    categoryFilter.addView(chip);
                }
                categoriesLoaded = true;
                Toast.makeText(context,"Categories loaded!",Toast.LENGTH_SHORT).show();
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

    public void viewByDateDesc(View view) {
        //(1) The RecyclerView is where the recycling will be done. In this case, this has already
        //    been declared in the activity_main.xml
        recyclerArea = findViewById(R.id.recyclerArea);

        //(2) The LinearLayoutManager is in-charge of the layout of the RecyclerView
        manager = new LinearLayoutManager(this);
        recyclerArea.setLayoutManager(manager);
        Toast.makeText(context,"Sorting...",Toast.LENGTH_SHORT).show();

        new Model().readPosts(new Model.DataStatus(){

            @Override
            public void DataIsLoaded(List<Post> posts, List<String> keys) {
                Collections.sort(posts);
                adapter = new PostAdapter((ArrayList) posts);
                recyclerArea.setAdapter(adapter);
                Toast.makeText(context,"Sorted by descending date.",Toast.LENGTH_SHORT).show();
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

    public void viewByDateAsc(View view) {
        //(1) The RecyclerView is where the recycling will be done. In this case, this has already
        //    been declared in the activity_main.xml
        recyclerArea = findViewById(R.id.recyclerArea);

        //(2) The LinearLayoutManager is in-charge of the layout of the RecyclerView
        manager = new LinearLayoutManager(this);
        recyclerArea.setLayoutManager(manager);
        Toast.makeText(context,"Sorting...",Toast.LENGTH_SHORT).show();

        new Model().readPosts(new Model.DataStatus(){

            @Override
            public void DataIsLoaded(List<Post> posts, List<String> keys) {
                adapter = new PostAdapter((ArrayList) posts);
                recyclerArea.setAdapter(adapter);
                Toast.makeText(context,"Sorted by ascending.",Toast.LENGTH_SHORT).show();
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
        //(1) The RecyclerView is where the recycling will be done. In this case, this has already
        //    been declared in the activity_main.xml
        recyclerArea = findViewById(R.id.recyclerArea);

        //(2) The LinearLayoutManager is in-charge of the layout of the RecyclerView
        manager = new LinearLayoutManager(this);
        recyclerArea.setLayoutManager(manager);
        Toast.makeText(context,"Filtering...",Toast.LENGTH_SHORT).show();

        new Model().readPosts(new Model.DataStatus(){

            @Override
            public void DataIsLoaded(List<Post> posts, List<String> keys) {
                List<Post> newPosts = new ArrayList<>();
                for (int i=0; i<categoryFilter.getChildCount();i++){
                    Chip chip = (Chip)categoryFilter.getChildAt(i);
                    if (chip.isChecked()){
                        for(int j = 0; j < posts.size() ; j++){
                            if(posts.get(j).getCategory().contains(chip.getText())){
                                if (!newPosts.contains(posts.get(j)))
                                {
                                    newPosts.add(posts.get(j));
                                }
                            }
                        }
                    }
                }
                adapter = new PostAdapter((ArrayList) newPosts);
                recyclerArea.setAdapter(adapter);
                Toast.makeText(context,"Filtered by category.",Toast.LENGTH_SHORT).show();
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
}
