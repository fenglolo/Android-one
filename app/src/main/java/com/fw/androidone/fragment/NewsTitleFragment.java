package com.fw.androidone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fw.androidone.R;
import com.fw.androidone.activity.fragment.NewsContentActivity;
import com.fw.androidone.entity.fragment.News;

import java.util.ArrayList;
import java.util.List;

/**
 * description :加载新闻标题的fragment
 * author : apple
 * date : 2021/3/8 11:25
 */
public class NewsTitleFragment extends Fragment {

    private View view;
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_title_frag, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    private List<News> getNews() {
        List<News> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            News news = new News();
            news.setTitle("这是一个标题" + i);
            news.setContent("这是内容" + i);
            list.add(news);
        }
        return list;
    }

    //适配器
    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        List<News> list = new ArrayList<>();

        public NewsAdapter(List<News> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            holder.news_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = list.get(holder.getAdapterPosition());
                    if (isTwoPane) {
                        NewsContentFragment contentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        contentFragment.refresh(news.getTitle(), news.getContent());
                    } else {
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news = list.get(position);
            holder.news_title.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView news_title;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                news_title = itemView.findViewById(R.id.news_title);
            }
        }
    }
}
