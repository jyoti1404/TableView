package com.example.table;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.table.adapters.BaseTableAdapter;
import com.inqbarna.tablefixheaders.TableFixHeaders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

public class MainTable extends Activity {
    TableViewModel tableViewModel;
    TableFixHeaders tableFixHeaders;
    List<ColumnHeader> dates = new ArrayList<>();
    List<List<Cell>> answers = new ArrayList<>();
    List<RowHeader> questions = new ArrayList<>();
    ArrayList<String> arrayListAll = new ArrayList();
    List<ColumnHeader> arrayListDates = new ArrayList<>();
    CheckBox cb;
    int count = 0, temp;
//    private final String headers[] = {
//            "Questions",
//            "Dates"
//    };
//
//    private class Main {
//        private final String name;
//        private final List<Next> list;
//
//        Main(String name) {
//            this.name = name;
//            list = new ArrayList<Next>();
//        }
//
//        public int size() {
//            return list.size();
//        }
//
//        public MainTable.Next get(int i) {
//            return list.get(i);
//        }
//    }
//
//    private class Next {
//        private final String[] data;
//
//        private Next(String question, String , String version, String api, String storage, String inches, String ram) {
//            data = new String[]{
//                    name,
//                    company,
//                    version,
//                    api,
//                    storage,
//                    inches,
//                    ram};
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);
        cb = findViewById(android.R.id.text1);

        tableFixHeaders = findViewById(R.id.table);
        tableViewModel = new TableViewModel(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_pie_chart_black_24dp);
        toolbar.setTitle("Table");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity();

            }
        });


        dates = tableViewModel.getColumnHeaderList();
        answers = tableViewModel.getCellList();
        questions = tableViewModel.getRowHeaderList();

        TableAdapter adapter = new MainTable.TableAdapter(this, dates, questions, answers);
//        TableAdapter adapter = new TableAdapter(this, tableViewModel);
        tableFixHeaders.setAdapter(adapter);
//        arrayList = adapter.getArrayList();
        arrayListDates = adapter.mdates;
                arrayListAll = adapter.arrayList;

//        com.inqbarna.tablefixheaders.TableFixHeaders tableFixHeaders = (TableFixHeaders) findViewById(R.id.table);
//        com.inqbarna.tablefixheaders.adapters.BaseTableAdapter baseTableAdapter = new FamilyTable.FamilyNexusAdapter(this);
//        tableFixHeaders.setAdapter(baseTableAdapter);
    }

    private void changeActivity() {

        final Intent intent = new Intent(MainTable.this, Analytics.class);
        Bundle args = new Bundle();
        args.putSerializable("yAxis", arrayListAll);
        args.putSerializable("xAxis", (Serializable) arrayListDates);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
    }


    public class TableAdapter extends BaseTableAdapter {

        //        private ArrayList<String> dateList;
        Context contextl;
        List<ColumnHeader> mdates;
        List<RowHeader> mquestions;
        List<List<Cell>> manswers;
        ArrayList<String> arrayList;
        ArrayList<CheckBox> mCheckBoxes = new ArrayList<CheckBox>();
        private final String[] headers;
        private final String[][] ans;
        private final String[] ques;
        CheckBox cb;
        TableViewModel model;

        final int[] widths = {
                120,
                100,

        };

        private float density;

        public TableAdapter(Context context, List<ColumnHeader> dates, List<RowHeader> questions, List<List<Cell>> answers) {
            this.contextl = context;
            this.mdates = dates;
            this.mquestions = questions;
            this.manswers = answers;
            arrayList = new ArrayList<>();
            model = new TableViewModel(context);
//        public TableAdapter(Context context, TableViewModel tableViewModel) {
//            this.contextl = context;
//            this.tableViewModel = tableViewModel;

//            mdates = (ArrayList<ColumnHeader>) tableViewModel.getColumnHeaderList();
            density = context.getResources().getDisplayMetrics().density;

            headers = new String[mdates.size()];
//            headers[0] = "Questions";
            for (int i = 0; i < mdates.size()  ; i++) {
//                headers[i + 1] = mdates.get(i).getAnswersList().get(i).getDataObservationTs();
                headers[i] = mdates.get(i).getData().toString();
            }
            ques = new String[mquestions.size()];

            for (int j = 0; j < mquestions.size()  ; j++) {
//                headers[i + 1] = mdates.get(i).getAnswersList().get(i).getDataObservationTs();
                ques[j] = mquestions.get(j).getData().toString();
            }

            ans = new String[mquestions.size()][mdates.size()];
            for (int i =0; i < mquestions.size(); i++){
                for (int j =0; j< mdates.size(); j++){
                    ans[i][j] = manswers.get(i).get(j).getAnswer();
              }
            }


        }
//

        @Override
        public int getRowCount() {
//            return questions.size() + 1;
            return questions.size();
        }

        @Override
        public int getColumnCount() {
            return mdates.size() ;
//                return 10;
        }

        @Override
        public View getView(int row, int column, View convertView, ViewGroup parent) {
            final View view;

            switch (getItemViewType(row, column)) {
                case 0:
                    view = getFirstHeader(row, column, convertView, parent);
                    break;
                case 1:
                    view = getHeader(row, column, convertView, parent);
                    break;
                case 2:
                    view = getFirstBody(row, column, convertView, parent);
                    break;
                case 3:
                    view = getBody(row, column, convertView, parent);
                    break;
//                case 4:
//                    view = getFamilyView(row, column, convertView, parent);
//                    break;
//                default:
//                    throw new RuntimeException("Stub!");
                default:
                    throw new IllegalStateException("Unexpected value: " + getItemViewType(row, column));
            }
            return view;
        }

        private View getFirstHeader(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_table_header_first, parent, false);
            }
            ((TextView) convertView.findViewById(android.R.id.text1)).setText("Questions");
            return convertView;
        }

        private View getHeader(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_table_header, parent, false);
            }
//            for (int i = 0; i < column - 1 ; i++) {
                ((TextView) convertView.findViewById(android.R.id.text1)).setText(headers[column ]);
//                column++;
//            }
//            row++;
            return convertView;
        }


        private View getFirstBody(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_table_first, parent, false);
            }
            convertView.setBackgroundResource(row % 2 == 0 ? R.drawable.bg_table_color1 : R.drawable.bg_table_color2);
//            for (int i = 0; i<questions.size(); i++) {
                ((CheckBox) convertView.findViewById(android.R.id.text1)).setText(questions.get(row).getData().toString());
//            ((CheckBox) convertView.findViewById(android.R.id.text1)).setChecked(false);

//            RowHeader model = questions.get(row);
//            (convertView).findViewById(android.R.id.text1).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    CheckBox current = (CheckBox) view;
//                    CheckBox cb = (CheckBox) view;
//                    cb.setChecked(true);
//                    int position = (Integer) view.getId();
////                    tableFixHeaders.setSelected(cb.isChecked());
//                    mCheckBoxes.add(cb);
//
//                    for (int i = 0; i < mCheckBoxes.size(); i++) {
//                        current = mCheckBoxes.get(i);
//                        if (current.getId() == position) {
//                            current.setChecked(true);
//                        } else {
//                            current.setChecked(false);
//                        }
//                    }
////
//                    if (current.isChecked()){
//                        count++;
//
//                        if (count >= 3){
//                            current.setChecked(false);
//                            Toast.makeText(getApplicationContext(),
//                                    "Cannot Select more than 3 Questions",
//                                    Toast.LENGTH_LONG).show();
//                        }
//                        else {
//                            for (int i = 0; i < mCheckBoxes.size() ; i++) {
//                                if (mCheckBoxes.get(i) == view) {
////                                    count = i;
////                                    for (int j = 0; j < dates.size(); j++ ) {
////                                    arrayList.add(answers.get(i).get(column).getAnswer());
//                                        arrayList.add(answers.get(i).get(i).getAnswer());
//                                        Log.d("TAG", arrayList.get(i));
////                                    }
//                                }
//                        }
//                    }
//
//                }
//                    else {
//                        count = 0;
//                    }
//                    for (int i = 0; i < getRowCount() ; i++) {
//
//                        if (arrayList.) {
//                            cb.setChecked(false);
//                        } else {
//                            cb.setChecked(true);
//                        }
//                    }
//                }
//            public ArrayList<String> getArrayList(CheckBox cb){
//            CheckBox cb;
//            cb = findViewById(android.R.id.text1);
//            ((CheckBox) convertView.findViewById(android.R.id.text1)).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        CheckBox checkBox = (CheckBox) view;
//                        if (checkBox.isChecked()) {
////                            for (int i = 0; i < getRowCount(); i++) {
////                                for (int j = 0; j < getColumnCount(); j++) {
//                                    arrayList.add(answers.get(row).get(column).getAnswer());
//
////                                }
////                            }
//                        }
//                    }
//                });
//
////                return arrayList;
//            }

//                column++;
//            ((CheckBox) convertView.findViewById(android.R.id.text1)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    if (compoundButton.isChecked()){
////                        String s = answers.get(row).get(column).getAnswer();
//                        arrayList.add(questions.get(row).getData().toString());
////                        for (int i = 0; i< answers.size(); i++){
////                            arrayList.add(s);
////
//               }}
//                }

//            });

//            }

//        });
            return convertView;
        }

//

        private View getBody(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_table, parent, false);
            }
            convertView.setBackgroundResource(row % 2 == 0 ? R.drawable.bg_table_color1 : R.drawable.bg_table_color2);
//            for (int i = 0; i<dates.size() + 1; i++) {
//                ((TextView) convertView.findViewById(android.R.id.text1)).setText((CharSequence) getDevice(row).data[column + 1]);

            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(answers.get(row).get(column).getAnswers().get(column).getAnswer());
//                column++;
//            if (answers.get(row).get(column).getAnswer().isEmpty()){
//                ((TextView) convertView.findViewById(android.R.id.text1)).setText("NIL");
//            }
//            }
//            row++;
            return convertView;
        }

//        private View getFamilyView(int row, int column, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                convertView = getLayoutInflater().inflate(R.layout.item_table_family, parent, false);
//            }
//            final String string;
//            if (column == -1) {
//                string = getFamily(row);
//            } else {
//                string = "";
//            }
//            ((TextView) convertView.findViewById(android.R.id.text1)).setText(string);
//            return convertView;
//        }
////
//
        @Override
        public int getWidth(int column) {
            return 380;
        }

        @Override
        public int getHeight(int row) {
            final int height;
            if (row == -1) {
                height = 55;
//            } else if (isFamily(row)) {
//                height = 25;
            } else {
                height = 100;
            }
            return Math.round(height * density);

        }

        @Override
        public int getItemViewType(int row, int column) {
            final int itemViewType;
            if (row == -1 && column == -1) {
                itemViewType = 0;
            } else if (row == -1) {
                itemViewType = 1;
//            } else if (isFamily(row)) {
//                itemViewType = 4;
            } else if (column == -1) {
                itemViewType = 2;
            } else {
                itemViewType = 3;
            }
            return itemViewType;
        }


//
//        private boolean isFamily(int row) {
//            int family = 0;
//            while (row > 0) {
//                row -= questions.size() + 1;
//                family++;
//            }
//            return row == 0;
//        }
//
//        private String getFamily(int row) {
//            int family = 0;
//            while (row >= 0) {
//                row -= answers.get(family).size();
//                family++;
//            }
//            return String.valueOf(answers.get(family - 1));
//        }
//
////        private MainTable. getDevice(int row) {
////            int family = 0;
////            while (row >= 0) {
////                row -= familys[family].size() + 1;
////                family++;
////            }
////            family--;
////            return familys[family].get(row + familys[family].size());
////        }
//
        @Override
        public int getViewTypeCount() {
            return 5;
        }
    }
}
