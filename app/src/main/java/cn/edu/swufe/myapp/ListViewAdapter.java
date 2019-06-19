package cn.edu.swufe.myapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    private LayoutInflater mInflater;


    public ListViewAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
        paramsList = new HashMap<>();
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        public TextView tvName;
        public EditText etParam;
    }


    private Map<String,String> paramMap;
    private static Map<Integer,Map<String,String>> paramsList;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_activity, null);
            holder.tvName = (TextView) convertView.findViewById(R.id.itemn);
            holder.etParam = (EditText) convertView.findViewById(R.id.itemde);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(mList.get(position));
        holder.etParam.clearFocus();
        //先清除之前的文本改变监听
        if (holder.etParam.getTag() instanceof TextWatcher) {
            holder.etParam.removeTextChangedListener((TextWatcher) (holder.etParam.getTag()));
        }
        //文本改变监听
        TextWatcher paramsWatcher = new SimpeTextWather() {

            @Override
            public void afterTextChanged(Editable s) {
                if(s!=null){
                    paramMap = new HashMap<>();
                    paramMap.put("value", s.toString());
                    paramsList.put(position, paramMap);
                }
                else{
                    Toast.makeText(mContext, "请输入内容!", Toast.LENGTH_SHORT).show();
                }
            }
        };
        holder.etParam.addTextChangedListener(paramsWatcher);
        return convertView;

    }

    //暴露一个方法供外界访问
    public static List<String> getParams(){
        List<String> lsit = new ArrayList<>();
        for (int i = 0; i < paramsList.size(); i++) {
            lsit.add(paramsList.get(i).get("value"));
        }
        return lsit;
    }

    public abstract class SimpeTextWather implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }
}

