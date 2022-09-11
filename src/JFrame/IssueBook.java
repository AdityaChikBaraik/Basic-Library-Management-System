/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package JFrame;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Aditya
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */

    protected IssueBook() {
        initComponents();
    }
    
    //Method to fetch the book details from Database and display it to the Book details panel
    private void GetBookDetails()
    {
        int BookID =Integer.parseInt(txt_BookID.getText());
        
        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from book_details where BookId = ?");
            pst.setInt(1,BookID);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {
                lbl_BookID.setText(rs.getString("BookId"));
                lbl_BookName.setText(rs.getString("BookName"));
                lbl_Author.setText(rs.getString("Author"));
                lbl_Quantity.setText(rs.getString("Quantity"));
                lbl_BookError.setText("");
            }
            else
            {
                lbl_BookID.setText("");
                lbl_BookName.setText("");
                lbl_Author.setText("");
                lbl_Quantity.setText("");
                lbl_BookError.setText("Invalid Book ID");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //Method to fetch the Student details from Database and display it to the Student details panel
    private void GetStudentDetails()
    {
        String RegistrationNo =txt_RegistrationNo.getText();
        
        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from student_details where RegistrationNo = ?");
            pst.setString(1,RegistrationNo);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {
                lbl_RegistrationNo.setText(rs.getString("RegistrationNo"));
                lbl_Name.setText(rs.getString("Name"));
                lbl_MobileNo.setText(rs.getString("MobileNo"));
                lbl_Department.setText(rs.getString("Department"));
                lbl_Semester.setText(rs.getString("Semester"));
                lbl_StudentError.setText("");
            }
            else
            {
                lbl_RegistrationNo.setText("");
                lbl_Name.setText("");
                lbl_MobileNo.setText("");
                lbl_Department.setText("");
                lbl_Semester.setText("");
                lbl_StudentError.setText("Invalid Registration No");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //Insert Issue Book Details into Database
    
    private Boolean IssueBook()
    {
        boolean IsIssued=false;
        int BookID=Integer.parseInt(txt_BookID.getText());
        String RegistrationNo = txt_RegistrationNo.getText();
        String BookName = lbl_BookName.getText();
        String Name = lbl_Name.getText();
        
        java.util.Date IDate =txt_IssueDate.getDate();
        long l1=IDate.getTime();
        java.sql.Date SqlIDate = new java.sql.Date(l1);
        
        java.util.Date DDate=txt_DueDate.getDate();
        long l2=DDate.getTime();
        java.sql.Date SqlDDate = new java.sql.Date(l2);
        
        try
        {
            Connection con = DBConnection.getConnection();
            String sql = "insert into issue_book(BookID,BookName,RegistrationNo,Name,IssueDate,DueDate,Status) values (?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,BookID);
            pst.setString(2, BookName);
            pst.setString(3, RegistrationNo);
            pst.setString(4, Name);
            pst.setDate(5,SqlIDate);
            pst.setDate(6, SqlDDate);
            pst.setString(7, "Pending");
            
            int RowCount = pst.executeUpdate();
            if(RowCount > 0)
            {
                IsIssued=true;
            }
            else
            {
                IsIssued=false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return IsIssued;
    }
    
    //Method to Update Book Count
    private void UpdateBookCount()
    {
        int BookID=Integer.parseInt(txt_BookID.getText());
        try
        {
            Connection con = DBConnection.getConnection();
            String sql = "update book_details set Quantity = Quantity-1 where BookId=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, BookID);
            
            int RowCount = pst.executeUpdate();
            if(RowCount > 0)
            {
                JOptionPane.showMessageDialog(this,"Book Count Updated");
                int InitialCount = Integer.parseInt(lbl_Quantity.getText());
                lbl_Quantity.setText(Integer.toString(InitialCount-1));
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Can't Update Book Count");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //Method to Check Whether Book is already Allocated to the same student
    private boolean IsAlreadyIssued()
    {
        boolean IsAlreadyIssued=false;
        int BookID=Integer.parseInt(txt_BookID.getText());
        String RegistrationNo = txt_RegistrationNo.getText();
        
        try
        {
            Connection con = DBConnection.getConnection();
            String sql = "select * from issue_book where BookID=? and RegistrationNo=? and Status=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, BookID);
            pst.setString(2, RegistrationNo);
            pst.setString(3, "Pending");
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {
                IsAlreadyIssued = true;
            }
            else
            {
                IsAlreadyIssued = false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return IsAlreadyIssued;
    }
    
    //Method to Check Whether the Student is valid or Not
    private Boolean StudentIsValid()
    {
        String RegistrationNo =txt_RegistrationNo.getText();
        boolean StudentIsValid=false;
        
        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from student_details where RegistrationNo = ?");
            pst.setString(1,RegistrationNo);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {
                StudentIsValid=true;
            }
            else
            {
                StudentIsValid=false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return StudentIsValid;
    }
    
    //Method to Check Whether the Book Details is valid or Not
    private Boolean BookIsValid()
    {
        int BookID =Integer.parseInt(txt_BookID.getText());
        boolean BookIsValid=false;
        
        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from book_details where BookID = ?");
            pst.setInt(1,BookID);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {
                BookIsValid=true;
            }
            else
            {
                BookIsValid=false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return BookIsValid;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_Main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbl_Semester = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_RegistrationNo = new javax.swing.JLabel();
        lbl_Name = new javax.swing.JLabel();
        lbl_MobileNo = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lbl_Department = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_StudentError = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbl_Quantity = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_BookID = new javax.swing.JLabel();
        lbl_BookName = new javax.swing.JLabel();
        lbl_Author = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbl_BookError = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_BookID = new app.bolivia.swing.JCTextField();
        txt_RegistrationNo = new app.bolivia.swing.JCTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        txt_DueDate = new com.toedter.calendar.JDateChooser();
        txt_IssueDate = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Panel_Main.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 360, 5));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Semester");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, -1, -1));

        lbl_Semester.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_Semester.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_Semester, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 570, 250, 30));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Name");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Mobile No");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText(" Registration No");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        lbl_RegistrationNo.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_RegistrationNo.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_RegistrationNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 210, 30));

        lbl_Name.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 260, 30));

        lbl_MobileNo.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_MobileNo.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_MobileNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 250, 30));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Department");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));

        lbl_Department.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_Department.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_Department, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 490, 240, 30));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 25)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel4.setText("Student Details");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 290, 100));

        lbl_StudentError.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_StudentError.setForeground(new java.awt.Color(255, 255, 0));
        jPanel1.add(lbl_StudentError, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, 350, 40));

        Panel_Main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 400, 680));

        jPanel4.setBackground(new java.awt.Color(255, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(102, 102, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel11.setText("Back");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 100, 30));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 50));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 25)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel12.setText("Book Details");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 270, 100));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 360, 5));

        lbl_Quantity.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_Quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_Quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 220, 30));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Book Name");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Author");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Book ID");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        lbl_BookID.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_BookID.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_BookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 220, 30));

        lbl_BookName.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_BookName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_BookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 220, 30));

        lbl_Author.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_Author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_Author, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 220, 30));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Quantity");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));

        lbl_BookError.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_BookError.setForeground(new java.awt.Color(255, 255, 0));
        jPanel4.add(lbl_BookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 350, 40));

        Panel_Main.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 680));

        jLabel2.setBackground(new java.awt.Color(255, 51, 51));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel2.setText("Issue Book");
        Panel_Main.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 110, 200, -1));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Panel_Main.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 170, 330, 5));

        jPanel7.setBackground(new java.awt.Color(255, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 25)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("X");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 30, 30));

        Panel_Main.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, 110, 50));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setText("Enter Book ID");
        Panel_Main.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 200, 210, 30));

        txt_BookID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_BookID.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_BookID.setPlaceholder("Enter Book ID");
        txt_BookID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_BookIDFocusLost(evt);
            }
        });
        txt_BookID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BookIDActionPerformed(evt);
            }
        });
        Panel_Main.add(txt_BookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 240, 370, 40));

        txt_RegistrationNo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_RegistrationNo.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_RegistrationNo.setPlaceholder("Enter Registration No");
        txt_RegistrationNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_RegistrationNoFocusLost(evt);
            }
        });
        txt_RegistrationNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_RegistrationNoActionPerformed(evt);
            }
        });
        Panel_Main.add(txt_RegistrationNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 340, 370, 40));

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 51, 51));
        jLabel18.setText("Enter Issue Date");
        Panel_Main.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 400, 210, 30));

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 51, 51));
        jLabel19.setText("Enter Registration No");
        Panel_Main.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 300, 210, 30));

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 51, 51));
        jLabel20.setText("Enter Due Date");
        Panel_Main.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 500, 210, 30));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle1.setText("Issue");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        Panel_Main.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 610, 270, 60));

        txt_DueDate.setDateFormatString("yyyy-MM-dd");
        txt_DueDate.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Panel_Main.add(txt_DueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 540, 360, 40));

        txt_IssueDate.setDateFormatString("yyyy-MM-dd");
        txt_IssueDate.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Panel_Main.add(txt_IssueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 440, 360, 40));

        getContentPane().add(Panel_Main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 680));

        setSize(new java.awt.Dimension(1288, 680));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        new HomePage().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void txt_BookIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BookIDFocusLost
        // TODO add your handling code here:
        if(!txt_BookID.getText().equals(""))
        {
            GetBookDetails();
        }
    }//GEN-LAST:event_txt_BookIDFocusLost

    private void txt_BookIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BookIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BookIDActionPerformed

    private void txt_RegistrationNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_RegistrationNoFocusLost
        // TODO add your handling code here:
        if(!txt_RegistrationNo.getText().equals(""))
        {
            GetStudentDetails();
        }
    }//GEN-LAST:event_txt_RegistrationNoFocusLost

    private void txt_RegistrationNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_RegistrationNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RegistrationNoActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        // TODO add your handling code here:
        if(lbl_Quantity.getText().equals("0"))
        {
            JOptionPane.showMessageDialog(this,"This Book Is Not Available");
        }
        else
        {
            if(BookIsValid()==true)
            {
                if(StudentIsValid()==true)
                {
            
                    if(IsAlreadyIssued()==false)
                    {
                        if(IssueBook() == true)
                        {
                            JOptionPane.showMessageDialog(this,"Book Issued Successfully");
                            UpdateBookCount();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this,"Book Not Issued");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"This Book Is Already Issued To This Student");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Invalid Registration No");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Invalid Book ID");
            }
            
            
        }
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Main;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lbl_Author;
    private javax.swing.JLabel lbl_BookError;
    private javax.swing.JLabel lbl_BookID;
    private javax.swing.JLabel lbl_BookName;
    private javax.swing.JLabel lbl_Department;
    private javax.swing.JLabel lbl_MobileNo;
    private javax.swing.JLabel lbl_Name;
    private javax.swing.JLabel lbl_Quantity;
    private javax.swing.JLabel lbl_RegistrationNo;
    private javax.swing.JLabel lbl_Semester;
    private javax.swing.JLabel lbl_StudentError;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private app.bolivia.swing.JCTextField txt_BookID;
    private com.toedter.calendar.JDateChooser txt_DueDate;
    private com.toedter.calendar.JDateChooser txt_IssueDate;
    private app.bolivia.swing.JCTextField txt_RegistrationNo;
    // End of variables declaration//GEN-END:variables
}
