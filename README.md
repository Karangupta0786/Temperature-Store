# project setup
// Firebase objectKey:
Val currentUser = auth.CurrentUser
currentUser?.let{user->
tempKey = databaseReference.child("users").child(user.uid).child("temp").push().key()
}
