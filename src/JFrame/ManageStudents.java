/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package JFrame;


import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
/**
 *
 * @author Aditya
 */
public class ManageStudents extends javax.swing.JFrame {

    /**
     * Creates new form ManageStudents
     */
    String Name,Semester,Department,RegistrationNo,MobileNo;
    DefaultTableModel model;
    
    protected ManageStudents() {
        initComponents();
        SetStudentDetailsToTable();
    }

    //Code to Fetch the data from database and Display in Table
    private void SetStudentDetailsToTable()
    {
        try
        {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from student_details");
            
            while(rs.next())
            {
                
                String R_No = rs.getString("RegistrationNo");
                String S_Name=rs.getString("Name");
                String M_No=rs.getString("MobileNo");
                String Dept=rs.getString("Department");
                String Sem=rs.getString("Semester");
                
                Object[] obj = {R_No,S_Name,M_No,Dept,Sem};
                model = (DefaultTableModel) tbl_StudentDetails.getModel();
                model.addRow(obj);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //Method To Add Books To the the Database
    private boolean AddStudent()
    {
        boolean IsAdded=false;
        RegistrationNo=txt_RegistrationNo.getText();
        Name=txt_Name.getText();
        MobileNo=txt_MobileNo.getText();
        Department=txt_Department.getSelectedItem().toString();
        Semester=txt_Semester.getSelectedItem().toString();
        
        try
        {
            Connection con = DBConnection.getConnection();
            String sql = "insert into student_details values(?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, RegistrationNo);
            pst.setString(2, Name);
            pst.setString(3, MobileNo);
            pst.setString(4, Department);
            pst.setString(5,Semester);
            
            int RowCount = pst.executeUpdate();
            if(RowCount>0)
            {
                IsAdded=true;
            }
            else
            {
                IsAdded=false;
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return IsAdded;
    }
    
    //Method to Update Student Details
    private boolean UpdateStudentDetails()
    {
        boolean IsUpdated=false;
        RegistrationNo=txt_RegistrationNo.getText();
        Name=txt_Name.getText();
        MobileNo=txt_MobileNo.getText();
        Department=txt_Department.getSelectedItem().toString();
        Semester=txt_Semester.getSelectedItem().toString();
        
        try
        {
            Connection con = DBConnection.getConnection();
            String sql = "update student_details set Name=?,MobileNo=?,Department=?,Semester=? where RegistrationNo=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Name);
            pst.setString(2, MobileNo);
            pst.setString(3, Department);
            pst.setString(4, Semester);
            pst.setString(5, RegistrationNo);
            
            int RowCount = pst.executeUpdate();
            if(RowCount>0)
            {
                IsUpdated=true;
            }
            else
            {
                IsUpdated=false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return IsUpdated;
    }
    
    //Method to Delete Book Detail
    private boolean DeleteStudent()
    {
        boolean IsDeleted=false;
        RegistrationNo=txt_RegistrationNo.getText();
        try
        {
            Connection con = DBConnection.getConnection();
            String sql = "delete from student_details where RegistrationNo=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, RegistrationNo);
            
            int RowCount = pst.executeUpdate();
            if(RowCount>0)
            {
                IsDeleted=true;
            }
            else
            {
                IsDeleted=false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return IsDeleted;
    }
    
    //Method to Clear Table
    private void ClearTable()
    {
        DefaultTableModel model = (DefaultTableModel) tbl_StudentDetails.getModel();
        model.setRowCount(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_RegistrationNo = new app.bolivia.swing.JCTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_Name = new app.bolivia.swing.JCTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_MobileNo = new app.bolivia.swing.JCTextField();
        rSMaterialButtonCircle1 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new necesario.RSMaterialButtonCircle();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_Semester = new javax.swing.JComboBox<>();
        txt_Department = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_StudentDetails = new rojerusan.RSTableMetro();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel1.setText("Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 50));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 50, 60));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Enter Registration No");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 210, 30));

        txt_RegistrationNo.setBackground(new java.awt.Color(0, 204, 255));
        txt_RegistrationNo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
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
        jPanel1.add(txt_RegistrationNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 290, 40));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 50, 60));

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Enter Name");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 110, 30));

        txt_Name.setBackground(new java.awt.Color(0, 204, 255));
        txt_Name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txt_Name.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_Name.setPlaceholder("Enter Student Name");
        txt_Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_NameFocusLost(evt);
            }
        });
        txt_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 290, 40));

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 40, 60));

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Mobile Number");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 140, 30));

        txt_MobileNo.setBackground(new java.awt.Color(0, 204, 255));
        txt_MobileNo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txt_MobileNo.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_MobileNo.setPlaceholder("Enter Mobile No");
        txt_MobileNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_MobileNoFocusLost(evt);
            }
        });
        txt_MobileNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MobileNoActionPerformed(evt);
            }
        });
        jPanel1.add(txt_MobileNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 290, 40));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(0, 255, 51));
        rSMaterialButtonCircle1.setText("ADD");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 100, 60));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle2.setText("DELETE");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 590, 100, 60));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(153, 51, 255));
        rSMaterialButtonCircle3.setText("UPDATE");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 590, 100, 60));

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Enter Department");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 180, 30));

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 40, 60));

        jLabel21.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Enter Semester");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, 160, 30));

        jLabel22.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 40, 60));

        txt_Semester.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        txt_Semester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1st", "2nd", "3rd", "4th", "5th", "6th" }));
        txt_Semester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SemesterActionPerformed(evt);
            }
        });
        jPanel1.add(txt_Semester, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, 290, 30));

        txt_Department.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        txt_Department.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CST", "EE", "CE", "Arch", "ETCE", "EIE" }));
        jPanel1.add(txt_Department, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 290, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 680));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 30, 30));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 110, 50));

        tbl_StudentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Registration No", "Name", "Mobile Number", "Department", "Semester"
            }
        ));
        tbl_StudentDetails.setColorBackgoundHead(new java.awt.Color(0, 204, 255));
        tbl_StudentDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_StudentDetails.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        tbl_StudentDetails.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tbl_StudentDetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tbl_StudentDetails.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tbl_StudentDetails.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tbl_StudentDetails.setRowHeight(25);
        tbl_StudentDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_StudentDetailsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_StudentDetails);

        jPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 890, 390));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel3.setText("  Manage Students");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 51, 51));
        jPanel5.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 360, 5));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 910, 680));

        setSize(new java.awt.Dimension(1290, 680));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        new HomePage().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txt_RegistrationNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_RegistrationNoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RegistrationNoFocusLost

    private void txt_RegistrationNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_RegistrationNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RegistrationNoActionPerformed

    private void txt_NameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_NameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NameFocusLost

    private void txt_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NameActionPerformed

    private void txt_MobileNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_MobileNoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MobileNoFocusLost

    private void txt_MobileNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MobileNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MobileNoActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        // TODO add your handling code here:
        if(AddStudent()==true)
        {
            JOptionPane.showMessageDialog(this,"Student Added Successfuly");
            ClearTable();
            SetStudentDetailsToTable();
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Student Not Added");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        // TODO add your handling code here:
        if(DeleteStudent()==true)
        {
            JOptionPane.showMessageDialog(this,"Student Deleted Successfuly");
            ClearTable();
            SetStudentDetailsToTable();
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Student Not Deleted");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        // TODO add your handling code here:
        if(UpdateStudentDetails()==true)
        {
            JOptionPane.showMessageDialog(this,"Student Details Updated Successfuly");
            ClearTable();
            SetStudentDetailsToTable();
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Student Details Not Updated");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tbl_StudentDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_StudentDetailsMouseClicked
        // TODO add your handling code here:
        int RowNo=tbl_StudentDetails.getSelectedRow();
        TableModel model = tbl_StudentDetails.getModel();

        txt_RegistrationNo.setText(model.getValueAt(RowNo,0).toString());
        txt_Name.setText(model.getValueAt(RowNo,1).toString());
        txt_MobileNo.setText(model.getValueAt(RowNo,2).toString());
        txt_Department.setSelectedItem(model.getValueAt(RowNo,3).toString());
        txt_Semester.setSelectedItem(model.getValueAt(RowNo,4).toString());

    }//GEN-LAST:event_tbl_StudentDetailsMouseClicked

    private void txt_SemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SemesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SemesterActionPerformed

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
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageStudents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane4;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private rojerusan.RSTableMetro tbl_StudentDetails;
    private javax.swing.JComboBox<String> txt_Department;
    private app.bolivia.swing.JCTextField txt_MobileNo;
    private app.bolivia.swing.JCTextField txt_Name;
    private app.bolivia.swing.JCTextField txt_RegistrationNo;
    private javax.swing.JComboBox<String> txt_Semester;
    // End of variables declaration//GEN-END:variables
}
