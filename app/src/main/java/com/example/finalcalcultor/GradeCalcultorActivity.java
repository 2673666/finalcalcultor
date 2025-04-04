package com.example.finalcalcultor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * GradeCalculatorActivity 是一个用于计算课程平均分和 GPA 的 Android 活动类。
 * 它实现了 View.OnClickListener 接口以处理按钮点击事件。
 */
public class GradeCalcultorActivity extends AppCompatActivity implements View.OnClickListener {

    // UI 控件声明
    private EditText editTextCourseName; // 输入课程名称的文本框
    private EditText editTextScore; // 输入课程成绩的文本框
    private EditText editTextCredit; // 输入课程学分的文本框
    private Button btnAddCourse; // 添加课程按钮
    private Button btnCalculate; // 计算平均分和 GPA 按钮
    private Button btnClear; // 清空数据按钮
    private ListView listViewCourses; // 显示课程列表的 ListView
    private TextView textViewAverageScore; // 显示平均分的 TextView
    private TextView textViewGPA; // 显示 GPA 的 TextView

    // 数据模型和适配器
    private List<Course> courses = new ArrayList<>(); // 保存所有课程信息的列表
    private CourseAdapter courseAdapter; // 用于将课程数据绑定到 ListView 的适配器

    /**
     * 在活动创建时调用，初始化界面和控件。
     *
     * @param savedInstanceState 保存的实例状态
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcultor); // 设置布局文件

        // 初始化 UI 控件
        editTextCourseName = findViewById(R.id.editTextCourseName);
        editTextScore = findViewById(R.id.editTextScore);
        editTextCredit = findViewById(R.id.editTextCredit);
        btnAddCourse = findViewById(R.id.btnAddCourse);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        listViewCourses = findViewById(R.id.listViewCourses);
        textViewAverageScore = findViewById(R.id.textViewAverageScore);
        textViewGPA = findViewById(R.id.textViewGPA);

        // 初始化适配器并设置给 ListView
        courseAdapter = new CourseAdapter(this, courses);
        listViewCourses.setAdapter(courseAdapter);

        // 设置按钮点击监听器
        btnAddCourse.setOnClickListener(this);
        btnCalculate.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    /**
     * 处理按钮点击事件。
     *
     * @param v 被点击的视图
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) { // 根据按钮 ID 执行相应操作
            case R.id.btnAddCourse:
                addCourse(); // 添加课程
                break;
            case R.id.btnCalculate:
                calculateAverageScoreAndGPA(); // 计算平均分和 GPA
                break;
            case R.id.btnClear:
                clearCourses(); // 清空课程数据
                break;
        }
    }

    /**
     * 添加课程到列表中。
     */
    private void addCourse() {
        String courseName = editTextCourseName.getText().toString(); // 获取课程名称
        String scoreText = editTextScore.getText().toString(); // 获取课程成绩
        String creditText = editTextCredit.getText().toString(); // 获取课程学分

        // 检查输入是否为空
        if (!courseName.isEmpty() && !scoreText.isEmpty() && !creditText.isEmpty()) {
            double score = Double.parseDouble(scoreText); // 将成绩转换为双精度浮点数
            double credit = Double.parseDouble(creditText); // 将学分转换为双精度浮点数
            Course course = new Course(courseName, score, credit); // 创建课程对象
            courses.add(course); // 将课程添加到列表中
            courseAdapter.notifyDataSetChanged(); // 通知适配器数据已更改
            clearInputFields(); // 清空输入框内容
        } else {
            Toast.makeText(this, "请填写所有字段", Toast.LENGTH_SHORT).show(); // 提示用户填写所有字段
        }
    }

    /**
     * 计算平均分和 GPA。
     */
    private void calculateAverageScoreAndGPA() {
        double totalScore = 0; // 总加权成绩
        double totalCredit = 0; // 总学分

        // 遍历课程列表计算总加权成绩和总学分
        for (Course course : courses) {
            totalScore += course.getScore() * course.getCredit();
            totalCredit += course.getCredit();
        }

        // 计算平均分
        double averageScore = totalCredit != 0 ? totalScore / totalCredit : 0;

        // 计算 GPA
        double gpa = calculateGPA(courses);

        // 更新 TextView 显示结果
        textViewAverageScore.setText(String.format("平均分: %.2f", averageScore));
        textViewGPA.setText(String.format("GPA: %.2f", gpa));
    }

    /**
     * 根据课程成绩计算 GPA。
     *
     * @param courses 课程列表
     * @return 计算得到的 GPA 值
     */
    private double calculateGPA(List<Course> courses) {
        double totalGradePoints = 0; // 总绩点
        double totalCredit = 0; // 总学分

        for(Course course: courses){
            if(course.getCredit() > 0) {
                totalGradePoints += (course.getScore() - 50) * course.getCredit() /10;
                totalCredit += course.getCredit();
            }
        }
        if(totalGradePoints < 0)
        {
            return 0;
        }
        return totalCredit != 0 ? totalGradePoints / totalCredit : 0;

//        // 遍历课程列表计算总绩点和总学分
//        for (Course course : courses) {
//            double gradePoint = 0; // 单个课程的绩点
//            if (course.getScore() >= 100) {
//                gradePoint = 5.0; // 成绩 100 分及以上，绩点为 5.0
//            } else if (course.getScore() >= 87) {
//                gradePoint = 3.7; // 成绩 87-99 分，绩点为 3.7
//            } else if (course.getScore() >= 70) {
//                gradePoint = 2.0; // 成绩 70-86 分，绩点为 2.0
//            } else if (course.getScore() >= 60) {
//                gradePoint = 0; // 成绩 60-69 分，绩点为 0
//            }
//            totalGradePoints += gradePoint * course.getCredit(); // 累加加权绩点
//            totalCredit += course.getCredit(); // 累加学分
//        }
//        return totalCredit != 0 ? totalGradePoints / totalCredit : 0; // 计算 GPA
    }

    /**
     * 清空课程数据。
     */
    private void clearCourses() {
        courses.clear(); // 清空课程列表
        courseAdapter.notifyDataSetChanged(); // 通知适配器数据已更改
        textViewAverageScore.setText("平均分: 0.0"); // 重置平均分显示
        textViewGPA.setText("GPA: 0.0"); // 重置 GPA 显示
    }

    /**
     * 清空输入框内容。
     */
    private void clearInputFields() {
        editTextCourseName.setText(""); // 清空课程名称输入框
        editTextScore.setText(""); // 清空课程成绩输入框
        editTextCredit.setText(""); // 清空课程学分输入框
    }
}
