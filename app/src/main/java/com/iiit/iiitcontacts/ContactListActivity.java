package com.iiit.iiitcontacts;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.iiit.iiitcontacts.dummy.DummyContent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.iiit.iiitcontacts.dummy.DummyContent.ITEMS;
import static com.iiit.iiitcontacts.dummy.DummyContent.addItem;


public class ContactListActivity extends AppCompatActivity {
    private boolean mTwoPane;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_contact_list);

        Boolean flag = false;
        // Bundle check to stop this activity from reading phone contacts on restart (When restarted from edit and create contact).
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            flag = bundle.getBoolean("flag");
        }

        if (!flag) {
            readContacts();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // floating button for create contact
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactListActivity.this, CreateContact.class));
            }
        });

        if (findViewById(R.id.contact_detail_container) != null) {
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.contact_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ContactListActivity mParentActivity;
        private final List<DummyContent.DummyItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();

                // check for tablet mode
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ContactDetailFragment.ARG_ITEM_ID, item.id);
                    ContactDetailFragment fragment = new ContactDetailFragment();
                    fragment.setArguments(arguments);

                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contact_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ContactDetailActivity.class);
                    intent.putExtra(ContactDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ContactListActivity parent,
                                      List<DummyContent.DummyItem> items,
                                      boolean twoPane) {
            mValues = ITEMS;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.contact_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            DummyContent.DummyItem dummyItem = mValues.get(position);
            Bitmap image = null;

            // Binding Contact Photo.
            if (!dummyItem.getPhoto().equalsIgnoreCase("") && dummyItem.getPhoto() != null) {
                image = BitmapFactory.decodeFile(dummyItem.getPhoto());
                if (image != null) {
                    holder.mIdView.setImageBitmap(image);
                } else {
                    image = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_contacts_launcher);
                    holder.mIdView.setImageBitmap(image);
                }
            } else {
                image = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_contacts_launcher);
                holder.mIdView.setImageBitmap(image);
            }

            holder.mContentView.setText(mValues.get(position).getName());
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (ImageView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }

    public void readContacts() {

        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        //Fetching contact details into cursor in Ascending Order
        Cursor contactCursor = this.getContentResolver().query(uri, null, null,
                null, ContactsContract.Contacts.DISPLAY_NAME + " ASC ");

        if (contactCursor.moveToFirst()) {
            do {
                //Getting contact ID
                long contactID = contactCursor.getLong(contactCursor.getColumnIndex("_ID"));
                //URI to get data of a contact
                Uri dataURI = ContactsContract.Data.CONTENT_URI;
                //Cursor for the given contact details
                Cursor dataCursor = getContentResolver().query(dataURI, null, ContactsContract.Data.CONTACT_ID + "=" + contactID, null, null);
                //Strings for retrieving all values of a contact

                String contact_name = "";

                String mobile_number = "";
                String home_number = "";
                String work_phone = "";
                String other_phone = "";

                String photo_path = "" + R.drawable.ic_launcher_background;
                byte[] photo_byte = null;

                String work_email = "";
                String homeEmail = "";
                String otherEmail = "";

                //Start getting details of a contact
                if (dataCursor.moveToFirst()) {
                    contact_name = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    do {
                        String contact_type_data = dataCursor.getString(dataCursor.getColumnIndex("mimetype"));
                        // NUMBER
                        if (contact_type_data.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {
                            String temp = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                            switch (dataCursor.getInt(dataCursor.getColumnIndex("data2"))) {
                                case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                    home_number = temp;
                                    break;
                                case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                                    work_phone = temp;
                                    break;
                                case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                    mobile_number = temp;
                                    break;
                                case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER:
                                    other_phone = temp;
                                    break;
                            }
                        }
                        //EMAIL
                        if (contact_type_data.equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {
                            String temp = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                            switch (dataCursor.getInt(dataCursor.getColumnIndex("data2"))) {
                                case ContactsContract.CommonDataKinds.Email.TYPE_HOME:
                                    homeEmail = temp;
                                    break;
                                case ContactsContract.CommonDataKinds.Email.TYPE_WORK:
                                    work_email = temp;
                                    break;
                                case ContactsContract.CommonDataKinds.Email.TYPE_OTHER:
                                    otherEmail = temp;
                            }

                        }

                        //PHOTO

                        if (contact_type_data.equals(ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)) {
                            photo_byte = dataCursor.getBlob(dataCursor.getColumnIndex("data15"));
                            if (photo_byte != null) {
                                Bitmap bitmap = BitmapFactory.decodeByteArray(photo_byte, 0, photo_byte.length);
                                File cacheDirectory = getBaseContext().getCacheDir();
                                File tmp = new File(cacheDirectory.getPath() + "/contact_pictures" + contactID + ".png");
                                try {
                                    FileOutputStream fop = new FileOutputStream(tmp);
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fop);
                                    fop.flush();
                                    fop.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                photo_path = tmp.getPath();
                            }
                        }
                    } while (dataCursor.moveToNext());

                    String contact_number;
                    if (mobile_number != "")
                        contact_number = mobile_number;
                    else if (home_number != "")
                        contact_number = home_number;
                    else if (work_phone != "")
                        contact_number = work_phone;
                    else
                        contact_number = other_phone;

                    String contact_email;
                    if (work_email != "")
                        contact_email = work_email;
                    else if (homeEmail != "")
                        contact_email = homeEmail;
                    else
                        contact_email = otherEmail;

                    DummyContent.DummyItem contact = new DummyContent.DummyItem(String.valueOf(contactID), contact_name, contact_number, contact_email, photo_path);
                    DummyContent.addItem(contact);
                }
            } while (contactCursor.moveToNext());
        }
    }
}