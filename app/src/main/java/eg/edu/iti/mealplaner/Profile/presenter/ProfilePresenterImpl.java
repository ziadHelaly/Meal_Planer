package eg.edu.iti.mealplaner.Profile.presenter;


import eg.edu.iti.mealplaner.model.firebase.FireBaseAuthModel;
import eg.edu.iti.mealplaner.model.repository.Repository;

public class ProfilePresenterImpl implements ProfilePresenter{
    FireBaseAuthModel firebaseAuth;
    Repository repository;
    View view;

    public ProfilePresenterImpl(Repository repository, View view) {
        this.repository = repository;
        this.view = view;
    }


    @Override
    public void signOut() {
        repository.signOut();
        repository.onSignOut();
        view.onSignOut();
    }
}
