package br.com.nerdspace;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private android.support.v7.widget.Toolbar mToolbar;

    private ImageView mProfileImage;
    private TextView mProfileName, mProfileStatus, mProfileFriendsCount;
    private Button mProfileSendRequestButton;

    private DatabaseReference mUsersDatabase;

    //botao sair
    private Button buttonLogout;

    private ProgressDialog mProgressDialog;

//    //precisa inserir para Toolbar
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //auth com firebase
        mAuth = FirebaseAuth.getInstance();

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("NerdSpace");

//        //test for chat video
//        String user_id = getIntent().getStringExtra("user_id");
//
//        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(user_id);

        //findViewById
        mProfileImage = (ImageView) findViewById(R.id.profile_image);
        mProfileName = (TextView) findViewById(R.id.profile_display_name);
        mProfileStatus = (TextView) findViewById(R.id.profile_status);
        mProfileFriendsCount = (TextView) findViewById(R.id.profile_totalFriends);
        mProfileSendRequestButton = (Button) findViewById(R.id.profile_send_req_btn);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);


//        mProgressDialog = new ProgressDialog(this);
//        mProgressDialog.setTitle("Loading user data");
//        mProgressDialog.setMessage("Please wait while we load the user daataa");
//        mProgressDialog.setCanceledOnTouchOutside(false);
//        mProgressDialog.show();

//        mUsersDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String displayName = dataSnapshot.child("name").getValue().toString();
//                String status = dataSnapshot.child("status").getValue().toString();
//                String image = dataSnapshot.child("image").getValue().toString();
//
//                mProfileName.setText(displayName);
//                mProfileStatus.setText(status);
////                Picasso.with(ProfileActivity.this).load(image).placeholder(R.drawable.com_facebook_profile_picture_blank_square).into(mProfileImage);
////                Picasso.get()
////                        .load(image)
////                        .placeholder(R.drawable.com_facebook_profile_picture_blank_square)
////                        .error(R.drawable.com_facebook_profile_picture_blank_square)
////                        .into(mProfileImage);
//
////                mProgressDialog.dismiss();
//            }

//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }/***fim onCreate method ***/

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null) {
            Intent startIntent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(startIntent);
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.main_logout_btn){
            mAuth.signOut();
            LoginManager.getInstance().logOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        return true;
    }

    @Override
    public void onClick(View view) {

        if(view == buttonLogout){
            mAuth.signOut();
            LoginManager.getInstance().logOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

    }

} /***fim ProfileActivity ***/

