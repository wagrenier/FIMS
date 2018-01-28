//to enable to be downloaded for offline use
FirebaseDatabase.getInstance().setPersistanceEnabled(true);

//Cause you need to get an instance for your dataBase?
productsDatabase = productsDatabase.getInstance()

//to start at the very beginning of users
productsDatabaseReference = productsDatabase.getReference ("users");

//updating data in real time
addValueEventListener(productsDatabaseReference);

//Suppose to display the damn product list!
ref.addValueEventListener(new ValueEventListener() )
{
@Override 
public void onDataChange (DataSnapchot dataSnapchot)
	{
	Post post = dataSnapshot.getValue(Post.class);
	System.out.println(post);
	}
	
@Override
public void onCancelled (DatabaseError databaseError)
	{
	System.out.println("Lol It didn't happen:");
	}
};
