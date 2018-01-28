package rubberduckies.fims.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import rubberduckies.fims.R;
import rubberduckies.fims.UserManager;


/**
 * Created by Etienne on 27/01/2018.
 */

public class MyAdapterProducts extends RecyclerView.Adapter<MyViewHolderProducts> {
    private List<Products> list;
    private Context context;

    public MyAdapterProducts(Context context, List<Products> list){
        this.list = list;
        this.context = context;
    }


    @Override
    public MyViewHolderProducts onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_element_list, parent, false);
        return new MyViewHolderProducts(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolderProducts holder, int position) {
        int p = position;
        Products prod = list.get(position);

        holder.getButton().setOnClickListener(e->{

            Animation animation = AnimationUtils.loadAnimation(
                    holder.getButton().getContext(), R.anim.delete_anim);
            animation.setDuration(800);

            holder.itemView.startAnimation(animation);


            holder.itemView.postOnAnimationDelayed(() -> {
                notifyItemRemoved(list.indexOf(prod));
                list.remove(prod);
                Log.d("in: ", prod.getUPC());
                deleteBreakFromDb(prod);

            },300);

        });

        //TODO on click delete

        holder.bind(prod);
    }

    private void deleteBreakFromDb(Products productToDelete) {
        Log.d("in: ", productToDelete.getUPC());

        DatabaseReference databaseUsers = FirebaseDatabase.getInstance().getReference("users").child(UserManager.getInstance().getCurrentUser().getId()).child("products");
        //Query userQuery = databaseUsers.orderByChild("upc").equalTo(productToDelete.getUPC());
        //alueEventListener postListener = new ValueEventListener() {
        //databaseUsers.addValueEventListener( new ValueEventListener() {
        databaseUsers.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Products temp;
                    Log.d("IN", "OOOO");
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    temp = singleSnapshot.getValue(Products.class);

                    Log.d("snap",temp.getUPC());
                    Log.d("product",productToDelete.getUPC());
                    if(temp.getUPC().equals(productToDelete.getUPC())) {
                        //singleSnapshot.getRef().removeValue();
                        databaseUsers.child(singleSnapshot.getKey()).removeValue();
                        break;
                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //userQuery.addValueEventListener(postListener);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
