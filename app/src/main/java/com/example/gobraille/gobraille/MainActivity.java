package com.example.gobraille.gobraille;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;

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

import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public static MediaPlayer mp = null;
    final int[] sound = new int[]{R.raw.belajar,
            R.raw.tentangkami,R.raw.bantuan,R.raw.keluar};

    public void play(int position){
        mp = MediaPlayer.create(getApplication(), sound[position]);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        play(0);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                play(position);
                ImageView dots1 = (ImageView) findViewById(R.id.dots1);
                ImageView dots4 = (ImageView) findViewById(R.id.dots4);
                ImageView dots5 = (ImageView) findViewById(R.id.dots5);
                ImageView dots6 = (ImageView) findViewById(R.id.dots6);
                dots1.setImageResource(R.drawable.rectangle);
                dots4.setImageResource(R.drawable.rectangle);
                dots5.setImageResource(R.drawable.rectangle);
                dots6.setImageResource(R.drawable.rectangle);
                switch(position+1) {
                    case 1: // belajar
                        dots1.setImageResource(R.drawable.rectangle2);
                        break;
                    case 2: // tentang kami
                        dots4.setImageResource(R.drawable.rectangle2);
                        break;
                    case 3: // bantuan
                        dots5.setImageResource(R.drawable.rectangle2);
                        break;
                    case 4: // keluar
                        dots6.setImageResource(R.drawable.rectangle2);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

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
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            final Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            final int Page = getArguments().getInt(ARG_SECTION_NUMBER), vib = 50;
            final int[] image = new int[]{R.drawable.ic_belajar,
                    R.drawable.ic_tentang_kami,
                    R.drawable.ic_bantuan,R.drawable.ic_keluar};
            final String[] text = new String[]{"BELAJAR","TENTANG KAMI",
                    "BANTUAN","KELUAR"};
            Button textMenu = (Button) rootView.findViewById(R.id.textMenu);
            ImageView imageMenu = (ImageView) rootView.findViewById(R.id.imageMenu);
            Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),
                    "fonts/Prime Regular.otf");
            textMenu.setTypeface(custom_font);
            textMenu.setText(text[Page-1]);
            imageMenu.setImageResource(image[Page-1]);
            textMenu.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    vibe.vibrate(vib);
                    Intent i = null;
                    switch(Page) {
                        case 1: // belajar
                            i = new Intent(getActivity(), belajar.class);
                            startActivityForResult(i, Page);
                            break;
                        case 2: // tentang kami
                            i = new Intent(getActivity(), tentang_kami.class);
                            startActivityForResult(i, Page);
                            break;
                        case 3: // bantuan
                            i = new Intent(getActivity(), bantuan.class);
                            startActivityForResult(i, Page);
                            break;
                        case 4: // keluar
                            i = new Intent(getActivity(), keluar.class);
                            startActivityForResult(i, Page);
                            break;
                    }
                }
            });
            imageMenu.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    vibe.vibrate(vib);
                    Intent i = null;
                    switch(Page) {
                        case 1: // belajar
                            i = new Intent(getActivity(), belajar.class);
                            startActivityForResult(i, Page);
                            break;
                        case 2: // tentang kami
                            i = new Intent(getActivity(), tentang_kami.class);
                            startActivityForResult(i, Page);
                            break;
                        case 3: // bantuan
                            i = new Intent(getActivity(), bantuan.class);
                            startActivityForResult(i, Page);
                            break;
                        case 4: // keluar
                            i = new Intent(getActivity(), keluar.class);
                            startActivityForResult(i, Page);
                            break;
                    }
                }
            });
            return rootView;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode<5){
            play(resultCode-1);
        }
        if (resultCode == 7) {
            moveTaskToBack(true);
            finish();
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
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
            // Show 4 total pages.
            return 4;
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(MainActivity.this, keluar.class);
        startActivityForResult(i, 4);
        super.onBackPressed();
    }
}
