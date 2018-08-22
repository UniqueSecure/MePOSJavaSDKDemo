package com.skyfoxdigital.mepos;

import java.util.regex.Pattern;

import com.skyfoxdigital.mepos.helpers.TestReceipt;
import com.uniquesecure.meposconnect.MePOS;
import com.uniquesecure.meposconnect.MePOSConnectionType;
import com.uniquesecure.meposconnect.MePOSException;
import com.uniquesecure.meposconnect.MePOSPrinterCallback;
import com.uniquesecure.meposconnect.log.MePOSLogger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class MainController {
	
	private final Pattern IP_PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
	
	@FXML private RadioButton radUSB, radWiFi;
	@FXML private TextField txtIP;
	@FXML private Button btnPrint, btnOpen;
	@FXML private Label lblStatus;
	
	@FXML
	private void usbSelected() {
		
		if (!radUSB.isSelected()) {
			radUSB.setSelected(true);
		} else {

			radWiFi.setSelected(false);
			txtIP.setDisable(true);
			txtIP.setOpacity(0.4f);
		}
	}

	@FXML
	private void wifiSelected() {

		if (!radWiFi.isSelected()) {
			radWiFi.setSelected(true);
		} else {

			radUSB.setSelected(false);
			txtIP.setDisable(false);
			txtIP.setOpacity(1.0f);
		}
	}

	@FXML
	private void createInstance() {

		String type = "";
		if (radUSB.isSelected()) {

			Main.mepos = new MePOS(MePOSConnectionType.USB, MePOSLogger.LOG_LEVEL.LEVEL_TRACE);
			type = "USB";
		} else if (radWiFi.isSelected()) {

			if (!validateIP()) return;
			Main.mepos = new MePOS(MePOSConnectionType.WIFI, MePOSLogger.LOG_LEVEL.LEVEL_TRACE);
			Main.mepos.getConnectionManager().setConnectionIPAddress(txtIP.getText());
			type = "WIFI";
		} else {
			
			type = "UNKNOWN";
			assert false : "This should not happen ever";
		}
		
		if (Main.mepos.isMePOSConnected()) {

			lblStatus.setText("MePOS ready");
			btnPrint.setDisable(false);
			btnOpen.setDisable(false);
		} else {
			
			lblStatus.setText(String.format("Unable to create %s instance, check console for details", type));
			btnPrint.setDisable(true);
			btnOpen.setDisable(true);
		}
	}
	
	private boolean validateIP() {
		
		if (!IP_PATTERN.matcher(txtIP.getText()).matches()) {
			
			lblStatus.setText("Inavlid IPv4 format");
			return false;
		}
		
		return true;
	}

	@FXML
	private void printReciept() {

		assert Main.mepos != null : "Should not be able to enter here without a proper instance";
		
		try {
			
			Main.mepos.print(new TestReceipt(), new MePOSPrinterCallback() {
				
				@Override
				public void onPrinterStarted(MePOSConnectionType type, String ipAddress) {
				}
				
				@Override
				public void onPrinterError(MePOSException exception) {
				}
				
				@Override
				public void onPrinterCompleted(MePOSConnectionType type, String ipAddress) {
					Platform.runLater(() -> {
						lblStatus.setText("Printing finished");
					});
					
				}
			});
		} catch (Exception e) {
			lblStatus.setText(String.format("Unable to print test receipt. %s", e.getMessage()));
		}
	}
	
	@FXML
	private void openCashDrawer() {
		
		assert Main.mepos != null : "Should not be able to enter here without a proper instance";
		
		try {
			
			Main.mepos.openCashDrawer(true);
			lblStatus.setText("Cash drawer opened");
		} catch (Exception e) {
			lblStatus.setText(String.format("Unable to print test receipt. %s", e.getMessage()));
		}
	}
}
