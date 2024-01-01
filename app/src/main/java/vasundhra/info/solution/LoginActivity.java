package vasundhra.info.solution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import vasundhra.info.solution.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NewDatabaseHelper db = new NewDatabaseHelper(LoginActivity.this);

        //db.getDataOfUser();
//        Long result = db.insertData("vibhav","vibhav2890@gmail.com","12345","04/08/2004","male");
//
//        if (result == -1){
//            Toast.makeText(this, "inserted failed", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(this, "insertion success", Toast.LENGTH_SHORT).show();
//        }

        binding.materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.email.getText().toString().isEmpty()
                || binding.password.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!db.checkEmailExists(binding.email.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "You are not register", Toast.LENGTH_SHORT).show();
                }else if (!db.checkPassword(binding.email.getText().toString(),binding.password.getText().toString())){
                    Toast.makeText(LoginActivity.this, "password not matched", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class).putExtra("email",binding.email.getText().toString()));
                    finish();
                }
            }
        });

        binding.lgToCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }
}