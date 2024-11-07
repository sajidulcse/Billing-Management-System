import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.JTableHeader;

public class CheckBoxHeaderRenderer extends JCheckBox implements TableCellRenderer {
    public CheckBoxHeaderRenderer(JTable table) {
        setHorizontalAlignment(SwingUtilities.CENTER);
        JTableHeader header = table.getTableHeader();
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                if (col == table.convertColumnIndexToView(4)) { // Assuming the checkbox column is at index 4
                    boolean isChecked = isSelected();
                    setSelected(!isChecked);
                    for (int row = 0; row < table.getRowCount(); row++) {
                        table.setValueAt(!isChecked, row, col);
                    }
                    header.repaint();
                }
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
