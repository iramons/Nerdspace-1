package br.com.nerdspace;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
//    private Toolbar mToolbar;

//    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

//        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle("NerdSpace");

//        mViewPager = (ViewPager) findViewById(R.id.main_tabPager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        //verifica se o usuário esta conectado(non-null) e age de acordo.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            Intent startIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(startIntent);
            finish();
        } else {
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

//        getMenuInflater().inflate(R.menu.main_menu, menu);

//        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
//
//        if(item.getItemId() == R.id.main_logout_btn) {
//            FirebaseAuth.getInstance().signOut();
//            sendToStart();
//
//        }

//        return true;
    }

}

