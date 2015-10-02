package ru.nitrobubbles.motoplaces.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.nitrobubbles.motoplaces.R;
import ru.nitrobubbles.motoplaces.model.Motoplace;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public class InfoFooterFragment extends Fragment {
    private Motoplace motoplace;

    @Bind(R.id.title_text)
    TextView titleText;

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
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_footer);
        return a;
    }
}
