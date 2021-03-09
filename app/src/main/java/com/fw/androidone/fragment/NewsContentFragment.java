package com.fw.androidone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fw.androidone.R;

/**
 * description :加载新闻内容的fragment
 * author : apple
 * date : 2021/3/8 11:25
 */
public class NewsContentFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_frag, container, false);
        return view;
    }

    public void refresh(String title, String content) {
        LinearLayout linearLayout = view.findViewById(R.id.visibility_layout);
        linearLayout.setVisibility(View.VISIBLE);
        TextView tvTitle = view.findViewById(R.id.news_title);
        TextView tvContent = view.findViewById(R.id.news_content);
        tvTitle.setText(title);
        tvContent.setText(content);
    }
}
