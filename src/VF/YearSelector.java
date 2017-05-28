package VF;

import javax.swing.*;
import java.awt.event.*;

public class YearSelector extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;

    public YearSelector() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(actionEvent -> onOK());

        buttonCancel.addActionListener(actionEvent -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(actionEvent -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onOK() {
        // add your code here
        MainWindow.years = comboBox1.getSelectedItem().toString();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        MainWindow.years = null;
        dispose();
    }

    public static void main(String[] args) {
        YearSelector dialog = new YearSelector();
        /*dialog.pack();
        dialog.setVisible(true);
        System.exit(0);*/
    }
}
