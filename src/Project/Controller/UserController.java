package Project.Controller;

import Project.Data.ActivationEmail;
import Project.Data.EmailDB;
import Project.Data.ForgotPasswordMail;
import Project.Data.RecommendDB;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import Project.Data.StudyDB;
import Project.Data.UserDB;
import Project.Model.User;
import Project.Data.tokenGenerator;
import Project.Model.TempUser;
import Project.Data.tempUserDB;
import Project.Model.TempUserPassword;
import Project.Data.TempUserPasswordDB;
import Project.Model.recommend;
import Project.Data.RecommendDB;
import Project.Util.PasswordUtil;
import static java.lang.String.format;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.ParseException;




@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) responsible for navigating to the respective pages.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		// get current action
		String url = "/home.jsp";
		String action = request.getParameter("action");
		// if action is null goes to home.jsp

		
		if (action == null || action.equals("") || action.isEmpty()) {
			url = "/home.jsp";
			Cookie c = new Cookie("host", request.getRemoteHost());
			Cookie c1 = new Cookie("port", request.getRemotePort() + "");
			c.setMaxAge(60 * 60 * 24 * 365 * 10);
			c1.setMaxAge(60 * 60 * 24 * 365 * 10);
			response.addCookie(c);
			response.addCookie(c1);
			Cookie[] cookies = request.getCookies();
			if(cookies==null){
			   response.setIntHeader("Refresh", 1);}
		
		}

		// perform login action and if credentials are right takes him to the main.jsp or admin.jsp based on usertype
		else {
			if (action.equals("login")) {
				// get parameters from the request
				String email = request.getParameter("email");
				String pwd = request.getParameter("password");
                                String salt = UserDB.getsalt(email);
                                String password = pwd + salt;
				// UserDB.getUsers();
				if (UserDB.selectUser(email, password)) {
					User user = UserDB.getUser(email);
					String userType = user.getUserType();

					if (userType.equalsIgnoreCase("Participant")) {
						session.setAttribute("theUser", user);
						int participants = StudyDB.getParticipants(user.getEmail());
						session.setAttribute("par", participants);
						url = "/main.jsp";
					} else if (userType.equalsIgnoreCase("Admin")) {
						session.setAttribute("theAdmin", user);
						url = "/admin.jsp";
					}
				} else {

					request.setAttribute("msg", "Invalid User");
					url = "/login.jsp";
				}

			}
			// If action is Create performs validations if user exists and
			// creates a new user
			if (action.equals("create")) {
				String email = request.getParameter("email");
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String cPassword = request.getParameter("confirm_password");
				if (UserDB.getUser(email) != null) {
					request.setAttribute("msg", "Email id already exists");
					request.setAttribute("email", email);
					request.setAttribute("name", name);
					url = "/signup.jsp";
				} else if (!password.equals(cPassword)) {
					request.setAttribute("msg", "Passwords dont match");
					request.setAttribute("email", email);
					request.setAttribute("name", name);
					url = "/signup.jsp";
				} else {
					//User user = new User(name, email, "Participant", password, cPassword, 0, 0, 0);
                                        tokenGenerator tg = new tokenGenerator();
                                        String tokenID = tg.randomToken();
                                        String issuedDate;
                                        String aurl = null;
                                        //String serverName = null;
                                        //String serverPort = null;
                                        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                                        Date dateobj = new Date();
                                        issuedDate = df.format(dateobj);
                                        TempUser tempUser = new TempUser(name, email, password, issuedDate, tokenID, "temp");
                                        tempUserDB.addUser(tempUser);
                                	session.setAttribute("theUser", tempUser);
                                        request.setAttribute("msgactivate","Please check your mail for the activation link");
                                        aurl = "http://"+request.getServerName()+":"+request.getServerPort()+"/Web/UserController?action=activate&value="+tokenID;
                                        
                                        ActivationEmail.emailRecommendTrigger(name, email, tokenID);
                                        //recommend userR1 = new recommend();
                                        //userR1 = RecommendDB.getUser(email);
                                        //System.out.println("IFout"+userR1.getsEmail());
                                        
                                        if (RecommendDB.getUser(email) != null){
                                            recommend userR = new recommend();
                                            userR = RecommendDB.getUser(email);
                                          //  System.out.println("IF"+userR.getsEmail());
                                            
                                            UserDB.updateUserCoins(userR.getsEmail(), 2);
                                        }
                                       
                                        
                                      
                                        
					//int participants = StudyDB.getParticipants(user.getEmail());
					//session.setAttribute("par", participants);
                                        
                                        
					//UserDB.addUser(user);
                                        //session.setAttribute("theUser", user);
                                       
					url = "/activatemsg.jsp";
				}

			}
                        if (action.equals("activate")){
                            String tokenId = request.getParameter("value");
                        
                            if(tempUserDB.getUser(tokenId)!= null){
                                TempUser user = tempUserDB.getUser(tokenId);
                                String password = user.getPassword();
                                String salt = PasswordUtil.getSalt();
                         
                                                      
                                User usernew = new User(user.getuName(),user.getEmail(),"Participant",salt,password,password,0,0,0);
                                int participants = StudyDB.getParticipants(usernew.getEmail());
		                session.setAttribute("par", participants);
                                UserDB.addUser(usernew);
                             
                                session.setAttribute("theUser", usernew);
                                tempUserDB.delUser(user);
                                url = "/main.jsp";
                               
                                
                        }
                            
                        }
                        if (action.equals("forgotpassword")){
                            String email = request.getParameter("email");
                            if(UserDB.getUser(email) != null){
                            User user = new User();
                            user = UserDB.getUser(email);
                            
                            tokenGenerator tg = new tokenGenerator();
                            String tokenID = tg.randomToken();
                            //java.util.Date date = new Date();
                            //Object param = new java.sql.Timestamp(date.getTime());
                            String expirationDate;
                            //long retryDate = System.currentTimeMillis();
                            //Timestamp original = new Timestamp(retryDate);
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                           // Date dateobj = new Date();
                            //expirationDate = df.format(dateobj);
                            Calendar cal = Calendar.getInstance();
                            expirationDate = df.format(cal.getTime());
                            
                            //System.out.println("");
                            TempUserPassword tempUser = new TempUserPassword(email, tokenID, expirationDate, "fp");
                            TempUserPasswordDB.addUser(tempUser);
                                System.out.println("cont");
			    session.setAttribute("theUser", tempUser);
                            request.setAttribute("msgfp", "You will receive an email with the password reset link shortly");
                            
                            ForgotPasswordMail.emailRecommendTrigger(user.getName(), email, tokenID, expirationDate);
                            
                            url = "/forgotpassword.jsp";
                            }else{
                                request.setAttribute("msg", "Email ID does not exist");
                                url = "/forgotpassword.jsp";
                                
                            }
                            
                        }
                        if(action.equals("resetpassword")){
                            String token = request.getParameter("value");
                            String email;
                            String currentDate;
                            long diff;
                            long retryDate = System.currentTimeMillis();
                            Timestamp original = new Timestamp(retryDate);
                            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            //Calendar cal = Calendar.getInstance();
                            //currentDate = df.format(cal.getTime());
                            TempUserPassword user = new TempUserPassword();
                            user = TempUserPasswordDB.getUser(token);
                           // java.sql.Timestamp dbSqlDate = rs.getTimestamp("ExpirationDate");
                            
                            diff = original.getTime() - (TempUserPasswordDB.getDate(token)).getTime();
                            System.out.println(diff);
                            System.out.println((TempUserPasswordDB.getDate(token)).getTime());
                            
                            //TempUserPassword user = new TempUserPassword();
                            //user = TempUserPasswordDB.getUser(token);
                            if((diff >= 300000)||(user.getEmail() == null)){
                                //System.out.println(user.getEmail());
                                request.setAttribute("msg", "The link has expired");
                                TempUserPasswordDB.delUser(token);
                                url = "/linkexpire.jsp";
                            }else {
                                email = user.getEmail();
                                request.setAttribute("email", email);
                                TempUserPasswordDB.delUser(token);
                                url = "/resetpassword.jsp";
                            }
                            
                             
                       }
                        if(action.equals("resetpasswordlink")){
                            String email = request.getParameter("email");
                            String password = request.getParameter("password");
                            String cPassword = request.getParameter("confirm_password");
                            if (!password.equals(cPassword)) {
					request.setAttribute("msg", "Passwords dont match");
					
					url = "/resetpassword.jsp";
                            }else{
                                UserDB.updatePassword(email, password);
                                request.setAttribute("msgrc", "Password is reset succesfully. Please login to continue.");
                                url = "/resetconfirm.jsp";
                                
                            }
                            
                        }
			if (action.equals("how")) {
				if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
					url = "/main.jsp";
				} else {
					url = "/how.jsp";
				}
			}

			if (action.equals("about")) {
				if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
					url = "/aboutl.jsp";
				} else {
					url = "/about.jsp";
				}
			}

			if (action.equals("home")) {
				if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
					url = "/main.jsp";
				} else {
					url = "/home.jsp";
				}
			}

			if (action.equals("main")) {
				if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
					url = "/main.jsp";
				} else {
					url = "/login.jsp";
				}
			}

			if (action.equals("logout")) {
				if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
					session.invalidate();
				}
				url = "/home.jsp";
			}
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
