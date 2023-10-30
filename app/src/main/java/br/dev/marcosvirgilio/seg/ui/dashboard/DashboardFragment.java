package br.dev.marcosvirgilio.seg.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import br.dev.marcosvirgilio.seg.R;
public class DashboardFragment extends Fragment {

   private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        this.view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.view = null;
    }
}