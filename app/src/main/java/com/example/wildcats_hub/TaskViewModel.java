package com.example.wildcats_hub;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TaskViewModel extends ViewModel {
    MutableLiveData<String> name = new MutableLiveData<>();
    MutableLiveData<String> description = new MutableLiveData<>();
    MutableLiveData<String> priorityLevel = new MutableLiveData<>();
}
