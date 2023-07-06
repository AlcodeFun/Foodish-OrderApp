package com.example.project.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import com.example.project.Adapter.PersonAdapter;
import com.example.project.Domain.Person;
import com.example.project.R;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    ListView listView;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        listView = findViewById(R.id.list);
        //Create Data
        ArrayList<Person> arrayList = new ArrayList<>();
        arrayList.add(new Person(R.drawable.a5,"Alisya","Halo kak, saya sudah didepan"));
        arrayList.add(new Person(R.drawable.a1,"Naufal","Pesanan sesuai aplikasi kak"));
        arrayList.add(new Person(R.drawable.a2,"Bintang","Pedes semua ya jadinya"));
        arrayList.add(new Person(R.drawable.a3,"Raidan","Halo kak, saya sudah didepan"));
        arrayList.add(new Person(R.drawable.a4,"Frida","Saya menuju restoran"));
        arrayList.add(new Person(R.drawable.a6,"Puspa","Less sugar ya kopinya yang latte"));
        arrayList.add(new Person(R.drawable.a7,"Pams","Sambelnya tinggal terasi"));
        arrayList.add(new Person(R.drawable.a5,"Alisya","Halo kak, saya sudah didepan"));
        arrayList.add(new Person(R.drawable.a1,"Naufal","Pesanan sesuai aplikasi kak"));
        arrayList.add(new Person(R.drawable.a2,"Bintang","Pedes semua ya jadinya"));
        //Person Adapter
        PersonAdapter personAdapter = new PersonAdapter(this,R.layout.list_row,arrayList);
        listView.setAdapter(personAdapter);
    }
}