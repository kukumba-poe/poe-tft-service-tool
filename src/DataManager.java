import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class DataManager {

    public static void loadSettings(ToolFrame frame) {
        try {
            File settings = new File("poe_sst_settings.txt");
            Scanner scanner = new Scanner(settings);
            frame.league_dropdown.setSelectedItem(scanner.nextLine());
            frame.aisling_check.setSelected(Boolean.parseBoolean(scanner.nextLine()));
            frame.aisling_amt.setText(scanner.nextLine());
            frame.aisling_currency.setSelectedItem(scanner.nextLine());
            frame.vorici_check.setSelected(Boolean.parseBoolean(scanner.nextLine()));
            frame.vorici_radio.setSelected(Boolean.parseBoolean(scanner.nextLine()));
            frame.vorici_amt.setText(scanner.nextLine());
            frame.vorici_currency.setSelectedItem(scanner.nextLine());
            frame.hillock_check.setSelected(Boolean.parseBoolean(scanner.nextLine()));
            frame.hillock_dropdown.setSelectedItem(scanner.nextLine());
            frame.hillock_amt.setText(scanner.nextLine());
            frame.hillock_currency.setSelectedItem(scanner.nextLine());
            frame.jorgin_check.setSelected(Boolean.parseBoolean(scanner.nextLine()));
            frame.jorgin_amt.setText(scanner.nextLine());
            frame.jorgin_currency.setSelectedItem(scanner.nextLine());

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Settings file not found, please save settings to avoid this error next time.", "File not found", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void save_settings(ToolFrame frame)  {
        try {
            FileWriter writer = new FileWriter("poe_sst_settings.txt");
            String settings_string = "";
            settings_string += frame.league_dropdown.getSelectedItem() + "\n";
            settings_string += frame.aisling_check.isSelected() + "\n";
            settings_string += frame.aisling_amt.getText() + "\n";
            settings_string += frame.aisling_currency.getSelectedItem() + "\n";
            settings_string += frame.vorici_check.isSelected() + "\n";
            settings_string += frame.vorici_radio.isSelected() + "\n";
            settings_string += frame.vorici_amt.getText() + "\n";
            settings_string += frame.vorici_currency.getSelectedItem() + "\n";
            settings_string += frame.hillock_check.isSelected() + "\n";
            settings_string += frame.hillock_dropdown.getSelectedItem() + "\n";
            settings_string += frame.hillock_amt.getText() + "\n";
            settings_string += frame.hillock_currency.getSelectedItem() + "\n";
            settings_string += frame.jorgin_check.isSelected() + "\n";
            settings_string += frame.jorgin_amt.getText() + "\n";
            settings_string += frame.jorgin_currency.getSelectedItem() + "\n";
            writer.write(settings_string);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
