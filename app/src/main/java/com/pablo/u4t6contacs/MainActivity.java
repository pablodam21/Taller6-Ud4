package com.pablo.u4t6contacs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private MyContacs myContacts;

    private RecyclerView recyclerView;

    private  static String[] PERMISSION_CONTACTS = {Manifest.permission.READ_CONTACTS};

    private static final int REQUEST_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        setUI();

        if (checkPermissions())
            setListAdapter();
    }

    private void setUI(){
        recyclerView = findViewById(R.id.rvContacts);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

    }

    private void setListAdapter(){
        myContacts = new MyContacs(this);

        recyclerView.setAdapter(new MyAdapter(myContacts));

        if (myContacts.getCount()>0){
            findViewById(R.id.tvEmptyList).setVisibility(View.INVISIBLE);
        }
    }

    private boolean checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, MainActivity.PERMISSION_CONTACTS, MainActivity.REQUEST_CONTACTS);
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CONTACTS){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                setListAdapter();
            else
                Toast.makeText(this,getString(R.string.contacts_read_right_required), Toast.LENGTH_LONG).show();
        }else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}