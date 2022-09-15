package com.example.usha.setting;

import static com.example.usha.setting.SettingFragment.WvUrl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.usha.R;
import com.example.usha.databinding.FragmentProfileBinding;
import com.example.usha.databinding.FragmentSettingBinding;
import com.example.usha.databinding.FragmentTermsOfServiceBinding;

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
