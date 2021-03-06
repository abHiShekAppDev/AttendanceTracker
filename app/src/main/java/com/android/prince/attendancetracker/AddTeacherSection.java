package com.android.prince.attendancetracker;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class AddTeacherSection extends AppCompatActivity {

    TextInputEditText teacherName;
    TextInputEditText teacherPassCode;

    Button addTeacher;

    String teacherNameStr;
    String teacherPassCodeStr;

    DatabaseReference databaseReferenceToTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher_section);

        teacherName = (TextInputEditText)findViewById(R.id.teacherNameEditTextAtAddTeacherSection);
        teacherPassCode = (TextInputEditText)findViewById(R.id.passCodeEditTextAtAddTeacherSection);

        addTeacher = (Button)findViewById(R.id.addTeacherButtonAtTeacherSection);

        databaseReferenceToTeacher = FirebaseDatabase.getInstance().getReference().child("TEACHER");

        addTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teacherNameStr = teacherName.getText().toString();
                teacherPassCodeStr = teacherPassCode.getText().toString();

                databaseReferenceToTeacher.child("TOTAL").child(teacherPassCodeStr).setValue(teacherNameStr);
                databaseReferenceToTeacher.child("ASSIGNED").child(teacherNameStr).setValue(true);

                startActivity(new Intent(AddTeacherSection.this,AdminSection.class));
                finish();

            }
        });
    }
}
