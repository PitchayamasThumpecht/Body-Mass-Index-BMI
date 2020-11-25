package randomnumber.su.ac.th.projectbmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabase myDB;
    ArrayList<String> data_name, data_id, data_weight, data_height;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() { //กดปุ่ม add_button จะไปยังหน้า AddUseActivity
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });


        myDB = new MyDatabase(MainActivity.this); //สร้างฐานข้อมูลไว้ที่หน้า MainActivity

        data_name = new ArrayList<>();
        data_id = new ArrayList<>();        //สร้าง ArrayList ให้กับข้อมูลที่ต้องการเก็บค่า
        data_weight = new ArrayList<>();
        data_height = new ArrayList<>();

        storeDataInArrays();


        customAdapter = new CustomAdapter(MainActivity.this,this, data_name, data_id, data_weight,
                data_height);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                data_name.add(cursor.getString(0));
                data_id.add(cursor.getString(1));
                data_weight.add(cursor.getString(2));
                data_height.add(cursor.getString(3));
            }
        }
    }

}