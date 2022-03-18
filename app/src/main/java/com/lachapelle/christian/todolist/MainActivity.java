package com.lachapelle.christian.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> dummyRepo;
    private ArrayList<String> currentItemList;
    private ArrayAdapter<String> lstTaskListAdapter;
    private ListView lstTasListRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dummyRepo = new ArrayList<String>();
        currentItemList = new ArrayList<String>();
        lstTasListRef = (ListView) findViewById(R.id.lstTodoList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.itmAddTask){
            final EditText taskInfo = new EditText(this);
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Add a new task")
                    .setMessage("Enter task information.")
                    .setView(taskInfo)
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String task = String.valueOf(taskInfo.getText());
                            dummyRepo.add(task);
                            currentItemList.add(task);
                            updateScreen();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            dialog.show();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateScreen(){
        if (lstTaskListAdapter == null){
            lstTaskListAdapter = new ArrayAdapter<String>(this,
                    R.layout.todoitem,
                    R.id.txtTaskTitle,
                    currentItemList);
            lstTasListRef.setAdapter(lstTaskListAdapter);
        }
        else{
            lstTaskListAdapter.clear();
            lstTaskListAdapter.addAll(dummyRepo);
            lstTaskListAdapter.notifyDataSetChanged();
        }
    }

    public void deleteTask(View view){
        View parent = (View) view.getParent();
        TextView txtTask = (TextView) parent.findViewById(R.id.txtTaskTitle);
        String task = String.valueOf(txtTask.getText());
        dummyRepo.remove(task);
        updateScreen();
    }
}