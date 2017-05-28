package VF;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aditya on 023, 23 May, 2017.
 */
public class MonthlyBySubjectTypeWindow {
    private JTable leftTable;
    private JPanel panel;
    private JTable rightTable;
    private JLabel leftTotal;
    private JLabel rightTotal;
    private JLabel balance;
    private double rightSum = 0, leftSum = 0;
    private double previousClosingBalance = 0;

    public static void main(String[] args) {
        new MonthlyBySubjectTypeWindow("", "");
    }

    MonthlyBySubjectTypeWindow(String month, String year) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("VF");
        jFrame.setContentPane(panel);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
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
        jFrame.setSize(1200, 600);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setTitle("Summary for " + getMonth(month) + " " + year);
        // jFrame.pack();

        Object[][] dataRightModel = new Object[26][3];
        Object[][] dataLeftModel = new Object[6][3];

        dataRightModel[0][0] = "	1	".trim();
        dataRightModel[1][0] = "	2	".trim();
        dataRightModel[2][0] = "	3	".trim();
        dataRightModel[3][0] = "	4A	".trim();
        dataRightModel[4][0] = "	4B	".trim();
        dataRightModel[5][0] = "	5	".trim();
        dataRightModel[6][0] = "	6	".trim();
        dataRightModel[7][0] = "	6A	".trim();
        dataRightModel[8][0] = "	7	".trim();
        dataRightModel[9][0] = "	7A	".trim();
        dataRightModel[10][0] = "	8A	".trim();
        dataRightModel[11][0] = "	8B	".trim();
        dataRightModel[12][0] = "	9A	".trim();
        dataRightModel[13][0] = "	9B	".trim();
        dataRightModel[14][0] = "	10A	".trim();
        dataRightModel[15][0] = "	10B	".trim();
        dataRightModel[16][0] = "	11	".trim();
        dataRightModel[17][0] = "	12	".trim();
        dataRightModel[18][0] = "	13	".trim();
        dataRightModel[19][0] = "	14A	".trim();
        dataRightModel[20][0] = "	14B	".trim();
        dataRightModel[21][0] = "	15A	".trim();
        dataRightModel[22][0] = "	15B	".trim();
        dataRightModel[23][0] = "	16A	".trim();
        dataRightModel[24][0] = "	16B	".trim();
        dataRightModel[25][0] = "	17	".trim();


        dataRightModel[0][1] = "	Petrol Expenses				".trim();
        dataRightModel[1][1] = "	Diesel Expenses				".trim();
        dataRightModel[2][1] = "	Cylinder Expenses				".trim();
        dataRightModel[3][1] = "	Light Bill / Property tax bills paid by Rajesh 				".trim();
        dataRightModel[4][1] = "	Llight Bill / Property tax Bills paid by Resort Income				".trim();
        dataRightModel[5][1] = "	Eggs / Bread Bill				".trim();
        dataRightModel[6][1] = "	Goods purchased Bills of Ravi Colddrinkwala 				".trim();
        dataRightModel[7][1] = "	Goods purchased Bills of Ravi Colddrinkwala by cheque				".trim();
        dataRightModel[8][1] = "	Goods purchased Bills of Sanjay Bhanushali 				".trim();
        dataRightModel[9][1] = "	Goods purchased Bills of Sanjay Bhanushali by cheque				".trim();
        dataRightModel[10][1] = "	Misc Expenses paid by Rajesh Mhatre				".trim();
        dataRightModel[11][1] = "	Misc Expenses paid by Resort Income				".trim();
        dataRightModel[12][1] = "	Resort Big Expenses paid by Rajesh Mhatre				".trim();
        dataRightModel[13][1] = "	Resort Big Expenses paid by Resort Income				".trim();
        dataRightModel[14][1] = "	Agriculture Expenses paid by Rajesh Mhatre				".trim();
        dataRightModel[15][1] = "	Agriculture Expenses paid by Resort Income				".trim();
        dataRightModel[16][1] = "	Chicken / mutton / fish Expenses				".trim();
        dataRightModel[17][1] = "	Vegetable goods purchased bills of Sunil Bhajiwala				".trim();
        dataRightModel[18][1] = "	Salary to Worker				".trim();
        dataRightModel[19][1] = "	Plumbing material & Labour charges Bills paid by Rajesh				".trim();
        dataRightModel[20][1] = "	Plumbing material & Labour charges Bills Resort Income				".trim();
        dataRightModel[21][1] = "	Civil Material & Labour Charges Bills paid by Rajesh 				".trim();
        dataRightModel[22][1] = "	Civil Material & Labour Charges Bills by Resort Income				".trim();
        dataRightModel[23][1] = "	Elelctric Material & Labour Charges Bills paid by Rajesh 				".trim();
        dataRightModel[24][1] = "	Elelctric Material & Labour Charges Bills  Resort Income				".trim();
        dataRightModel[25][1] = "	Goods purchased bills of Anjan Churi				".trim();


        dataLeftModel[0][0] = "	18A				".trim();
        dataLeftModel[1][0] = "	18B				".trim();
        dataLeftModel[2][0] = "	18C				".trim();
        dataLeftModel[3][0] = "	18D				".trim();
        dataLeftModel[4][0] = "	18E				".trim();
        dataLeftModel[5][0] = "	18F				".trim();

        dataLeftModel[0][1] = "	Amount credited to Bank by cheque or online				".trim();
        dataLeftModel[1][1] = "	Amount credited to Rajesh Mhatre by Cash				".trim();
        dataLeftModel[2][1] = "	Amount received from swimming				".trim();
        dataLeftModel[3][1] = "	Amount received from party in cash				".trim();
        dataLeftModel[4][1] = "	Amount received from Rajesh Mhatre				".trim();
        dataLeftModel[5][1] = "	Amount received from A.G. Income				".trim();

        try {
            for (int i = 0; i < 26; i++) {
                ResultSet rs = MainWindow.getConnection().createStatement().executeQuery("SELECT SUM(amount) FROM entries WHERE month = '" + month + "' AND YEAR = '" + year + "' AND subject_type = '" + dataRightModel[i][0] + "'");
                try {
                    if (rs.getString(1) != null) {
                        dataRightModel[i][2] = Integer.parseInt(rs.getString(1));
                        rightSum += (Integer) dataRightModel[i][2];

                    } else {
                        dataRightModel[i][2] = "0";
                    }
                } catch (Exception e) {
                    dataRightModel[i][2] = "0";
                    System.out.println(e.getMessage());
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        try {
            for (int i = 0; i < 6; i++) {
                ResultSet rs = MainWindow.getConnection().createStatement().executeQuery("SELECT SUM(amount) FROM entries WHERE month = '" + month + "' AND YEAR = '" + year + "' AND subject_type = '" + dataLeftModel[i][0] + "'");
                try {
                    if (rs.getString(1) != null) {
                        dataLeftModel[i][2] = Integer.parseInt(rs.getString(1));
                        leftSum += (Integer) dataLeftModel[i][2];

                    } else {
                        dataLeftModel[i][2] = "0";
                    }
                } catch (Exception e) {
                    dataLeftModel[i][2] = "0";
                    System.out.println(e.getMessage());
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        //------------PREVIOUS
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
        //System.out.println("SELECT balance FROM monthly_closing_balance WHERE month = '" + pMonth + "' AND YEAR = '" + pYear + "'");
        try {
            ResultSet rs = MainWindow.getConnection().createStatement().executeQuery("SELECT balance FROM monthly_closing_balance WHERE month = '" + pMonth + "' AND YEAR = '" + pYear + "'");
            while (rs.next())
                previousClosingBalance = Integer.parseInt(rs.getString(1));
            System.out.println(previousClosingBalance);
        } catch (Exception e) {
            e.printStackTrace();
        }


        leftTable.setModel(new DefaultTableModel(dataLeftModel, new Object[]{"Subject Type", "Particular", "Amount"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        rightTable.setModel(new DefaultTableModel(dataRightModel, new Object[]{"Subject Type", "Particular", "Amount"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        //leftSum = leftSum + leftSumPrevious - rightSumPrevious;


        leftTotal.setText("" + (leftSum + previousClosingBalance));
        rightTotal.setText("" + rightSum);
        resizeColumns(leftTable);
        resizeColumns(rightTable);

    }


    private void resizeColumns(JTable table) {
        int tW = table.getWidth();
        float[] columnWidthPercentage = {20.0f, 55.0f, 25.0f};
        TableColumn column;
        TableColumnModel jTableColumnModel = table.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }

    String getMonth(String month) {
        switch (month) {
            case "01":
                return "January";
            case "02":
                return "February";
            case "03":
                return "March";
            case "04":
                return "April";
            case "05":
                return "May";
            case "06":
                return "June";
            case "07":
                return "July";
            case "08":
                return "August";
            case "09":
                return "September";
            case "10":
                return "October";
            case "11":
                return "November";
            case "12":
                return "December";
            default:
                return month;
        }
    }
}
