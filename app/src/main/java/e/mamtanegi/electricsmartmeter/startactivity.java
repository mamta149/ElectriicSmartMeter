package e.mamtanegi.electricsmartmeter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.FirebaseApp;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;

public class startactivity extends AppCompatActivity {
    private static final String TAG = "startactivity";
    LinearLayout layout1;
    LinearLayout layout2;
    TextView textView;
    private FirebaseFirestore db;
    TextInputEditText username,meterno,phoneno,id;
    Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startactivity);
        FirebaseApp.initializeApp(this);
        db=FirebaseFirestore.getInstance();

id=findViewById(R.id.id);
        username=findViewById(R.id.username);
        meterno=findViewById(R.id.meterno);
        phoneno=findViewById(R.id.phoneno);
        signup=findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int phoneno1=Integer.parseInt(phoneno.getText().toString().trim());
                int id1=Integer.parseInt(id.getText().toString().trim());
                String username1=username.getText().toString().trim();
                int meterno1=Integer.parseInt(meterno .getText().toString().trim());
                signup sign = new signup(username1,phoneno1,meterno1,id1);
                db.collection("userdetails").document().set(sign).addOnSuccessListener
                        (new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "onSuccess: ");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFail"+e.toString());
                    }
                });
            }
        });

        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        textView =(TextView) findViewById(R.id.signintext);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            }
        });


    }   }

