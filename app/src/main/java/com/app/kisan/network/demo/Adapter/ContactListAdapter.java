package com.app.kisan.network.demo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.kisan.network.demo.Model.Contacts;
import com.app.kisan.network.demo.R;
import com.app.kisan.network.demo.UIActivity.ContactInfoActivity;

import java.util.List;

/**
 * Created by adyro on 18-02-2017.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder> {

    private List<Contacts> mContactsList;
    private Context mContext;
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String CONTACT_NUMBER = "CONTACT_NUMBER";


    @Override
    public ContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item,parent,false);
        return new ContactListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactListViewHolder holder, int position) {
        final String firstName = mContactsList.get(position).getFisrtName();
        final String lastName = mContactsList.get(position).getLastName();
        final long contactNo = mContactsList.get(position).getContactNumber();
        holder.mFirstName.setText(firstName);
        holder.mLastName.setText(lastName);
        holder.mContactCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start new Activity
                Intent intent = new Intent(mContext, ContactInfoActivity.class);
                intent.putExtra(FIRST_NAME , firstName);
                intent.putExtra(LAST_NAME , lastName);
                intent.putExtra(CONTACT_NUMBER , contactNo);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mContactsList.size();
    }

    public class ContactListViewHolder extends RecyclerView.ViewHolder {
        private TextView mFirstName;
        private TextView mLastName;
        private CardView mContactCardView;
        public ContactListViewHolder(View itemView) {
            super(itemView);
            mContactCardView = (CardView) itemView.findViewById(R.id.contact_list_cv);
            mFirstName = (TextView) itemView.findViewById(R.id.first_name);
            mLastName = (TextView) itemView.findViewById(R.id.last_name);


        }
    }

    public ContactListAdapter(List<Contacts> mContactsList,Context mContext){
        this.mContactsList = mContactsList;
        this.mContext = mContext;
    }
}
