package com.future.twoactivities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.future.twoactivities.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "extra_message";
    private static final int TEXT_REQUEST = 1;

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        
        mBinding.buttonMain.setOnClickListener(view -> launchSecondActivity());

        if (savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if (isVisible) {
                mBinding.textHeaderReply.setVisibility(View.VISIBLE);
                mBinding.textMessageReply.setText(savedInstanceState.getString("reply_text"));
                mBinding.textMessageReply.setVisibility(View.VISIBLE);
            }
        }
    }

    ActivityResultLauncher<Intent> mSecondActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            String replyMessage = result.getData().getStringExtra(SecondActivity.EXTRA_REPLY);
            mBinding.textHeaderReply.setVisibility(View.VISIBLE);
            mBinding.textMessageReply.setText(replyMessage);
            mBinding.textMessageReply.setVisibility(View.VISIBLE);
        }
    });

    private void launchSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mBinding.editTextMain.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        Log.d(TAG, "launch second activity: " + message);
        mSecondActivityLauncher.launch(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "call onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "call onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "call onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "call onResume");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "call onSaveInstanceState");
        if (mBinding.textHeaderReply.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", mBinding.textMessageReply.getText().toString());
        }
    }
}