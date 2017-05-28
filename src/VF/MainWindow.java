package VF;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Aditya on 015, 15 May, 2017.
 */
public class MainWindow {
    private JLabel dateLabel;
    private JPanel jPanel;
    private JTextField subject;
    private JButton actionButton;
    private JComboBox subjectType;
    private JTextField amount;
    private JTextField receiptNumber;
    private JButton viewDBButton;
    private JButton viewByMonthButton;
    private JComboBox daySelector;
    private JComboBox monthSelector;
    private JComboBox yearSelector;
    private JButton resetDateButton;
    private JButton viewByYearButton;
    private String day, month, year;

    static String monthSelect = null, yearSelect = null;
    static String years = null;
    private static Connection connection;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JFrame jFrame = new JFrame();
        MainWindow window = new MainWindow();
        jFrame.setTitle("VF");
        jFrame.setContentPane(window.jPanel);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (window.connection != null) {
                    try {
                        window.connection.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        jFrame.setSize(800, 600);
        jFrame.setResizable(false);

        window.setListeners();
        window.loadDate(true);
        window.setConnection();
        window.createEntriesTable();
        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
    }


    private void createEntriesTable() {
        try {
            String createEntriesTableSQL = "CREATE TABLE entries(id INTEGER PRIMARY KEY AUTOINCREMENT, date_entered VARCHAR(255), subject VARCHAR(1024), subject_type VARCHAR(255), amount VARCHAR(255), receipt_number VARCHAR(255), month VARCHAR(255), year VARCHAR(255), datetime VARCHAR(255));";
            connection.createStatement().execute(createEntriesTableSQL);
            System.out.println("Created Entries Table");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            String createEntriesTableSQL = "CREATE TABLE monthly_closing_balance(month varchar(255), year varchar(255), balance varchar(255), yearmonth varchar(255), primary key(month,year));";
            connection.createStatement().execute(createEntriesTableSQL);
            System.out.println("Created monthly closing balance Table");

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void setConnection() {
        connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private void setListeners() {

        actionButton.addActionListener(e -> {
            String date, subject, subjectType, amount, receiptNumber;
            date = day + "-" + month + "-" + year;
            subject = this.subject.getText().trim();
            subjectType = this.subjectType.getSelectedItem().toString().trim().split(":")[0];
            amount = this.amount.getText().trim();
            receiptNumber = this.receiptNumber.getText().trim();

            if (!subject.isEmpty() && !subjectType.isEmpty() && !amount.isEmpty() && !receiptNumber.isEmpty())
                insertInEntriesTable(date, subject, subjectType, amount, receiptNumber);
            else JOptionPane.showMessageDialog(null, "Insert all data");
        });

        viewDBButton.addActionListener(e -> {
            ViewTable viewTable = new ViewTable();
            viewTable.loadData();

        });

        viewByMonthButton.addActionListener(e -> {

            new MonthYearSelector();
            if (monthSelect != null && yearSelect != null) {
                new MonthlyBySubjectTypeWindow(monthSelect, yearSelect);
            }
        });

        viewByYearButton.addActionListener(e -> {
            new YearSelector();
            if (years != null) {
                new YearlyBySubjectTypeWindow(years);
            }
        });

        resetDateButton.addActionListener(e -> {
            loadDate(true);
        });

        daySelector.addActionListener(l -> {
            day = daySelector.getSelectedItem().toString();
            day = Integer.parseInt(day) <= 9 ? "0" + day : day;
            loadDate(false);
        });

        monthSelector.addActionListener(l -> {
            int monthIndex = monthSelector.getSelectedIndex() + 1;
            month = monthIndex <= 9 ? "0" + monthIndex : "" + monthIndex;
            loadDate(false);
        });

        yearSelector.addActionListener(l -> {
            year = yearSelector.getSelectedItem().toString();
            loadDate(false);
        });
    }

    private String changeLabel(int selectedIndex) {
        String labels[] = {"	Petrol Expenses				".trim(),
                "	Diesel Expenses				".trim(),
                "	Cylinder Expenses				".trim(),
                "	Light Bill / Property tax bills paid by Rajesh 				".trim(),
                "	Llight Bill / Property tax Bills paid by Resort Income				".trim(),
                "	Eggs / Bread Bill				".trim(),
                "	Goods purchased Bills of Ravi Colddrinkwala 				".trim(),
                "	Goods purchased Bills of Ravi Colddrinkwala by cheque				".trim(),
                "	Goods purchased Bills of Sanjay Bhanushali 				".trim(),
                "	Goods purchased Bills of Sanjay Bhanushali by cheque				".trim(),
                "	Misc Expenses paid by Rajesh Mhatre				".trim(),
                "	Misc Expenses paid by Resort Income				".trim(),
                "	Resort Big Expenses paid by Rajesh Mhatre				".trim(),
                "	Resort Big Expenses paid by Resort Income				".trim(),
                "	Agriculture Expenses paid by Rajesh Mhatre				".trim(),
                "	Agriculture Expenses paid by Resort Income				".trim(),
                "	Chicken / mutton / fish Expenses				".trim(),
                "	Vegetable goods purchased bills of Sunil Bhajiwala				".trim(),
                "	Salary to Worker				".trim(),
                "	Plumbing material & Labour charges Bills paid by Rajesh				".trim(),
                "	Plumbing material & Labour charges Bills Resort Income				".trim(),
                "	Civil Material & Labour Charges Bills paid by Rajesh 				".trim(),
                "	Civil Material & Labour Charges Bills by Resort Income				".trim(),
                "	Elelctric Material & Labour Charges Bills paid by Rajesh 				".trim(),
                "	Elelctric Material & Labour Charges Bills  Resort Income				".trim(),
                "	Goods purchased bills of Anjan Churi				".trim(),
                "	Amount credited to Bank by cheque or online				".trim(),
                "	Amount credited to Rajesh Mhatre by Cash				".trim(),
                "	Amount received from swimming				".trim(),
                "	Amount received from party in cash				".trim(),
                "	Amount received from Rajesh Mhatre				".trim(),
                "	Amount received from A.G. Income				".trim()};
        return labels[selectedIndex];
    }

    private void insertInEntriesTable(String date, String subject, String subjectType, String amount, String receiptNumber) {
        if (validateDate()) {

            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO entries(date_entered,subject,subject_type,amount,receipt_number,month,year,datetime) VALUES(?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, date);
                preparedStatement.setString(2, subject);
                preparedStatement.setString(3, subjectType);
                preparedStatement.setString(4, amount);
                preparedStatement.setString(5, receiptNumber);
                preparedStatement.setString(6, month);
                preparedStatement.setString(7, year);
                preparedStatement.setString(8, year.concat(month).concat(day));
                preparedStatement.execute();

                try {
                    preparedStatement = connection.prepareStatement("INSERT INTO monthly_closing_balance(month,year,balance,yearmonth) VALUES(?,?,?,?)");
                    preparedStatement.setString(1, month);
                    preparedStatement.setString(2, year);
                    int previousMonth = 0;
                    previousMonth = getMonthlyClosingBalance(month, year);
                    preparedStatement.setString(3, "" + (Integer.parseInt(amount) + previousMonth));
                    preparedStatement.setString(4, year.concat(month));
                    preparedStatement.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    if (this.subjectType.getSelectedIndex() < 26)
                        amount = "" + Integer.parseInt(amount) * -1;
                    //TODO: balance propogate kar to lower entries in monthly_closing_table
                    ResultSet resultSet1 = connection.prepareStatement("SELECT month,year FROM monthly_closing_balance WHERE yearmonth>=" + year.concat(month)).executeQuery();
                    while (resultSet1.next()) {
                        System.out.println("UPDATE monthly_closing_balance SET balance = balance + " + amount + " WHERE month='" + resultSet1.getString(1) + "' AND year='" + resultSet1.getString(2) + "'");
                        preparedStatement = connection.prepareStatement("UPDATE monthly_closing_balance SET balance = balance + " + amount + " WHERE month='" + resultSet1.getString(1) + "' AND year='" + resultSet1.getString(2) + "'");
                        preparedStatement.execute();
                    }
                }


                JOptionPane.showMessageDialog(null, "Added");
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } else JOptionPane.showMessageDialog(null, "Not a valid date");
    }

    private int getMonthlyClosingBalance(String month, String year) {
        int previousMonth, previousYear;
        previousMonth = Integer.parseInt(month);
        previousYear = Integer.parseInt(year);
        String pMonth, pYear;
        pYear = "" + previousYear;
        if (previousMonth == 1) {
            pMonth = "12";
            pYear = "" + (previousYear - 1);
        } else pMonth = "" + (previousMonth - 1);
        if (pMonth.length() == 1) pMonth = "0" + pMonth;
        System.out.println(pMonth + ":" + pYear);
        pMonth = pMonth.trim();
        pYear = pYear.trim();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT balance FROM monthly_closing_balance WHERE yearmonth=" + pYear.concat(pMonth));
            while (resultSet.next()) {
                return Integer.parseInt(resultSet.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    private boolean validateDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(day + "-" + month + "-" + year);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private void loadDate(boolean loadDefault) {
        Calendar calendar = Calendar.getInstance();
        if (loadDefault) {
            day = calendar.get(Calendar.DATE) <= 9 ? ("0" + calendar.get(Calendar.DATE)) : ("" + calendar.get(Calendar.DATE));
            month = calendar.get(Calendar.MONTH) <= 9 ? ("0" + (calendar.get(Calendar.MONTH) + 1)) : ("" + (calendar.get(Calendar.MONTH) + 1));
            year = "" + calendar.get(Calendar.YEAR);
        }
        dateLabel.setText(day + "-" + month + "-" + year);

        daySelector.setSelectedIndex(Integer.parseInt(day) - 1);
        monthSelector.setSelectedIndex(Integer.parseInt(month) - 1);
        yearSelector.setSelectedItem(year);
    }

    public static Connection getConnection() {
        return connection;
    }

}
