package com.example.unigoiascrud;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal extends AndroidViewModel {
    private UserRepository repository;
    private LiveData<List<UserModal>> allUsers;
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
    }
    public void insert(UserModal model) {
        repository.insert(model);
    }
    public void update(UserModal model) {
        repository.update(model);
    }
    public void delete(UserModal model) {
        repository.delete(model);
    }
    public void deleteAllUsers() {
        repository.deleteAllUsers();
    }
    public LiveData<List<UserModal>> getAllUsers() {
        return allUsers;
    }
}