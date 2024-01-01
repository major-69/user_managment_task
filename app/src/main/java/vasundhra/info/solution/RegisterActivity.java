package vasundhra.info.solution;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vasundhra.info.solution.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewDatabaseHelper db = new NewDatabaseHelper(RegisterActivity.this);
                if (binding.regEmail.getText().toString().isEmpty() ||
                        binding.regName.getText().toString().isEmpty() ||
                        binding.regPass.getText().toString().isEmpty() ||
                        binding.regCnfPass.getText().toString().isEmpty() ||
                        binding.regDob.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!binding.regPass.getText().toString().equalsIgnoreCase(binding.regCnfPass.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "both pass must be same", Toast.LENGTH_SHORT).show();
                } else if (binding.genderRadioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(RegisterActivity.this, "Select Gender please", Toast.LENGTH_SHORT).show();
                } else if (db.checkEmailExists(binding.regEmail.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "email already registered", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton button = findViewById(binding.genderRadioGroup.getCheckedRadioButtonId());
                    String gender = button.getText().toString();
                    Long result = db.insertData(
                            binding.regName.getText().toString(),
                            binding.regEmail.getText().toString(),
                            binding.regPass.getText().toString(),
                            binding.regDob.getText().toString(),
                            gender
                    );
                    if (result != -1) {
                        Toast.makeText(RegisterActivity.this, "Registration Success full", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    }

                }
            }
        });

    }
}