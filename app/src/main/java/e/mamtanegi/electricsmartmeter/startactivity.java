package e.mamtanegi.electricsmartmeter;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.firebase.FirebaseApp;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;

public class startactivity extends AppCompatActivity {
    LinearLayout layout1;
    LinearLayout layout2;
    TextView textView;
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseFirestore db;
    TextInputEditText username,createpassword,confirmpassword,meterno,email;
    Button signup;
    TextView generatebill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startactivity);
        FirebaseApp.initializeApp(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        db=FirebaseFirestore.getInstance();
       generatebill =findViewById(R.id.generatebill);

        username=findViewById(R.id.username);
        createpassword=findViewById(R.id.createpwd);
        confirmpassword=findViewById(R.id.confirmpwd);
        meterno=findViewById(R.id.meterno);
        email=findViewById(R.id.email);
        signup=findViewById(R.id.signup);
        generatebill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(startactivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=username.getText().toString().trim();
                String createpwd=createpassword.getText().toString().trim();
                String confirmpwd=confirmpassword.getText().toString().trim();
                int meterno1=Integer.parseInt(meterno .getText().toString().trim());
                signup sign = new signup(uname,createpwd,confirmpwd,meterno1);
                db.collection("userdetails").document("users").set(sign).addOnSuccessListener
                        (new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });
            }
        });





        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        textView =(TextView) findViewById(R.id.signuptext);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            }
        });


    }   }

