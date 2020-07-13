package com.example.table.adapters;

import com.example.table.Cell;
import com.example.table.ColumnHeader;
import com.example.table.R;
import com.example.table.RowHeader;
import com.example.table.TableViewModel;
import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.github.mikephil.charting.charts.Chart.LOG_TAG;

public class MatrixTableAdapter<T> extends BaseTableAdapter {

	private final static int WIDTH_DIP = 150;
	private final static int HEIGHT_DIP = 90;

	private final Context context;
	Activity activity;

	private T[][] table;
	TableViewModel tableViewModel;
	private final int width;
	private final int height;
	ArrayList<String> rowList;

	public MatrixTableAdapter(Context context) {
		this(context, null );
	}

	public MatrixTableAdapter(Context context, T[][] table) {
		this.context = context;
		Resources r = context.getResources();

//		this.rowList = arrayList;

		width = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, WIDTH_DIP, r.getDisplayMetrics()));
		height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, HEIGHT_DIP, r.getDisplayMetrics()));

		setInformation(table);
	}

	public void setInformation(T[][] table){
		this.table = table;
	}

	@Override
	public int getRowCount() {
		return table.length - 1;
	}

	@Override
	public int getColumnCount() {
		return table[0].length - 1;
	}

	@Override
	public View getView(final int row, final int column, View convertView, ViewGroup parent) {
////        CheckBox checkBox = null;
////        TextView textView = null;
////        if (convertView == null) {
//////            finalConvertView1 = convertView;
////            if (column == -1) {
////                checkBox = new CheckBox(context);
//////                convertView = new CheckBox(context);
//////                ((CheckBox) convertView).setGravity(Gravity.CENTER_VERTICAL);
////                checkBox.setGravity(Gravity.CENTER_VERTICAL);
////
////
////            }
////            else {
////                textView = new TextView(context);
//////                convertView = new TextView(context);
//////                ((TextView) convertView).setGravity(Gravity.CENTER_VERTICAL);
////                textView.setGravity(Gravity.CENTER_VERTICAL);
////            }
////
//////            column = -1;
////	    }
////        else {
////            if (column == -1){
////                checkBox = (CheckBox) convertView;
////            }
////            else {
////                textView = (TextView) convertView;
////            }
////        }
////        if (column == -1) {
//////                ((CheckBox) convertView).setText(table[row + 1][column + 1].toString());
////            checkBox.setText(table[row + 1][column + 1].toString());
////            final CheckBox finalCheckBox = checkBox;
////            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
////                @Override
////                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
////                    if (finalCheckBox.isChecked()) {
////                        for (int i = 0; i < row; i++) {
////                            rowList.add(String.valueOf(i));
////                        }
////                    }
////                }
////            });
////        } else {
//////            ((TextView) convertView).setText(table[row + 1][column + 1].toString());
////                textView.setText(table[row + 1][column + 1].toString());
////        }
////        if (column == -1) {
////            return checkBox;
////        }
////        else {
////            return  textView;
////        }
//        if (convertView == null) {
////            if (column == -1){
////                convertView = new CheckBox(context);
////                ((CheckBox) convertView).setGravity(Gravity.CENTER_VERTICAL);
//////				((CheckBox) convertView).setText(table[row + 1][column + 1].toString());
////			}else {
//                convertView = new TextView(context);
//                ((TextView) convertView).setGravity(Gravity.CENTER_VERTICAL);
//
////			}
//        }
////        if (column == -1){
////            ((CheckBox) convertView).setText(table[row + 1][column + 1].toString());
////			View finalConvertView = convertView;
////			((CheckBox)convertView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
////                @Override
////                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
////                    if (((CheckBox) finalConvertView).isChecked()) {
////                        for (int i = 0; i < row; i++) {
////                            rowList.add(String.valueOf(i));
////                        }
////                    }
////                }
////            });
////            return convertView;
////        }
////        ((TextView) convertView).setText(table[row + 1][column + 1].toString());
//		((TextView) convertView).setText(table[row + 1][column + 1].toString());
//        return convertView;

		if (convertView == null) {
			convertView = new TextView(context);
			((TextView) convertView).setGravity(Gravity.CENTER_VERTICAL);
		}
		((TextView) convertView).setText(table[row + 1 ][column + 1].toString());
		return convertView;
	}

	@Override
	public int getHeight(int row) {
		return height;
	}

	@Override
	public int getWidth(int column) {
		return width;
	}

	@Override
	public int getItemViewType(int row, int column) {
		return 0;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}


}
