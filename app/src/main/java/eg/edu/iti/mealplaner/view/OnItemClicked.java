package eg.edu.iti.mealplaner.view;

import eg.edu.iti.mealplaner.utilies.FilterType;

public interface OnItemClicked {
    default void navToDetailsScreen(String id) {


    }
    default void navToFilteredScreen(FilterType type, String name) {


    }
}
