package com.example.blogposting;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<Post> posts = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Post> posts, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public Model(){
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("posts");
    }

    public void readPosts(final DataStatus dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                posts.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keynode : dataSnapshot.getChildren()){
                    keys.add(keynode.getKey());
                    Post post = keynode.getValue(Post.class);
                    posts.add(post);
                }
                dataStatus.DataIsLoaded(posts,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addPost(Post post, final DataStatus dataStatus){
        String key = mReference.push().getKey();
        mReference.child(key).setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }

}
