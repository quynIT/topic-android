package com.letrungquyen.baitap2;

import android.app.Dialog;
import android.content.Context;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Sanpham> mylist =new ArrayList<>();
    ArrayAdapter<Sanpham> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView =findViewById(R.id.lv);
        mylist =SanphamDao.getAll(MainActivity.this);
        adapter =new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,mylist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog();
            }
        });
    }
    private void Dialog(){
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater=getLayoutInflater();
        View view =inflater.inflate(R.layout.dialog,null);
        builder.setView(view);
        Dialog dialog =builder.create();
        dialog.show();
        //
        EditText ma_sp=view.findViewById(R.id.edtmasp);
        EditText ten_sp=view.findViewById(R.id.edttensp);
        EditText gia_sp=view.findViewById(R.id.edtgiasp);
        Button btn_save=view.findViewById(R.id.btnsave);
        Button btn_back=view.findViewById(R.id.btnback);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma=ma_sp.getText().toString();
                String ten =ten_sp.getText().toString();
                int gia = Integer.parseInt(gia_sp.getText().toString());

                if (SanphamDao.inserts(MainActivity.this,ma, ten, gia)){
                    Toast.makeText(MainActivity.this, "Save Successfully", Toast.LENGTH_SHORT).show();
                    mylist.add(new Sanpham(ma, ten,gia));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(MainActivity.this, "Save fail", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
    }
}