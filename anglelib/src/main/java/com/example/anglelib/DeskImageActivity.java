package com.example.anglelib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.anglelib.view.DeskView;

public class DeskImageActivity extends AppCompatActivity {
    protected DeskView mDv;
    protected Button mBtDec;
    protected TextView mTvAngle;
    protected Button mBtAdd;
    protected Button mBtAllAngle;
    protected Button mBtClearLast;
    protected Button mBtZone;

    private void assignViews() {
        mDv = (DeskView) findViewById(R.id.dv);
        mBtDec = (Button) findViewById(R.id.bt_dec);
        mTvAngle = (TextView) findViewById(R.id.tv_angle);
        mBtAdd = (Button) findViewById(R.id.bt_add);
        mBtAllAngle = (Button) findViewById(R.id.bt_all_angle);
        mBtClearLast = (Button) findViewById(R.id.bt_clear_last);
        mBtZone = (Button) findViewById(R.id.bt_zone);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_desk_image);
        assignViews();


        mDv.setMode(DeskView.MODE_SINGLE_ANGLE);

        mBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double singleAngle = (mDv.getSingleAngle() +1) % 91;
                mDv.setSingleAngle(singleAngle);
                mDv.setMode(DeskView.MODE_SINGLE_ANGLE);
                mTvAngle.setText(""+singleAngle);
            }
        });
        mBtDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double singleAngle = (mDv.getSingleAngle() -1) % 91;
                mDv.setSingleAngle(singleAngle);
                mDv.setMode(DeskView.MODE_SINGLE_ANGLE);
                mTvAngle.setText(""+singleAngle);
            }
        });
        mBtAllAngle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDv.setMode(DeskView.MODE_All_ANGLE);
            }
        });
        mBtClearLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean clearLast = !mDv.isClearLast();
                mDv.setClearLast(clearLast);
                mBtClearLast.setText("ClearLast_"+clearLast);
            }
        });
        mBtZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDv.zone>=12){
                    mDv.zone =0;
                }
                mDv.zone++;
                mDv.setMode(DeskView.MODE_ZONE_ANGLE);

            }
        });

    }







}
