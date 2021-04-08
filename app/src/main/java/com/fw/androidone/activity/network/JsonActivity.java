package com.fw.androidone.activity.network;

import android.util.Log;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;
import com.fw.androidone.entity.network.App;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * description :JSON解析 测试类
 * author : apple
 * date : 2021/4/7 11:27
 */
public class JsonActivity extends BaseActivity {
    String jsonData = "[{\"id\":\"5\",\"name\":\"TT\",\"version\":\"1.0\"}," +
            "{\"id\":\"6\",\"name\":\"YY\",\"version\":\"1.3\"}," +
            "{\"id\":\"7\",\"name\":\"UU\",\"version\":\"1.5\"}]";

    @Override
    protected int getContentLayout() {
        return R.layout.activity_json;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {
        parseJSONWithJSONObject(jsonData);
        parseJSONWithGSON(jsonData);
    }

    //JSONObject方式解析
    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d("TAG", "id is =" + id);
                Log.d("TAG", "name is =" + name);
                Log.d("TAG", "version is =" + version);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //GSON方式解析
    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        List<App> list = gson.fromJson(jsonData, new TypeToken<List<App>>() {
        }.getType());
        for (App app : list) {
            Log.d("TAG", "id is =" + app.getId());
            Log.d("TAG", "name is =" + app.getName());
            Log.d("TAG", "version is =" + app.getVersion());
        }
    }
}
