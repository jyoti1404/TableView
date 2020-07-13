package com.example.table;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Cell implements  ISortableModel, IFilterableModel  {
    @NonNull
    private String mId;
    @Nullable
    private Object mData;
    @NonNull
    private String mFilterKeyword;
    private String mDate;
    private String questionTitle;
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    private List<AnswerVo> answers = new ArrayList<>();
    private List<String> dateList = new ArrayList<>();

    public List<AnswerVo> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerVo> answer) {
        this.answers = answer;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }


    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public Cell(@NonNull String id, @Nullable Object data) {
        this.mId = id;
        this.mData = data;
        this.mFilterKeyword = String.valueOf(data);
    }

    /**
     * This is necessary for sorting process.
     * See ISortableModel
     */

    @NonNull
    @Override
    public String getId() {
        return mId;
    }

    @NonNull
    @Override
    public String getFilterableKeyword() {
        return mFilterKeyword;
    }
    public void setDates(List<String> datesList) {
        this.dateList = datesList;
    }

    public List<String> getDates(){
        return dateList;
    }

    @Nullable
    @Override
    public Object getContent() {
        return mData;
    }
    @Nullable
    public Object getData() {
        return mData;
    }

    public void setData(@Nullable String data) {
        mData = data;
    }

}
