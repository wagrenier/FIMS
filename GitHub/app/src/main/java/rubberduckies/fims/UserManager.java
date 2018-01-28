package rubberduckies.fims;

/**
 * Created by Etienne on 27/01/2018.
 */


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.Observable;
import rubberduckies.fims.adapters.User;

public class UserManager extends Observable {
    /**The current user*/
    private User current_user;
    /**A reference to a UserManager object*/
    private static UserManager instance = null;
    /**A reference to the current database*/
    public DatabaseReference databaseUsers;

    protected UserManager() {
        // Exists only to defeat instantiation.
        getUserInformations();
    }
    /**Getter for the current user.*/
    public User getCurrentUser() {
        if (this.current_user == null) {
            getUserInformations();
        }
        return this.current_user;
    }
    /**Setter for the current user.*/
    public void setCurrentUser(User user) {
        this.current_user = user;
    }
    /**Returns an instance of the UserManager.*/

    public static UserManager getInstance() {
        if(instance == null) {
            instance = new UserManager();
        }
        return instance;
    }
    /**Updates the user's information in the database.*/
    public void editUserInformations(User userInformations) {
        this.databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        this.databaseUsers.child(userInformations.getId()).setValue(userInformations);
    }
    /**Adds a child node to the database.*/
    public void addUserInformations(User userInformations) {
        //simple validation of the object:
        this.databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        this.databaseUsers.child(userInformations.getId()).setValue(userInformations);
    }
    /**Clears the current user from the fields.*/
    public void clearCurrentUser() {
        this.current_user = null;
    }
    /**Gets the current user's information from the Database.*/
    public void getUserInformations() {
        this.databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        Query userQuery = this.databaseUsers.orderByChild("id").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = null;
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    user = singleSnapshot.getValue(User.class);

                    current_user = user;
                }
                // Set the class as changed so the update method gets called!.
                setChanged();
                if (user != null) {
                    notifyObservers(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        userQuery.addListenerForSingleValueEvent(postListener);
    }
}
