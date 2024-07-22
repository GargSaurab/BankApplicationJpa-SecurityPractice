package com.app.Service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Random;

import jakarta.servlet.http.HttpSession;

public class CaptchaServiceImpl implements CaptchaService{

    private static final String[] fonts = {
        "Courier New", "Comic Sans MS", "Lucida Console", 
        "Brush Script MT", "Algerian", "Chiller", 
        "Papyrus", "Impact", "Kristen ITC", "Stencil"
    };

    private static final String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Override
    public BufferedImage getCaptcha(HttpSession session) {

        // gets a random alphanumeric captcha
        String captchaVal = randomizeCaptcha();

       // gets a random font each time
       String font = fonts[new SecureRandom().nextInt(fonts.length)];

       //Saves the captcha in the client's session
       session.setAttribute("captcha", captchaVal);     
        
        int width = 200;
        int height = 100;

        BufferedImage captcha = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = captcha.getGraphics();
        g.setColor(Color.WHITE); // Applied on All subsequent drawing operations until another
        //  setColor is applied.
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Arial", Font.BOLD, 40)); // All subsequent drawing operations, like drawing text or shapes, will use black color.
        g.setColor(Color.black);
        g.drawString(captchaVal, max, height);
        g.dispose();

        return captcha ;
       
    }

    public String randomizeCaptcha()
    {
        StringBuilder str = new StringBuilder(6);

        SecureRandom random = new SecureRandom();
        
        int max = alphaNumeric.length() - 1;

        for(int i = 0; i < 6; i++)
        {
            int index = random.nextInt(max);

            str.append(alphaNumeric.charAt(index));
        }

        return str.toString();
    }

    @Override
    public  void validateCaptcha(HttpSession session, String captcha)
    {
         
    }

}


// java.awt.image.bufferedimage: To hold the image, we create the BufferedImage object;
//  we use BufferedImage class. This object is used to store an image in RAM.


// Graphics is an abstract class provided by Java AWT which is used to draw or paint on the 
// components. It consists of various fields which hold information like components to be 
//painted, font, color, XOR mode, etc., and methods that allow drawing various shapes on the GUI 
// components.