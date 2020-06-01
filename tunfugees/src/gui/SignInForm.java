/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import entities.Utilisateur;
import java.io.IOException;
import services.ServiceUser;
 
import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.NetworkEvent;
import com.codename1.io.Oauth2;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.LayeredLayout;
/**
 *
 * @author actar
 */
public class SignInForm extends BaseForm {
 public static String TOKEN;
  private Form main;
	public SignInForm(Resources res) {
		super(new BorderLayout());

		if (!Display.getInstance().isTablet()) {
			BorderLayout bl = (BorderLayout) getLayout();
			bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
			bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
		}
		getTitleArea().setUIID("Container");
		setUIID("SignIn");

		add(BorderLayout.NORTH, new Label(res.getImage("ekher.png"), "LogoLabel"));

		TextField username = new TextField("", "Username", 20, TextField.ANY);
		TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
		username.setSingleLineTextArea(true);
		password.setSingleLineTextArea(true);
		Button signIn = new Button("Sign In");
		Button signUp = new Button("Sign Up");
                 signUp.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent evt) {
                sign(main);
            }
        });
                 
                 
		//signUp.addActionListener(e -> new SignUpForm(res).show());
		//signUp.setUIID("Link");
		Label doneHaveAnAccount = new Label("Don't have an account?");

		Container content = BoxLayout.encloseY(
				new FloatingHint(username),
				createLineSeparator(),
				new FloatingHint(password),
				createLineSeparator(),
				signIn,
				FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
		);
		content.setScrollableY(true);
	
		//content.getAllStyles().setMarginTop(500);
		add(BorderLayout.CENTER, content);
		signIn.requestFocus();
		//signIn.addActionListener(e -> new NewsfeedForm(res).show());
		signIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
                            System.out.println("cococococo :"+password.getText());
				if (username.getText() != "" || password.getText() != "") {
					ServiceUser ser = new ServiceUser();
                                    System.out.println("cococococo :"+password.getText());
                                    
				 	Utilisateur u = ser.CheckLoginData(username.getText(), password.getText());
					if (u == null) {
						Dialog.show("Wrong Credentials", "Please verify your username and password.", "Ok", "");
						username.setText("");
						password.setText("");
					} else {
						Utilisateur.current_user = u;
						Database db;
						try {
							db = Database.openOrCreate("EcoSystem.db");
							db.execute("delete from appstates");
							db.execute("insert into appstates(loggedin,loggeduserid) values (1," + u.getId() + ");");
							  System.out.println("Passage vers l'autre écran");
                                                          AccueilForm h = new AccueilForm(res);
                                                            h.show();
                                                          
                                                          
                                                          
                                                          
						} catch (IOException ex) {
						}
					}
				}
			}
		});
	}
         private static void sign(final Form main) {
        FaceBookAccess.setClientId("2583096965341761");
        FaceBookAccess.setClientSecret("df13a4d6eebcbdb33ea689a546e26bbb");
        FaceBookAccess.setRedirectURI("http://www.codenameone.com/");
        FaceBookAccess.setPermissions(new String[]{"user_location", "user_photos", "friends_photos", "publish_stream", "read_stream", "user_relationships", "user_birthday",
                    "friends_birthday", "friends_relationships", "read_mailbox", "user_events", "friends_events", "user_about_me"});
        
        FaceBookAccess.getInstance().showAuthentication(new ActionListener() {
            
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() instanceof String) {
                    String token = (String) evt.getSource();
                    String expires = Oauth2.getExpires();
                    TOKEN = token;
                    System.out.println("recived a token " + token + " which expires on " + expires);
                    //store token for future queries.
                    Storage.getInstance().writeObject("token", token);
                    if (main != null) {
                        main.showBack();
                    }
                } else {
                    Exception err = (Exception) evt.getSource();
                    err.printStackTrace();
                    Dialog.show("Error", "An error occurred while logging in: " + err, "OK", null);
                }
            }
        });
    }
 public static boolean firstLogin() {
        return Storage.getInstance().readObject("token") == null;
    }
    
    public static void login(final Form form) {
        if (firstLogin()) {
          //  SignInForm logForm = new SignInForm(form);
          //  logForm.show();
        } else {
            //token exists no need to authenticate
            TOKEN = (String) Storage.getInstance().readObject("token");
            FaceBookAccess.setToken(TOKEN);
            //in case token has expired re-authenticate
            FaceBookAccess.getInstance().addResponseCodeListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent evt) {
                    NetworkEvent ne = (NetworkEvent) evt;
                    int code = ne.getResponseCode();
                    //token has expired
                    if (code == 400) {
                        sign(form);
                    }                    
                }
            });
        }
    }
}

