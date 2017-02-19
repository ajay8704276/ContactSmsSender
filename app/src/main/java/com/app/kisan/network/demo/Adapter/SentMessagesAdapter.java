package com.app.kisan.network.demo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.kisan.network.demo.Model.SentMessageDetails;
import com.app.kisan.network.demo.R;

import java.util.List;

/**
 * Created by adyro on 19-02-2017.
 */

public class SentMessagesAdapter extends RecyclerView.Adapter<SentMessagesAdapter.MessageViewHolder> {

    List<SentMessageDetails> mSentMessageDetailsList;
    Context mContext;


    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item,parent,false);
        return new SentMessagesAdapter.MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.mSentSMSContactName.setText(mSentMessageDetailsList.get(position).getContactName());
        holder.mSentSMSDelivaredDate.setText(mSentMessageDetailsList.get(position).getCurrentDateAndTime());
    }

    @Override
    public int getItemCount() {
        return mSentMessageDetailsList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView mSentSMSContactName;
        private TextView mSentSMSDelivaredDate;

        public MessageViewHolder(View itemView) {
            super(itemView);
            mSentSMSContactName = (TextView) itemView.findViewById(R.id.sent_sms_contact_name);
            mSentSMSDelivaredDate = (TextView) itemView.findViewById(R.id.sent_sms_delivered_date);
        }
    }

    public SentMessagesAdapter(List<SentMessageDetails> sentMessageDetailsList, Context mContext){

        this.mSentMessageDetailsList = sentMessageDetailsList;
        this.mContext = mContext;
    }
}
