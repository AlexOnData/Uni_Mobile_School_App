package com.test.cnim_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private Button login_button;
    private SwitchCompat switchRem;
    private TextInputLayout username, password;
    private FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_button = findViewById(R.id.aut_btn);
        switchRem = findViewById(R.id.switch_rem);

        username = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);
        firebaseAuth = FirebaseAuth.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences("saveData", MODE_PRIVATE);
        switchRem.setChecked(sharedPreferences.getBoolean("valueRem", false));

        switchRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchRem.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("saveData", MODE_PRIVATE).edit();
                    editor.putBoolean("valueRem", true);
                    editor.apply();
                    switchRem.setChecked(true);
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("saveData", MODE_PRIVATE).edit();
                    editor.putBoolean("valueRem", false);
                    editor.apply();
                    switchRem.setChecked(false);
                }
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enterusername = username.getEditText().getText().toString();
                String enterpassword = password.getEditText().getText().toString();

                if (enterusername.isEmpty()) {
                    username.setError("*Camp obligatoriu");
                    return;
                } else {
                    username.setError(null);
                }
                if (enterpassword.isEmpty()){
                    password.setError("*Camp obligatoriu");
                    return;
                } else {
                    password.setError(null);
                }

                databaseReference.child("Elevi").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(enterusername)) {
                            String getPassword = snapshot.child(enterusername).child("Parola").getValue(String.class);
                            if (getPassword.equals(enterpassword)) {
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                finish();
                            } else {
                                password.setError("Parola invalida!");;
                            }
                        } else {
                            username.setError("Nume utilizator invalid!");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }


//    public void loginUser(View v) {
//        if (!validateFields()) {
//            return;
//        } else {
//            String _username = username.getEditText().getText().toString().trim();
//            String _password = password.getEditText().getText().toString().trim();
//
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Clase");
//
//            Query checkUser = reference.orderByChild("Nume").equalTo(_password);
//
//            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.exists()) {
//                        String passwordFromDatabase = snapshot.child(_username).child("Parola").getValue(String.class);
//
//                        if (passwordFromDatabase.equals(_username)) {
//
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }
//    }

}