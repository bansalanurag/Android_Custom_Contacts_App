package com.iiit.iiitcontacts;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iiit.iiitcontacts.dummy.DummyContent;

import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;


public class ContactDetailFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    private DummyContent.DummyItem mItem;

    public ContactDetailFragment() {
    }

    TextView textView_email, textView_number;
    ImageView image_email, image_number;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.name);
            }
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_detail, container, false);

        AppBarLayout holder = (AppBarLayout) getActivity().findViewById(R.id.app_bar);

        if (mItem != null) {
            Bitmap image = null;
            Drawable drawable;

            // Setting the contact photo for the contact., particularly on the App Bar
            if(!mItem.getPhoto().equalsIgnoreCase("") && mItem.getPhoto() != null){
                image = BitmapFactory.decodeFile(mItem.getPhoto());

                // If contact has an existing Image show this on detail page.
                if(image!=null){
                    drawable = new BitmapDrawable(getResources(), image);
                    holder.setBackground(drawable);
                }
                // Else set the default image on the detail page.
                else{
                    image = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_contacts_launcher);
                    drawable = new BitmapDrawable(getResources(), image);
                    holder.setBackground(drawable);

                }
            }
            else{
                image = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.ic_contacts_launcher);
                drawable = new BitmapDrawable(getResources(), image);
                holder.setBackground(drawable);

            }

            //Display rest of the details
            ((TextView) rootView.findViewById(R.id.contact_detail1)).setText(mItem.name);
            ((TextView) rootView.findViewById(R.id.contact_detail2)).setText(mItem.number);
            ((TextView) rootView.findViewById(R.id.contact_detail3)).setText(mItem.email);

        }


        // Calling Email App on click of Email button
        textView_email = (TextView) rootView.findViewById(R.id.contact_detail3);

        image_email = (ImageView) rootView.findViewById(R.id.email_image);
        image_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null, chooser = null;

                String email = textView_email.getText().toString();
                // Check for empty email
                if (email.equalsIgnoreCase("")) {
                    Snackbar.make(view, "Empty Email Address", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    String[] recipent = email.split(",");

                    intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, recipent);
                    intent.setType("message/rfc822");

                    chooser = Intent.createChooser(intent, "Send Email");
                    startActivity(chooser);
                }
            }
        });


        // Calling the phone app, on the click of call button
        textView_number = (TextView) rootView.findViewById(R.id.contact_detail2);
        image_number = (ImageView) rootView.findViewById(R.id.number_image);
        image_number.setOnClickListener(new View.OnClickListener() {
            String num = textView_number.getText().toString();

            @Override
            public void onClick(View view) {
                // Check for empty number
                if (num.equalsIgnoreCase("")) {
                    Snackbar.make(view, "Empty Phone Number", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("tel:" + num));

                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }

                    getActivity().startActivity(intent);
                }
            }
        });
        return rootView;
    }
}