package apps.onkar.android.empapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addEmployee extends AppCompatActivity {

    private EditText nameEditText;
    private EditText ageEditText;
    private Button submitButton;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        nameEditText = (EditText) findViewById(R.id.enter_name);
        ageEditText = (EditText) findViewById(R.id.enter_age);
        submitButton = (Button) findViewById(R.id.submit);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Employee emp = new Employee(String.valueOf(nameEditText.getText()),String.valueOf(ageEditText.getText()));
                myRef.push().setValue(emp);

                nameEditText.setText("");
                ageEditText.setText("");
                Toast.makeText(getApplicationContext(),getString(R.string.submitfloatingbutton),Toast.LENGTH_SHORT).show();
            }
        });
        //submitButton.setOnClickListener();
    }
}
