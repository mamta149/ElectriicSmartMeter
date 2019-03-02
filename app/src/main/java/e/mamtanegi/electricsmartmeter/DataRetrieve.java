package e.mamtanegi.electricsmartmeter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DataRetrieve {
    private static final String TAG = "DataRetrieve";

    private Context context;

    private FirebaseFirestore firebaseFirestore;

    private ArrayList<UserLoginData> userslogindata;


    public DataRetrieve(Context context) {
        this.context = context;

        FirebaseApp.initializeApp(context);
        this.firebaseFirestore = FirebaseFirestore.getInstance();

        this.userslogindata = new ArrayList<UserLoginData>();
        loadData();


    }


    private void loadData(){
        firebaseFirestore.collection("userdocumentation")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                UserLoginData userlogin = document.toObject(UserLoginData.class);
                                add(userlogin);
                                Log.d(TAG, "onComplete: username" +userlogin.uname);


                        }
                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


    }

    private void add(UserLoginData UserLoginData){
        userslogindata.add(UserLoginData);
        Log.d(TAG, " outside size" +userslogindata.size());
    }

    public ArrayList<UserLoginData> getUserslogindata() {
        return userslogindata;
    }




}
