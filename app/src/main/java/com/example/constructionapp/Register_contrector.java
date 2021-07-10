package com.example.constructionapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Register_contrector extends AppCompatActivity {

    private FirebaseAuth rauth;
     public String URL_TO_upload="nothing ";

    public FirebaseStorage storage;
    public StorageReference storageReference;



    Uri ImageUri;
    ImageView pic_upload ;
    Button upload;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storage= FirebaseStorage.getInstance();
        storageReference= storage.getReference();


        setContentView(R.layout.activity_register_contrector);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Button next_to_dashboard = findViewById(R.id.Login_btn);
        TextView toward_login;

        toward_login= findViewById(R.id.Already_account);
        toward_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), contractor_login.class);
                startActivity(intent);
            }
        });


        ///////////////////////////////////////hooks///////////////////////////////////////////////
        Uri ImageUri;

        ProgressBar prgress= findViewById(R.id.progressBar);

        EditText u_name, phone_no, email,password;
        TextView image_url;
        u_name = findViewById(R.id.username_box2);
        phone_no = findViewById(R.id.phoneno_box2);
        email = findViewById(R.id.email_box3);
        password = findViewById(R.id.password_box2);




        //////////////Firebase/////////////////////////////////////////////////////////////////////

        rauth = FirebaseAuth.getInstance();





        //////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Pic Upload >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>///




     pic_upload= findViewById(R.id.profile_image);



        pic_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChoosePicture();
            }
        });





        /////////////////Next Button//////////////////////////////////////////////////////////////





        next_to_dashboard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                ///////////////////////////empty test//////////////////////////////////////////////////////


                String username_t = u_name.getText().toString().trim();
                String phone_t = phone_no.getText().toString().trim();
                String Email_t = email.getText().toString().trim();
                String password_t = password.getText().toString().trim();




                if (username_t.isEmpty())
                {
                    u_name.setError("Enter the user name");
                    u_name.requestFocus();
                    return;
                }
               else if (phone_t.isEmpty())
                {
                    phone_no.setError("Enter the phone No");
                    phone_no.requestFocus();
                    return;
                }
               else if (Email_t.isEmpty())
                {
                    email.setError("Enter the Email");
                    email.requestFocus();
                    return;
                }


               prgress.setVisibility(View.VISIBLE);
               rauth.createUserWithEmailAndPassword(Email_t, password_t)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {

                               if (task.isSuccessful()){

                                   user User= new user(username_t, Email_t, phone_t, URL_TO_upload);

                                   FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance()
                                           .getCurrentUser().getUid()).setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {

                                           if (task.isSuccessful())
                                           {
                                             //  UploadImage();
                                               Toast.makeText(Register_contrector.this, "user Register sucessffuly", Toast.LENGTH_LONG).show();

                                               prgress.setVisibility(View.GONE);



                                               Intent intent= new Intent(getApplicationContext(), Singin_contrector.class);
                                               startActivity(intent);


                                           }
                                           else {

                                               Toast.makeText(Register_contrector.this, "User not registerd", Toast.LENGTH_LONG).show();
                                               prgress.setVisibility(View.GONE);

                                           }

                                       }
                                   });


                               }

                           }
                       });








            }


        });





    }

    private void ChoosePicture() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){

            ImageUri = data.getData();
            pic_upload.setImageURI(ImageUri);




        }
    }

    private void UploadImage() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploding Image...");
        pd.show();

        final String randomKey = UUID.randomUUID().toString();

        StorageReference mountainsRef = storageReference.child("/image");
        mountainsRef.putFile(ImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
              ///    Snackbar.make(findViewById(R.id.Register_contractor), "Image uploaded", Snackbar.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(Register_contrector.this, "Uploading Failed: ", Toast.LENGTH_LONG).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                final double progresspercentage =(100.00 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                pd.setMessage("percentage "+(int) progresspercentage+ "%");
            }
        });



    }
}
