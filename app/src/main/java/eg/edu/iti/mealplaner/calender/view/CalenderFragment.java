package eg.edu.iti.mealplaner.calender.view;

import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.calender.presenter.CalenderPresenter;
import eg.edu.iti.mealplaner.calender.presenter.CalenderPresenterImpl;
import eg.edu.iti.mealplaner.databinding.FragmentCalenderBinding;
import eg.edu.iti.mealplaner.model.models.Plan;
import eg.edu.iti.mealplaner.model.repository.RepositoryImpl;
import eg.edu.iti.mealplaner.utilies.CalenderUtil;
import eg.edu.iti.mealplaner.view.OnItemClicked;


public class CalenderFragment extends Fragment implements CalenderPresenter.View ,OnPlanRemoved, OnItemClicked {
    FragmentCalenderBinding binding;
    CalenderPresenter presenter;
    public CalenderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentCalenderBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    PlansAdapter adapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter= new CalenderPresenterImpl(RepositoryImpl.getRepository(getContext()),this);
        adapter=new PlansAdapter(getContext(),this,this);
        binding.rvPlans.setAdapter(adapter);
        presenter.getPlansByDay(CalenderUtil.getToday());
        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date=CalenderUtil.getFormattedDate(dayOfMonth, month, year);
                presenter.getPlansByDay(date);
            }
        });

    }

    @Override
    public void showPlansByDay(List<Plan> plans) {
        adapter.setPlans(plans);
    }

    @Override
    public void showMsg(String msg) {
        Snackbar.make(getView(),msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showRemovedMessage(String msg) {
        Snackbar.make(getView(),msg,Snackbar.LENGTH_SHORT).setAction("UNDO",v->{
            presenter.redoPlan();
        }).show();
    }

    @Override
    public void showOnGuestMode() {
        binding.guest.setVisibility(VISIBLE);
    }

    @Override
    public void removePlan(Plan plan) {
        presenter.removePlan(plan);
    }
    @Override
    public void navToDetailsScreen(String id){

    }
}