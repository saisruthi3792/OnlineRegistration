package Project.Controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Project.Data.EmailDB;
import Project.Data.RecommendDB;
import Project.Data.ReportDB;
import Project.Data.StudyDB;
import Project.Data.UserDB;
import Project.Model.Answer;
import Project.Model.Report;
import Project.Model.Study;
import Project.Model.User;
import Project.Model.recommend;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/StudyController")
@MultipartConfig
public class StudyController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DATA_DIRECTORY = "images";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudyController() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		// get current action
		String action = request.getParameter("action");
		String url = "/home.jsp";
		if (action == null || action.equals("") || action.isEmpty()) {
			if (session.getAttribute("theUser") != null) {
				url = "/main.jsp";
			} else {
				if (session.getAttribute("theAdmin") != null) {
					url = "/admin.jsp";
				} else {
					url = "/home.jsp";
				}
			}
		}

// participate action retrieves list of studies on start status and navigates to participate.jsp if studycode null
//or retrieves particular study based on studycode navigating to question.jsp
 
		if (action.equals("participate")) {
			if (session.getAttribute("theUser") != null) {
				String studyCode = request.getParameter("StudyCode");
				if (studyCode == null) {
					List<Study> studies = StudyDB.getStudiesByStatus("start");
					request.setAttribute("studies", studies);
					url = "/participate.jsp";
				} else {
					Study study = StudyDB.getStudy(studyCode);
					request.setAttribute("study", study);
					url = "/question.jsp";
				}

			} else {
				url = "/login.jsp";
			}
		}
		
//edit action if the studycode is not null it will retrieve the study and makes it available for the user to edit
		
		if (action.equals("edit")) {
			if (session.getAttribute("theUser") != null) {
				String studyCode = request.getParameter("StudyCode");
				String email = request.getParameter("email");
				if (studyCode != null) {
					Study study = StudyDB.getStudy(studyCode, email);
					request.setAttribute("study", study);
					url = "/editstudy.jsp";
				}

			} else {
				url = "/login.jsp";
			}
		}
		
// report action will fetch all reports if studycode is null else will add the particular report to the db.
		
		if (action.equals("report")) {
			if (session.getAttribute("theUser") != null) {
				String studyCode = request.getParameter("StudyCode");
				String email = request.getParameter("email");
				if (studyCode == null) {
					User user = (User) session.getAttribute("theUser");
					email = user.getEmail();
					List<Report> repList = ReportDB.getReports(email);
					request.setAttribute("repList", repList);
					url = "/reporth.jsp";
				} else {
					String ques = request.getParameter("ques");
					SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
					Study study=StudyDB.getStudy(studyCode);
					if (ReportDB.getReport(studyCode, email) != null) {
						request.setAttribute("msg", "Already");
						url = "/confirmrep.jsp";

					} else {
						java.util.Date date= new java.util.Date();
						Report report = new Report(studyCode, ques, email, new Timestamp(date.getTime()), "Pending",study.getStudyCode()+""+study.getStudyName());
						ReportDB.addReport(report);
						url = "/confirmrep.jsp";
					}
				}

			} else {
				url = "/login.jsp";
			}
		}

//reportques will fetch the reports and make it available for admin 
		
		if (action.equals("reportques")) {

			List<Report> reports = ReportDB.getReports();
			request.setAttribute("reports", reports);
			url = "/reportques.jsp";
		}

//approve action will approve the particular report and navigates back to the reportques.jsp
		
		if (action.equals("approve")) {
			if (session.getAttribute("theAdmin") != null) {
				String studyCode = request.getParameter("StudyCode");
				String repmail = request.getParameter("repemail");
				if (studyCode != null) {
					ReportDB.updateReportStatus(studyCode, repmail,"approved");
					//StudyDB.removeStudy(studyCode);
				}
				List<Report> repList = ReportDB.getReports();
				request.setAttribute("reports", repList);
				url = "/reportques.jsp";
			} else {
				url = "/login.jsp";
			}
		}

//disapprove action will disapprove the particular report and navigates back to the reportques.jsp
		
		if (action.equals("disapprove")) {
			if (session.getAttribute("theAdmin") != null) {
				String studyCode = request.getParameter("StudyCode");
				String repmail = request.getParameter("repemail");
				if (studyCode != null) {
					ReportDB.updateReportStatus(studyCode, repmail,"disapproved");
				}
				List<Report> repList = ReportDB.getReports();
				request.setAttribute("reports", repList);
				url = "/reportques.jsp";
			} else {
				url = "/login.jsp";
			}
		}

//update action will update the study and navigates back to study.jsp
		
		if (action.equals("update")) {
			if (session.getAttribute("theUser") != null) {
                            String imgurl = request.getParameter("ogimg");
                            // Create a factory for disk-based file items
                            DiskFileItemFactory factory = new DiskFileItemFactory();

                            // Sets the size threshold beyond which files are written directly to
                            // disk.
                            factory.setSizeThreshold(MAX_MEMORY_SIZE);

                            // Sets the directory used to temporarily store files that are larger
                            // than the configured size threshold. We use temporary directory for
                            // java
                            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

                            
                            // constructs the folder where uploaded file will be stored
                            String uploadFolder = null;
                            if(System.getenv("OPENSHIFT_DATA_DIR") != null){
                            uploadFolder = System.getenv("OPENSHIFT_DATA_DIR");
                            }else{
                            uploadFolder = getServletContext().getRealPath("") + DATA_DIRECTORY;
                            }
                                
                            //FOR LOCAL USE BELOW
                            //uploadFolder = getServletContext().getRealPath("") + DATA_DIRECTORY;
                            // Create a new file upload handler
                            ServletFileUpload upload = new ServletFileUpload(factory);
                            // Set overall request size constraint
                            upload.setSizeMax(MAX_REQUEST_SIZE);
                            
                            //upload the file
                            Part filePart = request.getPart("imageURL"); 
                            String fileName = getSubmittedFileName(filePart);
                            if (fileName != null && !fileName.isEmpty()){
                                String filePath = uploadFolder + File.separator;
                                File file = new File(filePath, fileName);
                                InputStream input = filePart.getInputStream();
                                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                                imgurl = uploadFolder + fileName;
                                //FOR LOCAL USE BELOW
                                //imgurl = getServletContext().getRealPath("") + DATA_DIRECTORY +  File.separator +fileName;
                            }                             
				String name = request.getParameter("study_name");
				String ques = request.getParameter("question_text");
				String participantsNum = request.getParameter("participants");
				int parNum = Integer.parseInt(participantsNum);
				String participantsofNum = request.getParameter("numparticipants");
				int participNum = Integer.parseInt(participantsofNum);
				//String AnswersNum = request.getParameter("edit_study_answers");
				//int ansNum = Integer.parseInt(AnswersNum);
				String description = request.getParameter("description");
				String email = request.getParameter("email");
				String sCode = request.getParameter("code");
				String status = request.getParameter("status");
				//List ans = new ArrayList<>();
                                List answer = new ArrayList();
				for (int i = 1; i < 6; i++) {
                                    if(request.getParameter("DynamicTextBox" + i)!= null){	
                                        answer.add(request.getParameter("DynamicTextBox" + i));
                                    }
				}
				Study study = new Study(name, email, ques, parNum, participNum, description, answer, sCode, status, imgurl);

				StudyDB.updateStudy(sCode, study);
				List<Study> studyList = StudyDB.getStudies(email);
				request.setAttribute("studyList", studyList);
				url = "/studies.jsp";

			} else {
				url = "/login.jsp";
			}
		}
		 
//add action will add a new study in the db 
		
		if (action.equals("add")) {
			if (session.getAttribute("theUser") != null) {
                            // Create a factory for disk-based file items
                            DiskFileItemFactory factory = new DiskFileItemFactory();

                            // Sets the size threshold beyond which files are written directly to
                            // disk.
                            factory.setSizeThreshold(MAX_MEMORY_SIZE);

                            // Sets the directory used to temporarily store files that are larger
                            // than the configured size threshold. We use temporary directory for
                            // java
                            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

                            // constructs the folder where uploaded file will be stored
                            //String uploadFolder = System.getenv("OPEN_SHIFT_DIR");
                           // String uploadFolder = "C:\\TempImg";
                            String uploadFolder = null;
                            if(System.getenv("OPENSHIFT_DATA_DIR") != null){
                            uploadFolder = System.getenv("OPENSHIFT_DATA_DIR");
                            }else{
                            uploadFolder = getServletContext().getRealPath("") + DATA_DIRECTORY;
                            }
                            
                            //LOCAL USE BELOW
                            //uploadFolder = getServletContext().getRealPath("") + DATA_DIRECTORY;
                            // Create a new file upload handler
                            ServletFileUpload upload = new ServletFileUpload(factory);
                            // Set overall request size constraint
                            upload.setSizeMax(MAX_REQUEST_SIZE);
                            
                            //upload the file
                            Part filePart = request.getPart("imageURL"); 
                            String fileName = getSubmittedFileName(filePart);
                            String filePath = uploadFolder + File.separator;
                            File file = new File(filePath, fileName);
                            InputStream input = filePart.getInputStream();
                            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                         
                            //add to the db
                            //System.getenv("localhost");
                            //String imgurl = System.getenv("OPEN_SHIFT_DIR") + fileName;
                            String imgurl = uploadFolder + fileName;
                            System.out.println(imgurl);
                            //FOR LOCAL USE BELOW
                            //    imgurl = getServletContext().getRealPath("") + DATA_DIRECTORY +  File.separator +fileName;                            
                            String name = request.getParameter("study_name");
                            String ques = request.getParameter("question_text");
                            String participantsNum = request.getParameter("participant_text");
                            int parNum = Integer.parseInt(participantsNum);
                            String AnswersNum = request.getParameter("answers");
                            int ansNum = Integer.parseInt(AnswersNum);
                            String description = request.getParameter("description");
                            String email = request.getParameter("email");
                            int x = (int) (Math.random() * 100);
                            String sCode = "X" + x;
                            sCode=StudyDB.getSCode(sCode);
                            String status = "start";
                            List ans = new ArrayList();
                            for (int i = 1; i <= ansNum; i++) {
                                    ans.add(request.getParameter("DynamicTextBox" + i));
                            }
                            Study study = new Study(name, email, ques, parNum, 0, description, ans, sCode, status, imgurl);
                            StudyDB.addStudy(study);
                            List<Study> studyList = StudyDB.getStudies(email);
                            request.setAttribute("studyList", studyList);
                            url = "/studies.jsp";
			}
		}
		
// start action will start the study so that it will be available for users to participate
		
		if (action.equals("start")) {
			if (session.getAttribute("theUser") != null) {
				String studyCode = request.getParameter("StudyCode");
				String email = request.getParameter("email");
				if (studyCode != null) {
					// request.setAttribute("studystatus", "start");
					//StudyDB.getStudy(studyCode, email).setStatus("start");
					StudyDB.updateStudyStatus(studyCode, email,"start");
				}
				List<Study> studyList = StudyDB.getStudies(email);
				request.setAttribute("studyList", studyList);
				url = "/studies.jsp";

			} else {
				url = "/login.jsp";
			}
		}
		
//stop action will stop the study so that it wont be available for users to participate
		
		if (action.equals("stop")) {
			if (session.getAttribute("theUser") != null) {
				String studyCode = request.getParameter("StudyCode");
				String email = request.getParameter("email");
				if (studyCode != null) {
					// request.setAttribute("studystatus", "stop");
					//StudyDB.getStudy(studyCode, email).setStatus("stop");
					StudyDB.updateStudyStatus(studyCode, email,"stop");
				}
				List<Study> studyList = StudyDB.getStudies(email);
				request.setAttribute("studyList", studyList);
				url = "/studies.jsp";

			} else {
				url = "/login.jsp";
			}
		}
		
//answer action will add answwer record in db and update the user coins and participation and studyparticipants		
		
		if (action.equals("answer")) {
			if (session.getAttribute("theUser") != null) {
				User user = (User) session.getAttribute("theUser");
				//UserDB.getUser(user.getEmail()).setNumParticipation(user.getNumParticipation() + 1);
				UserDB.updateUserParticipation((user.getEmail()), (user.getNumParticipation() + 1));
				//user.setNumParticipation(user.getNumParticipation());
				//UserDB.getUser(user.getEmail()).setNumCoins(user.getNumCoins() + 1);
				UserDB.updateUserCoins((user.getEmail()), (user.getNumCoins() + 1));
				//user.setNumCoins(user.getNumCoins());
				session.setAttribute("theUser", UserDB.getUser(user.getEmail()));
				String studyCode = request.getParameter("StudyCode");
				Study stud = StudyDB.getStudy(studyCode);
				//stud.setNumOfParticipants(stud.getNumOfParticipants() + 1);
				StudyDB.updateStudyPar(studyCode, stud.getNumOfParticipants() + 1);
				if (!stud.getEmail().equals(user.getEmail())) {
					//UserDB.getUser(stud.getEmail()).setNumCoins(UserDB.getUser(stud.getEmail()).getNumCoins() + 1);
					UserDB.updateUserCoins((stud.getEmail()), (UserDB.getUser(stud.getEmail()).getNumCoins() + 1));
				}
				String email = request.getParameter("email");
				String choice = request.getParameter("radname");
				java.util.Date date = new java.util.Date();
				Answer ans = new Answer(studyCode, email, new Timestamp(date.getTime()), choice,(stud.getStudyCode() + "" + stud.getStudyName()));
				StudyDB.addAnswers(ans);
				List<Study> studyList = StudyDB.getStudiesByStatus("start");
				request.setAttribute("studies", studyList);
				System.out.println(user.getNumParticipation()+"@@@@@"+user.getNumCoins());
				url = "/participate.jsp";
			} else {
				url = "/login.jsp";
			}

		}
		
//studies action will retrieve all the study records for that particular user
		
		if (action.equals("studies")) {
			if (session.getAttribute("theUser") != null) {

				User user = (User) session.getAttribute("theUser");
				List<Study> studyList = StudyDB.getStudies(user.getEmail());
				request.setAttribute("studyList", studyList);
				url = "/studies.jsp";

			} else {
				url = "/login.jsp";
			}
		}
		
		if(action.equals("recommend"))
		{
			String name=request.getParameter("study_name");
			String sMail=request.getParameter("email");
			String fMail=request.getParameter("friend_email");
			String message=request.getParameter("message");
			EmailDB.emailRecommendTrigger(name, sMail, fMail, message);
                        recommend user = new recommend();
                        user.setsEmail(sMail);
                        user.setfEmail(fMail);
                        RecommendDB.addUser(user);
			url="/confirmr.jsp";
		}
		if(action.equals("contact"))
		{
			String name=request.getParameter("study_name");
			String sMail=request.getParameter("email");
			String message=request.getParameter("message");
			EmailDB.emailContactTrigger(name, sMail, message);
			url="/confirmc.jsp";
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) calls the doget method
	 */
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
private static String getSubmittedFileName(Part part) {
    for (String cd : part.getHeader("content-disposition").split(";")) {
        if (cd.trim().startsWith("filename")) {
            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
        }
    }
    return null;
}
}
