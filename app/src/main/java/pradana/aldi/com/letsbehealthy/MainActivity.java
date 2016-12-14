package pradana.aldi.com.letsbehealthy;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import layout.AndroidOpenDBHelper;
import ru.katso.livebutton.LiveButton;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * sections. We use a {@link FragmentPagerAdapter} derivative, which will keep every loaded
     * fragment in memory. If this becomes too memory intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private  static EditText ed1,ed2,ed3,ed4,ed5;
    private  static LovelyInfoDialog dialog,dialog2,dialog3;
    private static ListView listdata;

    private static ListAdapter listAdapter;
    private static AndroidOpenDBHelper aodh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1){
                View rootView = inflater.inflate(R.layout.fragment_sub_page1, container, false);
                ed1 = (EditText) rootView.findViewById(R.id.editText4);
                ed2 = (EditText) rootView.findViewById(R.id.editText5);
                ed3 = (EditText) rootView.findViewById(R.id.editText6);
                ed4 = (EditText) rootView.findViewById(R.id.editText7);
                ed5 = (EditText) rootView.findViewById(R.id.editText8);
                LiveButton btnCek = (LiveButton) rootView.findViewById(R.id.btnCekData);

                dialog = new LovelyInfoDialog(getContext());
                dialog2 = new LovelyInfoDialog(getContext());
                dialog3 = new LovelyInfoDialog(getContext());

                btnCek.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            String hasilcek=cekHasilKesehatan();


                            if(hasilcek.equalsIgnoreCase("")){
                                dialog3
                                        .setTopColorRes(R.color.c_green_crayola)
                                        //This will add Don't show again checkbox to the dialog. You can pass any ID as argument
                                        .setTitle("Selamat")
                                        .setMessage("Anda Sehat")
                                        .show();
                            }
                            else{
                                dialog
                                        .setTopColorRes(R.color.c_red_crayola)
                                        //This will add Don't show again checkbox to the dialog. You can pass any ID as argument
                                        .setTitle("Peringatan")
                                        .setMessage(hasilcek)
                                        .show();
                            }
                        }catch(Exception ex){
                            dialog2
                                    .setTopColorRes(R.color.c_yellow_crayola)
                                    //This will add Don't show again checkbox to the dialog. You can pass any ID as argument
                                    .setTitle("Peringatan")
                                    .setMessage("Harap Isikan terlebih dahulu semua data")
                                    .show();

                        }
                    }
                });
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
                View rootView = inflater.inflate(R.layout.fragment_sub_page2, container, false);
                dialog3 = new LovelyInfoDialog(getContext());
                aodh= new AndroidOpenDBHelper(getContext());
                listdata = (ListView) rootView.findViewById(R.id.ListViewData);

                listAdapter=new ArrayAdapter<String>(
                        getContext(),android.R.layout.simple_dropdown_item_1line,
                        aodh.ShowObat());
                listdata.setAdapter(listAdapter);

                listdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String nama_obat=(String) listdata.getItemAtPosition(position);

                        dialog3.setTopColorRes(R.color.c_green_crayola)
                                //This will add Don't show again checkbox to the dialog. You can pass any ID as argument
                                .setTitle("Data Obat")
                                .setMessage(aodh.ShowDetailObat(nama_obat))
                                .show();
                    }
                });

                return rootView;
            }
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return null;
        }
    }


    private static String cekHasilKesehatan(){
        String result="";
        float temp_jantung = Float.parseFloat(ed4.getText().toString())/Float.parseFloat(ed5.getText().toString());
        if(temp_jantung >140/90){
            result+="Anda berpotensi terkena Hipertensi\n";
        }
        else if(temp_jantung<90/60)
        {
            result+="Anda berpotensi terkena Hoptensi\n";
        }

        float temp_gula = Float.parseFloat(ed3.getText().toString());
        if(temp_gula>126)
            result+="Anda berpotensi terkena diabetes\n";
        if(temp_gula>=90 && temp_gula<=130)
            result+="Anda berpotensi terkena darah tinggi\n";
        if(temp_gula<60)
            result+="Anda berpotensi terkena darah rendah\n";

        float temp_trombosit = Float.parseFloat(ed1.getText().toString());
        if(temp_trombosit>400000)
            result+="Trombosit Anda Sangat Tinggi, Silahkan Periksakan Ke dokter\n ";
        if(temp_trombosit<200000)
            result+="Trombosit Anda Cukup Rendah, Silahkan Periksakan ke dokter\n";

        float temp_kolesterol = Float.parseFloat(ed2.getText().toString());
        if(temp_kolesterol>=240)
            result+="Kolesterol Anda Tinggi, Bahaya\n";
        if(temp_kolesterol>=200&&temp_kolesterol<240)
            result+="Kolesterol Anda mengkhawatirkan, sebaiknya periksakan ke doktor\n";

        return result;
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the
     * sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Check Your Healthy";
                case 1:
                    return "Medicines / Drugs Info";
//                case 2:
//                    return "SECTION 3";
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
