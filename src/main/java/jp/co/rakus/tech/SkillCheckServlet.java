package jp.co.rakus.tech;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tera on 2016/01/29.
 */
//@WebServlet(name = "SkillCheckServlet")
@WebServlet("/SkillCheckServlet")
public class SkillCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String engineerId=(String)request.getParameter("register_id");
        String[] arrayQuestionCode=(String[])request.getParameterValues("question_code");
        String[] arrayAnswer=(String[])request.getParameterValues("answer");
        DBController dbConn = new DBController();
        int sum = dbConn.insertCheckResult(engineerId, arrayQuestionCode, arrayAnswer);
        request.setAttribute("sum", sum);
        RequestDispatcher dispatch = request.getRequestDispatcher("/thanks.jsp");
        dispatch.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hr_code=(String)request.getParameter("hr_code");
        String name=(String)request.getParameter("name");
        System.out.println(name);
        if (name==null || name.isEmpty()){
            System.out.println("no name found");
            String url = "/skill_check";
            response.sendRedirect(url);
            return;
        }
        DBController dbConn = new DBController();
        request.setAttribute("register_id", dbConn.registerEngineer(hr_code, name));
        request.setAttribute("lstQuestion", dbConn.fetchQuestion());
        request.setAttribute("engineer_name", name);
        RequestDispatcher dispatch = request.getRequestDispatcher("/skillcheck.jsp");
        dispatch.forward(request, response);
    }

    public class Question{
        private String idQuestion;
        private String category;
        private String question;
        private String description;
        public Question(String idQuestion_, String category_, String question_, String description_){
            idQuestion = idQuestion_;
            category = category_;
            question = question_;
            description = description_;
        }
        public String getIdQuestion(){
            return idQuestion;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    private class DBController {
        // JDBC driver name and database URL
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://172.16.0.20/skill_check";

        //  Database credentials
        static final String USER = "root";
        static final String PASS = "rakus2000";

        public Integer registerEngineer(String hr_code_, String name_){
            Connection conn = null;
            Statement stmt = null;
            Integer idEngineer = null;
            String sql;
            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");
                //STEP 3: Open a connection
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

                //STEP 4: Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();
                sql = "INSERT INTO engineer (hr_code, engineer_name) VALUES (" + hr_code_ + ", '" + name_ + "');";
                stmt.executeUpdate(sql);

                sql = "SELECT LAST_INSERT_ID() AS LAST";
                ResultSet rs = stmt.executeQuery(sql);

                //STEP 5: Extract data from result set
                if(rs != null && rs.next()){
                    //Retrieve by column name
                    idEngineer = rs.getInt("LAST");
                }
                //STEP 6: Clean-up environment
                rs.close();
                stmt.close();
                conn.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
            System.out.println("idEngineer:");
            System.out.println(idEngineer);
            return idEngineer;

        }
        public List<Question> fetchQuestion() {
            Connection conn = null;
            Statement stmt = null;
            List<Question> lstQuestion = new ArrayList();
            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");
                //STEP 3: Open a connection
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

                //STEP 4: Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();
                String sql;
                sql = "SELECT idquestion, category, question, description FROM question ORDER BY question_order_no;";
                ResultSet rs = stmt.executeQuery(sql);

                //STEP 5: Extract data from result set
                while(rs.next()){
                    //Retrieve by column name
                    String idQuestion = rs.getString("idquestion");
                    String category = rs.getString("category");
                    String question = rs.getString("question");
                    String description = rs.getString("description");

                    Question objQuestion = new Question(idQuestion, category, question, description);
                    lstQuestion.add(objQuestion);
                }
                //STEP 6: Clean-up environment
                rs.close();
                stmt.close();
                conn.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
            System.out.println("Goodbye!");
            return lstQuestion;
        }//end main

        public Integer insertCheckResult(String engineerId, String[] arrayQuestionCode, String[] arrayAnswer) {
            Connection conn = null;
            Statement stmt = null;
            List<Question> lstQuestion = new ArrayList();
            Integer sum = 0;
            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");
                //STEP 3: Open a connection
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

                //STEP 4: Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();
                String sql;

                for (int i = 0; i < arrayQuestionCode.length; i++){
                    sum += Integer.parseInt(arrayAnswer[i]);
                    sql = "INSERT INTO answer (idengineer, idquestion, answer_no) VALUES " + "(" + engineerId +", " + arrayQuestionCode[i] + ", " + arrayAnswer[i]  + ")";
                    stmt.executeUpdate(sql);
                }

                //STEP 6: Clean-up environment
                stmt.close();
                conn.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
            System.out.println("Goodbye!");
            return sum;
        }
    }//end FirstExample

}
