package com.example.blogposting;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

// This class serves as the container class that the view rests on. All the manipulation and
// interaction functions of the view is stored here.
public class PostHolder extends RecyclerView.ViewHolder{

    private TextView title;
    private TextView desc;
    private TextView category;
    private TextView timestamp;
    private Button deleteButton;

    public void setDeleteButton(String key) {
        deleteButton.setText(key);
    }

    public void disableDeleteButton() {
        deleteButton.setHeight(0);
        deleteButton.setEnabled(false);
    }

    public void setTitle(String newTitle) {
        title.setText(newTitle);
    }

    public void setDesc(String newDesc) {
        desc.setText(newDesc);
    }

    public void setCategory(String newCategory) {
        category.setText(newCategory);
    }

    public void setTimestamp(String newTimestamp) {
        timestamp.setText(newTimestamp);
    }

    public PostHolder(View view) {
        super(view);

        title = view.findViewById(R.id.name);
        desc = view.findViewById(R.id.desc);
        category = view.findViewById(R.id.category);
        timestamp = view.findViewById(R.id.timestamp);
        deleteButton = view.findViewById(R.id.deleteButton);
    }

}
