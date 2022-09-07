package com.example.usha.setting;

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
    private WebView mWebView;
    private Button btn_terms;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {

        binding = FragmentTermsOfServiceBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mWebView= binding.wvLayout;
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://www.headingwarm.com/");
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
