package rubberduckies.fims.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import rubberduckies.fims.R;


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

        //TODO on click delete

        holder.bind(prod);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
