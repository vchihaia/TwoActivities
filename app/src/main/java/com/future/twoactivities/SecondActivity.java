package com.future.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.future.twoactivities.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
private static final String TAG = SecondActivity.class.getSimpleName();
    public static final String EXTRA_REPLY = "extra_reply";

    private ActivitySecondBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.d(TAG, "Message received: " + message);
        mBinding.textMessage.setText(message);

        mBinding.buttonSecond.setOnClickListener(view -> replyMessage());
    }

    private void replyMessage() {
        String message = mBinding.editTextSecond.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(EXTRA_REPLY, message);
        setResult(RESULT_OK, intent);
        finish();
    }
}