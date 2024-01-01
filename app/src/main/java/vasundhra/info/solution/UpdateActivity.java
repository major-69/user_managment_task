package vasundhra.info.solution;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdateActivity.this, "Update was not maked", Toast.LENGTH_SHORT).show();
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

                if (modal.getGen().equalsIgnoreCase("male")){
                    binding.radioMale.setChecked(true);
                }else {
                    binding.radioFeMale.setChecked(true);
                }

            }
        }
    }

}