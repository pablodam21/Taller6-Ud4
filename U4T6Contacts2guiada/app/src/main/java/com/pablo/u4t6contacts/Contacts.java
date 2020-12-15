package com.pablo.u4t6contacts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Contacts extends AppCompatActivity {

    private MyContacts myContacts;

    private RecyclerView recyclerView;

    private  static String[] PERMISSION_CONTACTS = {Manifest.permission.READ_CONTACTS};

    private static final int REQUEST_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        setUI();

        if (checkPermissions()) setListAdapter();
    }

    private void setUI(){
        recyclerView = findViewById(R.id.rvContacts);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }

    private void setListAdapter(){
        myContacts = new MyContacts(this);

        recyclerView.setAdapter(new MyAdapter(myContacts));

        if (myContacts.getCount()>0){
            findViewById(R.id.tvEmptyList).setVisibility(View.INVISIBLE);
        }
    }
    private boolean checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, Contacts.PERMISSION_CONTACTS, Contacts.REQUEST_CONTACTS);
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