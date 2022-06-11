package com.example.unigoiascrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewUserActivity extends AppCompatActivity {
 private EditText userNameEdt, courseDescEdt, estiloCursoEdt;
 private Button userBtn;
 public static final String EXTRA_ID =
         "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
 public static final String EXTRA_USER_NAME =
         "com.gtappdevelopers.gfgroomdatabase.EXTRA_USER_NAME";
 public static final String EXTRA_DESCRIPTION =
         "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DESCRIPTION";
 public static final String EXTRA_ESTILO =
         "com.gtappdevelopers.gfgroomdatabase.EXTRA_ESTILO";
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_new_user);
  userNameEdt = findViewById(R.id.idEdtNomeUsuario);
  courseDescEdt = findViewById(R.id.idEdtDescricaoCurso);
  estiloCursoEdt = findViewById(R.id.idEdtEstiloCurso);
  userBtn = findViewById(R.id.idBtnSaveUser);
  Intent intent = getIntent();
  if (intent.hasExtra(EXTRA_ID)) {
   userNameEdt.setText(intent.getStringExtra(EXTRA_USER_NAME));
   courseDescEdt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
   estiloCursoEdt.setText(intent.getStringExtra(EXTRA_ESTILO));
  }
  userBtn.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View v) {
    String userName = userNameEdt.getText().toString();
    String courseDesc = courseDescEdt.getText().toString();
    String estiloCurso = estiloCursoEdt.getText().toString();
    if (userName.isEmpty() || courseDesc.isEmpty()) {
     Toast.makeText(NewUserActivity.this, "Entre com os dados do usuario", Toast.LENGTH_SHORT).show();
     return;
    }
    saveUser(userName, courseDesc, estiloCurso);
   }
  });
 }
 private void saveUser(String userName, String courseDescription, String estiloCurso) {
  Intent data = new Intent();
  data.putExtra(EXTRA_USER_NAME, userName);
  data.putExtra(EXTRA_DESCRIPTION, courseDescription);
  data.putExtra(EXTRA_ESTILO, estiloCurso);
  int id = getIntent().getIntExtra(EXTRA_ID, -1);
  if (id != -1) {
   data.putExtra(EXTRA_ID, id);
  }
  setResult(RESULT_OK, data);
  Toast.makeText(this, "Usuario salvo no banco de dados.",
          Toast.LENGTH_SHORT).show();
 }
}