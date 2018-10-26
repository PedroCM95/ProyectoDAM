package com.example.pedro.proyecto_pgl_2018.manager;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.pedro.proyecto_pgl_2018.R;
import com.example.pedro.proyecto_pgl_2018.manager.data.Manager;


public class RequestRecyclerViewAdapter extends RecyclerView.Adapter<RequestRecyclerViewAdapter.ViewHolder> {

    Cursor cursor;

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public RequestRecyclerViewAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
       cursor.moveToPosition(position);
       String mName = cursor.getString(cursor.getColumnIndex(Manager.Name));
        holder.mName.setText(mName);
        holder.mDepartament.setText(cursor.getString(cursor.getColumnIndex(Manager.Departament)));
        holder.mDNI.setText(cursor.getString(cursor.getColumnIndex(Manager.DNI)));
        holder.mEmail.setText(cursor.getString(cursor.getColumnIndex(Manager.Email)));

        ColorGenerator generator = ColorGenerator.MATERIAL;
        TextDrawable drawable = TextDrawable.builder().buildRound(
                    mName.substring(0,1),
                    generator.getColor(mName));
        holder.mImageView.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mName;
        public final TextView mDepartament;
        public final TextView mDNI;
        public final TextView mEmail;
        public final ImageView mImageView;



        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = view.findViewById(R.id.textViewName);
            mDepartament = view.findViewById(R.id.textViewDepartament);
            mDNI = view.findViewById(R.id.textViewDNI);
            mEmail = view.findViewById(R.id.textViewEmail);
            mImageView = view.findViewById(R.id.imageView);
        }
    }
}
