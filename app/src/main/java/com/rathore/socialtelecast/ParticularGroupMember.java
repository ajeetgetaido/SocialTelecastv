package com.rathore.socialtelecast;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ParticularGroupMember extends MainActivity {

    //private static final String TAG = "createGroup";

    public static final String EMAIL_LIST_KEY = "email_list";

    //private static final int SELECT_PICTURE = 100;

    private TextView mTvList;
    private RecyclerView mRecyclerPGM;
    private Button btnPGMA;
    private FloatingActionButton btnFloatPGMA;
    private EditText editTextPGM;
    private View view;
    AppCompatImageView imgView;

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        super.onConnected(bundle);

        Button btnSend = (Button) findViewById(R.id.btnParticularGroup);

                final ArrayList<String> emailList = getEmailListFromIntent();
                if (emailList != null && !emailList.isEmpty()) {
                    String[] stockArr = emailList.toArray(new String[emailList.size()]);

//                    Intent it = new Intent(Intent.ACTION_SEND_MULTIPLE);
//                    it.putExtra(Intent.EXTRA_EMAIL, stockArr);
//                    it.putExtra(Intent.EXTRA_SUBJECT, "Hello");
//                    it.putExtra(Intent.EXTRA_TEXT, "Check the update");
//                    it.setType("message/rfc822");
//                    startActivity(it);
                }


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PickFileWithOpenerActivity.class);
                intent.putStringArrayListExtra("groupList",emailList);
                intent.setAction("ParticularGroupMember");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_group_member);

        mTvList = (TextView) findViewById(R.id.tvEmailList);
        mRecyclerPGM = (RecyclerView) findViewById(R.id.recyclerParticularGroup);
        btnPGMA = (Button) findViewById(R.id.btnParticularGroup);
        final ArrayList<String> emailList = getEmailListFromIntent();
        if (emailList != null && !emailList.isEmpty()) {
            setGroupAdapter(emailList);
        }

    }



    private ArrayList<String> getEmailListFromIntent() {
        if (getIntent() != null && getIntent().hasExtra(EMAIL_LIST_KEY)) {
            return getIntent().getStringArrayListExtra(EMAIL_LIST_KEY);
        }
        return new ArrayList<>();
    }

    private void setGroupAdapter(ArrayList<String> emailList) {
        AdapterParticularGroupMember adapter = new AdapterParticularGroupMember(this, emailList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerPGM.setLayoutManager(mLayoutManager);
        mRecyclerPGM.setItemAnimator(new DefaultItemAnimator());
        mRecyclerPGM.setAdapter(adapter);
    }



}
