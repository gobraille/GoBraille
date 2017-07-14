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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class belajar extends AppCompatActivity {

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
    final int[] sound = new int[]{R.raw.a,R.raw.b,R.raw.c,R.raw.d,R.raw.e,R.raw.f,R.raw.g,
            R.raw.h,R.raw.i,R.raw.j,R.raw.k,R.raw.l,R.raw.m,R.raw.n,R.raw.o,R.raw.p,R.raw.q,
            R.raw.r,R.raw.s,R.raw.t,R.raw.u,R.raw.v,R.raw.w,R.raw.x,R.raw.y,R.raw.z};

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
        setContentView(R.layout.activity_belajar);
        play(0);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final int position) {
                play(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_belajar, menu);
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
        public static MediaPlayer mp = null;

        public void play(){
            Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.stop();
                    mp.release();
                }
            });
            vibe.vibrate(90);
        }

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
            final View rootView = inflater.inflate(R.layout.fragment_belajar, container, false);
            final TextView character = (TextView) rootView.findViewById(R.id.character);
            final Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),
                    "fonts/Prime Regular.otf");
            final int Page = getArguments().getInt(ARG_SECTION_NUMBER);
            final int[] sound = new int[]{R.raw.doo,R.raw.ree,
                    R.raw.mii,R.raw.faa,R.raw.sol,R.raw.laa,R.raw.sii};
            final int[] cdot = new int[]{R.drawable.ic_dot_white,R.drawable.ic_dot_blue};
            final long[] back = {0,90,90,90};
            final String[] characters = new String[]{"a","b","c","d","e","f","g","h","i",
                    "j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
            ImageView dot1 = (ImageView) rootView.findViewById(R.id.dot1);
            ImageView dot2 = (ImageView) rootView.findViewById(R.id.dot2);
            ImageView dot3 = (ImageView) rootView.findViewById(R.id.dot3);
            ImageView dot4 = (ImageView) rootView.findViewById(R.id.dot4);
            ImageView dot5 = (ImageView) rootView.findViewById(R.id.dot5);
            ImageView dot6 = (ImageView) rootView.findViewById(R.id.dot6);
            dot1.setImageResource(cdot[0]);
            dot2.setImageResource(cdot[0]);
            dot3.setImageResource(cdot[0]);
            dot4.setImageResource(cdot[0]);
            dot5.setImageResource(cdot[0]);
            dot6.setImageResource(cdot[0]);
            character.setTypeface(custom_font);
            character.setText(characters[Page-1]);
            character.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        character.setAlpha((float)0.5);
                    }
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP ||
                            motionEvent.getAction() == MotionEvent.ACTION_CANCEL){
                        character.setAlpha(1);
                    }
                    return false;
                }
            });
            character.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    vibe.vibrate(back, -1);
                    Intent i   = new Intent(getActivity(),MainActivity.class);
                    getActivity().setResult(1, i);
                    getActivity().finish();
                    return true;
                }
            });

            for(int i=0; i<27; i++){
                if (Page<9 || 10<Page && Page<19 || 20<Page && Page<23 || 23<Page){
                    dot1.setImageResource(cdot[1]);
                    dot1.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                mp = MediaPlayer.create(getActivity(), sound[0]);
                                play();
                            }
                            return false;
                        }
                    });
                }
                if (1<Page && Page<3 || 5<Page && Page<11 || 11<Page && Page<13 ||
                        15<Page && Page<21 || 21<Page && Page<24){
                    dot2.setImageResource(cdot[1]);
                    dot2.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                mp = MediaPlayer.create(getActivity(), sound[1]);
                                play();
                            }
                            return false;
                        }
                    });
                }
                if (10<Page && Page<23 || 23<Page && Page<27){
                    dot3.setImageResource(cdot[1]);
                    dot3.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                mp = MediaPlayer.create(getActivity(), sound[2]);
                                play();
                            }
                            return false;
                        }
                    });
                }
                if (2<Page && Page<5 || 5<Page && Page<8 || 8<Page && Page<11 || 12<Page &&
                        Page<15 || 15<Page && Page<18 || 18<Page && Page<21 || 22<Page && Page<26){
                    dot4.setImageResource(cdot[1]);
                    dot4.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                mp = MediaPlayer.create(getActivity(), sound[3]);
                                play();
                            }
                            return false;
                        }
                    });
                }
                if (3<Page && Page<6 || 6<Page && Page<9 || 9<Page && Page<11 ||
                        13<Page && Page<16 || 16<Page && Page<19 || 19<Page && Page<21 ||
                        22<Page && Page<24 || 24<Page){
                    dot5.setImageResource(cdot[1]);
                    dot5.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                mp = MediaPlayer.create(getActivity(), sound[4]);
                                play();
                            }
                            return false;
                        }
                    });
                }
                if (Page>20){
                    dot6.setImageResource(cdot[1]);
                    dot6.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                mp = MediaPlayer.create(getActivity(), sound[5]);
                                play();
                            }
                            return false;
                        }
                    });
                }
            }
            return rootView;
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
            // Show 26 total pages.
            return 26;
        }
    }

    @Override
    public void onBackPressed() {
        Intent i   = new Intent(belajar.this,MainActivity.class);
        setResult(1, i);
        finish();
        super.onBackPressed();
    }
}
