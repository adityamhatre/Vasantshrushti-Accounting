package VF;

import sun.applet.Main;

import javax.swing.*;
import java.awt.event.*;

public class MonthYearSelector extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public MonthYearSelector() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onOK() {
        // add your code here
        int month = comboBox1.getSelectedIndex() + 1;
        MainWindow.monthSelect = month <= 9 ? "0" + month : "" + month;
        MainWindow.yearSelect = comboBox2.getSelectedItem().toString();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        MainWindow.monthSelect = null;
        MainWindow.yearSelect = null;
        dispose();
    }

    public static void main(String[] args) {
        MonthYearSelector dialog = new MonthYearSelector();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
