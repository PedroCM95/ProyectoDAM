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
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.pedro.proyecto_pgl_2018.ManagerEventApplication;
import com.example.pedro.proyecto_pgl_2018.R;
import com.example.pedro.proyecto_pgl_2018.RellenarFormularioInsertar;
import com.example.pedro.proyecto_pgl_2018.constantes.RequestUtility;
import com.example.pedro.proyecto_pgl_2018.constantes.Utilidades;

import java.io.FileNotFoundException;

public class RequestFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    RequestCursorAdapter xAdapter;
    LoaderManager.LoaderCallbacks<Cursor> mCallBacks;

    ActionMode mActionMode;
    View viewSeleccionado;

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
                Intent intent = new Intent(getActivity(), RellenarFormularioInsertar.class);
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

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mActionMode!=null){
                    return false;
                }
                mActionMode = getActivity().startActionMode(mActionModeCallback);
                view.setSelected(true);
                viewSeleccionado = view;
                return true;
            }
        });
    }

    ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater inflater = actionMode.getMenuInflater();
            inflater.inflate(R.menu.menu_contextual, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.menu_delete:
                    int requestID = (Integer) viewSeleccionado.getTag();
                    RequestUtility.deleteRecordBitacora(getActivity().getContentResolver(), requestID);
                    break;
                case R.id.menu_edit:
                    Intent intent = new Intent(getActivity(), RellenarFormularioModificar.class);
                    requestID = (Integer) viewSeleccionado.getTag();
                    intent.putExtra(ManagerEventApplication.Solicitud._ID, requestID);
                    startActivity(intent);
                    break;
            }
            mActionMode.finish();
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mActionMode = null;
        }
    };






    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String columns[] = {
                ManagerEventApplication.Solicitud._ID,
                ManagerEventApplication.Solicitud.Name,
                ManagerEventApplication.Solicitud.Lugar,
                ManagerEventApplication.Solicitud.Queja

        };

        Uri uri = ManagerEventApplication.Solicitud.CONTENT_URI;

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
            String Departament = cursor.getString(cursor.getColumnIndex(ManagerEventApplication.Solicitud.Lugar));
            String DNI = cursor.getString(cursor.getColumnIndex(ManagerEventApplication.Solicitud.Queja));


            TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
            textViewName.setText(Name);

            TextView textViewDepartament = (TextView) view.findViewById(R.id.textViewLugar);
            textViewDepartament.setText(Departament);

            TextView textViewDNI = (TextView) view.findViewById(R.id.textViewQueja);
            textViewDNI.setText(DNI);

            ImageView image = (ImageView) view.findViewById(R.id.imageView);
            try {
                Utilidades.loadImageFromStorage(getActivity(), "img_" + ID + ".jpg", image);
            } catch (FileNotFoundException e) {
                ColorGenerator generator = ColorGenerator.MATERIAL;
                int color = generator.getColor(Name);
                TextDrawable drawable = TextDrawable.builder().buildRound(
                        Name.substring(0,1),
                        color);
                image.setImageDrawable(drawable);
            }

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


