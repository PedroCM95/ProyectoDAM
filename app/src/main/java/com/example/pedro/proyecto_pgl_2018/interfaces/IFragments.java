package com.example.pedro.proyecto_pgl_2018.interfaces;


import com.example.pedro.proyecto_pgl_2018.fragments.BienvenidoFragment;
import com.example.pedro.proyecto_pgl_2018.fragments.ConsultarFragment;
import com.example.pedro.proyecto_pgl_2018.fragments.IncidenciasFragment;
import com.example.pedro.proyecto_pgl_2018.fragments.RegistrarUsuarioFragment;


/**
 * Created by CHENAO on 5/08/2017.
 */

public interface IFragments extends RegistrarUsuarioFragment.OnFragmentInteractionListener, BienvenidoFragment.OnFragmentInteractionListener,
        ConsultarFragment.OnFragmentInteractionListener, IncidenciasFragment.OnFragmentInteractionListener {
}
