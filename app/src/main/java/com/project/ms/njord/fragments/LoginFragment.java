package com.project.ms.njord.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.ms.njord.R;
import com.project.ms.njord.activities.MainActivity;
import com.project.ms.njord.model.DatabaseManager;
import com.project.ms.njord.model.Singleton;

/**
 * A login screen that offers login via email/password.
 *
 * LoaderCallbacks<Cursor>
 *
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    // Id to identity READ_CONTACTS permission request.
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    private DatabaseManager dbManager;

    // Keep track of the login and sign up task, to ensure we can cancel it if requested.
    private UserLoginTask loginTask = null;
    private UserSignUpTask signUpTask = null;

    // UI references.
    private AutoCompleteTextView emailView;
    private EditText passwordView;
    private View progressView;
    private View loginFormView;
    private Button loginButton;
    private Button signUpButton;
    private SharedPreferences prefs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        dbManager = Singleton.instance.getDataBaseManager();
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        // Set up the login form.
        emailView = (AutoCompleteTextView) v.findViewById(R.id.email);

        // Initializing views
        loginFormView = v.findViewById(R.id.login_form);
        progressView = v.findViewById(R.id.login_progress);

        passwordView = (EditText) v.findViewById(R.id.password);
        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLoginOrSignUp(true);
                    return true;
                }
                return false;
            }
        });

        // Initializing buttons
        loginButton = (Button) v.findViewById(R.id.login_login_button);
        loginButton.setOnClickListener(this);

        signUpButton = (Button) v.findViewById(R.id.login_sign_up_button);
        signUpButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v){
        if(v == loginButton){
            attemptLoginOrSignUp(true);
        }
        if(v == signUpButton){
            attemptLoginOrSignUp(false);
        }
    }

    /**
     * Attempts to login or register the profile specified by the login form.
     * If the login boolean is true login was chosen, if false sing up was chosen.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLoginOrSignUp(boolean login) {
        // TODO: could be split in two, one for login and one for sign up

        // Terminates the method if a sign up or login task is already running
        if (loginTask != null || signUpTask != null) return;


        // Reset errors.
        emailView.setError(null);
        passwordView.setError(null);

        // Store values at the time of the login attempt.
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a invalid password
        if (TextUtils.isEmpty(password)) {
            passwordView.setError(getString(R.string.error_field_required));
            focusView = passwordView;
            cancel = true;
        } else if(!isPasswordValid(password)){
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        // Check for a invalid email address.
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            // login was chosen - starting a new
            if(login){
                loginTask = new UserLoginTask(email, password);
                loginTask.execute((Void) null);
            }

            // sign up was chosen - starting a new thread to create profile
            else {
                signUpTask = new UserSignUpTask(email, password);
                signUpTask.execute((Void) null);
            }
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Add more stuff to verify email
        //return email.contains("@");
        return true;

    }

    private boolean isPasswordValid(String password) {
        //TODO: Add more stuff to verify password
        //password.length() >= 6;
        return true;
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            loginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    // Start a second thread that attempts to login
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String email;
        private final String password;

        UserLoginTask(String email, String password) {
            this.email = email;
            this.password = password;
        }


        @Override        // Returns true if the email is in the database
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

                dbManager.syncProfile(email);

                try {
                    return (Singleton.instance.getProfile().getEmail().equals(email));
                }
                    catch(NullPointerException e) {
                        return false;
                    }

        }


        @Override       // Updtaes the UI after the second thread is done
        protected void onPostExecute(final Boolean success) {
            loginTask = null;
            showProgress(false);

            if (success) {      // password and email was correct
                prefs.edit().putBoolean("isLoggedIn", true).commit();
                prefs.edit().putString("active_email", email).commit();

                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
                getActivity().finish();

            } else {
                emailView.setError("No user with that email exists");
                emailView.requestFocus();

                //passwordView.setError(getString(R.string.error_incorrect_password));
                //passwordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            loginTask = null;
            showProgress(false);
        }
    }

    // Starts a second thread that attempts to create a new profile for the user
    public class UserSignUpTask extends AsyncTask<Void, Void, Boolean> {

        private final String email;
        private final String password;
        ProgressDialog pLog = new ProgressDialog(getActivity());

        UserSignUpTask(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            pLog.show();
        }

        @Override       // Returns true if profile was created successfully
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            dbManager.syncProfile(email);

            try {
                return (!Singleton.instance.getProfile().getEmail().equals(email));

            } catch (NullPointerException e) {
                return true;
            }

           /* for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");

                // Returns false if the account already exists
                if (pieces[0].equals(email)) return false;
            }
            */

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            pLog.dismiss();
            showProgress(false);

          if (success) {

                // A new Profile is created
              Singleton.instance.createProfile(email, password);
              prefs.edit().putBoolean("isLoggedIn", true).commit();
              prefs.edit().putString("active_email", email).commit();

              getFragmentManager().beginTransaction()
                      .replace(R.id.login_fragment_container, new SignUpFragment())
                      .addToBackStack(null)
                      .commit();

              signUpTask = null;

            } else {

                emailView.setError("A user with that email already exists");
                emailView.requestFocus();

                signUpTask = null;

              //passwordView.setError(getString(R.string.error_incorrect_password));
                //passwordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            loginTask = null;
            showProgress(false);
        }
    }

}

