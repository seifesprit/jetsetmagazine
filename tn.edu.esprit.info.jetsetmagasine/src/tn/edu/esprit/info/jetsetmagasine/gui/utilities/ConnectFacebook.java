package tn.edu.esprit.info.jetsetmagasine.gui.utilities;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

import tn.edu.esprit.info.jetsetmagasine.domain.Subscriber;
import tn.edu.esprit.info.jetsetmagasine.gui.Principale;
import tn.edu.esprit.info.jetsetmagasine.services.business.impl.SubscriberBusiness;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.SubscriberDao;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;

import com.facebook.halo.application.token.FacebookTestClient;
import com.facebook.halo.application.types.User;
import com.facebook.halo.framework.common.AccessToken;

public class ConnectFacebook {

	private static ConnectFacebook instancesof;
	public String API_KEY = "1432910070326659";
	public String SECRET = "2f1ba5a5ea31899a21d0187a05c18f58";

	public String firstRequest = "https://graph.facebook.com/oauth/authorize?"
			+ "client_id="
			+ API_KEY
			+ "&redirect_uri=http://www.facebook.com/connect/login_success.html&"
			+ "scope=publish_stream,offline_access,create_event,read_stream,email,user_birthday";

	public String secondRequest = "https://graph.facebook.com/oauth/access_token?"
			+ "client_id="
			+ API_KEY
			+ "&redirect_uri=http://www.facebook.com/connect/login_success.html&"
			+ "client_secret=" + SECRET + "&code=";

	public String access_token = "";
	public boolean firstRequestDone = false;
	public boolean secondRequestDone = false;
	public FacebookTestClient testClient;
	public User user = null;
	private JWebBrowser webBrowser;
	private Principale parent;

	private ConnectFacebook(Principale parent) {
		this.parent = parent;
	}

	public User connect() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				NativeInterface.open();
				NativeInterface.initialize();

				System.out.println(" Bouton 2");
				final JFrame authFrame = new JFrame();
				// Create the JWebBrowser and add the
				// WebBrowserAdapter
				JPanel webBrowserPanel = new JPanel(new BorderLayout());
				webBrowser = new JWebBrowser();
				webBrowser.setMenuBarVisible(false);
				webBrowser.setButtonBarVisible(false);
				webBrowser.setLocationBarVisible(false);
				webBrowser.navigate(firstRequest);
				webBrowser.addWebBrowserListener(new WebBrowserAdapter() {
					@Override
					public void locationChanged(WebBrowserNavigationEvent e) {
						super.locationChanged(e);
						// Check if first request was
						// not done
						if (!firstRequestDone) {
							// Check if you left the
							// location and were
							// redirected to the next
							// location
							System.out.println("false");
							if (e.getNewResourceLocation()
									.contains(
											"http://www.facebook.com/connect/login_success.html?code=")) {
								// If it successfully
								// redirects you, get
								// the verification code
								// and go for a second
								// request
								String[] splits = e.getNewResourceLocation()
										.split("=");
								String stage2temp = secondRequest + splits[1];
								System.out.println("First =" + splits[1]);
								webBrowser.navigate(stage2temp);
								firstRequestDone = true;
							}
						} else {
							// If secondRequest is not
							// done yet, you perform
							// this and get the
							// access_token
							if (!secondRequestDone) {
								System.out.println(webBrowser.getHTMLContent());
								// Create reader with
								// the html content
								StringReader readerSTR = new StringReader(
										webBrowser.getHTMLContent());
								// Create a callback for
								// html parser
								HTMLEditorKit.ParserCallback callback = new HTMLEditorKit.ParserCallback() {
									@Override
									public void handleText(char[] data, int pos) {
										System.out.println(data);
										// because there
										// is only one
										// line with the
										// access_token
										// in the html
										// content you
										// can parse it.
										String string = new String(data);
										String[] temp1 = string.split("&");
										String[] temp2 = temp1[0].split("=");
										System.out.println("access tocken="
												+ temp2);
										access_token = temp2[1];

										// get User facebook
										getInformation();
										authFrame.dispose();
										
									}

								};
								try {
									// Call the parse
									// method
									new ParserDelegator().parse(readerSTR,
											callback, false);

								} catch (IOException e1) {
									e1.printStackTrace();
								}
								// After everything is
								// done, you can dispose
								// the jframe
								// authFrame.dispose();
							} else {

								System.out.println("fin");

							}
						}
					}
				});
				webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
				authFrame.add(webBrowserPanel);
				authFrame.setSize(400, 500);
				authFrame.setVisible(true);
			}

		});
		return user;
	}

	public static ConnectFacebook getInstancesof(Principale parent) {
		
		if (instancesof == null)
			instancesof = new ConnectFacebook(parent);
		return instancesof;
	}
	
	private void getInformation() {
		AccessToken.setAccessToken(access_token);
		User user = new User();
		user = user.createInstance("me");
		parent.getName_fb().setText(user.getName());
		URL url = null;
		try {
			url = new URL(user.getPicture());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		parent.getPicture_fb().setIcon(new javax.swing.ImageIcon(url));

		if(SubscriberBusiness.getInstanceof().findByIdfb(user.getId()) == null){
			
			SubscriberDao.getInstanceof().add(new Subscriber(0, user.getName(), user.getEmail(), user.getId()));
			JOptionPane.showMessageDialog(parent,
				    "Merci pour l'inscription chez jet set magasine",
				    "félicitations",
				    JOptionPane.INFORMATION_MESSAGE);
			
		}else{
			
			JOptionPane.showMessageDialog(parent,
				    "Bienvenue \" "+user.getName()+" \" dans jet set magasine",
				    "Bienvenue",
				    JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		
	}
	
}
