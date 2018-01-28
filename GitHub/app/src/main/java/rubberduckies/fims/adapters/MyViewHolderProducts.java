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
        button = (ImageView) itemView.findViewById(R.id.delete);
        name = (TextView) itemView.findViewById(R.id.name);
        upc = (TextView) itemView.findViewById(R.id.upc);
    }

    public void bind(Products product){
        name.setText(product.getName());
        upc.setText(String.valueOf(product.getUPC()));//TODO check
    }

    public ImageView getButton() {
        return button;
    }

    public void setButton(ImageView button) {
        this.button = button;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getUpc() {
        return upc;
    }

    public void setUpc(TextView upc) {
        this.upc = upc;
    }
}
