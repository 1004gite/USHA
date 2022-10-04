package com.example.usha.setting;
import static android.content.Context.CLIPBOARD_SERVICE;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.usha.MyApplication;
import com.example.usha.R;
import com.example.usha.databinding.FragmentSettingBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SettingFragment extends Fragment {
    private FragmentSettingBinding binding;
    static String WvUrl,CopyLink;
    int padding = 50;
    DisjoinApiInterface logoutService = MyApplication.retrofit.create(DisjoinApiInterface.class);
//    DeleteUserBody dBody = new DeleteUserBody(
//            MyApplication.loginInfo.name,
//            MyApplication.loginInfo.email,
//            "01012341234",
//            "6208b04d6714d6062f0fe9ba",
//            "credit card",
//            "50,000");

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        CopyLink = "https://www.headingwarm.com";

        //이용약관
        binding.TermsBtn.setOnClickListener(view1 -> {
            WvUrl = "https://www.headingwarm.com/term2";
            Navigation.findNavController(view1).navigate(R.id.action_setting_to_terms);
        });
        //개인정보 처리 방침
        binding.PolicyBtn.setOnClickListener(view2 -> {
            WvUrl = "https://www.headingwarm.com/term";
            Navigation.findNavController(view2).navigate(R.id.action_setting_to_terms);
        });
        //링크복사
        binding.CopyBtn.setOnClickListener(view3 -> {
            ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("copyText",CopyLink);
            clipboardManager.setPrimaryClip(clipData);
            MyApplication.toastPublisher.onNext("링크가 클립보드에 복사되었습니다.");
//            Toast.makeText(getActivity().getApplicationContext(),"링크가 클립보드에 복사되었습니다.",Toast.LENGTH_SHORT).show();
        });
        binding.btnLogout.setOnClickListener((btn) -> {
            AlertDialog alertD = new AlertDialog.Builder(getContext())
                    .setTitle("로그아웃")
                    .setPositiveButton("확인", ((dialogInterface, i) -> {
                        MyApplication.logoutFunc();
                        // 프로필 화면으로
                        NavHostFragment.findNavController(this).popBackStack();
                    }))
                    .setNegativeButton("아니오", null)
                    .create();
            TextView tv = new TextView(getContext());
            tv.setText("로그아웃 하시겠습니까?");
            tv.setPadding(padding,padding,padding,padding);
            alertD.setView(tv);
            alertD.show();
        });

        binding.btnDisjoin.setOnClickListener((btn) -> {

            AlertDialog alertD = new AlertDialog.Builder(getContext())
                    .setTitle("회원탈퇴")
                    .setPositiveButton("확인", ((dialogInterface, i) -> {
                        logoutService.deleteUser("Bearer "+MyApplication.loginInfo.token, MyApplication.loginInfo.id ).enqueue(new Callback<Object>() {
                            @Override
                            public void onResponse(Call<Object> call, Response<Object> response) {
                                Log.e("회원탈퇴", response.message());
                                MyApplication.logoutFunc();
                                NavHostFragment.findNavController(getParentFragment()).popBackStack();
                            }

                            @Override
                            public void onFailure(Call<Object> call, Throwable t) {
                                MyApplication.toastPublisher.onNext("회원탈퇴 실패");
                            }
                        });
                    }))
                    .setNegativeButton("아니오", null)
                    .create();
            TextView tv = new TextView(getContext());
            tv.setText("탈퇴 하시겠습니까?");
            tv.setPadding(padding,padding,padding,padding);
            alertD.setView(tv);
            alertD.show();
        });

        binding.settingBackBtn.setOnClickListener((btn) -> {
            NavHostFragment.findNavController(this).popBackStack();
        });
        return view;
    }
}
