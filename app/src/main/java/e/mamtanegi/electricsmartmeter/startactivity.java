package e.mamtanegi.electricsmartmeter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class startactivity extends AppCompatActivity {
    LinearLayout layout1;
    LinearLayout layout2;
    TextView textView,textViewSignUp;
    EditText editTextPhone, editTextCode;
    private static final String TAG = "startactivity";
    TextInputEditText username;
    TextInputEditText uname;

    FirebaseAuth mAuth;

    String codeSent;
        private FirebaseFirestore db;
    TextInputEditText meterno;
    Button signup;
    ArrayList<UserLoginData> usersdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startactivity);
        mAuth = FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        layout2.setVisibility(View.VISIBLE);

        textView=findViewById(R.id.signintext);
        textViewSignUp =findViewById(R.id.signuptext);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            }
        });
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
            }
        });

        FirebaseApp.initializeApp(this);

       // meterno=findViewById(R.id.meterno);
       editTextCode = findViewById(R.id.editTextCode);
        editTextPhone = findViewById(R.id.editTextPhone);
       // username=findViewById(R.id.username);

        findViewById(R.id.buttonGetVerificationCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendVerificationCode();
                

                
            }
        });


        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifySignInCode();
            }
        });
    }


    private boolean userDetailsVerify(){

        usersdata = retrieveData();
        for (UserLoginData userLoginData:usersdata){
            if(userLoginData.phonenumber==Double.parseDouble(editTextPhone.getText().toString())){
                Log.d(TAG, "onClick: successfull login");
                return true;

            }
        }
        return  false;
    }
    
    private ArrayList<UserLoginData> retrieveData(){
        DataRetrieve dataRetrieve=new DataRetrieve(this);
        return dataRetrieve.getUserslogindata();
        
    }

    private void verifySignInCode() {
        String code = editTextCode.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            signup sign = new signup(Float.parseFloat(editTextPhone.getText().toString().trim()));

                            if(userDetailsVerify()) {

                                Intent intent = new Intent(startactivity.this, NewMainActivity.class);
                                startActivity(intent);
                                Log.d(TAG, "onComplete: mmmm");
                            }else {
                                Toast.makeText(getApplicationContext()," user details not matching", Toast.LENGTH_LONG).show();
                            }
                            //here you can open new activity
                           } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(),
                                        "Incorrect Verification Code ", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    private void sendVerificationCode() {

        String phone = "+91" + editTextPhone.getText().toString();

        if (phone.isEmpty()) {
            editTextPhone.setError("Phone number is required");
            editTextPhone.requestFocus();
            return;
        }

        if (phone.length() < 10) {
            editTextPhone.setError("Please enter a valid phone");
            editTextPhone.requestFocus();
            return;
        }


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }


    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.d(TAG, e.toString());
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;

        }
    };

}

