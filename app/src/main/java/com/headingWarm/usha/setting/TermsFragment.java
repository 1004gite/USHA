package com.headingWarm.usha.setting;

import static com.headingWarm.usha.setting.SettingFragment.WvUrl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

import com.headingWarm.usha.R;
import com.headingWarm.usha.databinding.FragmentProfileBinding;
import com.headingWarm.usha.databinding.FragmentSettingBinding;
import com.headingWarm.usha.databinding.FragmentTermsOfServiceBinding;

public class TermsFragment extends Fragment {
    private FragmentTermsOfServiceBinding binding;
    private WebView wv;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {

        binding = FragmentTermsOfServiceBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        wv= binding.wvLayout;
        wv.getSettings().setJavaScriptEnabled(true);

        wv.loadUrl(WvUrl);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
