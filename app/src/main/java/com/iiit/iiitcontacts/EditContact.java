package com.iiit.iiitcontacts;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iiit.iiitcontacts.dummy.DummyContent;

import java.util.Collections;

import static com.iiit.iiitcontacts.ContactDetailFragment.ARG_ITEM_ID;
import static com.iiit.iiitcontacts.dummy.DummyContent.ITEMS;

public class EditContact extends AppCompatActivity {

    private DummyContent.DummyItem mItem;

    EditText edit_name, edit_number, edit_email;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("name");
            String number = bundle.getString("number");
            String email = bundle.getString("email");


            int idx = 0;
            for (int i = 0; i < DummyContent.ITEMS.size(); i++) {
                if (DummyContent.ITEMS.get(i).name.equalsIgnoreCase(name)) {
                    idx = i;
                    break;
                }
            }
            mItem = DummyContent.ITEMS.get(idx);

            edit_name = (EditText) findViewById(R.id.edit_name);
            edit_number = (EditText) findViewById(R.id.edit_number);
            edit_email = (EditText) findViewById(R.id.edit_email);

            button = (Button) findViewById(R.id.button_edit);

            edit_name.setText(name);
            edit_number.setText(number);
            edit_email.setText(email);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Checks for Valid Email and phone number.
                    if (edit_name.getText().toString().isEmpty()) {
                        Snackbar.make(view, "Name cannot be empty", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    } else if ((edit_number.getText().toString().isEmpty())) {
                        Snackbar.make(view, "Phone Number cannot be empty", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    } else if (edit_number.getText().toString().length() < 10 || edit_number.getText().toString().length() > 13) {
                        Snackbar.make(view, "Invalid Phone Number", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    } else if (!edit_email.getText().toString().isEmpty() && !(edit_email.getText().toString().contains("@"))) {
                        Snackbar.make(view, "Invalid Email Address", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        mItem.name = edit_name.getText().toString();
                        mItem.number = edit_number.getText().toString();
                        mItem.email = (edit_email.getText().toString());

                        //Sorting the list to display contacts in alphabetical order
                        Collections.sort(DummyContent.ITEMS);

                        Intent newIntent = new Intent(EditContact.this, ContactListActivity.class);

                        // Clearing Stack so that the previous version of Detail activity (unmodified) is not called on pressing back button.
                        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        // Passing a flag to Main Activity (ContactListAcitvity) to stop reading contacts from phone on restarting of that activity.
                        newIntent.putExtra("flag", true);
                        startActivity(newIntent);

                    }
                }
            });
        }
    }
}