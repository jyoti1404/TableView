package com.example.table;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.example.table.adapters.SampleTableAdapter;
import com.inqbarna.tablefixheaders.TableFixHeaders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class StyleTable extends Activity {

	public String loadJSONFromAsset(Context context) {
		String json = null;
		try {
			InputStream is = getApplicationContext().getAssets().open("list.json");

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
	ArrayList<String> arrayList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table);

		TableFixHeaders tableFixHeaders = (TableFixHeaders) findViewById(R.id.table);


			tableFixHeaders.setAdapter(new MyAdapter(this));
	}

	public class MyAdapter extends SampleTableAdapter {

		private final int width;
		private final int height;

		public MyAdapter(Context context) {
			super(context);

			Resources resources = context.getResources();

			width = resources.getDimensionPixelSize(R.dimen.table_width);
			height = resources.getDimensionPixelSize(R.dimen.table_height);

			try {
				JSONObject obj = new JSONObject(loadJSONFromAsset(context));
				JSONArray m_jArry = obj.getJSONArray("questions");
				HashMap<String, String> m_li;

				for (int i = 0; i < m_jArry.length(); i++) {
					JSONObject jo_inside = m_jArry.getJSONObject(i);
					Log.d("Details-->", jo_inside.getString("question_title"));
					String question_title = jo_inside.getString("question_title");
//				String url_value = jo_inside.getString("answers");
//				JSONArray jsonArrayAnswers = jo_inside.getJSONArray("answers");
//////
////				if (jsonArrayAnswers.length() > 0) {
////					for (int j = 0; j < jsonArrayAnswers.length(); j++) {
////						JSONObject jsonObjectAnswer = jsonArrayAnswers.getJSONObject(i);
////						String ans = jsonObjectAnswer.getString("answer");
////					}
//				}

					//Add your values in your `ArrayList` as below
					m_li = new HashMap<String, String>();
					m_li.put("questions", question_title);
//				m_li.put(");

					arrayList.add(question_title);

//					familys[0].list.add(new Nexus(question_title, "a"));
					//	formList.add(m_li);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}


		}

		@Override
		public int getRowCount() {
			return 10;
		}

		@Override
		public int getColumnCount() {
			return 6;
		}

		@Override
		public int getWidth(int column) {
			return width;
		}

		@Override
		public int getHeight(int row) {
			return height;
		}

		@Override
		public String getCellString(int row, int column) {
			return String.valueOf(arrayList);
//			return "Lorem (" + row + ", " + column + ")";
		}

		@Override
		public int getLayoutResource(int row, int column) {
			final int layoutResource;
			switch (getItemViewType(row, column)) {
				case 0:
					layoutResource = R.layout.item_table1_header;
				break;
				case 1:
					layoutResource = R.layout.item_table1;
				break;
				default:
					throw new RuntimeException("wtf?");
			}
			return layoutResource;
		}

		@Override
		public int getItemViewType(int row, int column) {
			if (row < 0) {
				return 0;
			} else {
				return 1;
			}
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}
	}
}
