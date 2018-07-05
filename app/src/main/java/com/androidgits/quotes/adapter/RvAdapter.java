package com.androidgits.quotes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidgits.quotes.R;
import com.androidgits.quotes.model.Quotes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 7/5/2018.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyviewHolder>{

    private Context mcontext;
    private List<Quotes> mData;

    public RvAdapter(Context mcontext, List<Quotes> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mcontext);
        View view = mInflater.inflate(R.layout.row,parent,false);
        final MyviewHolder viewHolder = new MyviewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        holder.tvQuote.setText(mData.get(position).getQuote());
        holder.tvCat.setText(mData.get(position).getCat());
        holder.tvAuthor.setText(mData.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{
        TextView tvQuote,tvCat,tvAuthor;

        public MyviewHolder(View itemView) {
            super(itemView);
            tvAuthor = (TextView) itemView.findViewById(R.id.author);
            tvAuthor.setTextIsSelectable(true);
            tvCat = (TextView) itemView.findViewById(R.id.categorie);
            tvQuote = (TextView) itemView.findViewById(R.id.quote);
        }
    }
}
