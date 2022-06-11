package com.example.unigoiascrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView usersRV;
    private static final int ADD_USER_REQUEST = 1;
    private static final int EDIT_USER_REQUEST = 2;
    private ViewModal viewmodal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usersRV = findViewById(R.id.idUsuario);
        FloatingActionButton fab = findViewById(R.id.idBotaoAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewUserActivity.class);
                startActivityForResult(intent, ADD_USER_REQUEST);
            }
        });
        usersRV.setLayoutManager(new LinearLayoutManager(this));
        usersRV.setHasFixedSize(true);
        final UserRVAdapter adapter = new UserRVAdapter();
        usersRV.setAdapter(adapter);
        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);
        viewmodal.getAllUsers().observe(this, new
                Observer<List<UserModal>>() {
                    @Override
                    public void onChanged(List<UserModal> models) {
                        adapter.submitList(models);
                    }
                });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull
                    RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewmodal.delete(adapter.getUserAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Usuario deletado.", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(usersRV);
        adapter.setOnItemClickListener(new
                                               UserRVAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(UserModal model) {
               Intent intent = new Intent(MainActivity.this,
                       NewUserActivity.class);
               intent.putExtra(NewUserActivity.EXTRA_ID, model.getId());
               intent.putExtra(NewUserActivity.EXTRA_USER_NAME,
                       model.getUserName());
               intent.putExtra(NewUserActivity.EXTRA_DESCRIPTION,
                       model.getCourseDescription());
               intent.putExtra(NewUserActivity.EXTRA_ESTILO,
                       model.getEstiloCurso());
               startActivityForResult(intent, EDIT_USER_REQUEST);
           }
       });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable
            Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_USER_REQUEST && resultCode == RESULT_OK) {
            String userName =
                    data.getStringExtra(NewUserActivity.EXTRA_USER_NAME);
            String courseDescription =
                    data.getStringExtra(NewUserActivity.EXTRA_DESCRIPTION);
            String estiloCurso =
                    data.getStringExtra(NewUserActivity.EXTRA_ESTILO);
            UserModal model = new UserModal(userName, courseDescription, estiloCurso);
            viewmodal.insert(model);
            Toast.makeText(this, "Usuario salvo.", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_USER_REQUEST && resultCode ==
                RESULT_OK) {
            int id = data.getIntExtra(NewUserActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Usuario não atualizado.",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            String courseName =
                    data.getStringExtra(NewUserActivity.EXTRA_USER_NAME);
            String courseDesc =
                    data.getStringExtra(NewUserActivity.EXTRA_DESCRIPTION);
            String estiloCurso =
                    data.getStringExtra(NewUserActivity.EXTRA_ESTILO);
            UserModal model = new UserModal(courseName, courseDesc, estiloCurso);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Usuario atualizado.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Usuario não salvo.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}