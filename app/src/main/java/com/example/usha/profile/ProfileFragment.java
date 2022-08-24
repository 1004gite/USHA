package com.example.usha.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.usha.MyApplication;
import com.example.usha.R;
import com.example.usha.databinding.FragmentProfileBinding;

//마이프로필 블록
public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private ImageButton btn_setting;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
//        if(!MyApplication.loginInfo.getLoginned()){
//            //stack에서 profile fragment가 나올때까지 stack을 pop한다.
//            // inclusive == true이면 인자로 넣은 fragment까지 pop한다.
//            NavOptions navOptions = new NavOptions.Builder()
//                    .setPopUpTo(R.id.profile,true).build();
//            NavHostFragment.findNavController(this).navigate(R.id.loginMain, null, navOptions);
//        }

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //설정버튼을 네비게이션으로 구현하기
//        btn_setting=view.findViewById(R.id.profileSettingBtn);
        btn_setting= binding.profileSettingBtn;
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_profile_to_setting);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}