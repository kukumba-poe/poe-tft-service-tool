import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class ToolFrame extends JFrame implements ActionListener {

   JCheckBox aisling_check;
   JComboBox<String> aisling_currency;
   JTextField aisling_amt;
   JCheckBox vorici_check;
   JComboBox<String> vorici_currency;
   JTextField vorici_amt;
   JCheckBox hillock_check;
   JComboBox<String> hillock_currency;
   JTextField hillock_amt;
   JCheckBox jorgin_check;
   JComboBox<String> jorgin_currency;
   JTextField jorgin_amt;
   JComboBox<String> league_dropdown;
   JComboBox<String> hillock_dropdown;
   JRadioButton vorici_radio;

   public ToolFrame() {

      ImageIcon icon = new ImageIcon("catarina.png");
      this.setTitle("PoE Syndicate Service Tool");
      this.setIconImage(icon.getImage());
      this.setResizable(false);
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      // Init all objects
      JPanel settings_panel = new JPanel(new SpringLayout());

      String[] currencies = {"divine", "chaos"};
      String[] league_options = {"Softcore", "Hardcore"};
      league_dropdown = new JComboBox<>(league_options);

      aisling_check = new JCheckBox("T4 Aisling");
      aisling_currency = new JComboBox<>(currencies);
      aisling_amt = new JTextField();
      vorici_check = new JCheckBox("T4 Vorici");
      vorici_currency = new JComboBox<>(currencies);
      vorici_amt = new JTextField();
      hillock_check = new JCheckBox("T4 Hillock");
      hillock_currency = new JComboBox<>(currencies);
      hillock_amt = new JTextField();
      jorgin_check = new JCheckBox("T4 Jorgin");
      jorgin_currency = new JComboBox<>(currencies);
      jorgin_amt = new JTextField();

      String[] hillock_options = {"30% Armour qual", "28% Flask qual", "30% Weapon qual"};
      hillock_dropdown = new JComboBox<>(hillock_options);

      vorici_radio = new JRadioButton("Priced per white socket");

      JButton copy_button = new JButton("Copy Message");
      copy_button.setEnabled(true);
      copy_button.setMnemonic(KeyEvent.VK_D);
      copy_button.setActionCommand("copy");
      copy_button.addActionListener(this);

       JButton save_button = new JButton("Save Settings");
       save_button.setEnabled(true);
       save_button.setMnemonic(KeyEvent.VK_D);
       save_button.setActionCommand("save");
       save_button.addActionListener(this);

      settings_panel.add(league_dropdown);
      settings_panel.add(new JLabel(""));
      settings_panel.add(copy_button);
      settings_panel.add(save_button);

      settings_panel.add(aisling_check);
      settings_panel.add(aisling_amt);
      settings_panel.add(aisling_currency);
      settings_panel.add(new JLabel(""));

      settings_panel.add(vorici_check);
      settings_panel.add(vorici_amt);
      settings_panel.add(vorici_currency);
      settings_panel.add(vorici_radio);

      settings_panel.add(hillock_check);
      settings_panel.add(hillock_amt);
      settings_panel.add(hillock_currency);
      settings_panel.add(hillock_dropdown);

      settings_panel.add(jorgin_check);
      settings_panel.add(jorgin_amt);
      settings_panel.add(jorgin_currency);
      settings_panel.add(new JLabel(""));



      layout.SpringUtilities.makeGrid(settings_panel,
              5, 4, //rows, cols
              5, 5, //initialX, initialY
              5, 5);//xPad, yPad

      //Add panel to frame
      this.add(settings_panel);
      this.pack();

      DataManager.loadSettings(this);
   }

    public String get_currency(JComboBox<String> selecter) {
         if(Objects.equals(selecter.getSelectedItem(), "chaos"))
            return "chaos";
         else return "divine";
    }

    public String build_message() {
         String msg = "";
         msg += "WTS " + league_dropdown.getSelectedItem() + "\n\n";
         if(aisling_check.isSelected()) {
            msg += "T4 Aisling - " + aisling_amt.getText() + " :" + get_currency(aisling_currency) + ":\n";
         }
         if(vorici_check.isSelected()) {
            String sockets = (vorici_radio.isSelected()) ? " per white socket" : "";
            msg += "T4 Vorici - " + vorici_amt.getText() + " :" + get_currency(vorici_currency) + ":" + sockets +"\n";
         }
         if(hillock_check.isSelected()) {
            msg += "T4 Hillock - " + hillock_dropdown.getSelectedItem() + " " + hillock_amt.getText() + " :" + get_currency(hillock_currency) + ":\n";
         }
         if(jorgin_check.isSelected()) {
            msg += "T4 Jorgin (T3 Talisman) x2 - " + jorgin_amt.getText() + " :" + get_currency(jorgin_currency) + ": ea\n";
         }
         msg += "\nI don't give collateral \nPortals ready";
         return msg;
    }

    public void actionPerformed(ActionEvent e) {
        if ("copy".equals(e.getActionCommand())) {
            StringSelection selection = new StringSelection(build_message());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        } else if("save".equals(e.getActionCommand())) {
            DataManager.save_settings(this);
        }
    }
}