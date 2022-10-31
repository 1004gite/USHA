package com.headingWarm.usha.profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {
    private final MutableLiveData<String> user = new MutableLiveData<String>("ProfileFragment");


}
