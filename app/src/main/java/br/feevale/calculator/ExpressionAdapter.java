package br.feevale.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpressionAdapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<String> expressions;

    public ExpressionAdapter(ArrayList<String> expressions, Context ctx){
        this.expressions = expressions;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return expressions.size();
    }

    @Override
    public Object getItem(int position) {
        return expressions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = inflater.inflate(R.layout.expression_item, null);
        TextView txtListItem = v.findViewById(R.id.txt_item);

        String exp = expressions.get(position);
        txtListItem.setText(exp);
        return v;
    }
}
