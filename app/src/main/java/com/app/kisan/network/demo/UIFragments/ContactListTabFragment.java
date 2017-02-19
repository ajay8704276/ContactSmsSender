package com.app.kisan.network.demo.UIFragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.kisan.network.demo.Adapter.ContactListAdapter;
import com.app.kisan.network.demo.Model.Contacts;
import com.app.kisan.network.demo.R;
import com.app.kisan.network.demo.Rest.DownloadJsonResponse;
import com.app.kisan.network.demo.Utils.GenericDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adyro on 18-02-2017.
 */

public class ContactListTabFragment extends Fragment {

    private RecyclerView mRecyclerView;
    public ProgressDialog mProgressDialog;
    public List<Contacts> mContactDetailsList;
    private Contacts mContacts ;


    public ContactListTabFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.contact_list_fragment, container, false);

        initUI(view);
        return view;
    }

    private void initUI(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.contact_list_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.hasFixedSize();

        //Download Json  response from service
        downLoadJson();

        // initialise Recycler view


    }

    private void downLoadJson() {

        //initialise the
        new DownloadJsonTask().execute();

    }

    private class DownloadJsonTask extends AsyncTask<Object, Object, String> {


        @Override
        protected String doInBackground(Object... params) {

            String jsonResponse = new DownloadJsonResponse(getContext()).loadJSONFromAsset();
            return jsonResponse;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = GenericDialogBuilder.builddefaultDialog(getContext(), "Please wait ....", true, false);
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            super.onPostExecute(jsonResponse);
            mProgressDialog.dismiss();
            populateUI(jsonResponse);
        }

        private void populateUI(String jsonResponse) {
            try {
                //mProgressDialog.dismiss();
                parseJsonResponse(jsonResponse);
                ContactListAdapter mContactListAdapter = new ContactListAdapter(mContactDetailsList,getContext());
                mRecyclerView.setAdapter(mContactListAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void parseJsonResponse(String jsonResponse) throws JSONException {

        mContactDetailsList = new ArrayList<>();
        JSONObject mJsonObject = new JSONObject(jsonResponse);
        JSONArray mContactListJsonArray = mJsonObject.getJSONArray("ContactDetails");

        for(int i=0 ;i<mContactListJsonArray.length();i++){
            JSONObject mContactList = mContactListJsonArray.getJSONObject(i);
            String firstName = mContactList.getString("FirstName");
            String lastName = mContactList.getString("LastName");
            long contactNo = mContactList.getLong("contact");
            Contacts tempContacts = new Contacts();
            tempContacts.setContactNumber(contactNo);
            tempContacts.setFisrtName(firstName);
            tempContacts.setLastName(lastName);
            mContactDetailsList.add(tempContacts);
        }

    }
}
