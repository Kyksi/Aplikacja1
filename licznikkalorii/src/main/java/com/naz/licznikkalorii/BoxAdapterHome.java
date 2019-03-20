package com.naz.licznikkalorii;


import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BoxAdapterHome extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<ProduktHome> objects;

    BoxAdapterHome(Context context, ArrayList<ProduktHome> productsHome) {
        ctx = context;
        objects = productsHome;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.itemhome, parent, false);
        }

        ProduktHome p = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.tvDescr)).setText(p.name);
        ((TextView) view.findViewById(R.id.tvPrice)).setText(p.price + "");
        ((TextView) view.findViewById(R.id.textView2)).setText(p.massa + "");
        return view;
    }

    // товар по позиции
    ProduktHome getProduct(int position) {
        return ((ProduktHome) getItem(position));
    }

}