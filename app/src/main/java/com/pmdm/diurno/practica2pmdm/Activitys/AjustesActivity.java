package com.pmdm.diurno.practica2pmdm.Activitys;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pmdm.diurno.practica2pmdm.Fragments.CombinacionFragment;
import com.pmdm.diurno.practica2pmdm.Fragments.DineroFragment;
import com.pmdm.diurno.practica2pmdm.R;

/**
 * Clase para la vista de ajustes. En ella se adaptarán los fragment dependiendo del item seleccionado del TabLayout
 */
public class AjustesActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DineroFragment dineroFragment;
    private CombinacionFragment combinacionFragment;
    private AjustesFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        //Cargamos los objetos donde se almacenan los item y el contenedor que cagará un fragment u otro dependiendo
        //de la pestaña cargada
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.contenedor);

        //Se inicializa las clases de los fragmentos
        dineroFragment = new DineroFragment();
        combinacionFragment = new CombinacionFragment();

        //Ajustamos la configuración para adaptar los fragment en el viewPager
        adapter = new AjustesFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Método para acabar la activity
     * @param v
     */
    public void volver(View v){
        finish();
    }

    /**
     * Método para guardar la apuesta realizada (Dinero y combinación)
     * @param v
     */
    public void guardar(View v){
        try {
            //Convertimos los valores de las casillas a int. Puede lanzar una excepción al estar las casillas vacias
            int casilla1 = Integer.parseInt(combinacionFragment.getApuesta1().getText().toString());
            int casilla2 = Integer.parseInt(combinacionFragment.getApuesta2().getText().toString());

            //Comprobamos que se haya realizado la apuesta económica
            if (dineroFragment.getSpinner().getItemAtPosition(dineroFragment.getSpinner().getSelectedItemPosition()) == null) {
                Toast.makeText(this, getResources().getText(R.string.toast_Elige_tu_Apuesta), Toast.LENGTH_LONG).show();
            //Se comprueba que las casillas de las apuestas no estáne vacias
            } else if (combinacionFragment.getApuesta1().getText().toString().isEmpty() || combinacionFragment.getApuesta1().getText().toString().isEmpty()) {
                Toast.makeText(this, getResources().getText(R.string.toast_Elige_tu_combinación_ganadora), Toast.LENGTH_LONG).show();
            //Se comprueba que las casillas de las apuestas enten entre los números 0 y 300 (ambos incluidos)
            } else if ((casilla1 < 0 || casilla1 > 300) || (casilla2 < 0 || casilla2 > 300)) {
                Toast.makeText(this, getResources().getText(R.string.toast_Combinacion_numero), Toast.LENGTH_LONG).show();
            //Si no hay ningún error de lo anterior se verifica que los datos son correctos y se finaliza la activity
            } else {
                Toast.makeText(this, getResources().getText(R.string.toast_Ajustes_guardado), Toast.LENGTH_LONG).show();
                finish();
            }
        }catch (NumberFormatException e){
            Toast.makeText(this, getResources().getText(R.string.toast_NumberFormatException), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Método para guardar los estados
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("DINERO", dineroFragment);
        outState.putSerializable("COMBINACION", combinacionFragment);
        outState.putString("APUESTA1", combinacionFragment.getApuesta1().getText().toString());
        outState.putString("APUESTA2", combinacionFragment.getApuesta2().getText().toString());
    }

    /**
     * Método para restaurar los datos
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        dineroFragment = (DineroFragment) savedInstanceState.getSerializable("DINERO");
        combinacionFragment = (CombinacionFragment) savedInstanceState.getSerializable("COMBINACION");
        combinacionFragment.getApuesta1().setText(savedInstanceState.getString("APUESTA1"));
        combinacionFragment.getApuesta2().setText(savedInstanceState.getString("APUESTA2"));

    }

    /**
     * Clase para ajustar los fragment dentro de la actvity
     */
    public class AjustesFragmentPagerAdapter extends FragmentPagerAdapter{

        public AjustesFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Método que devuelve fragment dependiendo del item pulsado
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return dineroFragment;
                case 1:
                    return combinacionFragment;
                default:
                    return null;
            }
        }

        /**
         * Método que devuelve el número de items disponibles
         * @return
         */
        @Override
        public int getCount() {
            return 2;
        }

        /**
         * Método que dibuja el nombre de los items que han sido creados
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getText(R.string.item_dinero);
                case 1:
                    return getResources().getText(R.string.item_combinaciones);
                default:
                    return null;
            }
        }
    }

}
