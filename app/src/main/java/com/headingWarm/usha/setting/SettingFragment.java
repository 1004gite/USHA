package com.headingWarm.usha.setting;
import static android.content.Context.CLIPBOARD_SERVICE;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.headingWarm.usha.MyApplication;
import com.headingWarm.usha.R;
import com.headingWarm.usha.databinding.FragmentSettingBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingFragment extends Fragment {
    private FragmentSettingBinding binding;
    private NavController navController;
    private Bundle bdterm,bdPolicy;
    ClipboardManager clipboardManager;
    int padding = 50;
    DisjoinApiInterface logoutService = MyApplication.retrofit.create(DisjoinApiInterface.class);
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        navController = NavHostFragment.findNavController(this);
        clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);

        bdterm = new Bundle();
        bdterm.putString("url","https://www.headingwarm.com/term2");

        bdPolicy = new Bundle();
        bdPolicy.putString("url","https://www.headingwarm.com/term");

        //이용약관
        binding.TermsBtn.setOnClickListener((v)->{navController.navigate(R.id.action_setting_to_terms, bdterm);});
        //개인정보 처리 방침
        binding.PolicyBtn.setOnClickListener((v) ->{navController.navigate(R.id.action_setting_to_terms, bdPolicy);});
        //링크복사
        binding.CopyBtn.setOnClickListener(view3 -> {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("copyText","https://www.headingwarm.com"));
            MyApplication.toastPublisher.onNext("링크가 클립보드에 복사되었습니다.");
        });
        binding.btnLogout.setOnClickListener((btn) -> {
            AlertDialog alertD = new AlertDialog.Builder(context)
                    .setTitle("로그아웃")
                    .setPositiveButton("확인", ((dialogInterface, i) -> {
                        MyApplication.logoutFunc();
                        // 프로필 화면으로
                        NavHostFragment.findNavController(this).popBackStack();
                    }))
                    .setNegativeButton("아니오", null)
                    .create();
            TextView tv = new TextView(context);
            tv.setText("로그아웃 하시겠습니까?");
            tv.setPadding(padding,padding,padding,padding);
            alertD.setView(tv);
            alertD.show();
        });

        binding.btnDisjoin.setOnClickListener((btn) -> {

            AlertDialog alertD = new AlertDialog.Builder(context)
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
            TextView tv = new TextView(context);
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
