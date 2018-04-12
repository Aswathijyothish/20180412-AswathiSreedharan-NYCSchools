package com.jpmchase.nycschool;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by aswathijyothish on 4/11/18.
 */

public class SchoolViewHolder extends RecyclerView.ViewHolder {
    protected TextView schoolName;

    public SchoolViewHolder(View view) {
        super(view);
        this.schoolName = (TextView) view.findViewById(R.id.name_vw);
    }

}
