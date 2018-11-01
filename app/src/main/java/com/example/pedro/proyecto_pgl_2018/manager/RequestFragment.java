package com.example.pedro.proyecto_pgl_2018.manager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.pedro.proyecto_pgl_2018.ManagerEventApplication;
import com.example.pedro.proyecto_pgl_2018.R;
import com.example.pedro.proyecto_pgl_2018.RellenarFormulario;
import com.example.pedro.proyecto_pgl_2018.constantes.Utilidades;

public class RequestFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    RequestCursorAdapter xAdapter;
    LoaderManager.LoaderCallbacks<Cursor> mCallBacks;


    public static RequestFragment newInstance() {
        RequestFragment f = new RequestFragment();

        return f;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        MenuItem menuItem = menu.add(Menu.NONE, Utilidades.INSERTAR, Menu.NONE, "Insertar");
        menuItem.setIcon(R.drawable.ic_formulario_white);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case Utilidades.INSERTAR:
                Intent intent = new Intent(getActivity(), RellenarFormulario.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_my_request, container, false);

        xAdapter = new RequestCursorAdapter(getActivity());
        setListAdapter(xAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCallBacks = this;

        getLoaderManager().initLoader(0, null, this);

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String columns[] = {
                ManagerEventApplication.Solicitud.Name,
                ManagerEventApplication.Solicitud.Departament,
                ManagerEventApplication.Solicitud.DNI,
                ManagerEventApplication.Solicitud.Email
        };
        Uri uri = Uri.parse(ManagerEventApplication.Solicitud.CONTENT_URI);

        String selection = null;

        return new CursorLoader(getActivity(), uri, columns, selection, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        Uri laUriBase = Uri.parse("content://"+ManagerEventApplication.AUTHORITY+"/Request");
        data.setNotificationUri(getActivity().getContentResolver(), laUriBase);

        xAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        xAdapter.swapCursor(null);
    }


    public class RequestCursorAdapter extends CursorAdapter {

        public RequestCursorAdapter(Context context) { super(context, null, false); }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            int ID = cursor.getInt(cursor.getColumnIndex(ManagerEventApplication.Solicitud._ID));
            String Name = cursor.getString(cursor.getColumnIndex(ManagerEventApplication.Solicitud.Name));
            String Departament = cursor.getString(cursor.getColumnIndex(ManagerEventApplication.Solicitud.Departament));
            String DNI = cursor.getString(cursor.getColumnIndex(ManagerEventApplication.Solicitud.DNI));
            String Email = cursor.getString(cursor.getColumnIndex(ManagerEventApplication.Solicitud.Email));

            TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
            textViewName.setText(Name);

            TextView textViewDepartament = (TextView) view.findViewById(R.id.textViewDepartament);
            textViewDepartament.setText(Departament);

            TextView textViewDNI = (TextView) view.findViewById(R.id.textViewDNI);
            textViewDNI.setText(DNI);

            TextView textViewEmail = (TextView) view.findViewById(R.id.textViewEmail);
            textViewEmail.setText(Email);

            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color = generator.getColor(Name);
            TextDrawable drawable = TextDrawable.builder().buildRound(
                    Name.substring(0,1),
                    color);

            ImageView image = (ImageView) view.findViewById(R.id.imageView);
            image.setImageDrawable(drawable);

            view.setTag(ID);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.fragment_request, parent, false);
            bindView(v, context, cursor);
            return v;
        }
    }
}


