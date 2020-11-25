package randomnumber.su.ac.th.projectbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class bmi_chart extends AppCompatActivity {
    Button exit_buttonC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_chart);
        exit_buttonC = findViewById(R.id.exit_button);

        exit_buttonC.setOnClickListener(new View.OnClickListener() { //กดปุ่ม exit_button จะไปยังหน้า AddUserActivity
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (bmi_chart.this, AddUserActivity.class);
                startActivity(intent);
            }
        });
    }
}