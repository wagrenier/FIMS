package rubberduckies.fims.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import rubberduckies.fims.R;

/**
 * Created by Etienne on 27/01/2018.
 */



public class MyViewHolderProducts extends RecyclerView.ViewHolder {
    private ImageView button;
    private TextView name;
    private TextView upc;

    public MyViewHolderProducts(View itemView) {
        super(itemView);
        button = (ImageView) itemView.findViewById(R.id.button);
        name = (TextView) itemView.findViewById(R.id.name);
        upc = (TextView) itemView.findViewById(R.id.upc);
    }
}
