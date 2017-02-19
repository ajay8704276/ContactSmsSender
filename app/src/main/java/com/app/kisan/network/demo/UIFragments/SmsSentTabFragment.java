package com.app.kisan.network.demo.UIFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.kisan.network.demo.Adapter.SentMessagesAdapter;
import com.app.kisan.network.demo.Model.SentMessageDetails;
import com.app.kisan.network.demo.R;
import com.app.kisan.network.demo.Utils.DatabaseHelper;
import com.app.kisan.network.demo.Utils.GenericDialogBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adyro on 18-02-2017.
 */

public class SmsSentTabFragment extends Fragment {

    private List<SentMessageDetails> mSentMessageDetailsList = new ArrayList<>();
    private RecyclerView mSentMessageRecyclerView;
    private ProgressDialog mProgressDialog;
    private DatabaseHelper mDatabaseHelper;
    public SmsSentTabFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_delivered_fragment,container,false);
        mDatabaseHelper = new DatabaseHelper(getContext());
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        mSentMessageRecyclerView = (RecyclerView) view.findViewById(R.id.sent_sms_recycler_view);
        mSentMessageRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSentMessageRecyclerView.hasFixedSize();

        retrieveDataFromDataBase();



/*
        mProgressDialog = GenericDialogBuilder.builddefaultDialog(getContext(),"Please wait while retrieve..",false,true);
        mProgressDialog.show();*/

    }

    private void retrieveDataFromDataBase() {
            
        new RetrieveData().execute();

    }

    public class RetrieveData extends AsyncTask<Object, Object, List<SentMessageDetails>> {


        @Override
        protected List<SentMessageDetails> doInBackground(Object... params) {
            mSentMessageDetailsList = mDatabaseHelper.getAllMessageDetails();
            return mSentMessageDetailsList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = GenericDialogBuilder.builddefaultDialog(getContext(), "Please wait ....", false, false);
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(List<SentMessageDetails> sentMessageDetailsList) {
            super.onPostExecute(sentMessageDetailsList);
            mProgressDialog.dismiss();
            populateUI(sentMessageDetailsList);
        }
    }

    private void populateUI(List<SentMessageDetails> sentMessageDetailsList) {
        SentMessagesAdapter mSentMessagesAdapter = new SentMessagesAdapter(sentMessageDetailsList,getContext());
        mSentMessageRecyclerView.setAdapter(mSentMessagesAdapter);
    }
}
