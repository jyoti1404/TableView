package com.example.table;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class TableViewModel {


    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    // Columns indexes
    public static final int MOOD_COLUMN_INDEX = 3;
    public static final int GENDER_COLUMN_INDEX = 4;

    // Constant size for dummy data sets
    private int COLUMN_SIZE = 0;
    private int ROW_SIZE = 0;

    private String answerData;
    private JSONObject jsonObjectMainData;

    public TableViewModel(Context context) {
//        this.answerData = answerData;

        try {
            jsonObjectMainData = new JSONObject(loadJSONFromAsset(context));
            ROW_SIZE = jsonObjectMainData.getJSONArray("questions").length();
            COLUMN_SIZE = jsonObjectMainData.getJSONArray("answer_dates").length();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private List<RowHeader> getSimpleRowHeaderList() {

        List<RowHeader> list = new ArrayList<>();
        if(COLUMN_SIZE == 0) {
            return list;
        }else {
            try {
                JSONArray jsonArrayQuestions = jsonObjectMainData.getJSONArray("questions");
                for (int i = 0; i < jsonArrayQuestions.length(); i++) {
                    JSONObject jsonObject = jsonArrayQuestions.getJSONObject(i);

                    RowHeader header = new RowHeader(String.valueOf(i), jsonObject.getString("question_title"));
                    list.add(header);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * This is a dummy model list test some cases.
     */
    @NonNull
    private List<ColumnHeader> getRandomColumnHeaderList() {

        List<ColumnHeader> list = new ArrayList<>();
        if(ROW_SIZE == 0){
            return list;
        }
        try {

            JSONArray rowArray = jsonObjectMainData.getJSONArray("questions");
            JSONArray columnArray = jsonObjectMainData.getJSONArray("answer_dates");

            for (int k=0;k<columnArray.length();k++) {
                String title = columnArray.getString(k);
                ColumnHeader header = new ColumnHeader(String.valueOf(k), title);
                ArrayList<Answer> answers = new ArrayList<>();
                for (int i = 0; i < rowArray.length(); i++) {
                    Gson gson = new Gson();
                    String jsonOutput = rowArray.getJSONObject(i).getJSONArray("answers").toString();
                    Type listType = new TypeToken<List<AnswerVo>>(){}.getType();
                    List<AnswerVo> answerVoList = gson.fromJson(jsonOutput, listType);


                    for (AnswerVo answerVo:answerVoList
                    ) {
                        String dateString = CommonClass.timeStampAnswertoDate(answerVo.getDataObservationTs());
                        //  String dateString = answerVo.getDataObservationTs();

                        if(dateString.equals(columnArray.getString(k))){
                            Answer answer = new Answer();
                            answer.setFormId(answerVo.getFormId());
                            answer.setAnswer(answerVo.getAnswer());
                            answer.setDataObservationTs(answerVo.getDataObservationTs());
                            answer.setPatientId(answerVo.getPatientId());
                            answer.setQuestionId(answerVo.getQuestionId());
                            answer.setCreatedBy(answerVo.getCreatedBy());
                            answer.setUpdatedBy(answerVo.getUpdatedBy());
                            answers.add(answer);
                        }
                    }


                }
                header.setAnswersList(answers);
                list.add(header);


            }
        } catch (JSONException je) {

        }

        return list;
    }

    /**
     * This is a dummy model list test some cases.
     */
    @NonNull
    private List<List<Cell>> getCellListForSortingTest() {

        List<List<Cell>> list = new ArrayList<>();
        try {
            JSONArray rowArray = jsonObjectMainData.getJSONArray("questions");
            JSONArray columnArray = jsonObjectMainData.getJSONArray("answer_dates");
            List<String> datesList = new ArrayList<>();
            for (int j=0;j<columnArray.length();j++) {
                datesList.add(columnArray.getString(j));
            }
            for (int i = 0; i < rowArray.length(); i++) {
                Gson gson = new Gson();
                String jsonOutput = rowArray.getJSONObject(i).getJSONArray("answers").toString();
                Type listType = new TypeToken<List<AnswerVo>>(){}.getType();
                List<AnswerVo> answerVoList = gson.fromJson(jsonOutput, listType);
                List<Cell> cellList = new ArrayList<>();


                for (int k=0;k<columnArray.length();k++) {

                    Object text = rowArray.getJSONObject(i).getString("question_title");

                    // Create dummy id.
                    String id = k + "-" + i;
                    Cell cell = new Cell(id, text);
                    cell.setmDate(columnArray.getString(k));
                    for (AnswerVo answerVo:answerVoList
                    ) {
                        String dateString = CommonClass.timeStampAnswertoDate(answerVo.getDataObservationTs());
                        //  String dateString = answerVo.getDataObservationTs();

                        if(dateString.equals(columnArray.getString(k))){
                            Log.e("date from answer: ",dateString);
                            Log.e("date from columnHeader:",columnArray.getString(k));
                            cell.setAnswer(answerVo.getAnswer());
                            cell.setAnswers(answerVoList);
                        }
                    }

                    cell.setDates(datesList);
                    cellList.add(cell);
                }


                list.add(cellList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    @NonNull
    public List<List<Cell>> getCellList() {
        return getCellListForSortingTest();
    }

    @NonNull
    public List<RowHeader> getRowHeaderList() {
        return getSimpleRowHeaderList();
    }

    @NonNull
    public List<ColumnHeader> getColumnHeaderList() {
        return getRandomColumnHeaderList();
    }

}
