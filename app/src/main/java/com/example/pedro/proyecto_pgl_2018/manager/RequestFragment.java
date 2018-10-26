package com.example.pedro.proyecto_pgl_2018.manager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.pedro.proyecto_pgl_2018.R;
import com.example.pedro.proyecto_pgl_2018.manager.data.Manager;


public class RequestFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private RequestRecyclerViewAdapter xAdapter;
    public RequestFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            xAdapter = new RequestRecyclerViewAdapter();
            recyclerView.setAdapter(xAdapter);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0,null,this);

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String projection[] = {
                Manager.Name,
                Manager.Departament,
                Manager.DNI,
                Manager.Email
        };
        Uri uri = Uri.parse(Manager.CONTENT_URI);
        return new CursorLoader(getActivity(), uri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        xAdapter.setCursor(cursor);
        xAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        xAdapter.setCursor(null);
        xAdapter.notifyDataSetChanged();
    }
}
