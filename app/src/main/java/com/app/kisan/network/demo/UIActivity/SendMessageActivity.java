package com.app.kisan.network.demo.UIActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.kisan.network.demo.Adapter.ContactListAdapter;
import com.app.kisan.network.demo.Model.SentMessageDetails;
import com.app.kisan.network.demo.R;
import com.app.kisan.network.demo.Utils.DatabaseHelper;
import com.app.kisan.network.demo.Utils.PhoneNumberValidator;
import com.app.kisan.network.demo.Utils.RandomNumberGenerator;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by adyro on 18-02-2017.
 */

public class SendMessageActivity extends AppCompatActivity {

    private String defaultText = "“Hi. Your OTP is :" + RandomNumberGenerator.randomNumber();
    private long mContactNumber;
    private EditText mComposeTxtView;
    private Button mSendSMSBtn;
    private long length;
    private String mMessageText;
    private String firstName ,lastName;
    private String fullName;
    private DatabaseHelper mDatabaseHelper;
    public static final String ACCOUNT_SID = "AC65b93ea784c7d42f83230a8b03e0f35d";
    public static final String AUTH_TOKEN = "386ee20864fbd447911418718a366aff";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_sms);
        if (getIntent().getExtras() != null) {
            mContactNumber = getIntent().getExtras().getLong(ContactListAdapter.CONTACT_NUMBER);
            length = Long.valueOf(mContactNumber);
            firstName = getIntent().getExtras().getString(ContactListAdapter.FIRST_NAME);
            lastName = getIntent().getExtras().getString(ContactListAdapter.LAST_NAME);
            fullName = firstName +"  "+lastName;
        }

        mDatabaseHelper = new DatabaseHelper(this);


        initUI();

    }

    private void initUI() {

        mComposeTxtView = (EditText) findViewById(R.id.txt_message);
        mSendSMSBtn = (Button) findViewById(R.id.send_compose_message_btn);
        /*if (!PhoneNumberValidator.isValidPhoneNumber(Long.toString(mContactNumber))) {
            Toast.makeText(getApplicationContext(), "Invalid Number unable to send message", Toast.LENGTH_LONG).show();
        } else {*/
            // Compose and send message
            mSendSMSBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMessageText = mComposeTxtView.getText().toString();
                    if (mMessageText != null) {
                        // initialise twillio
                        sendMessage(mMessageText);
                    } else {
                        Toast.makeText(getApplicationContext(), "Empty message unable to send message", Toast.LENGTH_LONG).show();
                    }
                }
            });
       // }
    }


    private void sendMessage(String message) {
        String body = defaultText + message;
        String from = "+1 678-515-5998";
        String to = Long.toString(mContactNumber);

        String base64EncodedCredentials = "Basic " + Base64.encodeToString(
                (ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes(), Base64.NO_WRAP
        );

        Map<String, String> data = new HashMap<>();
        data.put("From", from);
        data.put("To", to);
        data.put("Body", body);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.twilio.com/2010-04-01/")
                .build();
        TwilioApi api = retrofit.create(TwilioApi.class);

        api.sendMessage(ACCOUNT_SID, base64EncodedCredentials, data).enqueue(new Callback<ResponseBody>() {
            private Call<ResponseBody> call;
            private Response<ResponseBody> response;

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                this.call = call;
                this.response = response;
                final String dateAndTimeStamp ;
                if(response.isSuccessful()){
                    dateAndTimeStamp = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                    SentMessageDetails mSentMessageDetails = new SentMessageDetails(fullName,dateAndTimeStamp);
                    mDatabaseHelper.addContact(mSentMessageDetails);
                }else {
                    Toast.makeText(getApplicationContext(),"Unable to send message response error " +response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG", "onFailure");
            }
        });
    }

    interface TwilioApi {
        @FormUrlEncoded
        @POST("Accounts/{ACCOUNT_SID}/SMS/Messages")
        Call<ResponseBody> sendMessage(
                @Path("ACCOUNT_SID") String accountSId,
                @Header("Authorization") String signature,
                @FieldMap Map<String, String> metadata
        );
    }

}
