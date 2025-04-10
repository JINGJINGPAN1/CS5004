package view;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * StartScreen displays a "Start" message and a button.
 * When the user clicks the button, the game will start.
 */
public class StartScreen extends JPanel {

  public interface StartScreenListener {
    void onStartClicked();
  }

  private StartScreenListener listener;

  public StartScreen(StartScreenListener listener) {
    this.listener = listener;
    initializeUI();
  }

  private void initializeUI() {
    // 设置面板布局
    setLayout(new BorderLayout());

    // 显示标题
    JLabel titleLabel = new JLabel("Welcome to Gold Miner", JLabel.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
    add(titleLabel, BorderLayout.CENTER);

    // 创建启动按钮
    JButton startButton = new JButton("Start");
    startButton.setFont(new Font("Arial", Font.BOLD, 24));
    add(startButton, BorderLayout.SOUTH);

    // 点击按钮触发回调
    startButton.addActionListener(e -> {
      if (listener != null) {
        listener.onStartClicked();
      }
    });
  }
}
