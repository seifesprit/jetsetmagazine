package tn.edu.esprit.info.jetsetmagasine.test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;

import com.facebook.halo.application.token.FacebookTestClient;
import com.facebook.halo.application.types.User;
import com.facebook.halo.framework.common.AccessToken;

public class TestFacebook {

	public static String API_KEY = "1432910070326659";
	public static String SECRET = "2f1ba5a5ea31899a21d0187a05c18f58";

	public static String firstRequest = "https://graph.facebook.com/oauth/authorize?"
			+ "client_id="
			+ API_KEY
			+ "&redirect_uri=http://www.facebook.com/connect/login_success.html&"
			+ "scope=publish_stream,offline_access,create_event,read_stream,email,user_birthday";

	public static String secondRequest = "https://graph.facebook.com/oauth/access_token?"
			+ "client_id="
			+ API_KEY
			+ "&redirect_uri=http://www.facebook.com/connect/login_success.html&"
			+ "client_secret=" + SECRET + "&code=";

	public static String access_token = "";
	public static boolean firstRequestDone = false;
	public static boolean secondRequestDone = false;
	public static FacebookTestClient testClient;

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				NativeInterface.open();
				NativeInterface.initialize();
				testClient = new FacebookTestClient();

				testClient.setLoginListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(" Bouton 1");
						final JFrame loginFrame = new JFrame();
						JPanel webBrowserPanel = new JPanel(new BorderLayout());
						// this is the JWebBrowser i mentioned earlier
						final JWebBrowser webBrowser = new JWebBrowser();
						// You can set this fields to false, or even let them
						// activated
						webBrowser.setMenuBarVisible(false);
						webBrowser.setButtonBarVisible(false);
						webBrowser.setLocationBarVisible(false);
						final String fb_url = "http://www.facebook.com/";
						webBrowser.navigate(fb_url);

						webBrowser
								.addWebBrowserListener(new WebBrowserAdapter() {
									@Override
									public void locationChanging(
											WebBrowserNavigationEvent e) {
										super.locationChanging(e);
										System.out.println(e
												.getNewResourceLocation());

										if (!e.getNewResourceLocation().equals(
												fb_url)) {
											Timer timer = new Timer(5000,
													new ActionListener() {
														@Override
														public void actionPerformed(
																ActionEvent arg0) {
															// loginFrame.dispose();
														}
													});
											timer.start();
										}
									}
								});
						webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
						loginFrame.add(webBrowserPanel);
						loginFrame.setSize(400, 500);
						loginFrame.setVisible(true);
					}
				});
				testClient.setretrieveUserListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(" Bouton Information");
						AccessToken.setAccessToken(access_token);
						User user = new User();
						user = user.createInstance("me");
						testClient.getName_fb().setText(user.getName());
						URL url = null;
						try {
							url = new URL(user.getPicture());
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						testClient.getPicture().setIcon(
								new javax.swing.ImageIcon(url));

						// System.out.println(url.getPath());
					}
				});
				testClient.setgetAccessTokenListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(" Bouton 4");
						// JFrame parent = new JFrame();
						// JOptionPane.showMessageDialog(parent, access_token);
						final JWebBrowser webBrowser = new JWebBrowser();
						webBrowser.setMenuBarVisible(false);
						webBrowser.setButtonBarVisible(false);
						webBrowser.setLocationBarVisible(false);
						webBrowser.navigate(firstRequest);

						if (!secondRequestDone) {

							System.out.println(webBrowser.getHTMLContent());
							// Create reader with the html content
							StringReader readerSTR = new StringReader(
									webBrowser.getHTMLContent());
							// Create a callback for html parser
							HTMLEditorKit.ParserCallback callback = new HTMLEditorKit.ParserCallback() {
								@Override
								public void handleText(char[] data, int pos) {
									System.out.println(data);
									// because there is only one line with the
									// access_token
									// in the html content you can parse it.
									String string = new String(data);
									String[] temp1 = string.split("&");
									String[] temp2 = temp1[0].split("=");
									System.out
											.println("access tocken=" + temp2);
									access_token = temp2[1];
								}
							};
						}

					}
				});
				testClient
						.setgetPermissionsForAppListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								System.out.println(" Bouton 2");
								final JFrame authFrame = new JFrame();
								// Create the JWebBrowser and add the
								// WebBrowserAdapter
								JPanel webBrowserPanel = new JPanel(
										new BorderLayout());
								final JWebBrowser webBrowser = new JWebBrowser();
								webBrowser.setMenuBarVisible(false);
								webBrowser.setButtonBarVisible(false);
								webBrowser.setLocationBarVisible(false);
								webBrowser.navigate(firstRequest);
								webBrowser
										.addWebBrowserListener(new WebBrowserAdapter() {
											@Override
											public void locationChanged(
													WebBrowserNavigationEvent e) {
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
														String[] splits = e
																.getNewResourceLocation()
																.split("=");
														String stage2temp = secondRequest
																+ splits[1];
														System.out
																.println("First ="
																		+ splits[1]);
														webBrowser
																.navigate(stage2temp);
														firstRequestDone = true;
													}
												} else {
													// If secondRequest is not
													// done yet, you perform
													// this and get the
													// access_token
													if (!secondRequestDone) {
														System.out
																.println(webBrowser
																		.getHTMLContent());
														// Create reader with
														// the html content
														StringReader readerSTR = new StringReader(
																webBrowser
																		.getHTMLContent());
														// Create a callback for
														// html parser
														HTMLEditorKit.ParserCallback callback = new HTMLEditorKit.ParserCallback() {
															@Override
															public void handleText(
																	char[] data,
																	int pos) {
																System.out
																		.println(data);
																// because there
																// is only one
																// line with the
																// access_token
																// in the html
																// content you
																// can parse it.
																String string = new String(
																		data);
																String[] temp1 = string
																		.split("&");
																String[] temp2 = temp1[0]
																		.split("=");
																System.out
																		.println("access tocken="
																				+ temp2);
																access_token = temp2[1];
															}
														};
														try {
															// Call the parse
															// method
															new ParserDelegator()
																	.parse(readerSTR,
																			callback,
																			false);

														} catch (IOException e1) {
															e1.printStackTrace();
														}
														// After everything is
														// done, you can dispose
														// the jframe
														// authFrame.dispose();
													} else {

														System.out
																.println("fin");

													}
												}
											}
										});
								webBrowserPanel.add(webBrowser,
										BorderLayout.CENTER);
								authFrame.add(webBrowserPanel);
								authFrame.setSize(400, 500);
								authFrame.setVisible(true);
							}
						});
			}
		});
	}

}
