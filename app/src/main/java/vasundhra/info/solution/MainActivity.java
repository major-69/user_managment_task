package vasundhra.info.solution;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
                startActivity(new Intent(MainActivity.this, UpdateActivity.class).putExtra("email", email));
            }
        });

        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Are you want to delete")
                        .setCancelable(false)
                        .setTitle("Alert !")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteData(dialog);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                dialog.show();
            }
        });


    }

    private void deleteData(DialogInterface dialog) {
        NewDatabaseHelper db = new NewDatabaseHelper(MainActivity.this);
        int result = db.delete(email);
        if (result != -1){
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
        }
    }

    private void getUserData(String email) {
        NewDatabaseHelper db = new NewDatabaseHelper(MainActivity.this);

        List<UserModal> userModalList = db.getUsersByEmail(email);

        for (UserModal modal : userModalList) {
            if (email.equalsIgnoreCase(modal.getEmail())) {
                binding.dasBoardEmail.setText(modal.getEmail());
                binding.dashBoardName.setText(modal.getName());
                binding.dashBoardDob.setText(modal.getDob());
                binding.dashBoardGender.setText(modal.getGen());
            }
        }
    }


}