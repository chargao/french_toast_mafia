/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package french_toast_mafia.waitr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import french_toast_mafia.view.SlidingTabLayout;

public class SlidingTabsBasicFragment extends Fragment {

  private SlidingTabLayout mSlidingTabLayout;
  private ViewPager mViewPager;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_sample, container, false);
  }

  /**
   * This is called after the {@link #onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)} has finished.
   * Here we can pick out the {@link android.view.View}s we need to configure from the content view.
   * <p/>
   * We set the {@link android.support.v4.view.ViewPager}'s adapter to be an instance of {@link SamplePagerAdapter}. The
   * {@link SlidingTabLayout} is then given the {@link android.support.v4.view.ViewPager} so that it can populate itself.
   *
   * @param view View created in {@link #onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)}
   */
  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    // BEGIN_INCLUDE (setup_viewpager)
    // Get the ViewPager and set it's PagerAdapter so that it can display items
    mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
    mViewPager.setAdapter(new SamplePagerAdapter());
    // END_INCLUDE (setup_viewpager)

    // BEGIN_INCLUDE (setup_slidingtablayout)
    // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
    // it's PagerAdapter set.
    mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
    mSlidingTabLayout.setViewPager(mViewPager);
    // END_INCLUDE (setup_slidingtablayout)
  }
  // END_INCLUDE (fragment_onviewcreated)

  /**
   * The {@link android.support.v4.view.PagerAdapter} used to display pages in this sample.
   * The individual pages are simple and just display two lines of text. The important section of
   * this class is the {@link #getPageTitle(int)} method which controls what is displayed in the
   * {@link SlidingTabLayout}.
   */
  class SamplePagerAdapter extends PagerAdapter {

    /**
     * @return the number of pages to display
     */
    @Override
    public int getCount() {
      return 2;
    }

    /**
     * @return true if the value returned from {@link #instantiateItem(android.view.ViewGroup, int)} is the
     * same object as the {@link android.view.View} added to the {@link android.support.v4.view.ViewPager}.
     */
    @Override
    public boolean isViewFromObject(View view, Object o) {
      return o == view;
    }

    // BEGIN_INCLUDE (pageradapter_getpagetitle)

    /**
     * Return the title of the item at {@code position}. This is important as what this method
     * returns is what is displayed in the {@link SlidingTabLayout}.
     * <p/>
     * Here we construct one using the position value, but for real application the title should
     * refer to the item's contents.
     */
    @Override
    public CharSequence getPageTitle(int position) {
      switch (position) {
        case 0:
          return "Lines";
        case 1:
          return "Coupons";
        default:
          return "Oops";
      }
    }
    // END_INCLUDE (pageradapter_getpagetitle)

    /**
     * Instantiate the {@link android.view.View} which should be displayed at {@code position}. Here we
     * inflate a layout from the apps resources and then change the text view to signify the position.
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      // Inflate a new layout from our resources
      View view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
              container, false);
      // Add the newly created View to the ViewPager
      container.addView(view);

      // Retrieve a TextView from the inflated View, and update it's text
      TextView title = (TextView) view.findViewById(R.id.item_title);
      title.setText(String.valueOf(position + 1));

      // Return the View
      return view;
    }

    /**
     * Destroy the item from the {@link android.support.v4.view.ViewPager}. In our case this is simply removing the
     * {@link android.view.View}.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }

  }
}
