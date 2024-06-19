package com.nguyenducthang.cau2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter adapter;
    Button btn_save,btn_back;
    ArrayList<Book> mylist =new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        listView =findViewById(R.id.lv);
        btn_save=findViewById(R.id.btnsave);
        btn_back=findViewById(R.id.btnback);

        mylist =bookDao.getAll(MainActivity.this);
        adapter =new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,mylist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            showDialog();
            }
        });

    }
    private void showDialog(){
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater =getLayoutInflater();
        View view =inflater.inflate(R.layout.dialog,null);
        builder.setView(view);
        Dialog dialog =builder.create();
        dialog.show();

        EditText ma_sach=view.findViewById(R.id.editmasach);
        EditText ten_sach=view.findViewById(R.id.edittensach);
        EditText gia_sach=view.findViewById(R.id.editgiasach);
        btn_back=view.findViewById(R.id.btnback);
        btn_save=view.findViewById(R.id.btnsave);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String masach=ma_sach.getText().toString();
                String tensach=ten_sach.getText().toString();
                int giasach=Integer.parseInt(gia_sach.getText().toString());
                if(masach.isEmpty()||tensach.isEmpty()){
                    Toast.makeText(MainActivity.this,"hay nhap day du",Toast.LENGTH_SHORT).show();
                }
                else if (bookDao.insert(MainActivity.this,masach,tensach,giasach)){
                    Toast.makeText(MainActivity.this,"save successfully",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"save fail",Toast.LENGTH_SHORT).show();
                }
//                String masach=ma_sach.getText().toString();
//                String tensach=ten_sach.getText().toString();
//                int giasach=Integer.parseInt(gia_sach.getText().toString());
//                if(masach.isEmpty()||tensach.isEmpty()){
//                    Toast.makeText(MainActivity.this,"hay nhap day du",Toast.LENGTH_SHORT).show();
//                }else{
//                    try{
//                         giasach = Integer.parseInt(gia_sach.getText().toString());
//                        if (bookDao.insert(MainActivity.this,masach,tensach,giasach)){
//                            Toast.makeText(MainActivity.this,"save successfully",Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(MainActivity.this,"save fail",Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                    catch (NumberFormatException e){
//                        Toast.makeText(MainActivity.this, "Giá sách phải là số nguyên", Toast.LENGTH_SHORT).show();
//                    }
//                }

            }
        });
    }

}
