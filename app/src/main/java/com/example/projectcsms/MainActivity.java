package com.example.projectcsms;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progress;

    private static boolean  flag = false;
    public static final String MyPreferences = "CSMSMyPref";
    public static final String CSMSusername = "CSMSUserName";
    public static final String CSMSpassword = "CSMSPassowrd";



    public CheckBox checkBox;

    private SharedPreferences sharedPreferences;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    private Button btn_login;


    public String user=null;
    public String password=null;
    private static final String SHARED_PREFS_NAME = "MyPrefs";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER_ME = "rememberMe";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth mauth=FirebaseAuth.getInstance();
        FirebaseDatabase fdb=FirebaseDatabase.getInstance();
        DatabaseReference ref=fdb.getReference("User");


        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        EditText username = findViewById(R.id.inputusername1);
        EditText Password = findViewById(R.id.inputpassword1);
        checkBox=findViewById(R.id.checkBox);

        if (sharedPreferences.getBoolean(KEY_REMEMBER_ME, false)) {
            String ema = sharedPreferences.getString(KEY_EMAIL, "");
            String password = sharedPreferences.getString(KEY_PASSWORD, "");
            username.setText(ema);
            Password.setText(password);
            checkBox.setChecked(true);

            Intent intent=new Intent(MainActivity.this, Main_Page.class);

            startActivity(intent);

        }
        checkBox= findViewById(R.id.checkBox);


        btn_login =findViewById(R.id.btn_login);
        TextView btn=findViewById(R.id.textviewsignup);

        builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_baseline_warning_24)
                .setTitle("Warning")
                .setMessage("You are going to login without checking the remember me.\nHence you need to login each time when you open the application.")
        .setCancelable(false)
        .setPositiveButton("Ok,Proceed", (dialog, which) -> btn_login.callOnClick())
        .setNeutralButton("Close", (dialog, which) -> dialog.cancel());





        btn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,Register.class)));

        TextView btn1=findViewById(R.id.Forgotpassword);
        btn1.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,forgotpassword.class)));

        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Verification in progress");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);
        progress.setProgressStyle(20);


        btn_login.setOnClickListener(v -> {
            user = username.getText().toString();
            password = Password.getText().toString();
            if(user==null || user.equals("") || password== null) {
                user = username.getText().toString().trim();
                password = Password.getText().toString().trim();
            }


            else {
                mauth.signInWithEmailAndPassword(user,password).addOnCompleteListener(task -> {
                    if (checkBox.isChecked()) {
                        // Save login credentials in SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_EMAIL, user);
                        editor.putString(KEY_PASSWORD, password);
                        editor.putBoolean(KEY_REMEMBER_ME, true);
                        editor.apply();

                    } else {
                        // Clear saved login credentials
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear().apply();
                    }
                    startActivity(new Intent(MainActivity.this,Main_Page.class));
                });

            }
        });



        if(sharedPreferences!=null)
        {
            //progress.show();
            user = sharedPreferences.getString(CSMSusername,null);
            password = sharedPreferences.getString(CSMSpassword,null);


            if(user!=null && password!= null)
            {
                checkBox.setChecked(true);
                flag=false;
                btn_login.callOnClick();
                /*Intent intent = new Intent(MainActivity.this, Main_Page.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
                progress.dismiss();*/
            }
        }

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}