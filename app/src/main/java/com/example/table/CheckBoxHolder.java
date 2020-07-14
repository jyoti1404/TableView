package com.example.table;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CheckBoxHolder extends RecyclerView.ViewHolder {
    String[] ques;
    List<RowHeader> mquestions;
    CheckBox checkBox;
    TextView textView;

    public CheckBoxHolder(@NonNull View itemView, List<RowHeader> questions) {
        super(itemView);
        this.mquestions = questions;
        //Initialize the CheckBox
        checkBox = itemView.findViewById(R.id.checkbox);
        textView = itemView.findViewById(R.id.textView);
        //or checkBox = itemView.findViewById(R.id.text1);
        ques = new String[mquestions.size()];

    }



//    CheckBoxHolder viewHolder;
    public View getFirstBody(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_table_first, parent, false);
//            viewHolder = new CheckBoxHolder(convertView, mquestions);
        }
        CheckBoxHolder viewHolder = new CheckBoxHolder(convertView, mquestions);
        for (int j = 0; j < mquestions.size(); j++) {
//                headers[i + 1] = mdates.get(i).getAnswersList().get(i).getDataObservationTs();
            ques[j] = mquestions.get(j).getData().toString();
        }
//        textView.setText(ques[row]);

        viewHolder.textView.findViewById(R.id.textView);
        viewHolder.checkBox.findViewById(R.id.checkbox);
        viewHolder.textView.setText(ques[row]);
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Add the Code Here
            }
        });

        return convertView;
    }
}