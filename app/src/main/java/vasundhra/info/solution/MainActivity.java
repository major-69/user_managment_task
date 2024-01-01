package vasundhra.info.solution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import vasundhra.info.solution.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    String email;

    @Override
    protected void onResume() {
        super.onResume();
        getUserData(email);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        email = getIntent().getStringExtra("email");
        getUserData(email);
        binding.dashBoardToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,UpdateActivity.class).putExtra("email",email));
            }
        });


    }

    private void getUserData(String email) {
        NewDatabaseHelper db = new NewDatabaseHelper(MainActivity.this);

        List<UserModal> userModalList =  db.getUsersByEmail(email);

        for (UserModal modal: userModalList){
            if (email.equalsIgnoreCase(modal.getEmail())){
                binding.dasBoardEmail.setText(modal.getEmail());
                binding.dashBoardName.setText(modal.getName());
                binding.dashBoardDob.setText(modal.getDob());
                binding.dashBoardGender.setText(modal.getGen());
            }
        }
    }


}