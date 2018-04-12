package com.jpmchase.nycschool;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by aswathijyothish on 4/11/18.
 */

public class SchoolRecyclerViewAdapter extends RecyclerView.Adapter<SchoolViewHolder> {

    private List<SchoolDetail> schoolList;
    private Context context;

    public SchoolRecyclerViewAdapter(List<SchoolDetail> schoolList, Context cContext) {
        this.context = context;
        this.schoolList = schoolList;
    }

    @Override
    public SchoolViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.school_holder, null);
        SchoolViewHolder schoolViewHolder = new SchoolViewHolder(view);
        return schoolViewHolder;
    }

    @Override
    public void onBindViewHolder(SchoolViewHolder holder, int i) {
        if(i %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#EBF5FB"));
        }
        SchoolDetail schoolDetails = schoolList.get(i);
        holder.schoolName.setText(schoolDetails.getSchoolName());
    }


    public void loadNewData(List<SchoolDetail> schoolList) {
        this.schoolList = schoolList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (null!=schoolList ? schoolList.size() : 0);
    }


}
