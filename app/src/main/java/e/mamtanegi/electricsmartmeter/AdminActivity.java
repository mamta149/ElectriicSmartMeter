package e.mamtanegi.electricsmartmeter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    FirebaseFirestore db;
    Context c = getApplicationContext() ;
    TextInputEditText uname,password,meterno,phonenumber,username;
    LinearLayout layout1;
    LinearLayout layout2;
    Button login;
    TextView userdetailssubmit;
    private static final String TAG = "AdminActivity";

    ArrayList<UserLoginData> usersLoginData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c =
        setContentView(R.layout.activity_admin);
        meterno=findViewById(R.id.meterno);
        phonenumber=findViewById(R.id.phonenumber);
        uname = findViewById(R.id.unameadmin);
        username=findViewById(R.id.usernameU);
        password = findViewById(R.id.password);
        userdetailssubmit=findViewById(R.id.userdetailssubmit);
        db=FirebaseFirestore.getInstance();
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: click ");
                validate();

            }
        });

        layout1 = (LinearLayout) findViewById(R.id.layoutlogin);
        layout2 = (LinearLayout) findViewById(R.id.layoutadmin);
        layout2.setVisibility(View.VISIBLE);
        FirebaseApp.initializeApp(this);



    }
    private void validate(){
        Log.d(TAG, "validate: outside");
        if((uname.getText().toString().equals("Admin"))&&(password.getText().toString().equals("mamtu1@"))){
            layout2.setVisibility(View.GONE);
            layout1.setVisibility(View.VISIBLE);
            Log.d(TAG, "validate: inside");
        }
        userdetailssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname1=username.getText().toString().trim();
                double phoneno1=Double.parseDouble(phonenumber.getText().toString());
                float meterno1=Float.parseFloat(meterno.getText().toString().trim());
                UserLoginData udata=new UserLoginData(uname1,phoneno1,meterno1);
                DataRetrieve dataRetrieve=new DataRetrieve(c);
                if(userDetailsCheckDuplicates(phoneno1)){
                    Toast.makeText(getApplicationContext(),"duplicate Data",Toast.LENGTH_SHORT).show();


                }else {


                    db.collection("userdocumentation").document().set(udata).addOnSuccessListener
                            (new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: ");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFail" + e.toString());
                        }
                    });
                }

            }
        });

    }

    private boolean userDetailsCheckDuplicates(Double phone){

        usersLoginData = retrieveData();
        for (UserLoginData userLoginData:usersLoginData){
            if(userLoginData.phonenumber==phone){

                Log.d(TAG, "onClick: duplicate data");
                Toast.makeText(getApplicationContext(),"duplicate Data",Toast.LENGTH_SHORT).show();
                return true;

            }

        }
        return  false;
    }

    private ArrayList<UserLoginData> retrieveData(){
        DataRetrieve dataRetrieve=new DataRetrieve(this);
        return dataRetrieve.getUserslogindata();

    }
}
