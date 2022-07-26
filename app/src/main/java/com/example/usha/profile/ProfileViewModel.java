package com.example.usha.profile;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {
    private final MutableLiveData<String> user = new MutableLiveData<String>("ProfileFragment");


}
