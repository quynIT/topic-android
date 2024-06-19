package com.letrungquyen.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.letrungquyen.model.Oto;
import com.letrungquyen.test.MainActivity;
import com.letrungquyen.test.R;
import com.letrungquyen.model.Oto;

import java.util.ArrayList;
import java.util.List;

public class OtoAdapter extends BaseAdapter {

    MainActivity context;
    int layout_item;

    List<Oto> otos;

    public OtoAdapter(MainActivity context, int layout_item, List<Oto> otos) {
        this.context = context;
        this.layout_item = layout_item;
        this.otos = otos;
    }
    @Override
    public int getCount() {
        return otos.size();
    }

    @Override
    public Object getItem(int position) {
        return otos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout_item, null);
            holder.txtInfor =convertView.findViewById(R.id.txtInfor);
            holder.imgOto = convertView.findViewById(R.id.imgOto);
            holder.imgEdit = convertView.findViewById(R.id.imBtnEdit);
            holder.imgDelete = convertView.findViewById(R.id.imBtnDelete);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();

        }

        //binding data
        Oto p = otos.get(position);
        byte[] photo = p.getImage();
        if (photo != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
            holder.imgOto.setImageBitmap(bitmap);
        }
        // holder.imgOto
        holder.txtInfor.setText("Tên sản phẩm:" + p.getName() + "\n"
                +"Giá sản phẩm:" + p.getPrice()+"\n"
                +"Mô tả sản phẩm:" + p.getAddress()+"\n"
                +"Loại sản phẩm:" + p.getCategory() + "\n");

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.openEditDialog(p);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.OpenDeleteDialog(p);
            }
        });
        return convertView;
    }

    public void updateList(ArrayList<Oto> filteredList) {
        otos = new ArrayList<Oto>();
        otos.addAll(filteredList);
        notifyDataSetChanged();
    }


    public static class ViewHolder{

        ImageView imgOto;
        TextView txtInfor;

        ImageButton imgEdit;
        ImageButton imgDelete;

    }
}
