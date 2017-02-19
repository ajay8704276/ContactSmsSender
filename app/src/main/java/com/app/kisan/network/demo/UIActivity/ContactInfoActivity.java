package com.app.kisan.network.demo.UIActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.kisan.network.demo.Adapter.ContactListAdapter;
import com.app.kisan.network.demo.R;

/**
 * Created by adyro on 18-02-2017.
 */

public class ContactInfoActivity extends AppCompatActivity {

    private String firstName;
    private String lastName;
    private long contactNo;
    private TextView mFullNameTxtView;
    private TextView mContactNoTxtView;
    private Button mSendMessageBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details);

        if (getIntent().getExtras() != null) {
            firstName = getIntent().getExtras().getString(ContactListAdapter.FIRST_NAME);
            lastName = getIntent().getExtras().getString(ContactListAdapter.LAST_NAME);
            contactNo = getIntent().getExtras().getLong(ContactListAdapter.CONTACT_NUMBER);
        }

        initUI();
    }

    private void initUI() {
        mFullNameTxtView = (TextView) findViewById(R.id.name);
        mContactNoTxtView = (TextView) findViewById(R.id.contact_no);
        mSendMessageBtn = (Button) findViewById(R.id.send_message_btn);
        mFullNameTxtView.setText(firstName + lastName);
        mContactNoTxtView.setText(Long.toString(contactNo));
        mSendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactInfoActivity.this,SendMessageActivity.class);
                intent.putExtra(ContactListAdapter.CONTACT_NUMBER,contactNo);
                intent.putExtra(ContactListAdapter.FIRST_NAME,firstName);
                intent.putExtra(ContactListAdapter.LAST_NAME,lastName);
                startActivity(intent);
            }
        });
    }
}
