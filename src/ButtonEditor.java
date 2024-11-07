import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String pdfPath;
    private JTable table;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
        button = new JButton("Download");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();  // Stop editing the cell when the button is clicked

                int row = table.getSelectedRow();
                if (row != -1) {
                    pdfPath = (String) table.getValueAt(row, table.getColumn("Download PDF").getModelIndex());
                    try {
                        File file = new File(pdfPath);
                        if (file.exists()) {
                            
                            Desktop.getDesktop().open(file);
                        } else {
                           
                            JOptionPane.showMessageDialog(button, "File not found: " + pdfPath);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(button, "Error opening file: " + ex.getMessage());
                    }
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        pdfPath = (value == null) ? "" : value.toString();
        button.setText("Download");
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return pdfPath;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
