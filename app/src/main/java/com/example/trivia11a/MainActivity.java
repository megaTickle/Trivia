package com.example.trivia11a;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> resultLauncher;
    private FbModule fbModule;
    private ConstraintLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbModule = new FbModule(this);

        ll = findViewById(R.id.main);

        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if(o.getResultCode() == RESULT_OK)
                        {
                            Intent data = o.getData();
                            String str = data.getStringExtra("color");
                            fbModule.writeBackgroundColorToFb(str);

                        }

                    }
                }
        );
    }

    public void onClickStart(View view) {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    public void onClickSetting(View view) {
        Intent i = new Intent(this, SettingActivity.class);
        resultLauncher.launch(i);
    }

    public void onClickInstruction(View view) {
    }

    public void setNewColorFromFb(String str) {
        // the fire base call to this in the first time and every time the user change the color
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
        setBackgroundColor(str);
    }

    public void setBackgroundColor(String color){
        switch (color){
            case "Red":{
                ll.setBackgroundColor(Color.RED);
                break;
            }
            case "Yellow":{
                ll.setBackgroundColor(Color.YELLOW);
                break;
            }
            case "Blue":{
                ll.setBackgroundColor(Color.BLUE);
                break;
            }
            case "Pink":{
                ll.setBackgroundColor(Color.argb(255,255,192,203));
                break;
            }

            default:
                ll.setBackgroundColor(Color.WHITE);
                break;
        }
    }
}