package com.headingWarm.usha.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.headingWarm.usha.MyApplication;
import com.headingWarm.usha.R;
import com.headingWarm.usha.databinding.FragmentProfileBinding;

//마이프로필 블록
public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        if(!MyApplication.loginInfo.getLoginned()){
            //stack에서 profile fragment가 나올때까지 stack을 pop한다.
            // inclusive == true이면 인자로 넣은 fragment까지 pop한다.
            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.profile,true).build();
            NavHostFragment.findNavController(this).navigate(R.id.loginMain, null, navOptions);
        }

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this.getViewLifecycleOwner());
        View root = binding.getRoot();

        binding.setViewModel(
                new ViewModelProvider(this,
                        new ProfileViewModel.ProfileVMFac(new ProfileModel(NavHostFragment.findNavController(this))))
                        .get(ProfileViewModel.class)
        );


        return root;
    }

}