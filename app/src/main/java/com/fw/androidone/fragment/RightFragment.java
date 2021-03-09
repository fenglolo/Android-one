package com.fw.androidone.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fw.androidone.R;
import com.fw.androidone.activity.fragment.FragmentTest1Activity;

/**
 * description :
 * author : apple
 * date : 2021/3/8 11:25
 */
public class RightFragment extends Fragment {

    private static final String TAG = "RightFragment";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "--onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "--onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "--onCreateView");
        View view = inflater.inflate(R.layout.fragment_right, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "--onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        FragmentTest1Activity activity = (FragmentTest1Activity) getActivity();
        Log.d(TAG, "--onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "--onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "--onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "--onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "--onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "--onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "--onDetach");
    }
}
