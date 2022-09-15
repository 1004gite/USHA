package com.example.usha.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.usha.R;
import com.example.usha.databinding.FragmentProfileBinding;
import com.example.usha.databinding.FragmentSettingBinding;

public class SettingFragment extends Fragment {
    private FragmentSettingBinding binding;
    private Button btn_terms,btn_policy;
    static String WvUrl;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        btn_terms= binding.TermsBtn;
        btn_policy = binding.PolicyBtn;
        //이용약관
        btn_terms.setOnClickListener(view1 -> {
            WvUrl = "https://www.headingwarm.com/term2";
            Navigation.findNavController(view1).navigate(R.id.action_setting_to_terms);
        });
        //개인정보 처리 방침
        btn_policy.setOnClickListener(view2 -> {
            WvUrl = "https://www.headingwarm.com/term";
            Navigation.findNavController(view2).navigate(R.id.action_setting_to_terms);
        });
        return view;
    }
}
