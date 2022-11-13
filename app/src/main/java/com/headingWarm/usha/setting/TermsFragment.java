package com.headingWarm.usha.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.fragment.app.Fragment;
import com.headingWarm.usha.databinding.FragmentTermsOfServiceBinding;

public class TermsFragment extends Fragment {
    private FragmentTermsOfServiceBinding binding;
    private WebView wv;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentTermsOfServiceBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        View view = binding.getRoot();
        String WvUrl = getArguments().getString("url");

        wv= binding.wvLayout;
        wv.getSettings().setJavaScriptEnabled(true);

        wv.loadUrl(WvUrl);
        return view;
    }
}
