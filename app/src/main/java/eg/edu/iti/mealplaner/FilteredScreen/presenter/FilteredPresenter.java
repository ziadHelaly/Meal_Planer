package eg.edu.iti.mealplaner.FilteredScreen.presenter;

import eg.edu.iti.mealplaner.utilies.FilterType;
import eg.edu.iti.mealplaner.presenter.PresenterInterface;

public interface FilteredPresenter  {

    void getData(FilterType type,String c);
    interface View extends PresenterInterface.View{

    }
}
