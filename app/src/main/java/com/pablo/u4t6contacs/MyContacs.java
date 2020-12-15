package com.pablo.u4t6contacs;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.ImageView;

import java.util.ArrayList;

public class MyContacs {
    private ArrayList<ContactItem> myDataSet;
    private Context context;

    public MyContacs(Context context){
        this.context = context;
        this.myDataSet = getContacts();
    }

    private ArrayList<ContactItem> getContacts() {
        ArrayList<ContactItem> contactsList = new ArrayList<>();

        ContentResolver contentResolver = context.getContentResolver();

        String[] projection = new String[]{ContactsContract.Data.CONTACT_ID,
                ContactsContract.Data.LOOKUP_KEY,
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.TYPE,
                ContactsContract.Data.PHOTO_THUMBNAIL_URI,
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI
        };

        String selectionFilter = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND " +
                ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

        Cursor contactsCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI,
                projection,
                selectionFilter,
                null,
                ContactsContract.Data.DISPLAY_NAME + " ASC");
        if (contactsCursor != null){
            int nameIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME);
            int numberIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int idcontacnt = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.CONTACT_ID);
            int idPhotoContact = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.PHOTO_URI);

            while(contactsCursor.moveToNext()){
                String name = contactsCursor.getString(nameIndex);
                String number = contactsCursor.getString(numberIndex);
                String contactId = contactsCursor.getString(idcontacnt);
                String contactPhoto = contactsCursor.getString(idPhotoContact);
                int idContact = Integer.parseInt(contactId);

                ContactItem contactItem = new ContactItem(idContact,name,number,contactPhoto);
                contactsList.add(contactItem);
            }
            contactsCursor.close();
        }
        return contactsList;
    }
    public ContactItem getDataContactData (int position){
        return myDataSet.get(position);
    }

    public int getCount(){
        return myDataSet.size();
    }
}
