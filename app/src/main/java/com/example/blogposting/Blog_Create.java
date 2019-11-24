package com.example.blogposting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import java.util.List;

public class Blog_Create extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private ChipGroup categorySelection;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog__create);

        title = findViewById(R.id.name);
        description = findViewById(R.id.description);
        categorySelection = findViewById(R.id.categories);
        context = this;
        new Model().readCategories(new Model.DataStatusCategory(){

            @Override
            public void DataIsLoaded(List<String> categories, List<String> keys) {
                for(String categoryName : categories){
                    Chip chip = new Chip(context);
                    ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(context, null, 0, R.style.Widget_MaterialComponents_Chip_Choice);
                    chip.setChipDrawable(chipDrawable);
                    chip.setCheckable(true);
                    chip.setText(categoryName);
                    categorySelection.addView(chip);
                }
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
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = Long.toString(tsLong);
        String categoryString = "";
        //String category, String description, long timestamp, String title
        for (int i=0; i<categorySelection.getChildCount();i++){
            Chip chip = (Chip)categorySelection.getChildAt(i);
            if (chip.isChecked()){
                categoryString += chip.getText().toString() + ";";
            }
        }

        Post post = new Post(categoryString,description.getText().toString(),ts,title.getText().toString());
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
