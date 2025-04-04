package com.example.finalcalcultor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CourseAdapter extends ArrayAdapter<Course> {

    public CourseAdapter(Context context, List<Course> courses) {
        super(context, 0, courses); // 初始化父类
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Course course = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TextView textView1 = convertView.findViewById(android.R.id.text1);
        TextView textView2 = convertView.findViewById(android.R.id.text2);

        textView1.setText(course.getName());
        textView2.setText(String.format("成绩: %.2f, 学分: %.2f", course.getScore(), course.getCredit()));

        return convertView;
    }
}
