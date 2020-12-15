package com.pablo.u4t6contacs;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private MyContacs myContacts;

    private ContactItem contactItem;
    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewId;
        TextView textViewNombre;
        TextView textViewNumero;
        ImageView imageView;

        public MyViewHolder(View contactview) {
            super(contactview);
            textViewId = contactview.findViewById(R.id.textViewId);
            textViewNombre = contactview.findViewById(R.id.textViewNombre);
            textViewNumero = contactview.findViewById(R.id.textViewNumero);
            imageView = contactview.findViewById(R.id.imageContact);
        }

        public void bind(ContactItem contactItem){
            textViewId.setText(String.valueOf(contactItem.getId()));
            textViewNombre.setText(contactItem.getNombre());
            textViewNumero.setText(contactItem.getNumero());

            String image = contactItem.getFoto();
            if (image != null) {
                imageView.setImageURI(Uri.parse(image));
            } else {
                imageView.setImageResource(R.drawable.logoluffy);
            }
        }
    }

    public MyAdapter(MyContacs myContacts) {
        this.myContacts = myContacts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactsitemlayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.bind(myContacts.getDataContactData(position));

    }

    @Override
    public int getItemCount() {
        return myContacts.getCount();
    }
}