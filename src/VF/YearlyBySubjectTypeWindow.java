package VF;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aditya on 023, 23 May, 2017.
 */
public class YearlyBySubjectTypeWindow {
    private JTable table1;
    private JPanel panel;
    private JTable table2;
    private JLabel leftTotal;
    private JLabel rightTotal;

    private double rightSum = 0, leftSum = 0;

    public static void main(String[] args) {
        new MonthlyBySubjectTypeWindow("", "");
    }

    YearlyBySubjectTypeWindow(String years) {
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
        jFrame.setTitle("Summary for the year " + years);
        // jFrame.pack();

        Object[][] data = new Object[26][3];
        Object[][] data2 = new Object[6][3];

        data[0][0] = "	1	".trim();
        data[1][0] = "	2	".trim();
        data[2][0] = "	3	".trim();
        data[3][0] = "	4A	".trim();
        data[4][0] = "	4B	".trim();
        data[5][0] = "	5	".trim();
        data[6][0] = "	6	".trim();
        data[7][0] = "	6A	".trim();
        data[8][0] = "	7	".trim();
        data[9][0] = "	7A	".trim();
        data[10][0] = "	8A	".trim();
        data[11][0] = "	8B	".trim();
        data[12][0] = "	9A	".trim();
        data[13][0] = "	9B	".trim();
        data[14][0] = "	10A	".trim();
        data[15][0] = "	10B	".trim();
        data[16][0] = "	11	".trim();
        data[17][0] = "	12	".trim();
        data[18][0] = "	13	".trim();
        data[19][0] = "	14A	".trim();
        data[20][0] = "	14B	".trim();
        data[21][0] = "	15A	".trim();
        data[22][0] = "	15B	".trim();
        data[23][0] = "	16A	".trim();
        data[24][0] = "	16B	".trim();
        data[25][0] = "	17	".trim();


        data[0][1] = "	Petrol Expenses				".trim();
        data[1][1] = "	Diesel Expenses				".trim();
        data[2][1] = "	Cylinder Expenses				".trim();
        data[3][1] = "	Light Bill / Property tax bills paid by Rajesh 				".trim();
        data[4][1] = "	Llight Bill / Property tax Bills paid by Resort Income				".trim();
        data[5][1] = "	Eggs / Bread Bill				".trim();
        data[6][1] = "	Goods purchased Bills of Ravi Colddrinkwala 				".trim();
        data[7][1] = "	Goods purchased Bills of Ravi Colddrinkwala by cheque				".trim();
        data[8][1] = "	Goods purchased Bills of Sanjay Bhanushali 				".trim();
        data[9][1] = "	Goods purchased Bills of Sanjay Bhanushali by cheque				".trim();
        data[10][1] = "	Misc Expenses paid by Rajesh Mhatre				".trim();
        data[11][1] = "	Misc Expenses paid by Resort Income				".trim();
        data[12][1] = "	Resort Big Expenses paid by Rajesh Mhatre				".trim();
        data[13][1] = "	Resort Big Expenses paid by Resort Income				".trim();
        data[14][1] = "	Agriculture Expenses paid by Rajesh Mhatre				".trim();
        data[15][1] = "	Agriculture Expenses paid by Resort Income				".trim();
        data[16][1] = "	Chicken / mutton / fish Expenses				".trim();
        data[17][1] = "	Vegetable goods purchased bills of Sunil Bhajiwala				".trim();
        data[18][1] = "	Salary to Worker				".trim();
        data[19][1] = "	Plumbing material & Labour charges Bills paid by Rajesh				".trim();
        data[20][1] = "	Plumbing material & Labour charges Bills Resort Income				".trim();
        data[21][1] = "	Civil Material & Labour Charges Bills paid by Rajesh 				".trim();
        data[22][1] = "	Civil Material & Labour Charges Bills by Resort Income				".trim();
        data[23][1] = "	Elelctric Material & Labour Charges Bills paid by Rajesh 				".trim();
        data[24][1] = "	Elelctric Material & Labour Charges Bills  Resort Income				".trim();
        data[25][1] = "	Goods purchased bills of Anjan Churi				".trim();


        data2[0][0] = "	18A				".trim();
        data2[1][0] = "	18B				".trim();
        data2[2][0] = "	18C				".trim();
        data2[3][0] = "	18D				".trim();
        data2[4][0] = "	18E				".trim();
        data2[5][0] = "	18F				".trim();

        data2[0][1] = "	Amount credited to Bank by cheque or online				".trim();
        data2[1][1] = "	Amount credited to Rajesh Mhatre by Cash				".trim();
        data2[2][1] = "	Amount received from swimming				".trim();
        data2[3][1] = "	Amount received from party in cash				".trim();
        data2[4][1] = "	Amount received from Rajesh Mhatre				".trim();
        data2[5][1] = "	Amount received from A.G. Income				".trim();


        try {
            String year1 = years.split("-")[0];
            String year2 = years.split("-")[1];
            for (int i = 0; i < 26; i++) {
                ResultSet rs = MainWindow.getConnection().createStatement().executeQuery("SELECT SUM(amount) FROM entries WHERE datetime >='" + year1 + "0401' AND datetime <= '" + year2 + "0331' AND subject_type = '" + data[i][0] + "'");
                //System.out.println(rs.getString(1));
                try {
                    if (rs.getString(1) != null) {
                        data[i][2] = Integer.parseInt(rs.getString(1));
                        rightSum += (Integer) data[i][2];
                    } else {
                        data[i][2] = "0";
                    }
                } catch (Exception e) {
                    data[i][2] = "0";
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            String year1 = years.split("-")[0];
            String year2 = years.split("-")[1];
            for (int i = 0; i < 6; i++) {
                ResultSet rs = MainWindow.getConnection().createStatement().executeQuery("SELECT SUM(amount) FROM entries WHERE datetime >='" + year1 + "0401' AND datetime <= '" + year2 + "0331' AND subject_type = '" + data2[i][0] + "'");
                //System.out.println(rs.getString(1));
                try {
                    if (rs.getString(1) != null) {
                        data2[i][2] = Integer.parseInt(rs.getString(1));
                        leftSum += (Integer) data2[i][2];
                    } else {
                        data2[i][2] = "0";
                    }
                } catch (Exception e) {
                    data2[i][2] = "0";
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        table1.setModel(new DefaultTableModel(data2, new Object[]{"Subject Type", "Particular", "Amount"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table2.setModel(new DefaultTableModel(data, new Object[]{"Subject Type", "Particular", "Amount"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        leftTotal.setText("" + leftSum);
        rightTotal.setText("" + rightSum);
        resizeColumns(table1);
        resizeColumns(table2);

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

}
