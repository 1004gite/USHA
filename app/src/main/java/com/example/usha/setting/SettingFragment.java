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
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.usha.MyApplication;
import com.example.usha.R;
import com.example.usha.databinding.FragmentProfileBinding;
import com.example.usha.databinding.FragmentSettingBinding;

public class SettingFragment extends Fragment {
    private FragmentSettingBinding binding;
    private Button btn_terms;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        btn_terms= binding.TermsBtn;
        btn_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_setting_to_terms);
            }
        });
        binding.btnLogout.setOnClickListener((btn) -> {
            MyApplication.logoutFunc();
            // 프로필 화면으로
            NavHostFragment.findNavController(this).popBackStack();
        });
        return view;
    }
}
