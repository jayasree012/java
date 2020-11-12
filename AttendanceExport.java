package com.company;

//importing all the required packages
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

class PSGclg    //parent class
{
    //declaring instance variables
    protected String Name;
    protected String StudentID;

    //instantiating 9 arrays for 9 subjects
    protected int[] LA =new int[4];
    protected int[] DA =new int[4];
    protected int[] CA =new int[4];
    protected int[] DS =new int[4];
    protected int[] OO =new int[4];
    protected int[] EE =new int[4];
    protected int[] DL =new int[4];
    protected int[] ES =new int[4];
    protected int[] OOL =new int[4];

    protected double totalhrs;   //total number of hours
    protected double[] percentagearray=new double[9];   //instantiating percentagearray to store the percentage of 9 subjects

    //parametrized constructor
    PSGclg(String[] User)
    {
        StudentID = User[0];
        Name = User[1];

        int j=0,i;
        for(i=2;i<38;i++)   //Initializing subject arrays
        {
            if (i >= 2 && i < 6) {
                LA[j % 4] = Integer.parseInt(User[i]);
                j++;
            }
            if (i >= 6 && i < 10) {
                DA[j % 4] = Integer.parseInt(User[i]);
                j++;
            }
            if (i >= 10 && i < 14) {
                CA[j % 4] = Integer.parseInt(User[i]);
                j++;
            }
            if (i >= 14 && i < 18) {
                DS[j % 4] = Integer.parseInt(User[i]);
                j++;
            }
            if (i >= 18 && i < 22) {
                OO[j % 4] = Integer.parseInt(User[i]);
                j++;
            }
            if (i >= 22 && i < 26) {
                EE[j % 4] = Integer.parseInt(User[i]);
                j++;
            }
            if (i >= 26 && i < 30) {
                DL[j % 4] = Integer.parseInt(User[i]);
                j++;
            }
            if (i >= 30 && i < 34) {
                OOL[j % 4] = Integer.parseInt(User[i]);
                j++;
            }
            if (i >= 34) {
                ES[j % 4] = Integer.parseInt(User[i]);
                j++;
            }
        }

        //calculating percentage for each subject and storing it in percentagearray.Also rounding to 2 decimal places
        totalhrs = LA[0] + LA[2] + LA[3];  //Linear Algebra
        percentagearray[0] = Double.parseDouble(String.format("%.2f",(LA[0] / totalhrs)*100));

        totalhrs = DA[0] + DA[2] + DA[3]; //Data Structures
        percentagearray[1] = Double.parseDouble(String.format("%.2f",(DA[0] / totalhrs)*100));

        totalhrs = CA[0] + CA[2] + CA[3]; //Computer Architecture
        percentagearray[2] = Double.parseDouble(String.format("%.2f",(CA[0] / totalhrs)*100));

        totalhrs = DS[0] + DS[2] + DS[3];  //Discrete Structures
        percentagearray[3] = Double.parseDouble(String.format("%.2f",(DS[0] / totalhrs)*100));

        totalhrs = OO[0] + OO[2] + OO[3]; //Object Oriented Programming
        percentagearray[4] = Double.parseDouble(String.format("%.2f",(OO[0] / totalhrs)*100));

        totalhrs = EE[0] + EE[2] + EE[3]; //Economics for Engineers
        percentagearray[5] = Double.parseDouble(String.format("%.2f",(EE[0] / totalhrs)*100));

        totalhrs = DL[0] + DL[2] + DL[3]; //Data Structures Lab
        percentagearray[6] = Double.parseDouble(String.format("%.2f",(DL[0] / totalhrs)*100));

        totalhrs = OOL[0] + OOL[2] + OOL[3]; //Object Oriented Programming Lab
        percentagearray[7]= Double.parseDouble(String.format("%.2f",(OOL[0] / totalhrs)*100));

        totalhrs = ES[0] + ES[2] + ES[3]; //Environmental Science
        percentagearray[8]= Double.parseDouble(String.format("%.2f",(ES[0] / totalhrs)*100));
    }

    //function to insert the calculate percentage for all the subjects in the table for the given roll.no
    void viewreport(String rollno)
    {
        if (StudentID.equals(rollno))     //checks whether the given roll number matches with the StudentID
        {
            //true: creating the table percentagetableModel
            DefaultTableModel percentagetableModel = new DefaultTableModel();
            JTable table = new JTable(percentagetableModel);

            //adding columns for headings
            percentagetableModel.addColumn("Roll No");
            percentagetableModel.addColumn("Name");
            percentagetableModel.addColumn("Linear Algebra");
            percentagetableModel.addColumn("Data Structures");
            percentagetableModel.addColumn("Computer Architecture");
            percentagetableModel.addColumn("Discrete Structures");
            percentagetableModel.addColumn("Object Oriented Programming");
            percentagetableModel.addColumn("Economics for Engineers");
            percentagetableModel.addColumn("Data Structures Lab");
            percentagetableModel.addColumn("Object Oriented Programming Lab");
            percentagetableModel.addColumn("Environmental Science");

            //creating a frame,setting its size,visibility and adding table to the frame
            JFrame f = new JFrame();
            f.setSize(850, 350);
            f.add(new JScrollPane(table));
            f.setVisible(true);

            //inserting a row in the table percentagetableModel that contains a student name,roll no and percentage of each subjects
            percentagetableModel.insertRow(0, new Object[]{StudentID, Name, percentagearray[0], percentagearray[1], percentagearray[2], percentagearray[3], percentagearray[4], percentagearray[5], percentagearray[6], percentagearray[7], percentagearray[8]});
        }
    }

    void viewreport(DefaultTableModel percentagetableModel)
    {
        //inserting a row in the table percentagetableModel that contains a student name,roll no and percentage of each subjects
        percentagetableModel.insertRow(percentagetableModel.getRowCount(), new Object[]{StudentID, Name, percentagearray[0], percentagearray[1], percentagearray[2], percentagearray[3], percentagearray[4], percentagearray[5], percentagearray[6], percentagearray[7], percentagearray[8]});
    }
}

class Student extends PSGclg   //student class inherits PSGclg
{
    Student(String[] User)
    {
        super(User);
    }

    //function to display the present,late,excused and unexcused and present percentage
    void viewreportstudent(DefaultTableModel tableModel)
    {
        tableModel.insertRow(0, new Object[]{"Linear ALgebra", LA[0], LA[1], LA[2], LA[3],percentagearray[0]});
        tableModel.insertRow(1, new Object[]{"Data Structures", DA[0], DA[1], DA[2], DA[3],percentagearray[1]});
        tableModel.insertRow(2, new Object[]{"Computer Architecture", CA[0], CA[1], CA[2], CA[3],percentagearray[2]});
        tableModel.insertRow(3, new Object[]{"Discrete Structures", DS[0], DS[1], DS[2], DS[3],percentagearray[3]});
        tableModel.insertRow(4, new Object[]{"Object Oriented Programming", OO[0], OO[1], OO[2], OO[3],percentagearray[4]});
        tableModel.insertRow(5, new Object[]{"Economics For Engineers", EE[0], EE[1], EE[2], EE[3],percentagearray[5]});
        tableModel.insertRow(6, new Object[]{"Data Structure Laboratory", DL[0], DL[1], DL[2], DL[3],percentagearray[6]});
        tableModel.insertRow(7, new Object[]{"Object Oriented Programming Laboratory", OOL[0], OOL[1], OOL[2], OOL[3],percentagearray[7]});
        tableModel.insertRow(8, new Object[]{"Environmental Science", ES[0], ES[1], ES[2], ES[3],percentagearray[8]});
    }
}

class Staff extends PSGclg   //SubjectStaff class inherits Staff class
{
    Staff(String[] User) //parametrised constructor
    {
        super(User);
    }

    //function to view rollno,name,present,late,excused and unexcused leave and percentage of the given subject
    void viewreportstaff(String subject,int rownum,DefaultTableModel tableModel) {

        switch (subject) {
            case "Linear Algebra":tableModel.insertRow(rownum, new Object[]{StudentID, Name, LA[0], LA[1], LA[2], LA[3],percentagearray[0]});break;

            case "Data Structures":tableModel.insertRow(rownum, new Object[]{StudentID, Name, DA[0], DA[1], DA[2], DA[3], percentagearray[1]});break;

            case "Computer Architecture":tableModel.insertRow(rownum, new Object[]{StudentID, Name, CA[0], CA[1], CA[2], CA[3], percentagearray[2]});break;

            case "Discrete Structures": tableModel.insertRow(rownum, new Object[]{StudentID, Name, DS[0], DS[1], DS[2], DS[3], percentagearray[3]});break;

            case "Object Oriented Programming":tableModel.insertRow(rownum, new Object[]{StudentID, Name, OO[0], OO[1], OO[2], OO[3],percentagearray[4]});break;

            case "Economics for Engineers":tableModel.insertRow(rownum, new Object[]{StudentID, Name, EE[0], EE[1], EE[2], EE[3], percentagearray[5]});break;

            case "Data Structures Lab":tableModel.insertRow(rownum, new Object[]{StudentID, Name, DL[0], DL[1], DL[2], DL[3], percentagearray[6]});break;

            case "Object Oriented Programming Lab":tableModel.insertRow(rownum, new Object[]{StudentID, Name, OOL[0], OOL[1], OOL[2], OOL[3], percentagearray[7]});break;

            case "Environmental Science":tableModel.insertRow(rownum, new Object[]{StudentID, Name, ES[0], ES[1], ES[2], ES[3],percentagearray[8]});
        }
    }

    //funtion to view the students whose percentage is less than the given percentage for the given subject
    void viewreportstaff(String subject, int rownum, DefaultTableModel tableModel,double givenPercent)
    {
        switch (subject) {
            case "Linear Algebra": {
                if ((percentagearray[0]) < givenPercent)
                {
                    tableModel.insertRow(rownum, new Object[]{StudentID, Name, LA[0], LA[1], LA[2], LA[3]});
                }
            }
            break;
            case "Data Structures": {
                if ((percentagearray[1]) < givenPercent)
                {
                    tableModel.insertRow(rownum, new Object[]{StudentID, Name, DA[0], DA[1], DA[2], DA[3]});
                }
            }
            break;
            case "Computer Architecture": {

                if ((percentagearray[2]) < givenPercent)
                {
                    tableModel.insertRow(rownum, new Object[]{StudentID, Name, CA[0], CA[1], CA[2], CA[3]});
                }
            }
            break;
            case "Discrete Structures": {

                if ((percentagearray[3]) < givenPercent)
                {
                    tableModel.insertRow(rownum, new Object[]{StudentID, Name, DS[0], DS[1], DS[2], DS[3]});
                }
            }
            break;
            case "Object Oriented Programming": {

                if ((percentagearray[4] ) < givenPercent)
                {
                    tableModel.insertRow(rownum, new Object[]{StudentID, Name, OO[0], OO[1], OO[2], OO[3]});
                }
            }
            break;
            case "Economics for Engineers": {

                if ((percentagearray[5] ) < givenPercent) {
                    tableModel.insertRow(rownum, new Object[]{StudentID, Name, EE[0], EE[1], EE[2], EE[3]});
                }
            }
            break;
            case "Data Structures Lab": {

                if ((percentagearray[6] ) < givenPercent) {
                    tableModel.insertRow(rownum, new Object[]{StudentID, Name, DL[0], DL[1], DL[2], DL[3]});
                }
            }
            break;
            case "Object Oriented Programming Lab": {

                if ((percentagearray[7] ) < givenPercent) {
                    tableModel.insertRow(rownum, new Object[]{StudentID, Name, OOL[0], OOL[1], OOL[2], OOL[3]});
                }
            }
            break;
            case "Environmental Science": {

                if ((percentagearray[8] ) < givenPercent)
                {
                    tableModel.insertRow(rownum, new Object[]{StudentID, Name, ES[0], ES[1], ES[2], ES[3]});
                }
            }
        }
    }

    //to display the students below the given percentage in all the subjects
    void viewreportstaff1(DefaultTableModel tableModel,double givenPercent)
    {
        String[] subjectnames={"Linear Algebra","Data Structures","Computer Architecture","Discrete Structures","Object Oriented Programming","Economics for Engineers","Data Structures Lab","Object Oriented Programming Lab","Environmental Science"};

        int flag=0;

        for(int j=0;j<9;j++)
        {
            if(percentagearray[j]<givenPercent)
            {   if(flag==0)
            {
                tableModel.insertRow(tableModel.getRowCount(), new Object[]{StudentID, Name, subjectnames[j],String.format("%.2f",percentagearray[j])});
                flag = 1;
            }
            else
            {
                tableModel.insertRow(tableModel.getRowCount(), new Object[]{"","", subjectnames[j],String.format("%.2f",percentagearray[j])});
            }
            }
        }

    }
}

//Main class
public class AttendanceExport
{
    static BufferedReader input=new BufferedReader(new InputStreamReader(System.in));

    //function getinput1() to get and return input for choice
    static int getinput1()throws IOException
    {
        System.out.println("Enter 1 for STAFF and 2 for STUDENT");
        System.out.println("1.STAFF");
        System.out.println("2.STUDENT");
        return Integer.parseInt(input.readLine());
    }

    //function getinput2() to get and return input for month
    static int getinput2() throws IOException
    {
        System.out.println("Enter 1 for AUGUST, 2 for SEPTEMBER, 3 for OCTOBER AND 4 FOR ALL");
        System.out.println("1.AUGUST");
        System.out.println("2.SEPTEMBER");
        System.out.println("3.OCTOBER");
        System.out.println("4.ALL");

        return Integer.parseInt(input.readLine());
    }

    //function getinput3() to get and return input for month
    static String getinput3() throws IOException
    {
        System.out.println("Enter any of the following SUBJECTS:\nLinear Algebra\nDiscrete Structures\nData Structures\nData Structures Lab\nComputer Architecture\nObject Oriented Programming\nObject Oriented Programming Lab\nEconomics for Engineers\nEnvironmental Science");
        return input.readLine();
    }

    //function getinput4() to get and return input for roll no
    static String getinput4() throws IOException
    {
        System.out.println("Enter the STUDENT ROLL NUMBER (Eg:19Z301):");
        return input.readLine();
    }

    //function getinput5() to get and return input for percentage
    static double getinput5() throws IOException
    {   System.out.println("Enter the percentage");
        String temp=input.readLine();
        return Double.parseDouble(temp);
    }

    //function getinput6() to get and return input for filter choice
    static int getinput6()throws IOException
    {
        System.out.println("1.FILTER BY STUDENT(VIEW PRESENT,LEAVE,EXCUSED AND UNEXCUSED ABSENT)");
        System.out.println("2.FILTER BY SUBJECT(VIEW PRESENT,LEAVE,EXCUSED AND UNEXCUSED ABSENT,PRESENT PERCENTAGE)");
        System.out.println("3.VIEW FOR A PARTICULAR STUDENT FOR A PARTICULAR SUBJECT(VIEW PRESENT,LEAVE,EXCUSED AND UNEXCUSED ABSENT,PRESENT PERCENTAGE)");
        System.out.println("4.FILTER BY STUDENT-PERCENTAGE - GIVEN SUBJECT");
        System.out.println("5.VIEW ALL STUDENTS ALL SUBJECTS-PERCENTAGE");
        System.out.println("6.DISPLAY STUDENTS WHO LACK ATTENDANCE ALONG WITH THE SUBJECTS");
        return Integer.parseInt(input.readLine());
    }

    //function getinput7() to get and return input for filter choice
    static int getinput7()throws IOException
    {
        System.out.println("1.VIEW PRESENT,LATE,EXCUSED AND UNEXCUSED LEAVE,PRESENT PERCENTAGE");
        System.out.println("2.VIEW ONLY PERCENTAGE");
        return Integer.parseInt(input.readLine());
    }
    //table with roll.no,name,all subjects as  columns
    static DefaultTableModel createtablesubject()
    {
        DefaultTableModel percentagetableModel = new DefaultTableModel();
        JTable table = new JTable(percentagetableModel);

        //adding columns for headings
        percentagetableModel.addColumn("Roll No");
        percentagetableModel.addColumn("Name");
        percentagetableModel.addColumn("Linear Algebra");
        percentagetableModel.addColumn("Data Structures");
        percentagetableModel.addColumn("Computer Architecture");
        percentagetableModel.addColumn("Discrete Structures");
        percentagetableModel.addColumn("Object Oriented Programming");
        percentagetableModel.addColumn("Economics for Engineers");
        percentagetableModel.addColumn("Data Structures Lab");
        percentagetableModel.addColumn("Object Oriented Programming Lab");
        percentagetableModel.addColumn("Environmental Science");

        //creating a frame,setting its size,visibility and adding table to the frame
        JFrame f = new JFrame();
        f.setSize(850, 350);
        f.add(new JScrollPane(table));
        f.setVisible(true);

        return percentagetableModel;
    }
    //table with roll.no,name,all subjects and percentage as  columns

    static DefaultTableModel createtablepresentleavepercent(int percent)
    {
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);

        //adding the columns to the table tableModel
        tableModel.addColumn("Roll No");
        tableModel.addColumn("Name");
        tableModel.addColumn("Present");
        tableModel.addColumn("Late");
        tableModel.addColumn("Excused absence");
        tableModel.addColumn("Unexcused absence");
        if(percent == 1)
        {
            tableModel.addColumn("Present percentage");
        }

        //creating a frame,setting its size,visibility and adding the created table to the frame
        JFrame f = new JFrame();
        f.setSize(850, 350);
        f.add(new JScrollPane(table));
        f.setVisible(true);

        return tableModel;
    }

    //main function
    public static void main (String[] args)
    {
        String line = "";
        String splitBy = ",";

        try {
            int choice = getinput1();
            if (choice == 1)   //staff
            {
                BufferedReader br = null;
                int month = getinput2();  //calling input2() and it returns month
                if (month == 1) {
                    //parsing a CSV file into BufferedReader class constructor
                    br = new BufferedReader(new FileReader("C:\\Users\\HP\\Desktop\\Attendance - August.csv"));
                } else if (month == 2) {
                    br = new BufferedReader(new FileReader("C:\\Users\\HP\\Desktop\\Attendance - September.csv"));
                } else if (month == 3) {
                    br = new BufferedReader(new FileReader("C:\\Users\\HP\\Desktop\\Attendance - October.csv"));
                } else if (month == 4) {
                    br = new BufferedReader(new FileReader("C:\\Users\\HP\\Desktop\\Attendance - Sem.csv"));
                }

                int k = 0;
                Staff[] substaff = new Staff[73];  //creating an object substaff of class SubjectStaff

                while ((line = br.readLine()) != null)   //returns a Boolean value
                {
                    String[] user = line.split(splitBy);    // use comma as separator
                    substaff[k] = new Staff(user);
                    k++;
                }
                int filter = getinput6();
                if (filter == 1) {
                    String rollno = getinput4();    //calling input4() and it returns rollno
                    for (k = 0; k < 73; k++) {
                        substaff[k].viewreport(rollno);
                    }
                }

                if (filter == 2) {
                    String subject = getinput3();  //calling getinput3() and it returns subject

                    //creating a table tableModel
                    DefaultTableModel tableModel = createtablepresentleavepercent(1);

                    for (k = 0; k < 73; k++) {
                        substaff[k].viewreportstaff(subject, k, tableModel);
                    }
                }
                else if (filter == 3) //particular student and particular subject
                {
                    String subject = getinput3();   //calling input3() and it returns subject
                    String rollno = getinput4();    //calling input4() and it returns rollno

                    //creating a table tableModel
                    DefaultTableModel tableModel = createtablepresentleavepercent(1);

                    String temp1 = rollno.substring(rollno.length() - 2);//substring of last two characters
                    int index = Integer.parseInt(temp1);
                    if (rollno.charAt(3) == '3') {
                        substaff[index - 1].viewreportstaff(subject, 0, tableModel);
                    } else if (rollno.charAt(6) == '0') {
                        substaff[62 + (index % 9)].viewreportstaff(subject, 0, tableModel);
                    }
                }
                else if (filter == 4) //to display the percentage less than the givenpercentage for the given subject
                {
                    double givenpercent = getinput5();
                    String subject = getinput3();
                    DefaultTableModel tableModel = createtablepresentleavepercent(0);

                    for (k = 0; k < 73; k++) {
                        substaff[k].viewreportstaff(subject, tableModel.getRowCount(), tableModel, givenpercent);
                    }
                }
                else if (filter == 5)
                {
                    String rollno = getinput4();    //calling getinput4() and it returns rollno
                    for (k = 0; k < 73; k++) {
                        substaff[k].viewreport(rollno);
                    }
                }
                else if (filter == 6) {
                    double givenpercent = getinput5();
                    DefaultTableModel tableModel = new DefaultTableModel();
                    JTable table = new JTable(tableModel);

                    //adding columns for headings
                    tableModel.addColumn("Roll No");
                    tableModel.addColumn("Name");
                    tableModel.addColumn("Subject");
                    tableModel.addColumn("Percentage");
                    for (k = 0; k < 73; k++) {
                        substaff[k].viewreportstaff1(tableModel, givenpercent);
                    }
                    JFrame f = new JFrame();
                    f.setSize(850, 350);
                    f.add(new JScrollPane(table));
                    f.setVisible(true);
                }
            } else if (choice == 2) {
                String rollno = getinput4();
                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\HP\\Desktop\\Attendance - Sem.csv"));


                Student Stud = null;
                while ((line = br.readLine()) != null)   //returns a Boolean value
                {
                    String[] user = line.split(splitBy); // use comma as separator

                    if (user[0].equals(rollno)) {
                        Stud = new Student(user);
                        break;
                    }
                }
                int studchoice = getinput7();
                if (studchoice == 1) {
                    DefaultTableModel tableModel = new DefaultTableModel();
                    JTable table = new JTable(tableModel);

                    //adding the columns to the table tableModel
                    tableModel.addColumn("Subject Name");
                    tableModel.addColumn("Present");
                    tableModel.addColumn("Late");
                    tableModel.addColumn("Excused absence");
                    tableModel.addColumn("Unexcused absence");
                    tableModel.addColumn("Present percentage");

                    //creating a frame,setting its size,visibility and adding the created table to the frame
                    JFrame f = new JFrame();
                    f.setSize(850, 350);
                    f.add(new JScrollPane(table));
                    f.setVisible(true);
                    Stud.viewreportstudent(tableModel);
                }
                else
                {
                    DefaultTableModel percentagetableModel = createtablesubject();

                    Stud.viewreport(percentagetableModel);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        catch (Exception e)
        {
            System.out.println(" ");
        }
    }
}