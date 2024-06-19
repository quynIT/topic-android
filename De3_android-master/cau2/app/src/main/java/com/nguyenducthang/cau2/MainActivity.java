package com.nguyenducthang.cau2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.net.Uri;
import android.os.Build;
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

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter adapter;
    ArrayList<Sinhvien> mylist =new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView =findViewById(R.id.lv);
        mylist =SinhvienDao.getAll(MainActivity.this);
        adapter =new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,mylist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog();
            }
        });
    }
    private void Dialog(){
        AlertDialog.Builder buider=new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater =getLayoutInflater();
        View view=inflater.inflate(R.layout.showdialog,null );
        buider.setView(view);
        Dialog dialog=buider.create();
        dialog.show();
        EditText ma_sv=view.findViewById(R.id.edtmasv);
        EditText ten_sv=view.findViewById(R.id.edttensv);
        EditText lop=view.findViewById(R.id.edtlop);
        Button btn_save =view.findViewById(R.id.btnsave);
        Button btn_back =view.findViewById(R.id.btnback);
        
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                String ma =ma_sv.getText().toString();
                String ten =ten_sv.getText().toString();
                String lop_sv =lop.getText().toString();
                if(SinhvienDao.insert(MainActivity.this, ma, ten, lop_sv)){
                    Toast.makeText(MainActivity.this, "Save successfully", Toast.LENGTH_SHORT).show();
                    mylist.remove(new Sinhvien(ma,ten,lop_sv));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(MainActivity.this, "Save faill", Toast.LENGTH_SHORT).show();
                }
//                String ma = ma_sv.getText().toString();
//                if (SinhvienDao.delete(MainActivity.this, ma)) {
//                    Toast.makeText(MainActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
//                    // Remove the item from the list and notify the adapter
//                    for (int i = 0; i < mylist.size(); i++) {
//                        if (mylist.get(i).getMasv().equals(ma)) {
//                            mylist.remove(i);
//                            break;
//                        }
//                    }
//                    adapter.notifyDataSetChanged();
//                    dialog.dismiss();
//                } else {
//                    Toast.makeText(MainActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
}
