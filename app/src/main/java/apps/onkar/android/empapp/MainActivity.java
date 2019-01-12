package apps.onkar.android.empapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<Employee> emplist = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String TAG=MainActivity.class.getSimpleName();
    FirebaseDatabase database;
    DatabaseReference myRef;

    public FloatingActionButton addnewemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MyAdapter(emplist);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");


        addnewemp = (FloatingActionButton) findViewById(R.id.newemp);
        addnewemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, addEmployee.class));
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // specify an adapter (see also next example)
                long value=dataSnapshot.getChildrenCount();
                Log.d(TAG,"no of children: "+value);

                //mAdapter = (MyAdapter)dataSnapshot.getValue();
                //GenericTypeIndicator<ArrayList<Employee>> t = new GenericTypeIndicator<ArrayList<Employee>>() {};
                //ArrayList<Employee> values = dataSnapshot.getValue(t);

                ArrayList<Employee> values = new ArrayList<Employee>();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    values.add(child.getValue(Employee.class));
                }
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(new MyAdapter(values));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void prepareEmployeeData() {
        Employee employee = new Employee("Advait", "40");
        emplist.add(employee);

        employee = new Employee("Manoj", "30");
        emplist.add(employee);

        employee = new Employee("Ramesh", "25");
        emplist.add(employee);

        employee = new Employee("Ram", "32");
        emplist.add(employee);

        employee = new Employee("Aditya", "26");
        emplist.add(employee);

        employee = new Employee("Akshay", "37");
        emplist.add(employee);

        employee = new Employee("Yash", "24");
        emplist.add(employee);

        employee = new Employee("Deep", "45");
        emplist.add(employee);

        employee = new Employee("Kavita", "28");
        emplist.add(employee);

        employee = new Employee("Nakul", "37");
        emplist.add(employee);

        mAdapter.notifyDataSetChanged();
    }
}
