package randomnumber.su.ac.th.projectbmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class AddUserActivity extends AppCompatActivity {
    EditText name_input, id_input, weight, height;
    Button calButton;
    RecyclerView recyclerView;
    double resultBMI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        name_input = findViewById(R.id.name_edit_text);
        id_input = findViewById(R.id.id_edit_text);
        weight = findViewById(R.id.weight_edit_text);
        height= findViewById(R.id.height_edit_text);
        calButton = findViewById(R.id.cal_button);




        calButton.setOnClickListener(new View.OnClickListener() { //เมื่อกดปุ่ม calButton จะทำการคำนวณหาค่า BMI และทำการแสดงผลออกมาในรูปแบบ Dialog
            @Override
            public void onClick(View view) {

                MyDatabase myDB = new MyDatabase(AddUserActivity.this);
                myDB.addData(name_input.getText().toString().trim(),
                        weight.getText().toString().trim(),
                        height.getText().toString().trim());

                String weightText = weight.getText().toString(); //นำค่าที่รับมาแปลงให้อยู่ในรูปตัวอักษร
                double w = Double.parseDouble(weightText); //แปลงค่าน้ำหนักที่ใส่จากตัวอักษรเป็นทศนิยม

                String heightText = height.getText().toString(); //นำค่าที่รับมาแปลงให้อยู่ในรูปตัวอักษร
                double h = Double.parseDouble(heightText);//แปลงค่านส่วนสูงที่ใส่จากตัวอักษรเป็นทศนิยม

                resultBMI = w/((h*0.01)*2);
                String result = String.format(Locale.getDefault(), "%.2f", resultBMI);

                AlertDialog.Builder dialog = new AlertDialog.Builder(AddUserActivity.this);
                dialog.setTitle("Body Mass Index");

                if(resultBMI < 18.5){
                    dialog.setMessage("ค่า ฺBMI คือ "+ result+"\n"+"อยู่ในเกณฑ์ : น้ำหนักน้อย/ผอม");
                }else if (resultBMI>=18.5 && resultBMI<=22.90){
                    dialog.setMessage("ค่า ฺBMI คือ "+ result+"\n"+"อยู่ในเกณฑ์ : ปกติ(สุขภาพดี)");
                }else if(resultBMI>=23 && resultBMI<=24.90){
                    dialog.setMessage("ค่า ฺBMI คือ "+ result+"\n"+"อยู่ในเกณฑ์ : ท้วม/โรคอ้วนระดับ1");
                }else if (resultBMI>=25 && resultBMI<=29.90){
                    dialog.setMessage("ค่า ฺBMI คือ "+ result+"\n"+"อยู่ในเกณฑ์ : อ้วน/โรคอ้วนระดับ2");
                }else if (resultBMI >= 30){
                    dialog.setMessage("ค่า ฺBMI คือ "+ result+"\n"+"อยู่ในเกณฑ์ : อ้วนมาก/โรคอ้วนระดับ3");
                }

                dialog.setNegativeButton("OK!", null);
                dialog.show();

            }
        });

        Button exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(AddUserActivity.this);
                dialog.setTitle("Exit Body Mass Index");
                dialog.setMessage("Are you sure you want to exit?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() { // จะไม่ปิดจนกว่จะกดปุ่ม yes
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialog.setNegativeButton("No", null);
                dialog.show();
            }

        });

        Button bmichartButton = findViewById(R.id.bmi_chart_button);
        bmichartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AddUserActivity.this, bmi_chart.class);
                startActivity(intent);
            }
        });

    }
}