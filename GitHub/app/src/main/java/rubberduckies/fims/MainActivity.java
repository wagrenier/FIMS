package rubberduckies.fims;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import rubberduckies.fims.adapters.MyAdapterProducts;
import rubberduckies.fims.adapters.Products;
import rubberduckies.fims.adapters.User;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private Context context;
    private ProgressDialog progressDialog;
    private ArrayList<Products> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Shopping List");
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Log.d("igned_in:", user.getUid());
            } else {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                Log.d("signed_out", " nope");
            }

        };

        progressDialog = new ProgressDialog(this);

        loadUser();


        recyclerView.setAdapter(new MyAdapterProducts(getApplicationContext(), products));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Double d = (Math.random()*10);
            String stemp = "" +d.intValue();
            Log.d("user: ", UserManager.getInstance().getCurrentUser().getName());
            UserManager.getInstance().getCurrentUser().addProductInList(new Products(stemp,"rubber duck", 27,1,1997));

            UserManager.getInstance().editUserInformations(UserManager.getInstance().getCurrentUser());

            for(Products p : UserManager.getInstance().getCurrentUser().getProducts()){
                Log.d("Element: ", p.getName());
            }
        });
    }

    void addProduct(Products p){
        products.add(p);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void loadProducts() {
        User currentUser = UserManager.getInstance().getCurrentUser();

        DatabaseReference usersTable = FirebaseDatabase.getInstance().getReference("users");
        usersTable.child(currentUser.getId()).child("products").addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot snapshot) {
                products.clear();
                for (DataSnapshot products: snapshot.getChildren()) {
                    Products temp = products.getValue(Products.class);
                    Log.d("Product", temp.getName());

                    addProduct(products.getValue(Products.class));
                    /*FirebaseDatabase.getInstance().getReference("users").child(products.getValue(String.class)).addValueEventListener(new ValueEventListener() {
                        public void onDataChange(DataSnapshot productSnapshot) {
                            addProduct(productSnapshot.getValue(Products.class));

                        }
                        public void onCancelled(DatabaseError firebaseError) {
                            //Not handled
                        }
                    });*/
                }
            }
            public void onCancelled(DatabaseError firebaseError) {
                //Not Handled
            }
        });
    }

    private void loadUser() {
        progressDialog.setMessage("Loading user data...");
        progressDialog.show();
        DatabaseReference databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        Query userQuery = databaseUsers.orderByChild("id").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = null;
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    user = singleSnapshot.getValue(User.class);
                    Log.d("User: ", user.getName());

                    UserManager.getInstance().setCurrentUser(user);

                    loadProducts();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        userQuery.addListenerForSingleValueEvent(postListener);
    }


    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
