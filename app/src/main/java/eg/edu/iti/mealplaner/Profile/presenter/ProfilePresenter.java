package eg.edu.iti.mealplaner.Profile.presenter;

public interface ProfilePresenter {
    void signOut();
    interface View{
        void onSignOut();
    }
}
