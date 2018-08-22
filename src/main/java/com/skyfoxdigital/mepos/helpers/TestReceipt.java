package com.skyfoxdigital.mepos.helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import com.uniquesecure.meposconnect.MePOS;
import com.uniquesecure.meposconnect.MePOSReceipt;
import com.uniquesecure.meposconnect.MePOSReceiptBarcodeLine;
import com.uniquesecure.meposconnect.MePOSReceiptFeedLine;
import com.uniquesecure.meposconnect.MePOSReceiptImageLine;
import com.uniquesecure.meposconnect.MePOSReceiptSingleCharLine;
import com.uniquesecure.meposconnect.MePOSReceiptTextLine;


public class TestReceipt extends MePOSReceipt {

    public TestReceipt () {
    	
    	this.addLine(new MePOSReceiptTextLine("----Start of test receipt----", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_CENTER));
    	
        this.addLine(new MePOSReceiptTextLine("MePOS Test Receipt", MePOS.TEXT_STYLE_BOLD, MePOS.TEXT_SIZE_WIDE, MePOS.TEXT_POSITION_CENTER));

        this.addLine(new MePOSReceiptFeedLine(2));

        this.addLine(new MePOSReceiptTextLine("This is normal", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_LEFT));
        this.addLine(new MePOSReceiptTextLine("This is underlined", MePOS.TEXT_STYLE_UNDERLINED, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_LEFT));
        this.addLine(new MePOSReceiptTextLine("This is wide", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_WIDE, MePOS.TEXT_POSITION_LEFT));
        this.addLine(new MePOSReceiptTextLine("This is italic", MePOS.TEXT_STYLE_ITALIC, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_LEFT));
        this.addLine(new MePOSReceiptTextLine("This is reversed", MePOS.TEXT_STYLE_INVERSE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_LEFT));
        this.addLine(new MePOSReceiptTextLine("This is bold", MePOS.TEXT_STYLE_BOLD, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_LEFT));
        this.addLine(new MePOSReceiptTextLine("Justify Right", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_RIGHT));
        this.addLine(new MePOSReceiptTextLine("Justify Center", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_CENTER));
        this.addLine(new MePOSReceiptTextLine("Justify Left", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_LEFT));

        this.addLine(new MePOSReceiptSingleCharLine('-'));
        
        this.addLine(new MePOSReceiptTextLine("- Title in bold, size wide and centered\n- Two lines feed\n- Six text lines\n- Text justify right, center, left ", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_LEFT));

        this.addLine(new MePOSReceiptFeedLine(2));

        this.addLine(new MePOSReceiptSingleCharLine('-'));
        
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File("ic_mepos.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.addLine(new MePOSReceiptImageLine(img));
        
        this.addLine(new MePOSReceiptSingleCharLine('-'));

        this.addLine(new MePOSReceiptTextLine("- Three single character lines ", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_LEFT));

        this.addLine(new MePOSReceiptFeedLine(2));

        this.addLine(new MePOSReceiptTextLine("Line Fill", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_CENTER));

        this.addLine(new MePOSReceiptSingleCharLine('t'));
        this.addLine(new MePOSReceiptSingleCharLine('e'));
        this.addLine(new MePOSReceiptSingleCharLine('s'));
        this.addLine(new MePOSReceiptSingleCharLine('t'));
        this.addLine(new MePOSReceiptSingleCharLine('-'));

        this.addLine(new MePOSReceiptTextLine("- Single line character with t\n- Single line character with e\n- Single line character with s\n- Single line character with t\n- Single line character with -\n", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_LEFT));

        this.addLine(new MePOSReceiptFeedLine(2));

        this.addLine(new MePOSReceiptTextLine("UPC", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_CENTER));
        this.addLine(new MePOSReceiptBarcodeLine(MePOS.BARCODE_TYPE_UPCA, "01234567891"));
        this.addLine(new MePOSReceiptFeedLine(2));
        this.addLine(new MePOSReceiptTextLine("Code 39", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_CENTER));
        this.addLine(new MePOSReceiptBarcodeLine(MePOS.BARCODE_TYPE_CODE39, "HELLO USER"));
        this.addLine(new MePOSReceiptFeedLine(2));
        this.addLine(new MePOSReceiptTextLine("PDF 417", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_CENTER));
        this.addLine(new MePOSReceiptBarcodeLine(MePOS.BARCODE_TYPE_PDF417, "END OF THE PRINT RECIPT TEST"));

        this.addLine(new MePOSReceiptFeedLine(1));
        
        this.addLine(new MePOSReceiptTextLine("- UPC barcode line with 01234567891\n- Code 39 barcode line with HELLO USER\n- PDF 417 barcode line with END OF THE PRINT RECIPT TEST", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_LEFT));
        
        this.addLine(new MePOSReceiptTextLine("----End of test receipt----", MePOS.TEXT_STYLE_NONE, MePOS.TEXT_SIZE_NORMAL, MePOS.TEXT_POSITION_CENTER));
    }
}
