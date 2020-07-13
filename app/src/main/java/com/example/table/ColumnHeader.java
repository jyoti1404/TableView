package com.example.table;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ColumnHeader extends Cell{
    ArrayList<Answer> answersList = new ArrayList<>();

    public ArrayList<Answer> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(ArrayList<Answer> answersList) {
        this.answersList = answersList;
    }


    public ColumnHeader(@NonNull String id, @Nullable Object data) {
        super(id, data);
    }

}
