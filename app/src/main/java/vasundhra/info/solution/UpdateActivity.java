package vasundhra.info.solution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

import vasundhra.info.solution.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {

    String email;

    ActivityUpdateBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        email = getIntent().getStringExtra("email");


        getUserData(email);

//

        NewDatabaseHelper helper = new NewDatabaseHelper(this);

        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (binding.regEmail.getText().toString().isEmpty() ||
                        binding.regName.getText().toString().isEmpty() ||
                        binding.regPass.getText().toString().isEmpty() ||
                        binding.regCnfPass.getText().toString().isEmpty() ||
                        binding.regDob.getText().toString().isEmpty()) {
                    Toast.makeText(UpdateActivity.this, "fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!binding.regPass.getText().toString().equalsIgnoreCase(binding.regCnfPass.getText().toString())) {
                    Toast.makeText(UpdateActivity.this, "both pass must be same", Toast.LENGTH_SHORT).show();
                } else if (binding.genderRadioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(UpdateActivity.this, "Select Gender please", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton button = findViewById(binding.genderRadioGroup.getCheckedRadioButtonId());
                    String gender = button.getText().toString();
                    int result = helper.updateData(
                            binding.regName.getText().toString(),
                            binding.regEmail.getText().toString(),
                            binding.regPass.getText().toString(),
                            binding.regDob.getText().toString(),
                            gender
                    );
                    if (result != -1) {
                        Toast.makeText(UpdateActivity.this, "Update Successfull", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });
        
    }

    private void getUserData(String email) {
        NewDatabaseHelper db = new NewDatabaseHelper(UpdateActivity.this);
        List<UserModal> userModalList =  db.getUsersByEmail(email);

        for (UserModal modal: userModalList){
            if (email.equalsIgnoreCase(modal.getEmail())){
                binding.regEmail.setText(modal.getEmail());
                binding.regName.setText(modal.getName());
                binding.regDob.setText(modal.getDob());
                binding.regCnfPass.setText(modal.getPass());
                binding.regPass.setText(modal.getPass());
                if (modal.getGen().equalsIgnoreCase("male")){
                    binding.radioMale.setChecked(true);
                }else {
                    binding.radioFeMale.setChecked(true);
                }

            }
        }
    }

}