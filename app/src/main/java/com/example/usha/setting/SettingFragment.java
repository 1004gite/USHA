package com.example.usha.setting;
import static android.content.Context.CLIPBOARD_SERVICE;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.usha.R;
import com.example.usha.databinding.FragmentSettingBinding;

public class SettingFragment extends Fragment {
    private FragmentSettingBinding binding;
    private TextView btn_terms,btn_policy,btn_copy;
    static String WvUrl,CopyLink;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        btn_terms= binding.TermsBtn;
        btn_policy = binding.PolicyBtn;
        btn_copy = binding.CopyBtn;
        CopyLink = "https://www.headingwarm.com";

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
        //링크복사
        btn_copy.setOnClickListener(view3 -> {
            ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("copyText",CopyLink);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(getActivity().getApplicationContext(),"링크가 클립보드에 복사되었습니다.",Toast.LENGTH_SHORT).show();
        });
        return view;
    }
}
