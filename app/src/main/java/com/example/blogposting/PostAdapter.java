package com.example.blogposting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//(1) The ContactAdapter is a child of RecyclerView.Adapter and uses the holder ContactHolder.
//    This means the adapter manages entries of ContactHolder
public class PostAdapter extends RecyclerView.Adapter<PostHolder> {

    //(2) The constructor and ArrayList are used to keep track of the information displayed. Though
    //    the RecyclerView.Adapter functions manage the views in the holders, the information in
    //    the views are kept separately and should be managed by the adapter class itself.
    private ArrayList<Post> posts;

    public PostAdapter(ArrayList<Post> posts){
        this.posts = posts;

        //(3) The hard coded entries as shown here illustrate how information can be initially
        //    populated onto a list. It is also possible to pass information onto the constructor
        //    which will be used to populate the list.
    }

    //(4) The onCreateViewHolder function is used to create the views to be used. This function
    //    expects a holder with a view to be returned. The information in the holder will be
    //    overwritten in another function.
    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //(5) An inflater is again used to populate the view. This information can be directly
        //    taken from a layout.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.post_row, parent, false);

        //(6) The view created must be given to a holder. The holder will serve as the in-between
        //    system that interacts with the view.
        PostHolder holder = new PostHolder(view);
        return holder;
    }

    //(7) The onBindViewHolder function is called to replace the information of the view on a
    //    specific position. This is why the position parameter will dictate what information
    //    should be displayed on the specific view.
    @Override
    public void onBindViewHolder(PostHolder holder, final int position) {
        holder.setTitle(posts.get(position).getTitle());
        holder.setDesc(posts.get(position).getDescription());
        holder.setCategory(posts.get(position).getCategory());
        holder.setTimestamp(posts.get(position).getTimestamp());
    }

    //(8) As the list of information grows, the adapter's parent functions must be notified of the
    //    change of size in the list of elements. Thus it needs the getItemCount function to
    //    indicate the current size of the list of elements.
    @Override
    public int getItemCount() {
        return posts.size();
    }

}
