package com.example.unigoiascrud;
import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
public class UserRepository {
    private Dao dao;
    private LiveData<List<UserModal>> allUsers;
    public UserRepository(Application application) {
        UserDatabase database = UserDatabase.getInstance(application);
        dao = database.Dao();
        allUsers = dao.getAllUsers();
    }
    public void insert(UserModal model) {
        new InsertUserAsyncTask(dao).execute(model);
    }
    public void update(UserModal model) {
        new UpdateUserAsyncTask(dao).execute(model);
    }
    public void delete(UserModal model) {
        new DeleteUserAsyncTask(dao).execute(model);
    }
    public void deleteAllUsers() {
        new DeleteAllUsersAsyncTask(dao).execute();
    }
    public LiveData<List<UserModal>> getAllUsers() {
        return allUsers;
    }
    private static class InsertUserAsyncTask extends AsyncTask<UserModal,
            Void, Void> {
        private Dao dao;
        private InsertUserAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(UserModal... model) {dao.insert(model[0]);
            return null;
        }
    }
    private static class UpdateUserAsyncTask extends AsyncTask<UserModal,
            Void, Void> {
        private Dao dao;
        private UpdateUserAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(UserModal... models) {
            dao.update(models[0]);
            return null;
        }
    }
    private static class DeleteUserAsyncTask extends AsyncTask<UserModal,
            Void, Void> {
        private Dao dao;
        private DeleteUserAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(UserModal... models) {
            dao.delete(models[0]);
            return null;
        }
    }
    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void,
            Void, Void> {
        private Dao dao;
        private DeleteAllUsersAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllUsers();
            return null;
        }
    }
}
