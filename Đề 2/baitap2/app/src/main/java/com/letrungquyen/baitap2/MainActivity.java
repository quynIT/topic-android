package com.letrungquyen.baitap2;

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

import java.util.ArrayList;

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
    private void Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        //
        EditText ma_sp = view.findViewById(R.id.edtmasp);
        EditText ten_sp = view.findViewById(R.id.edttensp);
        EditText gia_sp = view.findViewById(R.id.edtgiasp);
        Button btn_delete = view.findViewById(R.id.btndelete);
        Button btn_back = view.findViewById(R.id.btnback);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ma = ma_sp.getText().toString();
                if (SanphamDao.delete(MainActivity.this, ma)) {
                    Toast.makeText(MainActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                    // Remove the item from the list and notify the adapter
                    for (int i = 0; i < mylist.size(); i++) {
                        if (mylist.get(i).getMasp().equals(ma)) {
                            mylist.remove(i);
                            break;
                        }
                    }
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}