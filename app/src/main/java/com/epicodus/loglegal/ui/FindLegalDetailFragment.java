package com.epicodus.loglegal.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.loglegal.R;
import com.epicodus.loglegal.models.Legal;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindLegalDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.legalImageView) ImageView mLegalImageView;
    @Bind(R.id.legalNameTextView) TextView mLegalNameTextView;
    @Bind(R.id.legalCategoryTextView) TextView mLegalCategoryTextView;
    @Bind(R.id.legalRatingTextView) TextView mLegalRatingTextView;
    @Bind(R.id.legalWebsiteTextView) TextView mLegalWebsiteTextView;
    @Bind(R.id.legalPhoneTextView) TextView mLegalPhoneTextView;
    @Bind(R.id.legalAddressTextView) TextView mLegalAddressTextView;

    @Bind(R.id.sendEmailButton) Button mSendEmailButton;

    private Legal mLegal;
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    public static FindLegalDetailFragment newInstance(Legal legal) {
        FindLegalDetailFragment findLegalDetailFragment = new FindLegalDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("legal", Parcels.wrap(legal));
        findLegalDetailFragment.setArguments(args);
        return findLegalDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLegal = Parcels.unwrap(getArguments().getParcelable("legal"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_legal_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mLegal.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerInside()
                .into(mLegalImageView);

        mLegalNameTextView.setText(mLegal.getName());
        mLegalCategoryTextView.setText(android.text.TextUtils.join(", ", mLegal.getCategories()));
        mLegalRatingTextView.setText(Double.toString(mLegal.getRating()) + "/5");
        mLegalPhoneTextView.setText(mLegal.getPhone());
        mLegalAddressTextView.setText(android.text.TextUtils.join(", ", mLegal.getAddress()));

        mLegalWebsiteTextView.setOnClickListener(this);
        mLegalPhoneTextView.setOnClickListener(this);
        mLegalAddressTextView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.legalWebsiteTextView:
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(mLegal.getWebsite()));
                startActivity(webIntent);
                break;
            case R.id.legalPhoneTextView:
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + mLegal.getPhone()));
                startActivity(phoneIntent);
                break;
            case R.id.legalAddressTextView:
                Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:" + mLegal.getLatitude()
                                + "," + mLegal.getLongitude()
                                + "?q=" + mLegal.getName()));
                startActivity(mapIntent);
                break;
        }
    }

}
