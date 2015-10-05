package ru.nitrobubbles.motoplaces.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.nitrobubbles.motoplaces.R;
import ru.nitrobubbles.motoplaces.model.Motoplace;
import ru.nitrobubbles.motoplaces.model.PlaceType;
import ru.nitrobubbles.motoplaces.support.GeoSupport;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public class InfoFooterFragment extends Fragment {
    private Motoplace motoplace;

    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.subtitle_text)
    TextView subTitleText;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.web_address)
    TextView webaddress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.info_footer_fragment, null);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        this.motoplace = (Motoplace) bundle.getSerializable("motoplace");
        titleText.setText(motoplace.getTitle());
        subTitleText.setText(motoplace.getSubscription());
        if(TextUtils.isEmpty(motoplace.getAddress())) {
            GeoSupport.loadAddress(motoplace.getPosition())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        address.setText(s);
                    });
        }else {
            address.setText(motoplace.getAddress());
        }
        webaddress.setText(motoplace.getSite());
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_footer);
        return a;
    }
}
