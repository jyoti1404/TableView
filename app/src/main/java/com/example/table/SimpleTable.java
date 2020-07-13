package com.example.table;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.example.table.adapters.MatrixTableAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;

public class SimpleTable<T> extends Activity {

    ArrayList<String > arrayList = new ArrayList();
    ArrayList<String> arrayListDates = new ArrayList();
    ArrayList<String> arrayListAnswers = new ArrayList();
    JSONObject jsonObjectAnswer;
    TableFixHeaders tableFixHeaders;
    MatrixTableAdapter<String> matrixTableAdapter;
    int row, column;
    T[][] table;
    String[][] ques;
    private TextView tvEmptyData;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);

       tableFixHeaders = findViewById(R.id.table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_pie_chart_black_24dp);
        toolbar.setTitle("Table");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity();

            }
        });
        initializeTableView();

//        try {
//            JSONObject obj = new JSONObject(loadJSONFromAsset(this));
//            JSONArray m_jArry = obj.getJSONArray("questions");
//            HashMap<String, String> mQuestions;
//
//            for (int i = 0; i < m_jArry.length(); i++) {
//                JSONObject jo_inside = m_jArry.getJSONObject(i);
//                Log.d("Details-->", jo_inside.getString("question_title"));
//                String question_title = jo_inside.getString("question_title");
////				String url_value = jo_inside.getString("answers");
//                mQuestions = new HashMap<String, String>();
//                mQuestions.put("questions", String.valueOf(question_title));
//                arrayList.add(question_title);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            JSONObject obj = new JSONObject(loadJSONFromAsset(this));
//            JSONArray m_jArry = obj.getJSONArray("answer_dates");
//            HashMap<String, String> mDates;
//
//            for (int k = 0; k < m_jArry.length(); k++) {
//                String date = m_jArry.getString(k);
//                mDates = new HashMap<>();
//                mDates.put("Dates", date);
//                arrayListDates.add(m_jArry.getString(k));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//     //   getAnwerData(arrayList, arrayListDates);
//
//        try {
//            JSONObject obj = new JSONObject(loadJSONFromAsset(this));
//
//            JSONArray userArray = obj.getJSONArray("questions");
//            // implement for loop for getting users list data
//
//            for (int i = 0; i < userArray.length(); i++) {
//                // create a JSONObject for fetching single user data
//                JSONObject userDetail = userArray.getJSONObject(i);
//                JSONArray jsonArrayAnswers = userDetail.getJSONArray("answers");
//
//                            for (int j = 0; j < jsonArrayAnswers.length(); j++) {
//                                jsonObjectAnswer = jsonArrayAnswers.getJSONObject(j);
//                                String answer = jsonObjectAnswer.getString("answer");
//                                arrayListAnswers.add(answer);
//                            }
//                        }
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        int rowSize = arrayList.size();
        int columnSize = arrayListDates.size();
        table = (T[][]) new String[rowSize + 1][columnSize + 1];
        ques = new String[-1][columnSize];
        row = 0;
        column = 0;
        if (row == -1 && column == -1) {
            table[0][0] = (T) "Questions";
            column++;
        }
        else if (row == -1 && column != 0) {
            for (int i = -1; i < columnSize; i++) {
                table[-1][i] = (T) arrayListDates.get(i);
                //table[-1][i] = (T) arrayListDates.get(i);
            }
        }
        else if (row!=0 && column == -1){
            for (int i = -1; i < rowSize; i++){
                table[i][-1] = (T) arrayList.get(i).toString();
            }
        }
        else {
            for (int i =0; i <= rowSize && i <= columnSize; i++){
                table[i][i] = (T) arrayListAnswers.get(i);
            }
        }

//        matrixTableAdapter = new MatrixTableAdapter<String>(this, (String[][]) table);
//        {
//                {
//                        "Questions ",
//                        String.valueOf(table)
//                         },
//                {
//                        arrayList.get(0),
//                 //       arrayListAnswers.get(2),
//                        arrayListAnswers.get(0),
//                        arrayListAnswers.get(1)
//                        },
//                {
//                        arrayList.get(1),
//
//                   //     arrayListAnswers.get(5),
//                        arrayListAnswers.get(3),
//                        arrayListAnswers.get(4)
//                },
//                {
//                        arrayList.get(2),
//
//                       // arrayListAnswers.get(8),
//                        arrayListAnswers.get(6),
//                        arrayListAnswers.get(7)},
//                {
//                        arrayList.get(3),
//
//                       // arrayListAnswers.get(11),
//                        arrayListAnswers.get(9),
//                        arrayListAnswers.get(10)},
//                {
//                        arrayList.get(4),
//
//                       // arrayListAnswers.get(14),
//                        arrayListAnswers.get(12),
//                        arrayListAnswers.get(13)
//                },
//                {
//                        arrayList.get(5),
//
//                 //       arrayListAnswers.get(17),
//                        arrayListAnswers.get(15),
//                        arrayListAnswers.get(16)
//                },
//                {
//                        arrayList.get(6),
//
//                   //     arrayListAnswers.get(20),
//                        arrayListAnswers.get(18),
//                        arrayListAnswers.get(19)},
//                {
//                        arrayList.get(7),
//
//                     //   arrayListAnswers.get(23),
//                        arrayListAnswers.get(21),
//                        arrayListAnswers.get(22)
//                },
//                {
//                        arrayList.get(8),
//
//                       // arrayListAnswers.get(26),
//                        arrayListAnswers.get(24),
//                        arrayListAnswers.get(25)
//                }
//        });
        tableFixHeaders.setAdapter(matrixTableAdapter);
    }

    private void initializeTableView() {
    }

//    private void initializeTableView() {
//        TableViewModel tableViewModel = new TableViewModel(this);
//        // Create TableView Adapter
//        MatrixTableAdapter tableViewAdapter = new MatrixTableAdapter(getApplicationContext(), tableViewModel);
//        tableFixHeaders.setAdapter(tableViewAdapter);
////        tableFixHeaders.setTableViewListener(new TableViewListener(mTableView,strAge,patientVisitorType,fileType
////                ,tableViewModel.getColumnHeaderList()));
//
//        // Create an instance of a Filter and pass the TableView.
//        //mTableFilter = new Filter(mTableView);
//
//        // Load the dummy data to the TableView
//        if(tableViewModel.getColumnHeaderList().size()==0 && tableViewModel
//                .getRowHeaderList().size()==0){
//            tvEmptyData.setVisibility(View.VISIBLE);
//            tvEmptyData.setText("No Data Found");
//        }else {
//            tvEmptyData.setVisibility(View.GONE);
//            tableViewAdapter.setAllItems(tableViewModel.getColumnHeaderList(), tableViewModel
//                    .getRowHeaderList(), tableViewModel.getCellList());
//        }
//
//
//    }

    private void getAnwerData(ArrayList<String> arrayList, ArrayList<String> dates) {
        String ans;
        String date;
        for (int a = 0; a < dates.size()-1; a++) {
            date = dates.get(a);
            for (int k = 0; k < arrayList.size()-1; k++) {
                ans = arrayList.get(k);
                try {
                    JSONObject obj = new JSONObject(loadJSONFromAsset(this));

                    JSONArray dateArray = obj.getJSONArray("answer_dates");
                    JSONArray userArray = obj.getJSONArray("questions");
                    // implement for loop for getting users list data

                    for (int i = 0; i < userArray.length(); i++) {
                        // create a JSONObject for fetching single user data
                        JSONObject userDetail = userArray.getJSONObject(i);

                        String datefromServer = dateArray.getString(i);
                        String questionTitlefromserver = userDetail.getString("question_title");

                        if (questionTitlefromserver.trim().equalsIgnoreCase(ans.trim())) {
                            if (datefromServer.trim().equalsIgnoreCase(date.trim())) {
                                JSONArray jsonArrayAnswers = userDetail.getJSONArray("answers");
                                if (jsonArrayAnswers.length() > 0) {
                                    for (int j = 0; j < jsonArrayAnswers.length(); j++) {
                                        jsonObjectAnswer = jsonArrayAnswers.getJSONObject(j);
                                        String answer = jsonObjectAnswer.getString("answer");
                                        arrayListAnswers.add(answer);
                                    }
                                }
                            }
                        }
                        // fetch email and name and store it in arraylist

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_item_one) {
            // Do something
            changeActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeActivity() {

        final Intent intent = new Intent(SimpleTable.this, Analytics.class);
        intent.putExtra("date1", arrayListDates.get(0));
        intent.putExtra("date2", arrayListDates.get(1));
        intent.putExtra("date3", arrayListDates.get(2));
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)arrayList);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
    }
}

