// Generated by view binder compiler. Do not edit!
package com.example.internshipweek6recycleview.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.internshipweek6recycleview.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FloatingActionButton btAdd;

  @NonNull
  public final FrameLayout flAddEmployee;

  @NonNull
  public final ImageView ivClose;

  @NonNull
  public final ImageView ivDelete;

  @NonNull
  public final ImageView ivSearch;

  @NonNull
  public final ImageView ivSelectAll;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final RecyclerView rvListNv;

  @NonNull
  public final SearchView svSearchBar;

  @NonNull
  public final Toolbar tbSelect;

  @NonNull
  public final MaterialToolbar toolbar;

  @NonNull
  public final TextView tvAppName;

  @NonNull
  public final TextView tvCountSelected;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView,
      @NonNull FloatingActionButton btAdd, @NonNull FrameLayout flAddEmployee,
      @NonNull ImageView ivClose, @NonNull ImageView ivDelete, @NonNull ImageView ivSearch,
      @NonNull ImageView ivSelectAll, @NonNull ConstraintLayout main,
      @NonNull RecyclerView rvListNv, @NonNull SearchView svSearchBar, @NonNull Toolbar tbSelect,
      @NonNull MaterialToolbar toolbar, @NonNull TextView tvAppName,
      @NonNull TextView tvCountSelected) {
    this.rootView = rootView;
    this.btAdd = btAdd;
    this.flAddEmployee = flAddEmployee;
    this.ivClose = ivClose;
    this.ivDelete = ivDelete;
    this.ivSearch = ivSearch;
    this.ivSelectAll = ivSelectAll;
    this.main = main;
    this.rvListNv = rvListNv;
    this.svSearchBar = svSearchBar;
    this.tbSelect = tbSelect;
    this.toolbar = toolbar;
    this.tvAppName = tvAppName;
    this.tvCountSelected = tvCountSelected;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bt_add;
      FloatingActionButton btAdd = ViewBindings.findChildViewById(rootView, id);
      if (btAdd == null) {
        break missingId;
      }

      id = R.id.fl_add_employee;
      FrameLayout flAddEmployee = ViewBindings.findChildViewById(rootView, id);
      if (flAddEmployee == null) {
        break missingId;
      }

      id = R.id.iv_close;
      ImageView ivClose = ViewBindings.findChildViewById(rootView, id);
      if (ivClose == null) {
        break missingId;
      }

      id = R.id.iv_delete;
      ImageView ivDelete = ViewBindings.findChildViewById(rootView, id);
      if (ivDelete == null) {
        break missingId;
      }

      id = R.id.iv_search;
      ImageView ivSearch = ViewBindings.findChildViewById(rootView, id);
      if (ivSearch == null) {
        break missingId;
      }

      id = R.id.iv_select_all;
      ImageView ivSelectAll = ViewBindings.findChildViewById(rootView, id);
      if (ivSelectAll == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.rv_list_nv;
      RecyclerView rvListNv = ViewBindings.findChildViewById(rootView, id);
      if (rvListNv == null) {
        break missingId;
      }

      id = R.id.sv_searchBar;
      SearchView svSearchBar = ViewBindings.findChildViewById(rootView, id);
      if (svSearchBar == null) {
        break missingId;
      }

      id = R.id.tb_select;
      Toolbar tbSelect = ViewBindings.findChildViewById(rootView, id);
      if (tbSelect == null) {
        break missingId;
      }

      id = R.id.toolbar;
      MaterialToolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.tv_app_name;
      TextView tvAppName = ViewBindings.findChildViewById(rootView, id);
      if (tvAppName == null) {
        break missingId;
      }

      id = R.id.tv_count_selected;
      TextView tvCountSelected = ViewBindings.findChildViewById(rootView, id);
      if (tvCountSelected == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, btAdd, flAddEmployee, ivClose,
          ivDelete, ivSearch, ivSelectAll, main, rvListNv, svSearchBar, tbSelect, toolbar,
          tvAppName, tvCountSelected);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
