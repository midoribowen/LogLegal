package com.epicodus.loglegal.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.loglegal.R;
import com.epicodus.loglegal.models.Legal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LegalListAdapter extends RecyclerView.Adapter<LegalListAdapter.LegalViewHolder> {
    private ArrayList<Legal> mLegalOffices = new ArrayList<>();
    private Context mContext;

    public LegalListAdapter(Context context, ArrayList<Legal> legalOffices) {
        mContext = context;
        mLegalOffices = legalOffices;
    }

    @Override
    public LegalListAdapter.LegalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.legal_list_item, parent, false);
        LegalViewHolder viewHolder = new LegalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LegalListAdapter.LegalViewHolder holder, int position) {
        holder.bindLegal(mLegalOffices.get(position));
    }

    @Override
    public int getItemCount() {
        return mLegalOffices.size();
    }

    public class LegalViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.legalImageView) ImageView mLegalImageView;
        @Bind(R.id.legalNameTextView) TextView mLegalNameTextView;
        @Bind(R.id.legalCategoryTextView) TextView mLegalCategoryTextView;
        @Bind(R.id.legalAddressTextView) TextView mLegalAddressTextView;
        @Bind(R.id.legalRatingTextView) TextView mLegalRatingTextView;

        private Context mContext;

        public LegalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindLegal(Legal legal) {
            Picasso.with(mContext).load(legal.getImageUrl()).fit().centerCrop().into(mLegalImageView);
            if (legal.getName().length() > 31) {
                mLegalNameTextView.setText(legal.getName().substring(0, 31) + "...");
            } else {
                mLegalNameTextView.setText(legal.getName());
            }
            mLegalCategoryTextView.setText(android.text.TextUtils.join(", ", legal.getCategories()));
            mLegalAddressTextView.setText(android.text.TextUtils.join(", ", legal.getShortAddress()));
            mLegalRatingTextView.setText("Rating: " + legal.getRating() + "/5");
        }
    }
}
